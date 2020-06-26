package com.mynrg.dao;

import java.sql.Blob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

import com.mynrg.dto.MyPortalDataBean;
import com.mynrg.dto.ProductionIssueDataBean;
import com.mynrg.model.Bank;
import com.mynrg.model.Connection;
import com.mynrg.model.IssueComments;
import com.mynrg.model.Portal;
import com.mynrg.model.ProductionIssue;
import com.mynrg.model.ServerInfo;
import com.mynrg.util.Custom;

//import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.sql.rowset.serial.SerialBlob;



@Repository
@Singleton
public class NrgDaoImpl implements NrgDao {

	public static final Logger logger = LoggerFactory.getLogger(NrgDaoImpl.class);

	@PersistenceContext(unitName="mynrg-pu")
	private EntityManager entitymanager;
	
	/*
	 * @Value("${memeber.silver}") private String silver;
	 * 
	 * @Value("${memeber.gold}") private String gold;
	 * 
	 * @Value("${memeber.platinum}") private String platinum;
	 */
	 

	 
	    
	 	/* Portal */
	 	
	 	// save
	 	@Transactional(value = "transactionManager")
		@Override
		public String myPortalReg(Portal portal) {
			String status = "Fail";
			try {
					Date createddate = new Date();
					portal.setStatus("Active");
					portal.setCreatedDate(createddate);
					portal.setUpdatedDate(createddate);
					entitymanager.persist(portal);
					entitymanager.flush();
					entitymanager.clear();
					status = "success";
				//}
			} catch (Exception e) {

			}
			return status;
		}
	    
	    // load
	    @Transactional(value = "transactionManager")
	    @Override
		public List<Portal> myportaltable(
				List<Portal> myportaltable) {
			//List<MyPortalDataBean> list = new ArrayList<MyPortalDataBean>();
			try {
				Query q = null;
				q = entitymanager.createQuery("from Portal");
				myportaltable = (List<Portal>) q.getResultList();
					} catch (Exception e) {
				e.printStackTrace();
			}
			return myportaltable;
		}
	    
		// get
	    @Transactional(value = "transactionManager")
	    @Override
	    public Portal myPortalview(int id) {
	    	Portal portal=null;
			try {
				portal = entitymanager.find(Portal.class, id);
				return portal;
			} catch (Exception e) {				
				e.printStackTrace();
				return portal;
			}
	
	    }
	    
	    // update
	    @Transactional(value = "transactionManager")
	    @Override
		public String myPortalupdate(Portal portal) {
			try {
				Date updateddate = new Date();
				portal.setUpdatedDate(updateddate);
				entitymanager.merge(portal);
				return "success";
			} catch (Exception e) {				
				e.printStackTrace();
				return "fail";
			}
	
	    
		}
		
	    
	    // remove	    
	    @Transactional(value = "transactionManager")
	    @Override
	    public String myPortaldelete(int id) {
	    	Portal portal=null;
	    	String status=null;
			try {
				portal = entitymanager.find(Portal.class, id);
				entitymanager.remove(portal);
				status="success";
				return status;
			} catch (Exception e) {		
				status="fail";
				e.printStackTrace();
				return status;
			}
			//return portal;
		
	    }
	    
