package com.mynrg.dto;

import java.util.ArrayList;
import java.util.List;

public class CompetitorsDataBean {
	
	public String competitorcompanyname; 
	public String competitorproductname;
	public String competitorwebsite;
	public String competitormailID;
	public String competitorphoneno;
	public String competitorcontactperson;
	private List<String> competitorcustomername = new ArrayList<String>();
	public String competitorcomments;
	public String loggedinuser;
	public String customerName; 
	
	public String competitorFaxno; 
	public String compInchargePhn; 
	public String compCountry; 
	public String compindustry;
	
	
	
	public String getCompetitorFaxno() {
		return competitorFaxno;
	}
	public void setCompetitorFaxno(String competitorFaxno) {
		this.competitorFaxno = competitorFaxno;
	}
	public String getCompInchargePhn() {
		return compInchargePhn;
	}
	public void setCompInchargePhn(String compInchargePhn) {
		this.compInchargePhn = compInchargePhn;
	}
	public String getCompCountry() {
		return compCountry;
	}
	public void setCompCountry(String compCountry) {
		this.compCountry = compCountry;
	}
	public String getCompindustry() {
		return compindustry;
	}
	public void setCompindustry(String compindustry) {
		this.compindustry = compindustry;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCompetitormailID() {
		return competitormailID;
	}
	public void setCompetitormailID(String competitormailID) {
		this.competitormailID = competitormailID;
	}
	public String getLoggedinuser() {
		return loggedinuser;
	}
	public void setLoggedinuser(String loggedinuser) {
		this.loggedinuser = loggedinuser;
	}
	public String getCompetitorcompanyname() {
		return competitorcompanyname;
	}
	public void setCompetitorcompanyname(String competitorcompanyname) {
		this.competitorcompanyname = competitorcompanyname;
	}
	public String getCompetitorproductname() {
		return competitorproductname;
	}
	public void setCompetitorproductname(String competitorproductname) {
		this.competitorproductname = competitorproductname;
	}
	public String getCompetitorwebsite() {
		return competitorwebsite;
	}
	public void setCompetitorwebsite(String competitorwebsite) {
		this.competitorwebsite = competitorwebsite;
	}
	public String getCompetitorphoneno() {
		return competitorphoneno;
	}
	public void setCompetitorphoneno(String competitorphoneno) {
		this.competitorphoneno = competitorphoneno;
	}
	public String getCompetitorcontactperson() {
		return competitorcontactperson;
	}
	public void setCompetitorcontactperson(String competitorcontactperson) {
		this.competitorcontactperson = competitorcontactperson;
	}
	
	public List<String> getCompetitorcustomername() {
		return competitorcustomername;
	}
	public void setCompetitorcustomername(List<String> competitorcustomername) {
		this.competitorcustomername = competitorcustomername;
	}
	public String getCompetitorcomments() {
		return competitorcomments;
	}
	public void setCompetitorcomments(String competitorcomments) {
		this.competitorcomments = competitorcomments;
	}
	
	

}
