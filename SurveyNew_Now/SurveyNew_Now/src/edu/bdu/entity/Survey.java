package edu.bdu.entity;

/**
 * 制作日期：2011年8月18日
 * 版本1.0
 * @author lenov
 *
 */

import java.util.Date;

public class Survey {
	
	private int surveyID;//调查问卷编号
	
	private String surveyTitle;//调查问卷标题
	
	private int surveyOwnerID;//调查问卷创建者编号
	
	private String surveyLink;//调查问卷说明连接设置项
	
	private Date surveyCreateDate;//调查问卷创建时间
	
	private String surveyExpirationDate;//调查问卷过期时间设置//ExpirationDate
	
	

	///////////////////////////调查问卷id/////////////////////////////////
	public int getSurveyID() {
		return surveyID;
	}

	public void setSurveyID(int surveyID) {
		this.surveyID = surveyID;
	}
   
	/////////////////////////调查问卷标题/////////////////////////////////
	public String getSurveyTitle() {
		return surveyTitle;
	}

	public void setSurveyTitle(String surveyTitle) {
		this.surveyTitle = surveyTitle;
	}

	///////////////////////调查问卷创建id////////////////////////////////////
	public int getSurveyOwnerID() {
		return surveyOwnerID;
	}

	public void setSurveyOwnerID(int surveyOwnerID) {
		this.surveyOwnerID = surveyOwnerID;
	}
  
	/////////////////////调查问卷介绍连接///////////////////////////////////
	public String getSurveyLink() {
		return surveyLink;
	}

	public void setSurveyLink(String surveyLink) {
		this.surveyLink = surveyLink;
	}

	////////////////////调查问卷创建时间////////////////////////////////////
	public Date getSurveyCreateDate() {
		return surveyCreateDate;
	}

	public void setSurveyCreateDate(Date surveyCreateDate) {
		this.surveyCreateDate = surveyCreateDate;
	}

	////////////////////调查问卷过期时间设置///////////////////////////////
	public String getSurveyExpirationDate() {
		return surveyExpirationDate;
	}

	public void setSurveyExpirationDate(String surveyExpirationDate) {
		this.surveyExpirationDate = surveyExpirationDate;
	}
	
	

}
