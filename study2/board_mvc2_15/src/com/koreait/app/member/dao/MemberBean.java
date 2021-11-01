package com.koreait.app.member.dao;

public class MemberBean {
	private String userid;
	private String userpw;
	private String username;
	private String usergender;
	private String userphone;
	private String userpostcode;
	private String useraddr;
	private String useraddrdetail;
	private String useraddretc;
	
	public String getUserid() {
		return userid;
	}
	public String getUserpostcode() {
		return userpostcode;
	}
	public void setUserpostcode(String userpostcode) {
		this.userpostcode = userpostcode;
	}
	public String getUseraddr() {
		return useraddr;
	}
	public void setUseraddr(String useraddr) {
		this.useraddr = useraddr;
	}
	public String getUseraddrdetail() {
		return useraddrdetail;
	}
	public void setUseraddrdetail(String useraddrdetail) {
		this.useraddrdetail = useraddrdetail;
	}
	public String getUseraddretc() {
		return useraddretc;
	}
	public void setUseraddretc(String useraddretc) {
		this.useraddretc = useraddretc;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsergender() {
		return usergender;
	}
	public void setUsergender(String usergender) {
		this.usergender = usergender;
	}
	public String getUserphone() {
		return userphone;
	}
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
}
