package com.erp.mongo.dal;

import java.util.List;

import com.erp.mongo.model.Menu;
import com.erp.mongo.model.Submenu;
import com.erp.mongo.model.UserRole;

public interface UserMgtDAL {

	public UserRole save(UserRole user);

	public List<UserRole> load(List<UserRole> userlist); 

	public UserRole updateUser(UserRole userrole);

	public UserRole removeUser(String id);

	public List<Menu> getMenuName(String menuCode);

	public List<Submenu> getSubMenuName(String subMenuCode);  

}