package com.mynrg.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyTaskDataBean {
	
	/*public String taskname;*/
	public Date startdate; 
	public Date targetdate;
	public String team;
	public String employeename;
	public String tasknotes;
	public String projectname;
	public String currentuser;
	public String taskStatus;
	public Date completedDate;
	public String taskNumber;
	public String pathName;	
	
	private List<MyTaskDataBean> enddate = new ArrayList<MyTaskDataBean>();
	private List<MyTaskDataBean> enddate1 = new ArrayList<MyTaskDataBean>();
	private List<MyTaskDataBean> enddate2 = new ArrayList<MyTaskDataBean>();
	private String employeeEmailID;
	
	public int Newcount;
	public int InProgresscount;
	public int Pendingcount;
	public int Closedcount;
	public int Completedcount;
	
	
	public int getNewcount() {
		return Newcount;
	}
	public void setNewcount(int newcount) {
		Newcount = newcount;
	}
	public int getInProgresscount() {
		return InProgresscount;
	}
	public void setInProgresscount(int inProgresscount) {
		InProgresscount = inProgresscount;
	}
	public int getPendingcount() {
		return Pendingcount;
	}
	public void setPendingcount(int pendingcount) {
		Pendingcount = pendingcount;
	}
	public int getClosedcount() {
		return Closedcount;
	}
	public void setClosedcount(int closedcount) {
		Closedcount = closedcount;
	}
	public int getCompletedcount() {
		return Completedcount;
	}
	public void setCompletedcount(int completedcount) {
		Completedcount = completedcount;
	}
	public String getPathName() {
		return pathName;
	}
	public void setPathName(String pathName) {
		this.pathName = pathName;
	}
	public String getEmployeeEmailID() {
		return employeeEmailID;
	}
	public void setEmployeeEmailID(String employeeEmailID) {
		this.employeeEmailID = employeeEmailID;
	}
	public List<MyTaskDataBean> getEnddate() {
		return enddate;
	}
	public void setEnddate(List<MyTaskDataBean> enddate) {
		this.enddate = enddate;
	}
	public List<MyTaskDataBean> getEnddate1() {
		return enddate1;
	}
	public void setEnddate1(List<MyTaskDataBean> enddate1) {
		this.enddate1 = enddate1;
	}
	public List<MyTaskDataBean> getEnddate2() {
		return enddate2;
	}
	public void setEnddate2(List<MyTaskDataBean> enddate2) {
		this.enddate2 = enddate2;
	}
	public String getTaskNumber() {
		return taskNumber;
	}
	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}
	public Date getCompletedDate() {
		return completedDate;
	}
	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	public String getCurrentuser() {
		return currentuser;
	}
	public void setCurrentuser(String currentuser) {
		this.currentuser = currentuser;
	}
	/*public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}*/
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getTargetdate() {
		return targetdate;
	}
	public void setTargetdate(Date targetdate) {
		this.targetdate = targetdate;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getEmployeename() {
		return employeename;
	}
	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}
	public String getTasknotes() {
		return tasknotes;
	}
	public void setTasknotes(String tasknotes) {
		this.tasknotes = tasknotes;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	

}
