package com.mynrg.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_role database table.
 * 
 */
@Entity
@Table(name="user_role")
@NamedQuery(name="UserRole.findAll", query="SELECT u FROM UserRole u")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ROLE_ID")
	private int userRoleId;

	private String status;

	@Column(name="USER_MENU")
	private String userMenu;

	@Column(name="USER_MENU_PAGES")
	private String userMenuPages;

	@Column(name="USER_SUBMENU")
	private String userSubmenu;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="ROLE_ID")
	private Role role;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;

	public UserRole() {
	}

	public int getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserMenu() {
		return this.userMenu;
	}

	public void setUserMenu(String userMenu) {
		this.userMenu = userMenu;
	}

	public String getUserMenuPages() {
		return this.userMenuPages;
	}

	public void setUserMenuPages(String userMenuPages) {
		this.userMenuPages = userMenuPages;
	}

	public String getUserSubmenu() {
		return this.userSubmenu;
	}

	public void setUserSubmenu(String userSubmenu) {
		this.userSubmenu = userSubmenu;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}