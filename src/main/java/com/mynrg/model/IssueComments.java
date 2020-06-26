package com.mynrg.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import java.text.DateFormat;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


/**
 * The persistent class for the production_issue database table.
 * 
 */
@Entity
@Table(name="issue_comments")
@TableGenerator(name="issue",initialValue=0)
@NamedQuery(name="IssueComments.findAll", query="SELECT p FROM IssueComments p")
public class IssueComments implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column(name="ISSUE_COMMENTS_ID")
	private int id;

	
	//@Temporal(TemporalType.DATE)
	@Column(name="reported_date")
	private String  date;

	
	@Column(name="ADDED_PERSON")
	private String addedPerson;

	@Column(name="ISSUE_COMMENTS", columnDefinition = "MEDIUMTEXT")
	private String issueComments;

	@Column(name="BY_ISSUE_ID")
	private int byissueId;

	/*
	 * //bi-directional many-to-one association to CustomerDetail
	 * 
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="comments") private ProductionIssue comments;
	 */
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	/*
	 * @Temporal(TemporalType.TIMESTAMP)
	 * 
	 * @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss a")
	 * 
	 * @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss a")
	 */
	/*
	 * public DateFormat getDate() { return date; }
	 * 
	 * public void setDate(DateFormat date) { this.date = date; }
	 */

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAddedPerson() {
		return addedPerson;
	}

	public void setAddedPerson(String addedPerson) {
		this.addedPerson = addedPerson;
	}

	public String getIssueComments() {
		return issueComments;
	}

	public void setIssueComments(String issueComments) {
		this.issueComments = issueComments;
	}

	public int getByissueId() {
		return byissueId;
	}

	public void setByissueId(int byissueId) {
		this.byissueId = byissueId;
	}

	
	

	
}