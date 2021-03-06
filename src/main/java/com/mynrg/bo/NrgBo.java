package com.mynrg.bo;

//import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mynrg.dto.ProductionIssueDataBean;
import com.mynrg.model.Bank;
import com.mynrg.model.Connection;
import com.mynrg.model.IssueComments;
//import com.mynrg.dto.MyPortalDataBean;
import com.mynrg.model.Portal;
import com.mynrg.model.ProductionIssue;
import com.mynrg.model.ServerInfo;
import com.mynrg.model.User;



public interface NrgBo {
	
	
	public static final Logger logger = LoggerFactory.getLogger(NrgBo.class);
	// Job portal
	public String myPortalReg(Portal portal);
	public String myPortalupdate(Portal portal);	
	public List<Portal> myportaltable(List<Portal> myportalDataBean);
	public Portal myPortalview(int id);
	public String myPortaldelete(int id);
	
	// Production Issues 
	public boolean save(ProductionIssue issue);
	public boolean saveComments(IssueComments comments);	
	public String update(ProductionIssue issue);	
	public List<ProductionIssueDataBean> load(List<ProductionIssueDataBean> issue,String status);
	public List<IssueComments> loadComments(int id);	
	public ProductionIssue get(int id);
	public String remove(int id);
	public Map<String,Integer> reportLoad();
	
	// Connection Issues 
	public String saveConnection(Connection connection);
	public String updateConnection(Connection connection);	
	public List<Connection> loadConnection(List<Connection> connection);
	public Connection getConnection(int id);
	public String removeConnection(int id);

	// Server Info 
	public String saveServerInfo(ServerInfo serverinfo);
	public String updateServerInfo(ServerInfo serverinfo);	
	public List<ServerInfo> loadServerInfo(List<ServerInfo> serverinfo);
	public ServerInfo getServerInfo(int id);
	public String removeServerInfo(int id);
	
	// Bank 
	public String saveBank(Bank serverinfo);
	public String updateBank(Bank serverinfo);	
	public List<Bank> loadBank(List<Bank> serverinfo);
	public Bank getBank(int id);
	public String removeBank(int id);
	

	
}
