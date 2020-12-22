package com.erp.mongo.dal;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.erp.bo.ErpBo;
import com.erp.dto.Career;
import com.erp.dto.Enquiry;
import com.erp.dto.User;
import com.erp.model.UserLogin;
import com.erp.mongo.model.Index;
import com.erp.mongo.model.Login;
import com.erp.mongo.model.POInvoiceDetails;
import com.erp.mongo.model.POReturnDetails;
import com.erp.mongo.model.StockDamage;
import com.erp.mongo.model.UserRole;
import com.erp.mongo.model.Vendor;

@Repository
public class LoginImpl implements LoginDAL {

	public static final Logger logger = LoggerFactory.getLogger(LoginImpl.class);

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<UserRole> userLogin(User user, List<UserRole> result) {
		Query query = new Query();
		logger.info("LoginImpl UserLogin");
		try {
			if(user.getId()==1) {
				logger.info("User Name Validation");
				query.addCriteria( new Criteria().andOperator(
						Criteria.where("username").is(user.getUsername()),
						Criteria.where("status").is("Active") ) );
				result = mongoTemplate.find(query, UserRole.class);
			}
			if(user.getId()==2){
				logger.info("User Name and Password Validation");
				query.addCriteria( new Criteria().andOperator(
						Criteria.where("username").is(user.getUsername()),Criteria.where("status").is("Active"),
						Criteria.where("password").is(user.getPassword()) ) );
				
				result = mongoTemplate.find(query, UserRole.class);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			
		}
		return result;
	}

	@Override
	public User Checkuser(User user){
		Query query = new Query();
		List<UserRole> list = new ArrayList<UserRole>();
		try {
			query.addCriteria( new Criteria().andOperator(
					Criteria.where("username").is(user.getUsername()),
					Criteria.where("status").is("Active") ) );
			list = mongoTemplate.find(query, UserRole.class);
			if(list.size() > 0) {
				user.setStatus("success");
			}else {
				user.setStatus("failure");
			}
		}catch(Exception e) {
			logger.error("Exception -->"+e.getMessage());
			user.setStatus("failure");
		}finally {
			
		}
		return user;
	}

	@Override
	public User resetPassword(User user) {
		Query query = new Query();
		Update update = new Update();
		Login login = new Login();
		try {
			query.addCriteria( new Criteria().andOperator(
					Criteria.where("username").is(user.getUsername()),
					Criteria.where("status").is("Active") ) );
			login = mongoTemplate.findOne(query, Login.class);
			
			query.addCriteria(Criteria.where("id").is(login.getId()));
			update.set("invnumber", login.getInvnumber());
			update.set("username", user.getUsername());
			update.set("password", user.getPassword());
			update.set("status", "Active");
			update.set("userOtp", "");
			mongoTemplate.updateFirst(query, update, Login.class);
			
			user.setStatus("success");
		}catch(Exception e) {
			logger.error("Exception -->"+e.getMessage());
			user.setStatus("failure");
		}finally {
			
		}
		return user;
	}
	

	public Enquiry saveEnquiry(Enquiry enquiry) {
		mongoTemplate.save(enquiry);
		enquiry.setStatus("Success"); 
		return enquiry;
	}
	
	@Override
	public List<Enquiry> loadEnquiry(List<Enquiry> enquirylist) {
		enquirylist = mongoTemplate.findAll(Enquiry.class);	
		logger.debug("Enquiry list Size-->"+enquirylist.size());
		return enquirylist;
	}

	@Override
	public List<Index> loadIndex(List<Index> indexlist) {
		indexlist = mongoTemplate.findAll(Index.class);	
		logger.debug("Enquiry list Size-->"+indexlist.size());
		return indexlist;
	}

	
	public Career saveCareer(Career career) {
		mongoTemplate.save(career);
		career.setStatus("Success"); 
		return career;
	}
	
	public List<UserRole> getUser(List<UserRole> userlist,String invoice) {
		Query query = new Query();
		query.addCriteria(Criteria.where("invnumber").is(invoice));
		query.addCriteria(Criteria.where("status").is("Active"));
		userlist = mongoTemplate.find(query,UserRole.class);
		return userlist;
	}

}
