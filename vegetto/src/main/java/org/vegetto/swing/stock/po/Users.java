package org.vegetto.swing.stock.po;

public class Users {
	private int userid;
	private String username;
	private String password;
	private int usertype;
	public Users() {
	}
	public Users(int userid, String username, String password, int usertype) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.usertype = usertype;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
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
	public int getUsertype() {
		return usertype;
	}
	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}

}
