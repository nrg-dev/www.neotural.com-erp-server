package com.mynrg.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the competitors_customer database table.
 * 
 */
@Entity
@Table(name="competitors_customer")
@NamedQuery(name="CompetitorsCustomer.findAll", query="SELECT c FROM CompetitorsCustomer c")
public class CompetitorsCustomer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COMPETITORS_CUSTOMER_ID")
	private int competitorsCustomerId;

	private String status;

	//bi-directional many-to-one association to CustomerDetail
	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID")
	private CustomerDetail customerDetail;

	//bi-directional many-to-one association to Competitor
	@ManyToOne
	@JoinColumn(name="COMPETITOR_ID")
	private Competitor competitor;

	public CompetitorsCustomer() {
	}

	public int getCompetitorsCustomerId() {
		return this.competitorsCustomerId;
	}

	public void setCompetitorsCustomerId(int competitorsCustomerId) {
		this.competitorsCustomerId = competitorsCustomerId;
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

	public Competitor getCompetitor() {
		return this.competitor;
	}

	public void setCompetitor(Competitor competitor) {
		this.competitor = competitor;
	}

}