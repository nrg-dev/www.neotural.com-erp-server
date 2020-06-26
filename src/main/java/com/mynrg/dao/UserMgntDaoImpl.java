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
import com.mynrg.model.User;
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
public class UserMgntDaoImpl implements UserMgntDao {

	public static final Logger logger = LoggerFactory.getLogger(UserMgntDaoImpl.class);

	@PersistenceContext(unitName="mynrg-pu")
	private EntityManager entitymanager;
	
	@Override
	@Transactional(value = "transactionManager")
	public List<User> loadUserInfo(String type){
	    logger.debug("Type-->"+type);
		List<User> list=null;
		Query q = null;
		q = entitymanager.createQuery("from User");
		list = (List<User>) q.getResultList();
		return list;
	}

	@Override
	@Transactional(value = "transactionManager")
	public User isAuthentication(String username,String password) {
	    logger.debug("UserName-->"+username);
		logger.debug("Password-->"+password);
		User response=null;
		Query q = null;
		try {
			q = entitymanager.createQuery("from User where userName=? ");
			q.setParameter(1, username);
			response=(User)q.getSingleResult();
			return response;
		}catch(NoResultException e) {
			response=null;
			logger.info("No user found");
		return response;
		}
		/*
		 * if(response.getUserName()!=null) { logger.info("Found user");
		 * logger.debug("DB User Name-->"+response.getUserName());
		 * logger.debug("DB Password-->"+response.getUserPassword()); return response; }
		 * else {
		 * 
		 * }
		 */
	}
	    
	    
}
