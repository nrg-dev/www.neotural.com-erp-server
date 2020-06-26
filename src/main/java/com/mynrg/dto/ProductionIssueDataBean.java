package com.mynrg.dto;

import java.util.Date;



public class ProductionIssueDataBean {
	
	/*
	 * public int issueId; public String clientName; public String issuenotes;
	 * public String priority; public String country; public String team; public
	 * String currentuser; public String issueStatus; public String product; public
	 * String IssueNumber;
	 * 
	 * public int Newcount; public int Pendingcount; public int Inprogresscount;
	 * public int Completecount; public int Closedcount; public int
	 * Clarificationcount; public int Cancelcount;
	 */
	
	public int issueId;

	public String clientName;

	public String country;

	public Date createdDate;

	public String createdPerson;

	public String issueNotes;

	public String issueNumber;

	public String issueStatus;

	public String priority;

	public String product;

	public String status;

	public String team;
	
	public String cardImageBase64;
	
	/*
	 * public ProductionIssueDataBean( int issueId,String clientName,String country,
	 * Date createdDate, String createdPerson,String issueNotes, String issueNumber,
	 * String issueStatus,String priority, String product, String status,String
	 * team,String cardImageBase64){ this.issueId=issueId;
	 * this.clientName=clientName; this.country=country;
	 * this.createdDate=createdDate; this.createdPerson=createdPerson;
	 * this.issueNotes=issueNotes; this.issueNumber=issueNumber;
	 * this.issueStatus=issueStatus; this.priority=priority; this.product=product;
	 * this.status=status; this.team=team; this.cardImageBase64=cardImageBase64; }
	 */

	public int getIssueId() {
		return issueId;
	}

	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedPerson() {
		return createdPerson;
	}

	public void setCreatedPerson(String createdPerson) {
		this.createdPerson = createdPerson;
	}

	public String getIssueNotes() {
		return issueNotes;
	}

	public void setIssueNotes(String issueNotes) {
		this.issueNotes = issueNotes;
	}

	public String getIssueNumber() {
		return issueNumber;
	}

	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}

	public String getIssueStatus() {
		return issueStatus;
	}

	public void setIssueStatus(String issueStatus) {
		this.issueStatus = issueStatus;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getCardImageBase64() {
		return cardImageBase64;
	}

	public void setCardImageBase64(String cardImageBase64) {
		this.cardImageBase64 = cardImageBase64;
	}
	
}
