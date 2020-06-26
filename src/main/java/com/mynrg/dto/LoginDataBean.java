package com.mynrg.dto;


public class LoginDataBean{
	
public int id;
public String username;
public String userpassword;
public String usermenu;
public String usersubmenu;
public String usermenupages;
public String userstatus;
public String emailId;

public String usertype;
public String department;

public int count;
public int follow;
public int notstarted;
public int Sucess;
public int Failedcount;
public int Initiatedcount;



public int New;
public int Pending;
public int InProgress;
public int Completed;






public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getFailedcount() {
	return Failedcount;
}
public void setFailedcount(int failedcount) {
	Failedcount = failedcount;
}
public int getInitiatedcount() {
	return Initiatedcount;
}
public void setInitiatedcount(int initiatedcount) {
	Initiatedcount = initiatedcount;
}
public String getUsertype() {
	return usertype;
}
public void setUsertype(String usertype) {
	this.usertype = usertype;
}
public String getDepartment() {
	return department;
}
public void setDepartment(String department) {
	this.department = department;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
public int getFollow() {
	return follow;
}
public void setFollow(int follow) {
	this.follow = follow;
}
public int getNotstarted() {
	return notstarted;
}
public void setNotstarted(int notstarted) {
	this.notstarted = notstarted;
}
public int getSucess() {
	return Sucess;
}
public void setSucess(int sucess) {
	Sucess = sucess;
}
public int getNew() {
	return New;
}
public void setNew(int new1) {
	New = new1;
}
public int getPending() {
	return Pending;
}
public void setPending(int pending) {
	Pending = pending;
}
public int getInProgress() {
	return InProgress;
}
public void setInProgress(int inProgress) {
	InProgress = inProgress;
}
public int getCompleted() {
	return Completed;
}
public void setCompleted(int completed) {
	Completed = completed;
}
public String getUsermenupages() {
	return usermenupages;
}
public void setUsermenupages(String usermenupages) {
	this.usermenupages = usermenupages;
}
public String getUsersubmenu() {
	return usersubmenu;
}
public void setUsersubmenu(String usersubmenu) {
	this.usersubmenu = usersubmenu;
}
public String loginstatus;

public String getLoginstatus() {
	return loginstatus;
}
public void setLoginstatus(String loginstatus) {
	this.loginstatus = loginstatus;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getUserpassword() {
	return userpassword;
}
public void setUserpassword(String userpassword) {
	this.userpassword = userpassword;
}
public String getUsermenu() {
	return usermenu;
}
public void setUsermenu(String usermenu) {
	this.usermenu = usermenu;
}
public String getUserstatus() {
	return userstatus;
}
public void setUserstatus(String userstatus) {
	this.userstatus = userstatus;
}
public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}

}
