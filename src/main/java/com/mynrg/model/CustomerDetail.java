package com.mynrg.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the customer_details database table.
 * 
 */
@Entity
@Table(name="customer_details")
@NamedQuery(name="CustomerDetail.findAll", query="SELECT c FROM CustomerDetail c")
public class CustomerDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int customer_ID;

	private String company_FaxNo;

	private String company_MailID;

	private String company_Name;

	private String company_PhoneNo;

	private String company_Website;

	@Temporal(TemporalType.DATE)
	private Date created_Date;

	private String created_person;

	private String customer_Category;

	private String customer_Comments;

	private String customer_Country;

	private String customer_Product;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FOLLOW_UP_DATE")
	private Date followUpDate;

	@Column(name="MODE_OF_COMMUNICATION")
	private String modeOfCommunication;

	private String person_FaxNo;

	private String person_MailID;

	private String person_Name;

	private String person_PhoneNo;

	private String person_Site;

	private String status;

	@Temporal(TemporalType.DATE)
	private Date updated_Date;

	private String updated_person;

	//bi-directional many-to-one association to CompetitorsCustomer
	@OneToMany(mappedBy="customerDetail")
	private List<CompetitorsCustomer> competitorsCustomers;

	//bi-directional many-to-one association to CustFollowUp
	@OneToMany(mappedBy="customerDetail")
	private List<CustFollowUp> custFollowUps;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="Has_USER_ID")
	private User user;

	public CustomerDetail() {
	}

	public int getCustomer_ID() {
		return this.customer_ID;
	}

	public void setCustomer_ID(int customer_ID) {
		this.customer_ID = customer_ID;
	}

	public String getCompany_FaxNo() {
		return this.company_FaxNo;
	}

	public void setCompany_FaxNo(String company_FaxNo) {
		this.company_FaxNo = company_FaxNo;
	}

	public String getCompany_MailID() {
		return this.company_MailID;
	}

	public void setCompany_MailID(String company_MailID) {
		this.company_MailID = company_MailID;
	}

	public String getCompany_Name() {
		return this.company_Name;
	}

	public void setCompany_Name(String company_Name) {
		this.company_Name = company_Name;
	}

	public String getCompany_PhoneNo() {
		return this.company_PhoneNo;
	}

	public void setCompany_PhoneNo(String company_PhoneNo) {
		this.company_PhoneNo = company_PhoneNo;
	}

	public String getCompany_Website() {
		return this.company_Website;
	}

	public void setCompany_Website(String company_Website) {
		this.company_Website = company_Website;
	}

	public Date getCreated_Date() {
		return this.created_Date;
	}

	public void setCreated_Date(Date created_Date) {
		this.created_Date = created_Date;
	}

	public String getCreated_person() {
		return this.created_person;
	}

	public void setCreated_person(String created_person) {
		this.created_person = created_person;
	}

	public String getCustomer_Category() {
		return this.customer_Category;
	}

	public void setCustomer_Category(String customer_Category) {
		this.customer_Category = customer_Category;
	}

	public String getCustomer_Comments() {
		return this.customer_Comments;
	}

	public void setCustomer_Comments(String customer_Comments) {
		this.customer_Comments = customer_Comments;
	}

	public String getCustomer_Country() {
		return this.customer_Country;
	}

	public void setCustomer_Country(String customer_Country) {
		this.customer_Country = customer_Country;
	}

	public String getCustomer_Product() {
		return this.customer_Product;
	}

	public void setCustomer_Product(String customer_Product) {
		this.customer_Product = customer_Product;
	}

	public Date getFollowUpDate() {
		return this.followUpDate;
	}

	public void setFollowUpDate(Date followUpDate) {
		this.followUpDate = followUpDate;
	}

	public String getModeOfCommunication() {
		return this.modeOfCommunication;
	}

	public void setModeOfCommunication(String modeOfCommunication) {
		this.modeOfCommunication = modeOfCommunication;
	}

	public String getPerson_FaxNo() {
		return this.person_FaxNo;
	}

	public void setPerson_FaxNo(String person_FaxNo) {
		this.person_FaxNo = person_FaxNo;
	}

	public String getPerson_MailID() {
		return this.person_MailID;
	}

	public void setPerson_MailID(String person_MailID) {
		this.person_MailID = person_MailID;
	}

	public String getPerson_Name() {
		return this.person_Name;
	}

	public void setPerson_Name(String person_Name) {
		this.person_Name = person_Name;
	}

	public String getPerson_PhoneNo() {
		return this.person_PhoneNo;
	}

	public void setPerson_PhoneNo(String person_PhoneNo) {
		this.person_PhoneNo = person_PhoneNo;
	}

	public String getPerson_Site() {
		return this.person_Site;
	}

	public void setPerson_Site(String person_Site) {
		this.person_Site = person_Site;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdated_Date() {
		return this.updated_Date;
	}

	public void setUpdated_Date(Date updated_Date) {
		this.updated_Date = updated_Date;
	}

	public String getUpdated_person() {
		return this.updated_person;
	}

	public void setUpdated_person(String updated_person) {
		this.updated_person = updated_person;
	}

	public List<CompetitorsCustomer> getCompetitorsCustomers() {
		return this.competitorsCustomers;
	}

	public void setCompetitorsCustomers(List<CompetitorsCustomer> competitorsCustomers) {
		this.competitorsCustomers = competitorsCustomers;
	}

	public CompetitorsCustomer addCompetitorsCustomer(CompetitorsCustomer competitorsCustomer) {
		getCompetitorsCustomers().add(competitorsCustomer);
		competitorsCustomer.setCustomerDetail(this);

		return competitorsCustomer;
	}

	public CompetitorsCustomer removeCompetitorsCustomer(CompetitorsCustomer competitorsCustomer) {
		getCompetitorsCustomers().remove(competitorsCustomer);
		competitorsCustomer.setCustomerDetail(null);

		return competitorsCustomer;
	}

	public List<CustFollowUp> getCustFollowUps() {
		return this.custFollowUps;
	}

	public void setCustFollowUps(List<CustFollowUp> custFollowUps) {
		this.custFollowUps = custFollowUps;
	}

	public CustFollowUp addCustFollowUp(CustFollowUp custFollowUp) {
		getCustFollowUps().add(custFollowUp);
		custFollowUp.setCustomerDetail(this);

		return custFollowUp;
	}

	public CustFollowUp removeCustFollowUp(CustFollowUp custFollowUp) {
		getCustFollowUps().remove(custFollowUp);
		custFollowUp.setCustomerDetail(null);

		return custFollowUp;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}