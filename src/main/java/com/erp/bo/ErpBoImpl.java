package com.erp.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.erp.dto.Career;
import com.erp.dto.Enquiry;
import com.erp.dto.User;

import com.erp.mongo.dal.LoginDAL;
import com.erp.mongo.dal.RandomNumberDAL;
import com.erp.mongo.model.Index;
import com.erp.mongo.model.Login;
import com.erp.mongo.model.UserRole;


@Service("bo")
public class ErpBoImpl implements ErpBo{
	
	public static final Logger logger = LoggerFactory.getLogger(ErpBoImpl.class);

	 
	private final LoginDAL logindal;
	//private final RandomNumberDAL randomnumberdal;
	
	public ErpBoImpl(LoginDAL logindal,RandomNumberDAL randomnumberdal) {
		this.logindal = logindal;
		//this.randomnumberdal = randomnumberdal;
	}
	
	@Override
	public User userLogin(User user){
		logger.info("BoImpl UserLogin");
		List<UserRole> result = null;
		try {
			user.setId(1);
			result = logindal.userLogin(user,result);
			if(result.size() > 0) {
				result=null;
				user.setId(2);
				result = logindal.userLogin(user,result);
				if(result.size() > 0) {
					if(result.get(0).getStatus().equalsIgnoreCase("Active")){
						user.setStatus("success");
					}
					if(result.get(0).getStatus().equalsIgnoreCase("De-Active")){
						user.setStatus("Your Account was De-Active");
					}
				}
				else {
					user.setStatus("Invalid Password");
				}
			}else {
				user.setStatus("Invalid User Name.");
			}
		}catch(Exception e){
			logger.error("BO Exception-->"+e.getMessage());
			user.setStatus("Network Error Please try again");

		}
		finally{
			result=null;
		}
		return user;
	}
	
	// ---------------- forget Password use check ------------------------------
	public User Checkuser(User user,int temp){
		if(temp ==1 ){
			user = logindal.Checkuser(user);
			logger.debug("UserName Check status-->"+user.getStatus());
		}
		
		if(temp == 2) {
			user = logindal.resetPassword(user);	 		
			logger.debug("Userpassword check status-->"+user.getStatus());
		}
		
		return user;
	}
	
	public Enquiry saveEnquiry(Enquiry enquiry) {
		enquiry = logindal.saveEnquiry(enquiry);
		return enquiry;
	} 
	
	@Override
	public List<Enquiry> loadEnquiry(List<Enquiry> enquirylist) {
		enquirylist = logindal.loadEnquiry(enquirylist);
		return enquirylist;
	}
	
	@Override
	public List<Index> loadIndex(List<Index> indexlist) {
		indexlist = logindal.loadIndex(indexlist);
		return indexlist;
	}

	public Career saveCareer(Career career) {
		career = logindal.saveCareer(career);
		return career;
	} 
	
}
