package edu.bdu.entity;

/**
 * 制作日期：2011年8月18日
 * 版本1.0
 * @author lenov
 *
 */
public class RadioItem {
	
	private int radioID;//调查项答案项编号 
	
	private int radioOwnerID;//调查项答案项所属调查项id
	
	private int radioIndex;//调查项答案项在答案项中的顺序
	
	private String radioCaption;//调查项答案项标题
	
	private int defaultSelected;//默认是否选中
	
	private int selectCount;//点击次数记录

	///////////////////////调查项答案项编号/////////////////////////////
	public int getRadioID() {
		return radioID;
	}

	public void setRadioID(int radioID) {
		this.radioID = radioID;
	}

	/////////////////////////调查项答案项所属调查项的编号/////////////////
	public int getRadioOwnerID() {
		return radioOwnerID;
	}

	public void setRadioOwnerID(int radioOwnerID) {
		this.radioOwnerID = radioOwnerID;
	}

	/////////////////////答案项在此调查项中的顺序////////////////////////
	public int getRadioIndex() {
		return radioIndex;
	}

	public void setRadioIndex(int radioIndex) {
		this.radioIndex = radioIndex;
	}

	//////////////////////答案项的标题/////////////////////////////
	public String getRadioCaption() {
		return radioCaption;
	}

	public void setRadioCaption(String radioCaption) {
		this.radioCaption = radioCaption;
	}

	////////////////////默认是否选中////////////////////////////////////
	public int getDefaultSelected() {
		return defaultSelected;
	}

	public void setDefaultSelected(int defaultSelected) {
		this.defaultSelected = defaultSelected;
	}

	///////////////////点击次数///////////////////////////////////////
	public int getSelectCount() {
		return selectCount;
	}

	public void setSelectCount(int selectCount) {
		this.selectCount = selectCount;
	}
	
	
	
	

}
