package com.mynrg.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the document database table.
 * 
 */
@Entity
@Table(name="document")
@NamedQuery(name="Document.findAll", query="SELECT d FROM Document d")
public class Document implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="DOCUMENT_ID")
	private int documentId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="CREATED_PERSON")
	private String createdPerson;

	@Temporal(TemporalType.DATE)
	@Column(name="DOCUMENT_DATE")
	private Date documentDate;

	@Column(name="DOCUMENT_NAME")
	private String documentName;

	@Column(name="DOCUMENT_NUMBER")
	private String documentNumber;

	@Column(name="DOCUMENT1_PATH")
	private String document1Path;

	@Column(name="DOCUMENT2_PATH")
	private String document2Path;

	@Column(name="DOCUMENT3_PATH")
	private String document3Path;

	@Column(name="HASH_USER_ID")
	private int hashUserId;

	private String status;

	@Temporal(TemporalType.DATE)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

	@Column(name="UPDATED_PERSON")
	private String updatedPerson;

	public Document() {
	}

	public int getDocumentId() {
		return this.documentId;
	}

	public void setDocumentId(int documentId) {
		this.documentId = documentId;
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

	public Date getDocumentDate() {
		return this.documentDate;
	}

	public void setDocumentDate(Date documentDate) {
		this.documentDate = documentDate;
	}

	public String getDocumentName() {
		return this.documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentNumber() {
		return this.documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getDocument1Path() {
		return this.document1Path;
	}

	public void setDocument1Path(String document1Path) {
		this.document1Path = document1Path;
	}

	public String getDocument2Path() {
		return this.document2Path;
	}

	public void setDocument2Path(String document2Path) {
		this.document2Path = document2Path;
	}

	public String getDocument3Path() {
		return this.document3Path;
	}

	public void setDocument3Path(String document3Path) {
		this.document3Path = document3Path;
	}

	public int getHashUserId() {
		return this.hashUserId;
	}

	public void setHashUserId(int hashUserId) {
		this.hashUserId = hashUserId;
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

}