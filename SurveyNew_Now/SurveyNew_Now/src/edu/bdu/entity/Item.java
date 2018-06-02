package edu.bdu.entity;
/**
 * 制作日期：2011年8月18日
 * 版本1.0
 * @author lenov
 *
 */
public class Item {
	
	private int itemID;//调查项编号
	
	private String itemCaption;//调查项标题
	
	private int itemType;//调查项类型、单选、多选、文字问答，当前阶段未用
	
	private int itemAttribute;//调查项属性、公开、私有，现阶段未用、默认为私有
	
	private int itemOwnerID;//调查项创建者编号,当前阶段未用
	
	private int itemOwnerSurveyID;//调查项所属调查问卷
	
	private int radioCount;//调查项点击次数，此阶段未用
	
	
	

	//////////////////调查项编号////////////////////////////
	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	////////////////调查项标题///////////////////////////////
	public String getItemCaption() {
		return itemCaption;
	}

	public void setItemCaption(String itemCaption) {
		this.itemCaption = itemCaption;
	}

	//////////////////调查项类型/////////////////////////////
	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	///////////////////调查项属性标记/////////////////////////
	public int getItemAttribute() {
		return itemAttribute;
	}

	public void setItemAttribute(int itemAttribute) {
		this.itemAttribute = itemAttribute;
	}

	////////////////////调查项创建者编号///////////////////////
	public int getItemOwnerID() {
		return itemOwnerID;
	}

	public void setItemOwerID(int itemOwnerID) {
		this.itemOwnerID = itemOwnerID;
	}

	///////////////////调查项所属调查问卷id////////////////////
	public int getItemOwnerSurveyID() {
		return itemOwnerSurveyID;
	}

	public void setItemOwnerSurveyID(int itemOwnerSurveyID) {
		this.itemOwnerSurveyID = itemOwnerSurveyID;
	}
 
	//////////////////调查项点击次数///////////////////////////
	public int getRadioCount() {
		return radioCount;
	}

	public void setRadioCount(int radioCount) {
		this.radioCount = radioCount;
	}

}
