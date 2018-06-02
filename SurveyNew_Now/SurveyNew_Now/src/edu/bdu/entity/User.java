package edu.bdu.entity;

/**
 * 制作日期：2011年8月18日
 * 版本1.0
 * @author lenov
 *
 */
public class User {
	
	private int userID;//用户标号
	
	private String userName;//用户名称
	
	private String userPassword;//用户密码
	
	private int userType;//用户类型代号

	///////////////////userid////////////////////////////////////////////
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
//////////////////////username////////////////////////////////////////
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	////////////////////////userpassword///////////////////////////////
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	////////////////////////usertype////////////////////////////////////////
	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}
	

}
