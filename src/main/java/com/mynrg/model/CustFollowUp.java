package com.mynrg.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the cust_follow_up database table.
 * 
 */
@Entity
@Table(name="cust_follow_up")
@NamedQuery(name="CustFollowUp.findAll", query="SELECT c FROM CustFollowUp c")
public class CustFollowUp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CUSTFOLLOWUP_ID")
	private int custfollowupId;

	private String comments;

	@Column(name="CONTACT_EMAIL")
	private String contactEmail;

	@Column(name="CONTACT_PERSON")
	private String contactPerson;

	@Temporal(TemporalType.DATE)
	@Column(name="FOLLOW_DATE")
	private Date followDate;

	@Column(name="MODE_OF_CONTACT")
	private String modeOfContact;

	private String stage;

	private String status;

	//bi-directional many-to-one association to CustomerDetail
	@ManyToOne
	@JoinColumn(name="HAS_CUSTID")
	private CustomerDetail customerDetail;

	public CustFollowUp() {
	}

	public int getCustfollowupId() {
		return this.custfollowupId;
	}

	public void setCustfollowupId(int custfollowupId) {
		this.custfollowupId = custfollowupId;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getContactEmail() {
		return this.contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactPerson() {
		return this.contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public Date getFollowDate() {
		return this.followDate;
	}

	public void setFollowDate(Date followDate) {
		this.followDate = followDate;
	}

	public String getModeOfContact() {
		return this.modeOfContact;
	}

	public void setModeOfContact(String modeOfContact) {
		this.modeOfContact = modeOfContact;
	}

	public String getStage() {
		return this.stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CustomerDetail getCustomerDetail() {
		return this.customerDetail;
	}

	public void setCustomerDetail(CustomerDetail customerDetail) {
		this.customerDetail = customerDetail;
	}

}