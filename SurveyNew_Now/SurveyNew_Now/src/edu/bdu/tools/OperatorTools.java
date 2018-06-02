package edu.bdu.tools;

public class OperatorTools {
	
	//获得指定大小带换行符的字符串
    public String getNewString(String oldString,int size)
    {
    	String newString = "";
    	
    	for(int i = 0; i < oldString.length(); i = i + size)
    	{
    	  int endI = i + size - 1;
    	  
    	  if(endI >= oldString.length())
    	  {
    		  endI = oldString.length();
    	  }
    	  
    	  newString += oldString.substring(i,endI);	
    	  
    	  newString += "<br>";//添加换行
    	}
    	
    	return newString;
    }
    
    //去除特殊字符
    public String getStringReplace(String oldString)
    {
    	return oldString.replaceAll("[\\t\\n\\r]","");
    }

}
