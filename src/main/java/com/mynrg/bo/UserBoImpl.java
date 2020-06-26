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


@Service("userbo")
public class UserBoImpl implements UserBo{
	
	public static final Logger logger = LoggerFactory.getLogger(UserBoImpl.class);


	@Autowired
	UserMgntDao userdao;
	// User Management
	@Override
	public List<User> loadUserInfo(String type) {
		return userdao.loadUserInfo(type);
	}
	
	public User isAuthentication(String username,String password){
		return userdao.isAuthentication(username,password);

	}

}
