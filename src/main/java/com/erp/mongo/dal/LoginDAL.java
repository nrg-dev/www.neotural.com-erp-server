package com.erp.mongo.dal;

import java.util.List;

import com.erp.dto.Career;
import com.erp.dto.Enquiry;
import com.erp.dto.User;
import com.erp.mongo.model.Index;
import com.erp.mongo.model.UserRole;

public interface LoginDAL {

	public List<UserRole> userLogin(User user, List<UserRole> result);

	public User Checkuser(User user);

	public User resetPassword(User user);

	public Enquiry saveEnquiry(Enquiry enquiry);

	public List<Enquiry> loadEnquiry(List<Enquiry> enquirylist);

	public List<Index> loadIndex(List<Index> indexlist);

	public Career saveCareer(Career career);   

	

}