package com.erp.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Submenu {

	@Id
	private String id;
	String menucode;
	String submenucode;
	String submenuname;
	String displayOrder;
	String langcode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMenucode() {
		return menucode;
	}

	public void setMenucode(String menucode) {
		this.menucode = menucode;
	}

	public String getSubmenucode() {
		return submenucode;
	}

	public void setSubmenucode(String submenucode) {
		this.submenucode = submenucode;
	}

	public String getSubmenuname() {
		return submenuname;
	}

	public void setSubmenuname(String submenuname) {
		this.submenuname = submenuname;
	}

	public String getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getLangcode() {
		return langcode;
	}

	public void setLangcode(String langcode) {
		this.langcode = langcode;
	}

		

}
