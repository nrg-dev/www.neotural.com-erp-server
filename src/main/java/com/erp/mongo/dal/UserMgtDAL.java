package com.erp.mongo.dal;

import java.util.List;
import com.erp.mongo.model.UserRole;

public interface UserMgtDAL {

	public UserRole save(UserRole user);

	public List<UserRole> load(List<UserRole> userlist); 

	public UserRole updateUser(UserRole userrole);

	public UserRole removeUser(String id);

}