	    /* Production Issues */
	    
	    
	    @Override
		@Transactional(value = "transactionManager")
		public Map<String,Integer> reportLoad() {
			Map<String,Integer> map=new HashMap<String,Integer>();
			Query q = null;
			// by status
			q = entitymanager.createQuery("select count(issue_status) from ProductionIssue where issue_status like '%Open'");
		    Number cResults1=(Number) q.getSingleResult();
		    q = entitymanager.createQuery("select count(issue_status) from ProductionIssue where issue_status like '%In Progress'");
		    Number cResults2=(Number) q.getSingleResult();
		    q = entitymanager.createQuery("select count(issue_status) from ProductionIssue where issue_status like '%Ready For Development'");
		    Number cResults3=(Number) q.getSingleResult();
		    q = entitymanager.createQuery("select count(issue_status) from ProductionIssue where issue_status like '%Development Completed'");
		    Number cResults4=(Number) q.getSingleResult();
			 		    
		 		 
		    // by priority
		    q = entitymanager.createQuery("select count(priority) from ProductionIssue where priority like '%Critical'");
		    Number cResults5=(Number) q.getSingleResult();
			q = entitymanager.createQuery("select count(priority) from ProductionIssue where priority like '%Highest'");
		    Number cResults6=(Number) q.getSingleResult();
			q = entitymanager.createQuery("select count(priority) from ProductionIssue where priority like '%High'");
		    Number cResults7=(Number) q.getSingleResult();
			 		    
		 		   
		    logger.info("Open Count-->"+cResults1);
		    logger.info("In Progress Count-->"+cResults2);
		    logger.info("Ready For Development Count-->"+cResults3);
		    logger.info("Development Completed Count-->"+cResults3);
		    // by Status
		    map.put("key1", cResults1.intValue());
		    map.put("key2", cResults2.intValue());
		    map.put("key3", cResults3.intValue());
		    map.put("key4", cResults4.intValue());
		    // by Priority
		    map.put("key5", cResults5.intValue());
		    map.put("key6", cResults6.intValue());
		    map.put("key7", cResults7.intValue());
		    
			return map;
		}
			
		@Override
		@Transactional(value = "transactionManager")
		public boolean save(ProductionIssue issue) {
			logger.info("Issue status-->"+issue.getIssueStatus());
			try {
				Date createddate = new Date();
				System.out.println("Before Set the blob");
				issue.setCardImageBase64(issue.getCardImageBase64());
				System.out.println("After Set the blob");
				issue.setStatus("Active");
				issue.setCreatedDate(createddate);
				issue.setUpdatedDate(createddate);
				entitymanager.persist(issue);
				entitymanager.flush();
				entitymanager.clear();
			} catch (Exception e) {
				return false;
			}
			return true;
		
		}
		
