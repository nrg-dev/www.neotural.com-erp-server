package com.erp.dto;

import java.io.Serializable;

public class EmployeeDto  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3962716785378801138L;
	
	String id 	;
	String employeecode 	;
	String date;
	String report;
	String type;
	
	
	 String checkinreason;
	 String checkintime;
	 String checkoutreason;
	 String checkouttime;
	 String absent;
	 String reason;
	 
	 String reporttype;
	 String monthname;
	 String year;
	 String fromdate;
	 String todate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmployeecode() {
		return employeecode;
	}
	public void setEmployeecode(String employeecode) {
		this.employeecode = employeecode;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCheckinreason() {
		return checkinreason;
	}
	public void setCheckinreason(String checkinreason) {
		this.checkinreason = checkinreason;
	}
	public String getCheckintime() {
		return checkintime;
	}
	public void setCheckintime(String checkintime) {
		this.checkintime = checkintime;
	}
	public String getCheckoutreason() {
		return checkoutreason;
	}
	public void setCheckoutreason(String checkoutreason) {
		this.checkoutreason = checkoutreason;
	}
	public String getCheckouttime() {
		return checkouttime;
	}
	public void setCheckouttime(String checkouttime) {
		this.checkouttime = checkouttime;
	}
	public String getAbsent() {
		return absent;
	}
	public void setAbsent(String absent) {
		this.absent = absent;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getReporttype() {
		return reporttype;
	}
	public void setReporttype(String reporttype) {
		this.reporttype = reporttype;
	}
	public String getMonthname() {
		return monthname;
	}
	public void setMonthname(String monthname) {
		this.monthname = monthname;
	}
	public String getFromdate() {
		return fromdate;
	}
	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}
	public String getTodate() {
		return todate;
	}
	public void setTodate(String todate) {
		this.todate = todate;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	
	 
}
