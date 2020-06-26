package com.mynrg.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the portal database table.
 * 
 */
@Entity
@Table(name="portal")
@NamedQuery(name="Portal.findAll", query="SELECT p FROM Portal p")
public class Portal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PORTAL_ID")
	private int portalId;

	private String country;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="CREATED_PERSON")
	private String createdPerson;

	@Column(name="EMAIL_ID1")
	private String emailId1;

	@Column(name="EMAIL_ID2")
	private String emailId2;

	private String password;

	@Column(name="PHONE_NUMBER1")
	private String phoneNumber1;

	@Column(name="PHONE_NUMBER2")
	private String phoneNumber2;

	@Column(name="PORTAL_NAME")
	private String portalName;
	
	@Column(name="PORTAL_TYPE")
	private String portaltype;

	private String status;

	@Temporal(TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

	@Column(name="UPDATED_PERSON")
	private String updatedPerson;

	private String url;

	@Column(name="USER_NAME")
	private String userName;

	@Column(name="NOTES")
	private String notes;
	
	public Portal() {
	}

	public int getPortalId() {
		return this.portalId;
	}

	public void setPortalId(int portalId) {
		this.portalId = portalId;
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

	public String getEmailId1() {
		return this.emailId1;
	}

	public void setEmailId1(String emailId1) {
		this.emailId1 = emailId1;
	}

	public String getEmailId2() {
		return this.emailId2;
	}

	public void setEmailId2(String emailId2) {
		this.emailId2 = emailId2;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber1() {
		return this.phoneNumber1;
	}

	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}

	public String getPhoneNumber2() {
		return this.phoneNumber2;
	}

	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}

	public String getPortalName() {
		return this.portalName;
	}

	public void setPortalName(String portalName) {
		this.portalName = portalName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPortaltype() {
		return portaltype;
	}

	public void setPortaltype(String portaltype) {
		this.portaltype = portaltype;
	}

}