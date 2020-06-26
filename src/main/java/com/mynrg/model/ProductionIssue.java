package com.mynrg.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the production_issue database table.
 * 
 */
@Entity
@Table(name="production_issue")
@NamedQuery(name="ProductionIssue.findAll", query="SELECT p FROM ProductionIssue p")
public class ProductionIssue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ISSUE_ID")
	private int issueId;

	@Column(name="CLIENT_NAME")
	private String clientName;

	private String country;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="CREATED_PERSON")
	private String createdPerson;

	@Column(name="ISSUE_NOTES")
	private String issueNotes;

	@Column(name="ISSUE_NUMBER")
	private String issueNumber;

	@Column(name="ISSUE_STATUS")
	private String issueStatus;

	private String priority;

	private String product;

	private String status;

	private String team;
	
	//private Blob cardImageBase64;
	//@Lob
	@Column(columnDefinition = "MEDIUMTEXT")
	private String cardImageBase64;

	@Temporal(TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

	@Column(name="UPDATED_PERSON")
	private String updatedPerson;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;
	
	/*
	 * //bi-directional many-to-one association to CustFollowUp
	 * 
	 * @OneToMany(mappedBy="comments") private List<IssueComments> comments;
	 * 
	 */

	public ProductionIssue() {
	}

	public int getIssueId() {
		return this.issueId;
	}

	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}

	public String getClientName() {
		return this.clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedPerson() {
		return this.createdPerson;
	}

	public void setCreatedPerson(String createdPerson) {
		this.createdPerson = createdPerson;
	}

	public String getIssueNotes() {
		return this.issueNotes;
	}

	public void setIssueNotes(String issueNotes) {
		this.issueNotes = issueNotes;
	}

	public String getIssueNumber() {
		return this.issueNumber;
	}

	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}

	public String getIssueStatus() {
		return this.issueStatus;
	}

	public void setIssueStatus(String issueStatus) {
		this.issueStatus = issueStatus;
	}

	public String getPriority() {
		return this.priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTeam() {
		return this.team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedPerson() {
		return this.updatedPerson;
	}

	public void setUpdatedPerson(String updatedPerson) {
		this.updatedPerson = updatedPerson;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCardImageBase64() {
		return cardImageBase64;
	}

	public void setCardImageBase64(String cardImageBase64) {
		this.cardImageBase64 = cardImageBase64;
	}
	/*
	 * public List<IssueComments> getComments() { return comments; }
	 * 
	 * public void setComments(List<IssueComments> comments) { this.comments =
	 * comments; }
	 */

}