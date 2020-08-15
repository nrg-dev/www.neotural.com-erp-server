package com.erp.bo;

import java.util.List;

import com.erp.dto.Career;
import com.erp.dto.Enquiry;
import com.erp.dto.User;
import com.erp.mongo.model.Index;

public interface ErpBo {

	public User userLogin(User user);

	public User Checkuser(User user, int temp);

	public Enquiry saveEnquiry(Enquiry enquiry);

	public List<Enquiry> loadEnquiry(List<Enquiry> enquirylist);

	public List<Index> loadIndex(List<Index> indexlist);

	public Career saveCareer(Career career);    
	
}
