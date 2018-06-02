package edu.bdu.entity;

/**
 * 制作日期：2011年8月18日
 * 版本1.0
 * @author lenov
 *
 */
public class SurveyAssociateItem {
	
	private int surveyID;//调查问卷id
	
	private int itemID;//调查项id
	
	

	////////////////////调查问卷id/////////////////////
	public int getSurveyID() {
		return surveyID;
	}

	public void setSurveyID(int surveyID) {
		this.surveyID = surveyID;
	}

	//////////////////调查项id///////////////////////
	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	
	
	

}
