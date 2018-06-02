<%@ page language="java" import="java.util.*,edu.bdu.dao.*,edu.bdu.entity.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>调查项修改页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <%
 
    if(request.getParameter("itemid") != null)
    {
      int itemid = Integer.parseInt(request.getParameter("itemid"));//获得调查项编号
      
      ItemDaoImpl itemOp = new ItemDaoImpl();//调查项操作类
      
      Item item = itemOp.getItemByItemId(itemid);//根据调查项编号查询调查项
      
      if(item != null)
      {
        String itemType = "";
        switch(item.getItemType())
        {
           case 0:
                itemType = "单选项";
                break;
           case 1:
                itemType = "多选项";
                break;
           case 2:
                itemType = "文字项";
                break;
                
        }
        out.println("<p>");
       
        out.println("问题类型：" + itemType);//调查项问题
        
        out.println("</p>");
        
        out.println("<form name='itemform' action='upaction_i.jsp' method='post'>");
        
        out.println("<p>");
       
        out.println("<input type = 'hidden' name = 'itemid' value = '" + item.getItemID() + "'>");//调查项编号
        
        out.println("问题题目：<input type = 'text' name = 'itemCaption' value = '" + item.getItemCaption() + "'>");//调查项问题
        
        out.println("<input type='submit' name='subButton' value='保存'>&nbsp;"
        +"<input type='reset' name='resButton' value='重置'>");
        
        out.println("</p>");
        
        out.println("</form>");
        
        List answers = null;//调查项答案项列表
        
        switch(item.getItemType())
        {
           case 0:
              RadioItemDaoImpl radioOp = new RadioItemDaoImpl();//获得单选项操作类
              
              answers = radioOp.getRadioList(item.getItemID());//根据调查项编号查询答案列表
              
              out.println("<a href='addanswer.jsp?itemid=" + item.getItemID() + 
              "&itemtype=" + item.getItemType() + "'>添加答案项</a>");
              
              out.println("<table border='1'>");
              for(int i = 0; i < answers.size(); i++)
              {
               RadioItem answer = (RadioItem)answers.get(i);//获得答案项实例
               out.println("<tr>");
               out.println("<td>" + answer.getRadioID() + "</td>");
               out.println("<td>" + answer.getRadioCaption() + "</td>");
               out.println("<td>" + answer.getRadioIndex() + "</td>");
               out.println("<td>" + answer.getRadioOwnerID() + "</td>");
               out.println("<td>" + answer.getSelectCount() + "</td>");
               out.println("<td>" + answer.getDefaultSelected() + "</td>");
               out.println("<td><a href='updateradio.jsp?radioid=" + answer.getRadioID() + "'>修改</a></td>");
               out.println("</tr>");
              }
              out.println("</table>");
              
              break;
           case 1:
           
              CheckboxItemDaoImpl checkOp = new CheckboxItemDaoImpl();//获得多选项操作类
              
              answers = checkOp.getCheckboxList(item.getItemID());//根据调查项编号查询答案列表
              
              out.println("<a href='addanswer.jsp?itemid=" + item.getItemID() + 
              "&itemtype=" + item.getItemType() + "'>添加答案项</a>");
              
                            
              out.println("<table border='1'>");
              for(int i = 0; i < answers.size(); i++)
              {
               CheckboxItem answer = (CheckboxItem)answers.get(i);//获得答案项实例
               out.println("<tr>");
               out.println("<td>" + answer.getCheckboxID() + "</td>");
               out.println("<td>" + answer.getCheckboxCaption() + "</td>");
               out.println("<td>" + answer.getCheckboxIndex() + "</td>");
               out.println("<td>" + answer.getCheckboxOwnerID() + "</td>");
               out.println("<td>" + answer.getSelectCount() + "</td>");
               out.println("<td>" + answer.getDefaultSelected() + "</td>");
               out.println("<td><a href='updatecheck.jsp?checkid=" + answer.getCheckboxID() + "'>修改</a></td>");
               out.println("</tr>");
              }
              out.println("</table>");
              
              
              break;
           
           case 2:
           
              out.println("<a href='textlist.jsp?itemid=" + itemid + "'>查看回答列表</a>");//根据调查项编号查看文字项回答列表
              
              break;
        }
        
        
      }
      else
      {
        out.println("此调查项不存在，请检查！");
      }
      
      
      
    }
    else
    {
      out.println("没有调查项编号，请检查！");
    }
   %>
  
   
  </body>
</html>
