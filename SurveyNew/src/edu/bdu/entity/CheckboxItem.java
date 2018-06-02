package edu.bdu.entity;
/**
 * 制作日期：2011年8月18日
 * 版本1.0
 * @author lenov
 *
 */

public class CheckboxItem {
	
	private int checkboxID;//调查项多选答案项编号
	
	private int checkboxOwnerID;//多选答案项所属调查项编号
	
	private int checkboxIndex;//多选答案项在所属调查项中的顺序
	
	private String checkboxCaption;//多选答案项标题
	
	private int defaultSelected;//默认是否当前选中项
	
	private int selectCount;//多选答案项点击次数

	//////////////////多选答案项编号////////////////////////
	public int getCheckboxID() {
		return checkboxID;
	}

	public void setCheckboxID(int checkboxID) {
		this.checkboxID = checkboxID;
	}

	/////////////////多选答案项所属调查项编号//////////////////
	public int getCheckboxOwnerID() {
		return checkboxOwnerID;
	}

	public void setCheckboxOwnerID(int checkboxOwnerID) {
		this.checkboxOwnerID = checkboxOwnerID;
	}

	////////////////多选答案项所属调查中的顺序编号///////////////
	public int getCheckboxIndex() {
		return checkboxIndex;
	}

	public void setCheckboxIndex(int checkboxIndex) {
		this.checkboxIndex = checkboxIndex;
	}

	///////////////////多选答案项标题////////////////////////////
	public String getCheckboxCaption() {
		return checkboxCaption;
	}

	public void setCheckboxCaption(String checkboxCaption) {
		this.checkboxCaption = checkboxCaption;
	}

	/////////////////////多选答案项是否默认选中//////////////////////
	public int getDefaultSelected() {
		return defaultSelected;
	}

	public void setDefaultSelected(int defaultSelected) {
		this.defaultSelected = defaultSelected;
	}

	////////////////////多选答案项点击次数/////////////////////////
	public int getSelectCount() {
		return selectCount;
	}

	public void setSelectCount(int selectCount) {
		this.selectCount = selectCount;
	}
	
	

}
