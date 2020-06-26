package com.mynrg.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerDataBean {

	public String companyname; 
	public String companymail;
	public String companyPhoneno;
	public String companyfax;
	public String companysite;
	public String personname;
	public String personmail;
	public String personPhoneno;
	public String personfax;
	public String personsite;
	public String customerComment;
	public String customerCountry;
	public String customerCategory;
	public String customerProduct;
	public String customerstatus;
	public String coldcalby;
	
	public String mailsubject;
	public String mailcontant;
	
	public String username; 
	public String usermail;
	public String userPhoneno;
	public String useradrs;
	public String userdepartment;
	public String usertype;
	public String userpassword;
	public String userdetail;
	
	public String modeOfcommunication;
	public Date followupdatetime; 
	
	
	public String Menu;
	public String Submenu;
	public String MenuPages;
	public String loggedinuser;
	
	public Date followDate;
	public String followMode;
	public String followPerson;
	public String followEmail;
	public String followStage;
	public String followComments;
	
	private List<String> competitorname= new ArrayList<String>();

	
	
	
	public Date getFollowDate() {
		return followDate;
	}
	public void setFollowDate(Date followDate) {
		this.followDate = followDate;
	}
	public String getFollowMode() {
		return followMode;
	}
	public void setFollowMode(String followMode) {
		this.followMode = followMode;
	}
	public String getFollowPerson() {
		return followPerson;
	}
	public void setFollowPerson(String followPerson) {
		this.followPerson = followPerson;
	}
	public String getFollowEmail() {
		return followEmail;
	}
	public void setFollowEmail(String followEmail) {
		this.followEmail = followEmail;
	}
	public String getFollowStage() {
		return followStage;
	}
	public void setFollowStage(String followStage) {
		this.followStage = followStage;
	}
	public String getFollowComments() {
		return followComments;
	}
	public void setFollowComments(String followComments) {
		this.followComments = followComments;
	}
	public Date getFollowupdatetime() {
		return followupdatetime;
	}
	public void setFollowupdatetime(Date followupdatetime) {
		this.followupdatetime = followupdatetime;
	}
	public String getModeOfcommunication() {
		return modeOfcommunication;
	}
	public void setModeOfcommunication(String modeOfcommunication) {
		this.modeOfcommunication = modeOfcommunication;
	}
	public List<String> getCompetitorname() {
		return competitorname;
	}
	public void setCompetitorname(List<String> competitorname) {
		this.competitorname = competitorname;
	}
	public String getMenuPages() {
		return MenuPages;
	}
	public void setMenuPages(String menuPages) {
		MenuPages = menuPages;
	}
	public String getMenu() {
		return Menu;
	}
	public void setMenu(String menu) {
		Menu = menu;
	}
	public String getSubmenu() {
		return Submenu;
	}
	public void setSubmenu(String submenu) {
		Submenu = submenu;
	}
	public String getMailsubject() {
		return mailsubject;
	}
	public void setMailsubject(String mailsubject) {
		this.mailsubject = mailsubject;
	}
	public String getMailcontant() {
		return mailcontant;
	}
	public void setMailcontant(String mailcontant) {
		this.mailcontant = mailcontant;
	}
	public String getLoggedinuser() {
		return loggedinuser;
	}
	public void setLoggedinuser(String loggedinuser) {
		this.loggedinuser = loggedinuser;
	}
	public String getUserdetail() {
		return userdetail;
	}
	public void setUserdetail(String userdetail) {
		this.userdetail = userdetail;
	}
	public String getColdcalby() {
		return coldcalby;
	}
	public void setColdcalby(String coldcalby) {
		this.coldcalby = coldcalby;
	}
	public String getCustomerstatus() {
		return customerstatus;
	}
	public void setCustomerstatus(String customerstatus) {
		this.customerstatus = customerstatus;
	}
	
	public String getCustomerProduct() {
		return customerProduct;
	}
	public void setCustomerProduct(String customerProduct) {
		this.customerProduct = customerProduct;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String returnStatus;
	
	
	public String getUserdepartment() {
		return userdepartment;
	}
	public void setUserdepartment(String userdepartment) {
		this.userdepartment = userdepartment;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsermail() {
		return usermail;
	}
	public void setUsermail(String usermail) {
		this.usermail = usermail;
	}
	public String getUserPhoneno() {
		return userPhoneno;
	}
	public void setUserPhoneno(String userPhoneno) {
		this.userPhoneno = userPhoneno;
	}
	public String getUseradrs() {
		return useradrs;
	}
	public void setUseradrs(String useradrs) {
		this.useradrs = useradrs;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getReturnStatus() {
		return returnStatus;
	}
	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}
	public String getCustomerComment() {
		return customerComment;
	}
	public void setCustomerComment(String customerComment) {
		this.customerComment = customerComment;
	}
	public String getCustomerCountry() {
		return customerCountry;
	}
	public void setCustomerCountry(String customerCountry) {
		this.customerCountry = customerCountry;
	}
	public String getCustomerCategory() {
		return customerCategory;
	}
	public void setCustomerCategory(String customerCategory) {
		this.customerCategory = customerCategory;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getCompanymail() {
		return companymail;
	}
	public void setCompanymail(String companymail) {
		this.companymail = companymail;
	}

	public String getCompanysite() {
		return companysite;
	}
	public void setCompanysite(String companysite) {
		this.companysite = companysite;
	}
	public String getPersonname() {
		return personname;
	}
	public void setPersonname(String personname) {
		this.personname = personname;
	}
	public String getPersonmail() {
		return personmail;
	}
	public void setPersonmail(String personmail) {
		this.personmail = personmail;
	}

	public String getPersonsite() {
		return personsite;
	}
	public void setPersonsite(String personsite) {
		this.personsite = personsite;
	}
	public String getCompanyPhoneno() {
		return companyPhoneno;
	}
	public void setCompanyPhoneno(String companyPhoneno) {
		this.companyPhoneno = companyPhoneno;
	}
	public String getCompanyfax() {
		return companyfax;
	}
	public void setCompanyfax(String companyfax) {
		this.companyfax = companyfax;
	}
	public String getPersonPhoneno() {
		return personPhoneno;
	}
	public void setPersonPhoneno(String personPhoneno) {
		this.personPhoneno = personPhoneno;
	}
	public String getPersonfax() {
		return personfax;
	}
	public void setPersonfax(String personfax) {
		this.personfax = personfax;
	}

	

}
