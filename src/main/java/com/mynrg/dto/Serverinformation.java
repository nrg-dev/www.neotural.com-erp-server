package com.mynrg.dto;

public final class Serverinformation {
	public String name;
	public String publichostname;
	public String privatehostname;
	public String dnsname;
	public String username;
	public String password;
	public String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPublichostname() {
		return publichostname;
	}
	public void setPublichostname(String publichostname) {
		this.publichostname = publichostname;
	}
	public String getPrivatehostname() {
		return privatehostname;
	}
	public void setPrivatehostname(String privatehostname) {
		this.privatehostname = privatehostname;
	}
	public String getDnsname() {
		return dnsname;
	}
	public void setDnsname(String dnsname) {
		this.dnsname = dnsname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
