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



public interface UserBo {
	
	// User Management
	public List<User> loadUserInfo(String type);
	public User isAuthentication(String username,String password);

	
	
}
