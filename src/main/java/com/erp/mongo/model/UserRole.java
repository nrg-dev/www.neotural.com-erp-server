package com.erp.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserRole {

	@Id
	private String id;
	private String invnumber;
	private String username;
	private String password;
	private String userRole;
	private String menuItem;
	private String subMenuItem;
	private String departmentname;
	private String status;
	private String menuItem1;
	private String menuItem2;
	private String menuItem3;
	private String menuItem4;
	private String menuItem5;
	private String menuItem6;
	private String menuItem7;
	private String menuItem8;

	private String purchasesubmenu;
	private String productsubmenu;
	private String salessubmenu;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInvnumber() {
		return invnumber;
	}
	public void setInvnumber(String invnumber) {
		this.invnumber = invnumber;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getMenuItem() {
		return menuItem;
	}
	public void setMenuItem(String menuItem) {
		this.menuItem = menuItem;
	}
	public String getSubMenuItem() {
		return subMenuItem;
	}
	public void setSubMenuItem(String subMenuItem) {
		this.subMenuItem = subMenuItem;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMenuItem1() {
		return menuItem1;
	}
	public void setMenuItem1(String menuItem1) {
		this.menuItem1 = menuItem1;
	}
	public String getMenuItem2() {
		return menuItem2;
	}
	public void setMenuItem2(String menuItem2) {
		this.menuItem2 = menuItem2;
	}
	public String getMenuItem3() {
		return menuItem3;
	}
	public void setMenuItem3(String menuItem3) {
		this.menuItem3 = menuItem3;
	}
	public String getMenuItem4() {
		return menuItem4;
	}
	public void setMenuItem4(String menuItem4) {
		this.menuItem4 = menuItem4;
	}
	public String getMenuItem5() {
		return menuItem5;
	}
	public void setMenuItem5(String menuItem5) {
		this.menuItem5 = menuItem5;
	}
	public String getMenuItem6() {
		return menuItem6;
	}
	public void setMenuItem6(String menuItem6) {
		this.menuItem6 = menuItem6;
	}
	public String getMenuItem7() {
		return menuItem7;
	}
	public void setMenuItem7(String menuItem7) {
		this.menuItem7 = menuItem7;
	}
	public String getMenuItem8() {
		return menuItem8;
	}
	public void setMenuItem8(String menuItem8) {
		this.menuItem8 = menuItem8;
	}
	public String getPurchasesubmenu() {
		return purchasesubmenu;
	}
	public void setPurchasesubmenu(String purchasesubmenu) {
		this.purchasesubmenu = purchasesubmenu;
	}
	public String getProductsubmenu() {
		return productsubmenu;
	}
	public void setProductsubmenu(String productsubmenu) {
		this.productsubmenu = productsubmenu;
	}
	public String getSalessubmenu() {
		return salessubmenu;
	}
	public void setSalessubmenu(String salessubmenu) {
		this.salessubmenu = salessubmenu;
	}
		
	 
}
