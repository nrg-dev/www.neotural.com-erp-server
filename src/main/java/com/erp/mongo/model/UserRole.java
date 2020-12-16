package com.erp.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserRole {

	@Id
	private long  id;
	private String invnumber;
	private String username;
	private String password;
	private String userRole;
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
	private String menuItem9;

	private String purchasesubmenu1;
	private String purchasesubmenu2;
	private String purchasesubmenu3;
	private String purchasesubmenu4;
	private String productsubmenu1;
	private String productsubmenu2;
	private String productsubmenu3;
	private String salessubmenu1;
	private String salessubmenu2;
	private String salessubmenu3;
	private String salessubmenu4;
	private String salessubmenu5;
	private String salessubmenu6;
	private String financesubmenu1;
	private String financesubmenu2;
	private String financesubmenu3;
	private String financesubmenu4;
	private String reportsubmenu1;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public String getPurchasesubmenu1() {
		return purchasesubmenu1;
	}
	public void setPurchasesubmenu1(String purchasesubmenu1) {
		this.purchasesubmenu1 = purchasesubmenu1;
	}
	public String getPurchasesubmenu2() {
		return purchasesubmenu2;
	}
	public void setPurchasesubmenu2(String purchasesubmenu2) {
		this.purchasesubmenu2 = purchasesubmenu2;
	}
	public String getPurchasesubmenu3() {
		return purchasesubmenu3;
	}
	public void setPurchasesubmenu3(String purchasesubmenu3) {
		this.purchasesubmenu3 = purchasesubmenu3;
	}
	public String getProductsubmenu1() {
		return productsubmenu1;
	}
	public void setProductsubmenu1(String productsubmenu1) {
		this.productsubmenu1 = productsubmenu1;
	}
	public String getProductsubmenu2() {
		return productsubmenu2;
	}
	public void setProductsubmenu2(String productsubmenu2) {
		this.productsubmenu2 = productsubmenu2;
	}
	public String getProductsubmenu3() {
		return productsubmenu3;
	}
	public void setProductsubmenu3(String productsubmenu3) {
		this.productsubmenu3 = productsubmenu3;
	}
	public String getSalessubmenu1() {
		return salessubmenu1;
	}
	public void setSalessubmenu1(String salessubmenu1) {
		this.salessubmenu1 = salessubmenu1;
	}
	public String getSalessubmenu2() {
		return salessubmenu2;
	}
	public void setSalessubmenu2(String salessubmenu2) {
		this.salessubmenu2 = salessubmenu2;
	}
	public String getSalessubmenu3() {
		return salessubmenu3;
	}
	public void setSalessubmenu3(String salessubmenu3) {
		this.salessubmenu3 = salessubmenu3;
	}
	public String getSalessubmenu4() {
		return salessubmenu4;
	}
	public void setSalessubmenu4(String salessubmenu4) {
		this.salessubmenu4 = salessubmenu4;
	}
	public String getSalessubmenu5() {
		return salessubmenu5;
	}
	public void setSalessubmenu5(String salessubmenu5) {
		this.salessubmenu5 = salessubmenu5;
	}
	public String getMenuItem9() {
		return menuItem9;
	}
	public void setMenuItem9(String menuItem9) {
		this.menuItem9 = menuItem9;
	}
	public String getPurchasesubmenu4() {
		return purchasesubmenu4;
	}
	public void setPurchasesubmenu4(String purchasesubmenu4) {
		this.purchasesubmenu4 = purchasesubmenu4;
	}
	public String getSalessubmenu6() {
		return salessubmenu6;
	}
	public void setSalessubmenu6(String salessubmenu6) {
		this.salessubmenu6 = salessubmenu6;
	}
	public String getFinancesubmenu1() {
		return financesubmenu1;
	}
	public void setFinancesubmenu1(String financesubmenu1) {
		this.financesubmenu1 = financesubmenu1;
	}
	public String getFinancesubmenu2() {
		return financesubmenu2;
	}
	public void setFinancesubmenu2(String financesubmenu2) {
		this.financesubmenu2 = financesubmenu2;
	}
	public String getFinancesubmenu3() {
		return financesubmenu3;
	}
	public void setFinancesubmenu3(String financesubmenu3) {
		this.financesubmenu3 = financesubmenu3;
	}
	public String getFinancesubmenu4() {
		return financesubmenu4;
	}
	public void setFinancesubmenu4(String financesubmenu4) {
		this.financesubmenu4 = financesubmenu4;
	}
	public String getReportsubmenu1() {
		return reportsubmenu1;
	}
	public void setReportsubmenu1(String reportsubmenu1) {
		this.reportsubmenu1 = reportsubmenu1;
	}
	
		
	 
}
