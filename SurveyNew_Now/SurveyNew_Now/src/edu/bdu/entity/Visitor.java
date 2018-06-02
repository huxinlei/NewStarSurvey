package edu.bdu.entity;

import java.util.Date;

/**
 * 制作日期：2011年8月18日
 * 版本1.0
 * @author lenov
 *
 */

public class Visitor {
	
	private String visitorIP;//调查问卷访问者ip地址
	
	private Date visiteDateTime;//调查问卷访问者访问时间
	
	private int visitorNumber;//调查问卷访问者总编号、主健唯一标志 
	
	private int visiteSurveyID;//调查问卷访问者访问的调查问卷ID
	
	private String visiteSurveyData;//调查问卷访问者回答单选项、多选项问题的答案

	////////////////访问者ip//////////////////////////////////
	public String getVisitorIP() {
		return visitorIP;
	}

	public void setVisitorIP(String visitorIP) {
		this.visitorIP = visitorIP;
	}

	///////////////访问者访问时间////////////////////////////
	public Date getVisiteDateTime() {
		return visiteDateTime;
	}

	public void setVisiteDateTime(Date visiteDateTime) {
		this.visiteDateTime = visiteDateTime;
	}

	////////////////访问者编号/////////////////////////////
	public int getVisitorNumber() {
		return visitorNumber;
	}

	public void setVisitorNumber(int visitorNumber) {
		this.visitorNumber = visitorNumber;
	}

	/////////////////访问者访问的调查问卷id//////////////////
	public int getVisiteSurveyID() {
		return visiteSurveyID;
	}

	public void setVisiteSurveyID(int visiteSurveyID) {
		this.visiteSurveyID = visiteSurveyID;
	}

	/////////////////访问者回答单选、多选问题答案///////////////
	public String getVisiteSurveyData() {
		return visiteSurveyData;
	}

	public void setVisiteSurveyData(String visiteSurveyData) {
		this.visiteSurveyData = visiteSurveyData;
	}
	
	
	
	
	

}
