<%@ page language="java" import="java.util.*,edu.bdu.dao.UserDaoImpl,edu.bdu.entity.User" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户登录验证</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <script language="javascript">
 
  </script>
  </head>
  
  <body>
  
    <form name='myform' action='loginaction.jsp' method='post'>
        
       
      <p>用户名称：<input type = 'text' name = 'username' value = ''></p>
        
      <p>用户密码：<input type = 'password' name = 'userpassword' value = ''></p>
        
      <p>验证码:<input type='text' name='checkcode' value=''> 
      <iframe name="codeframe" src= "getPictureCheckCode.jsp"　width="200" height="60" frameborder="0"></iframe></p>
        
        <p><a href="getPictureCheckCode.jsp" target="codeframe">看不清，换一张</a></p>
       <p><input type='submit'  name='subButton' value='提交'>
      
       &nbsp;<input type='reset' name='resButton' value='重置'></p>
        
        
      </form>
  </body>
</html>
