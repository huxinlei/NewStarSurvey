package edu.bdu.entity;

/**
 * 制作日期：2011年8月18日
 * 版本1.0
 * @author lenov
 *
 */
public class TextItem {

	private int textID;//文字答案项编号
	
	private int textOwnerID;//文字答案项所属调查项编号
	
	private String textCaption;//文字答案项标题
	
	private String textContent;//文字答案项内容
	
	

	///////////////////////文字答案项编号///////////////////////////
	public int getTextID() {
		return textID;
	}

	public void setTextID(int textID) {
		this.textID = textID;
	}

	////////////////////文字答案项所属调查项的编号//////////////////
	public int getTextOwnerID() {
		return textOwnerID;
	}

	public void setTextOwnerID(int textOwnerID) {
		this.textOwnerID = textOwnerID;
	}

	////////////////////文字答案项标题///////////////////////////
	public String getTextCaption() {
		return textCaption;
	}

	public void setTextCaption(String textCaption) {
		this.textCaption = textCaption;
	}

	////////////////////文字答案项内容///////////////////////////
	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	
}
