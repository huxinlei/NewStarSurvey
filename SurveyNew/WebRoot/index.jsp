<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新星调查问卷系统</title>
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

     <p>
      <font color='red'><a href = "login.jsp">新星调查问卷欢迎您！</a></font>
     </p>
     <p>
      <font color='red'><a href='testuserdao.jsp'>用户管理</a></font>
     </p>
     <p>
      <font color='red'><a href='testserveydao.jsp'>调查问卷管理</a></font>
     </p>
  
  </body>
</html>
