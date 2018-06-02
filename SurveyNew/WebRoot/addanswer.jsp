<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
  request.setCharacterEncoding("UTF-8");//设置编码格式
  
  int itemid = -1;//当为-1时没有调查编号为空
  int itemType = -1;//当为-1时调查项类型为空
  String itemString = "";//调查型类型字符串
  
  if(request.getParameter("itemid") != null)
  {
    itemid = Integer.parseInt(request.getParameter("itemid"));//获得调查项编号
  }
  
  if(request.getParameter("itemtype") != null)
  {
    itemType = Integer.parseInt(request.getParameter("itemtype"));//获得调查项类型代号
    
    if(itemType == 0)
    {
      itemString = "添加单选答案";
    }
    else if(itemType == 1)
    {
      itemString = "添加多选答案";
    }
  }
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    
    
    <title><%=itemString %></title>
    
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
     if(itemid != -1 && itemType != -1)//调查项编号和调查项类型编号
     {
        if(itemType == 0)
        {
        %>
        
       <form name="myform" action="addradio.jsp" method="post">
         <p>
           <input type="hidden" name="radioindex" value="0">
           <input type="hidden" name="radioownerid" value="<%=itemid %>">
             选项内容：<input type="text" name="radiocaption" value="">
         </p>
         <p>
           <input type="submit" name="subButton" value="添加">
           &nbsp;
           <input type="reset" name="resButton" value="清空">
         </p>
       </form>
        
        <%
        }
        else if(itemType == 1)
        {
        %>
       <form name="myform" action="addcheck.jsp" method="post">
         <p>
           <input type="hidden" name="checkindex" value="0">
           <input type="hidden" name="checkownerid" value="<%=itemid %>">
             选项内容：<input type="text" name="checkcaption" value="">
         </p>
         <p>
           <input type="submit" name="subButton" value="添加">
           &nbsp;
           <input type="reset" name="resButton" value="清空">
         </p>
       </form>
        <%
        }
     }
     else
     {
       out.println("数据传递中出现异常，请检查!");//打印异常信息
     }
    %>
  </body>
</html>
