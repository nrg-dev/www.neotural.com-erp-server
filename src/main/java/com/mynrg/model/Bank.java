package com.mynrg.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the bank database table.
 * 
 */
@Entity
@Table(name="bank")
@NamedQuery(name="Bank.findAll", query="SELECT b FROM Bank b")
public class Bank implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BANK_ID")
	private int bankId;

	@Column(name="ACCOUNT_HOLDER_NAME")
	private String accountHolderName;

	@Column(name="ACCOUNT_NUMBER")
	private String accountNumber;

	@Column(name="ATM_PIN_NUMBER")
	private String atmPinNumber;

	@Column(name="BANK_ADDRESS")
	private String bankAddress;

	@Column(name="BANK_COUNTRY")
	private String bankCountry;

	@Column(name="BANK_EMAILID")
	private String bankEmailid;

	@Column(name="BANK_NAME")
	private String bankName;

	@Column(name="BANK_PHONE")
	private String bankPhone;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="CREATED_PERSON")
	private String createdPerson;

	@Column(name="DEBIT_CARD_ATM_PIN")
	private String debitCardAtmPin;

	@Column(name="DEBIT_CARD_NUMBER")
	private String debitCardNumber;

	@Column(name="DEBIT_CARD_TYPE")
	private String debitCardType;

	@Column(name="NETBANKING_PASSWORD")
	private String netbankingPassword;

	@Column(name="NETBANKING_USERNAME")
	private String netbankingUsername;

	private String status;

	@Column(name="UPDATED_PERSON")
	private String updatedPerson;

	@Temporal(TemporalType.DATE)
	@Column(name="UPDATES_DATE")
	private Date updatesDate;
	
	@Column(name="NOTES")
	private String notes;
	
	/*
	 * //bi-directional many-to-one association to Transaction
	 * 
	 * @OneToMany(mappedBy="bank",orphanRemoval=true)
	 * 
	 * @JsonIgnore private List<Transaction> transactions;
	 */
	public Bank() {
	}

	public int getBankId() {
		return this.bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public String getAccountHolderName() {
		return this.accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAtmPinNumber() {
		return this.atmPinNumber;
	}

	public void setAtmPinNumber(String atmPinNumber) {
		this.atmPinNumber = atmPinNumber;
	}

	public String getBankAddress() {
		return this.bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getBankCountry() {
		return this.bankCountry;
	}

	public void setBankCountry(String bankCountry) {
		this.bankCountry = bankCountry;
	}

	public String getBankEmailid() {
		return this.bankEmailid;
	}

	public void setBankEmailid(String bankEmailid) {
		this.bankEmailid = bankEmailid;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankPhone() {
		return this.bankPhone;
	}

	public void setBankPhone(String bankPhone) {
		this.bankPhone = bankPhone;
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

	public String getDebitCardAtmPin() {
		return this.debitCardAtmPin;
	}

	public void setDebitCardAtmPin(String debitCardAtmPin) {
		this.debitCardAtmPin = debitCardAtmPin;
	}

	public String getDebitCardNumber() {
		return this.debitCardNumber;
	}

	public void setDebitCardNumber(String debitCardNumber) {
		this.debitCardNumber = debitCardNumber;
	}

	public String getDebitCardType() {
		return this.debitCardType;
	}

	public void setDebitCardType(String debitCardType) {
		this.debitCardType = debitCardType;
	}

	public String getNetbankingPassword() {
		return this.netbankingPassword;
	}

	public void setNetbankingPassword(String netbankingPassword) {
		this.netbankingPassword = netbankingPassword;
	}

	public String getNetbankingUsername() {
		return this.netbankingUsername;
	}

	public void setNetbankingUsername(String netbankingUsername) {
		this.netbankingUsername = netbankingUsername;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdatedPerson() {
		return this.updatedPerson;
	}

	public void setUpdatedPerson(String updatedPerson) {
		this.updatedPerson = updatedPerson;
	}

	public Date getUpdatesDate() {
		return this.updatesDate;
	}

	public void setUpdatesDate(Date updatesDate) {
		this.updatesDate = updatesDate;
	}

	
	
	
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	/*
	 * public List<Transaction> getTransactions() { return this.transactions; }
	 * 
	 * public void setTransactions(List<Transaction> transactions) {
	 * this.transactions = transactions; }
	 */

	/*
	 * public Transaction addTransaction(Transaction transaction) {
	 * getTransactions().add(transaction); transaction.setBank(this);
	 * 
	 * return transaction; }
	 * 
	 * public Transaction removeTransaction(Transaction transaction) {
	 * getTransactions().remove(transaction); transaction.setBank(null);
	 * 
	 * return transaction; }
	 */

}