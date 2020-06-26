package com.mynrg.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ID")
	private int userId;

	@Temporal(TemporalType.DATE)
	private Date created_Date;

	private String created_person;

	private String employee_detail;

	@Column(name="FORGOTPASSWORD_COUNT")
	private String forgotpasswordCount;

	@Column(name="OTP_PASSWORD")
	private String otpPassword;

	@Temporal(TemporalType.DATE)
	private Date updated_Date;

	private String updated_person;

	@Column(name="USER_ADDRESS")
	private String userAddress;

	@Column(name="USER_DEPARTMENT")
	private String userDepartment;

	@Column(name="USER_EMAILID")
	private String userEmailid;

	@Column(name="USER_NAME")
	private String userName;

	@Column(name="USER_PASSWORD")
	private String userPassword;

	@Column(name="USER_PHONE_NO")
	private String userPhoneNo;

	@Column(name="USER_STATUS")
	private String userStatus;

	@Column(name="USER_TYPE")
	private String userType;

	//bi-directional many-to-one association to CustomerDetail
	@OneToMany(mappedBy="user")
	@JsonIgnoreProperties
	private List<CustomerDetail> customerDetails;

	//bi-directional many-to-one association to ProductionIssue
	@OneToMany(mappedBy="user")
	@JsonIgnoreProperties
	private List<ProductionIssue> productionIssues;

	//bi-directional many-to-one association to Task
	@OneToMany(mappedBy="user")
	@JsonIgnoreProperties
	private List<Task> tasks;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="user")
	@JsonIgnoreProperties
	private List<UserRole> userRoles;

	public User() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getEmployee_detail() {
		return this.employee_detail;
	}

	public void setEmployee_detail(String employee_detail) {
		this.employee_detail = employee_detail;
	}

	public String getForgotpasswordCount() {
		return this.forgotpasswordCount;
	}

	public void setForgotpasswordCount(String forgotpasswordCount) {
		this.forgotpasswordCount = forgotpasswordCount;
	}

	public String getOtpPassword() {
		return this.otpPassword;
	}

	public void setOtpPassword(String otpPassword) {
		this.otpPassword = otpPassword;
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

	public String getUserAddress() {
		return this.userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserDepartment() {
		return this.userDepartment;
	}

	public void setUserDepartment(String userDepartment) {
		this.userDepartment = userDepartment;
	}

	public String getUserEmailid() {
		return this.userEmailid;
	}

	public void setUserEmailid(String userEmailid) {
		this.userEmailid = userEmailid;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPhoneNo() {
		return this.userPhoneNo;
	}

	public void setUserPhoneNo(String userPhoneNo) {
		this.userPhoneNo = userPhoneNo;
	}

	public String getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public List<CustomerDetail> getCustomerDetails() {
		return this.customerDetails;
	}

	public void setCustomerDetails(List<CustomerDetail> customerDetails) {
		this.customerDetails = customerDetails;
	}

	public CustomerDetail addCustomerDetail(CustomerDetail customerDetail) {
		getCustomerDetails().add(customerDetail);
		customerDetail.setUser(this);

		return customerDetail;
	}

	public CustomerDetail removeCustomerDetail(CustomerDetail customerDetail) {
		getCustomerDetails().remove(customerDetail);
		customerDetail.setUser(null);

		return customerDetail;
	}

	public List<ProductionIssue> getProductionIssues() {
		return this.productionIssues;
	}

	public void setProductionIssues(List<ProductionIssue> productionIssues) {
		this.productionIssues = productionIssues;
	}

	public ProductionIssue addProductionIssue(ProductionIssue productionIssue) {
		getProductionIssues().add(productionIssue);
		productionIssue.setUser(this);

		return productionIssue;
	}

	public ProductionIssue removeProductionIssue(ProductionIssue productionIssue) {
		getProductionIssues().remove(productionIssue);
		productionIssue.setUser(null);

		return productionIssue;
	}

	public List<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Task addTask(Task task) {
		getTasks().add(task);
		task.setUser(this);

		return task;
	}

	public Task removeTask(Task task) {
		getTasks().remove(task);
		task.setUser(null);

		return task;
	}

	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addUserRole(UserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setUser(this);

		return userRole;
	}

	public UserRole removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setUser(null);

		return userRole;
	}

}