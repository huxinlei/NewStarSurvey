<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加用户</title>
    
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
      
      out.println("<form name='myform' action='addaction_u.jsp' method='post'>");
        
        // out.println("<p>用户编号：<input type = 'text' name = 'userid'  value = '" + "'></p>");
        
        out.println("<p>用户名称：<input type = 'text' name = 'username' value = '"  + "'></p>");
        
        out.println("<p>用户密码：<input type = 'password' name = 'userpassword' value = '" + "'></p>");
        
        //String userType1 = user1.getUserType()==1?"系统管理员":"普通管理员";
        
        out.println("<p>用户类型：<input type = 'text' name = 'usertype' readonly='true' value = '普通管理员'></p>");
        
        
        out.println("<p><input type='submit'  name='subButton' value='添加'>"
        +
        "&nbsp;<input type='reset' name='resButton' value='重置'></p>");
        
        
        out.println("</form>");
    %>
  </body>
</html>
