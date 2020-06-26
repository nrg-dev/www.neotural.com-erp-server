package com.mynrg.bo;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mynrg.dao.NrgDao;
import com.mynrg.dao.UserMgntDao;
import com.mynrg.dto.ProductionIssueDataBean;
import com.mynrg.model.Bank;
import com.mynrg.model.Connection;
import com.mynrg.model.IssueComments;
import com.mynrg.model.Portal;
import com.mynrg.model.ProductionIssue;
import com.mynrg.model.ServerInfo;
import com.mynrg.model.User;


@Service("bo")
public class NrgBoImpl implements NrgBo{
	
	public static final Logger logger = LoggerFactory.getLogger(NrgBoImpl.class);

	
	/*
	 * @Value("${memeber.silver}") private String silver;
	 * 
	 * @Value("${memeber.gold}") private String gold;
	 * 
	 * @Value("${memeber.platinum}") private String platinum;
	 */
	@Autowired
	NrgDao dao;
	
	
	
	
	@Override
	public String myPortalReg(Portal portal) {
		return dao.myPortalReg(portal);
	}

	@Override
	public String myPortalupdate(Portal portal) {
		return dao.myPortalupdate(portal);
	}
	
	@Override
	public List<Portal> myportaltable(List<Portal> myportaltable) {
		return dao.myportaltable(myportaltable);
	}
	@Override
	public Portal myPortalview(int id) {
		return dao.myPortalview(id);
	}
	@Override
	public String myPortaldelete(int id) {
		return dao.myPortaldelete(id);
	}

	
	
	// Production Issues 
	@Override
	public boolean save(ProductionIssue issue) {
		return dao.save(issue);
	}
	@Override
	public boolean saveComments(IssueComments comments) {	
		return dao.saveComments(comments);
	}
	
	@Override
	public String update(ProductionIssue issue) {
		return dao.update(issue);
	}
	@Override
	public List<ProductionIssueDataBean> load(List<ProductionIssueDataBean> issue,String status){
		return dao.load(issue,status);
	}
	@Override
	public List<IssueComments> loadComments(int id){
		return dao.loadComments(id);
	}
	
	
	@Override
	public ProductionIssue get(int id) {
		return dao.get(id);
	}
	@Override
	public String remove(int id) {
		return dao.remove(id);
	}
		
	@Override
	public Map<String,Integer> reportLoad() {
		return dao.reportLoad();
	}
		
	
	// Connection Issues 
	@Override
	public String saveConnection(Connection connection) {
		return dao.saveConnection(connection);
	}
	@Override
	public String updateConnection(Connection connection) {
		return dao.updateConnection(connection);
	}
	@Override
	public List<Connection> loadConnection(List<Connection> connection){
		return dao.loadConnection(connection);
	}
	@Override
	public Connection getConnection(int id) {
		return dao.getConnection(id);
	}
	@Override
	public String removeConnection(int id) {
		return dao.removeConnection(id);
	}

		
	// Connection  
	@Override
	public String saveServerInfo(ServerInfo serverinfo) {
		return dao.saveServerInfo(serverinfo);
	}
	@Override
	public String updateServerInfo(ServerInfo serverinfo) {
		return dao.updateServerInfo(serverinfo);
	}
	@Override
	public List<ServerInfo> loadServerInfo(List<ServerInfo> serverinfo){
		return dao.loadServerInfo(serverinfo);
	}
	@Override
	public ServerInfo getServerInfo(int id) {
		return dao.getServerInfo(id);
	}
	@Override
	public String removeServerInfo(int id) {
		return dao.removeServerInfo(id);
	}

	// Bank  
	@Override
	public String saveBank(Bank bank) {
		return dao.saveBank(bank);
	}
	@Override
	public String updateBank(Bank bank) {
		return dao.updateBank(bank);
	}
	@Override
	public List<Bank> loadBank(List<Bank> bank){
		return dao.loadBank(bank);
	}
	@Override
	public Bank getBank(int id) {
		return dao.getBank(id);
	}
	@Override
	public String removeBank(int id) {
		return dao.removeBank(id);
	}
	

	
}
