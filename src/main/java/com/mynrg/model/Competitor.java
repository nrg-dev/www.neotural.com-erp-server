package com.mynrg.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the competitors database table.
 * 
 */
@Entity
@Table(name="competitors")
@NamedQuery(name="Competitor.findAll", query="SELECT c FROM Competitor c")
public class Competitor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COMPETITOR_ID")
	private int competitorId;

	private String comments;

	@Column(name="COMPETITOR_COMPANY")
	private String competitorCompany;

	@Column(name="COMPETITOR_CONTACT_PERSON")
	private String competitorContactPerson;

	@Column(name="COMPETITOR_COUNTRY")
	private String competitorCountry;

	@Column(name="COMPETITOR_FAXNO")
	private String competitorFaxno;

	@Column(name="COMPETITOR_INCHARGE_PHONENO")
	private String competitorInchargePhoneno;

	@Column(name="COMPETITOR_INDUSTRY")
	private String competitorIndustry;

	@Column(name="COMPETITOR_MAIL_ID")
	private String competitorMailId;

	@Column(name="COMPETITOR_PHONE_NO")
	private String competitorPhoneNo;

	@Column(name="COMPETITOR_PRODUCT_NAME")
	private String competitorProductName;

	@Column(name="COMPETITOR_WEBSITE")
	private String competitorWebsite;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="CREATED_PERSON")
	private String createdPerson;

	private String status;

	@Temporal(TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

	@Column(name="UPDATED_PERSON")
	private String updatedPerson;

	//bi-directional many-to-one association to CompetitorsCustomer
	@OneToMany(mappedBy="competitor")
	private List<CompetitorsCustomer> competitorsCustomers;

	public Competitor() {
	}

	public int getCompetitorId() {
		return this.competitorId;
	}

	public void setCompetitorId(int competitorId) {
		this.competitorId = competitorId;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCompetitorCompany() {
		return this.competitorCompany;
	}

	public void setCompetitorCompany(String competitorCompany) {
		this.competitorCompany = competitorCompany;
	}

	public String getCompetitorContactPerson() {
		return this.competitorContactPerson;
	}

	public void setCompetitorContactPerson(String competitorContactPerson) {
		this.competitorContactPerson = competitorContactPerson;
	}

	public String getCompetitorCountry() {
		return this.competitorCountry;
	}

	public void setCompetitorCountry(String competitorCountry) {
		this.competitorCountry = competitorCountry;
	}

	public String getCompetitorFaxno() {
		return this.competitorFaxno;
	}

	public void setCompetitorFaxno(String competitorFaxno) {
		this.competitorFaxno = competitorFaxno;
	}

	public String getCompetitorInchargePhoneno() {
		return this.competitorInchargePhoneno;
	}

	public void setCompetitorInchargePhoneno(String competitorInchargePhoneno) {
		this.competitorInchargePhoneno = competitorInchargePhoneno;
	}

	public String getCompetitorIndustry() {
		return this.competitorIndustry;
	}

	public void setCompetitorIndustry(String competitorIndustry) {
		this.competitorIndustry = competitorIndustry;
	}

	public String getCompetitorMailId() {
		return this.competitorMailId;
	}

	public void setCompetitorMailId(String competitorMailId) {
		this.competitorMailId = competitorMailId;
	}

	public String getCompetitorPhoneNo() {
		return this.competitorPhoneNo;
	}

	public void setCompetitorPhoneNo(String competitorPhoneNo) {
		this.competitorPhoneNo = competitorPhoneNo;
	}

	public String getCompetitorProductName() {
		return this.competitorProductName;
	}

	public void setCompetitorProductName(String competitorProductName) {
		this.competitorProductName = competitorProductName;
	}

	public String getCompetitorWebsite() {
		return this.competitorWebsite;
	}

	public void setCompetitorWebsite(String competitorWebsite) {
		this.competitorWebsite = competitorWebsite;
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

	public List<CompetitorsCustomer> getCompetitorsCustomers() {
		return this.competitorsCustomers;
	}

	public void setCompetitorsCustomers(List<CompetitorsCustomer> competitorsCustomers) {
		this.competitorsCustomers = competitorsCustomers;
	}

	public CompetitorsCustomer addCompetitorsCustomer(CompetitorsCustomer competitorsCustomer) {
		getCompetitorsCustomers().add(competitorsCustomer);
		competitorsCustomer.setCompetitor(this);

		return competitorsCustomer;
	}

	public CompetitorsCustomer removeCompetitorsCustomer(CompetitorsCustomer competitorsCustomer) {
		getCompetitorsCustomers().remove(competitorsCustomer);
		competitorsCustomer.setCompetitor(null);

		return competitorsCustomer;
	}

}