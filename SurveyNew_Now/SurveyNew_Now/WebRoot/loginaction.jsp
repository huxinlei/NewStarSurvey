<%@ page language="java" import="java.util.*,edu.bdu.dao.UserDaoImpl,edu.bdu.entity.User" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户验证执行页</title>
    
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
  request.setCharacterEncoding("UTF-8");
  
  String checkCode = "";//验证
  
  if(request.getParameter("checkcode")!= null)
  {
      checkCode = request.getParameter("checkcode");//验证码获取
  }
  
  if(!checkCode.equals("")&&checkCode.equals(session.getAttribute("randCheckCode"))){
  
       User user = null;//用户默认为空值
  
       if(request.getParameter("username")!= null)
       {
           user = new User();//用户实例化
     
           user.setUserName(request.getParameter("username"));//用户名赋值  
       }
  
       if(user != null && request.getParameter("userpassword") != null)
       {
          user.setUserPassword(request.getParameter("userpassword"));//用户密码赋值
          
          UserDaoImpl userOper = new UserDaoImpl();//获得用户数据库类
          
          if((user=userOper.loginCheck(user)) != null)
          {
             
             session.setAttribute("user",user);//将用户信息放入session
             
             if(user.getUserType() == 1)//超级管理
             {
               response.sendRedirect("admin/index.html");//跳转到超级管理员界面       
             }
             else
             {
               response.sendRedirect("admin/index_user.html");//普通管理员首页
             }
             

          }
          else
          {
            response.sendRedirect("login.jsp?errorCode=1");//跳转到登录页，提示错误代码为1
            //out.println("用户名或密码输入错误！");
            //out.println("<a href='login.jsp'>重新登录</a>");
          }
         
       }
       
  }
  else
  {
  
   response.sendRedirect("login.jsp?errorCode=2");//跳转到登录页，提示错误代码为2
   //out.println("验证码不正确！");
   //out.println("<a href='login.jsp'>重新登录</a>");
  }
   %>
   
  </body>
</html>
