<%@ page language="java" import="java.util.*,edu.bdu.dao.UserDaoImpl,edu.bdu.entity.User" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>测试用户数据库操作类</title>
    
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
     UserDaoImpl userop = new UserDaoImpl();//用户数据库操作类
     
     List userlist = userop.getUserList(null);//获得用户列表
     
     out.println("<table color='red' border='1'>");
     out.println("<tr><td><a href='adduser.jsp'>添加用户</a></td><tr>");
     out.println("<tr><td><a href='search_u.jsp'>查找用户</a></td><tr>");
     out.println("</table>");
     out.println("<table color = 'red' border='1'>");
     out.println("<tr>");
     out.println("<td width = '100'>用户编号</td>");
     out.println("<td width = '200'>用户名称</td>");
     out.println("<td width = '200'>用户密码</td>");
     out.println("<td width = '200'>用户类型</td>");
     out.println("<td width = '100'>用户操作</td>");
     out.println("<td width = '100'>用户操作</td>");
     out.println("<td width = '100'>用户操作</td>");
     out.println("</tr>");
     for(int i = 0;i < userlist.size();i++)
     {
       User user = (User)userlist.get(i);//获得用户信息
       
       out.println("<tr>");
       
       out.println("<td>" + user.getUserID() + "</td>");
       
       out.println("<td>" + user.getUserName() + "</td>");
       
       out.println("<td>" + user.getUserPassword() + "</td>");
       
       String userType = user.getUserType()==1?"系统管理员":"普通管理员";
       
       out.println("<td>" + userType + "</td>");
       out.println("<td>" + "<a href='useroperator.jsp?comm=see&userId=" + user.getUserID() + "'>查看</a>" + "</td>");
       out.println("<td>" + "<a href='useroperator.jsp?comm=upd&userId=" + user.getUserID() + "'>修改</a>" + "</td>");
       out.println("<td>" + "<a href='useroperator.jsp?comm=del&userId=" + user.getUserID() + "'>删除</a>" + "</td>");
       
       out.println("</tr>");
       
     }
     out.println("</table>");
     
     out.println("<a href='index.jsp'>总管理页面</a>");
     
    %>
  </body>
</html>