		@Override
		@Transactional(value = "transactionManager")
		public boolean saveComments(IssueComments comments) {
			logger.info("Issue Id-->"+comments.getByissueId());
			try {
				//IssueComments data = entitymanager.find(IssueComments.class, comments.getComments().getIssueId());
				//Date createddate = new Date();
				//comments.setDate(createddate);
				//data.setComments(comments);
				//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				//Date date = new Date();
				//logger.debug("Date-->"+dateFormat.format(date));
				//comments.setDate(dateFormat.format(date));
				//comments.setDate("yyyy/MM/dd HH:mm:ss");
				entitymanager.persist(comments);
				entitymanager.flush();
				entitymanager.clear();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		
		}
		
		@Override
		@Transactional(value = "transactionManager")
		public String update(ProductionIssue issue) {
			logger.info("issue id-->"+issue.getIssueId());
			try {
			/*
			 * ProductionIssue res = new ProductionIssue(); res =
			 * entitymanager.find(ProductionIssue.class, issue.getIssueId());
			 * res.setIssueStatus(issue.getIssueStatus());
			 * res.setClientName(issue.getClientName());
			 * res.setPriority(issue.getPriority()); res.setCountry(issue.getCountry());
			 * res.setIssueNotes(issue.getIssueNotes());
			 */
				entitymanager.merge(issue);
				return "success";
			} catch (Exception e) {				
				e.printStackTrace();
				return "fail";
			}
		}
		
	
		ProductionIssueRepo repo;
		
		@Override
		@Transactional(value = "transactionManager")
		public List<ProductionIssueDataBean> load(List<ProductionIssueDataBean> issuelist,String status){
			logger.info("Inside loadIssue");
			try {
				Query q = null;
				if(status.equalsIgnoreCase("All")) {
				  TypedQuery<Object[]> query = entitymanager.createQuery(
				  "SELECT issueId, issueNotes,clientName,issueStatus,priority,createdDate FROM ProductionIssue order by createdDate desc",
				  Object[].class); 
				  List<Object[]> results = query.getResultList();
				  for (Object[] result : results) {
					  ProductionIssueDataBean productionissuedatabean = new ProductionIssueDataBean();
					  productionissuedatabean.setIssueId((Integer) result[0]);
					  productionissuedatabean.setIssueNotes((String) result[1]);
					  productionissuedatabean.setClientName((String) result[2]);
					  productionissuedatabean.setIssueStatus((String) result[3]);
					  productionissuedatabean.setPriority((String) result[4]);
					  productionissuedatabean.setCreatedDate((Date) result[5]);				  
					  issuelist.add(productionissuedatabean);
				      logger.debug(
				      "ID: " + result[0] + ", Notes: " + result[1]);
				  }
				/*
				 * issue = (List<ProductionIssue>) entitymanager.createQuery(
				 * "SELECT issueId, issueNotes,clientName,status,priority FROM ProductionIssue"
				 * ,ProductionIssue.class).getResultList(); logger.info("Size-->"+issue.size());
				 */
				// issue = query.getResultList();
				 logger.info("Size-->"+results.size());
						  
					//q.setP
					//issue = (List<ProductionIssue>) q.getResultList();
				}else {
					q = entitymanager.createQuery("from ProductionIssue where  issueStatus = '" + status + "'");
					//q.setP
				/*
				 * issue = (List<ProductionIssue>) q.getResultList(); issue =
				 * (List<ProductionIssue>) q.getResultList();
				 */
				}

				
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return issuelist;
		
		}
		
		public List<IssueComments> loadComments(int id) {
			Query q = null;
			List<IssueComments> list = null;
			try {
				q = entitymanager.createQuery("from IssueComments where  byissueId = '" + id + "'" + "order by date");
				list = (List<IssueComments>) q.getResultList();
				logger.info("Comments List Size-->"+list.size());
				return list;
			} catch (Exception e) {				
				e.printStackTrace();
				return list;
			}
	
	    
		
		}

		
		@Override
		@Transactional(value = "transactionManager")
		public ProductionIssue get(int id) {

			ProductionIssue issue=null;
			try {
				issue = entitymanager.find(ProductionIssue.class, id);
				return issue;
			} catch (Exception e) {				
				e.printStackTrace();
				return issue;
			}
	
	    
		}
		
		@Override
		@Transactional(value = "transactionManager")
		public String remove(int id) {
			ProductionIssue issue=null;
	    	String status=null;
			try {
				issue = entitymanager.find(ProductionIssue.class, id);
				entitymanager.remove(issue);
				status="success";
				return status;
			} catch (Exception e) {		
				status="fail";
				e.printStackTrace();
				return status;
			}

		}
		
	// Connection		
	
	@Override
	@Transactional(value = "transactionManager")
	public String saveConnection(Connection connection) {

		String status = "Fail";
		try {
			Date createddate = new Date();
			connection.setStatus("Active");
			connection.setCreatedDate(createddate);
			connection.setUpdatedDate(createddate);
			entitymanager.persist(connection);
			entitymanager.flush();
			entitymanager.clear();
			status = "success";
			//}
		} catch (Exception e) {

		}
		return status;
	
	
	}
	@Override
	@Transactional(value = "transactionManager")
	public String updateConnection(Connection connection) {
		try {
			entitymanager.merge(connection);
			return "success";
		} catch (Exception e) {				
			e.printStackTrace();
			return "fail";
		}
	
	}
	@Override
	@Transactional(value = "transactionManager")
	public List<Connection> loadConnection(List<Connection> connection){
		try {
			Query q = null;
			q = entitymanager.createQuery("from Connection order by updatedDate desc");
			connection = (List<Connection>) q.getResultList();
			return connection;
			} catch (Exception e) {
			e.printStackTrace();
			return connection;
		}
	}
	@Override
	@Transactional(value = "transactionManager")
	public Connection getConnection(int id) {
		Connection connection=null;
		try {
			connection = entitymanager.find(Connection.class, id);
			return connection;
		} catch (Exception e) {				
			e.printStackTrace();
			return connection;
		}

    
	
	}
	@Override
	@Transactional(value = "transactionManager")
	public String removeConnection(int id) {

		Connection connection;
    	String status=null;
		try {
			connection = entitymanager.find(Connection.class, id);
			entitymanager.remove(connection);
			status="success";
			return status;
		} catch (Exception e) {		
			status="fail";
			e.printStackTrace();
			return status;
		}

	
	}

	
	
	   // ServerInfo		
	
		@Override
		@Transactional(value = "transactionManager")
		public String saveServerInfo(ServerInfo serverinfo) {

			String status = "Fail";
			try {
				Date createddate = new Date();
				serverinfo.setStatus("Active");
				serverinfo.setCreatedDate(createddate);
				serverinfo.setUpdated_date(createddate);
				entitymanager.persist(serverinfo);
				entitymanager.flush();
				entitymanager.clear();
				status = "success";
				//}
			} catch (Exception e) {

			}
			return status;
		
		
		}
		@Override
		@Transactional(value = "transactionManager")
		public String updateServerInfo(ServerInfo serverinfo) {
			try {
				entitymanager.merge(serverinfo);
				return "success";
			} catch (Exception e) {				
				e.printStackTrace();
				return "fail";
			}
		
		}
		@Override
		@Transactional(value = "transactionManager")
		public List<ServerInfo> loadServerInfo(List<ServerInfo> serverinfo){
			try {
				Query q = null;
				q = entitymanager.createQuery("from ServerInfo order by updatedDate desc");
				serverinfo = (List<ServerInfo>) q.getResultList();
				return serverinfo;
				} catch (Exception e) {
				e.printStackTrace();
				return serverinfo;
			}
		}
		@Override
		@Transactional(value = "transactionManager")
		public ServerInfo getServerInfo(int id) {
			ServerInfo serverinfo=null;
			try {
				serverinfo = entitymanager.find(ServerInfo.class, id);
				return serverinfo;
			} catch (Exception e) {				
				e.printStackTrace();
				return serverinfo;
			}

	    
		
		}
		@Override
		@Transactional(value = "transactionManager")
		public String removeServerInfo(int id) {

			ServerInfo serverinfo=null;
	    	String status=null;
			try {
				serverinfo = entitymanager.find(ServerInfo.class, id);
				entitymanager.remove(serverinfo);
				status="success";
				return status;
			} catch (Exception e) {		
				status="fail";
				e.printStackTrace();
				return status;
			}

		
		}
		
		
		
		    // Bank		
			@Override
			@Transactional(value = "transactionManager")
			public String saveBank(Bank bank) {

				String status = "Fail";
				try {
					Date createddate = new Date();
					bank.setStatus("Active");
					bank.setCreatedDate(createddate);
					bank.setUpdatesDate(createddate);
					entitymanager.persist(bank);
					entitymanager.flush();
					entitymanager.clear();
					status = "success";
					//}
				} catch (Exception e) {

				}
				return status;
			
			
			}
			@Override
			@Transactional(value = "transactionManager")
			public String updateBank(Bank bank) {
				try {
					entitymanager.merge(bank);
					return "success";
				} catch (Exception e) {				
					e.printStackTrace();
					return "fail";
				}
			
			}
			@Override
			@Transactional(value = "transactionManager")
			public List<Bank> loadBank(List<Bank> bank){
				try {
					Query q = null;
					q = entitymanager.createQuery("from Bank order by updatesDate desc");
					bank = (List<Bank>) q.getResultList();
					return bank;
					} catch (Exception e) {
						System.out.println("exception-->"+e.getMessage());
					//e.printStackTrace();
					return bank;
				}
			}
			@Override
			@Transactional(value = "transactionManager")
			public Bank getBank(int id) {
				Bank bank=null;
				try {
					bank = entitymanager.find(Bank.class, id);
					return bank;
				} catch (Exception e) {				
					e.printStackTrace();
					return bank;
				}

		    
			
			}
			@Override
			@Transactional(value = "transactionManager")
			public String removeBank(int id) {

				Bank bank=null;
		    	String status=null;
				try {
					bank = entitymanager.find(Bank.class, id);
					entitymanager.remove(bank);
					status="success";
					return status;
				} catch (Exception e) {		
					status="fail";
					e.printStackTrace();
					return status;
				}

			
			}
			
			
	    
	    
}
