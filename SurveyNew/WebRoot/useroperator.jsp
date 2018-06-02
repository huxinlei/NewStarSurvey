<%@ page language="java" import="java.util.*,edu.bdu.dao.UserDaoImpl,edu.bdu.entity.User" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户操作页面</title>
    
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
     String comm;//命令类型
     
     int commID = 0;//命令代号默认为零，没有任何操作
     
     int userId ;//用户id
     
     if(request.getParameter("comm") != null && request.getParameter("userId") != null)
     {
       comm = request.getParameter("comm");//获得操作类型
     
       userId = Integer.parseInt(request.getParameter("userId"));//获得用户id
     
       commID = 1;//命令代号为1，表示存在操作，具体操作还不知道
     }
     else
     {
       comm = null;//命令为空
       
       commID = 0;//命令不存在
       
       userId = 0;//用户id为零
     }
     
     if(commID != 0)
     {
      
       if(comm.equals("see"))
       {
          commID = 2;//查看用户的详细信息
       }
       
       if(comm.equals("upd"))
       {
          commID = 3;//删除用户信息
       }
       
       if(comm.equals("del"))
       {
          commID = 4;//更新用户信息
       }
       
      
     }
     
     switch(commID)
     {
       case 0:
           out.println("<font color = 'red'>您的访问存在问题，没有出现任何命令!</font>");
           
           break;
           
       case 1:
           out.println("<font color = 'red'>您的命令出现了问题，请检查！</font>");
           break;
           
           
      case 2:
      
        UserDaoImpl userOper = new UserDaoImpl();//操作类
        
        User user = userOper.getUser(userId);//根据用户编号查找用户
        
        out.println("<p>用户编号：" + user.getUserID() + "</p>");
        
        out.println("<p>用户名称：" + user.getUserName() + "</p>");
        
        out.println("<p>用户密码：" + user.getUserPassword() + "</p>");
        
        String userType = user.getUserType()==1?"系统管理员":"普通管理员";
        
        out.println("<p>用户类型：" + userType + "</p>");
        
        out.println("<a href='testuserdao.jsp'>返回用户列表</a>");//返回用户列表
           
        break;
        
       case 3:
       
        UserDaoImpl userOper1 = new UserDaoImpl();//操作类
        
        User user1 = userOper1.getUser(userId);//根据用户编号查找用户
        
        out.println("<form name='myform' action='updateuser.jsp' method='post'>");
        
         out.println("<p>用户编号：<input type = 'text' name = 'userid' readonly='true' value = '" + user1.getUserID() + "'></p>");
        
        out.println("<p>用户名称：<input type = 'text' name = 'username' value = '" + user1.getUserName() + "'></p>");
        
        out.println("<p>用户密码：<input type = 'text' name = 'userpassword' value = '" + user1.getUserPassword() + "'></p>");
        
        String userType1 = user1.getUserType()==1?"系统管理员":"普通管理员";
        
        out.println("<p>用户类型：<input type = 'text' name = 'usertype' readonly='true' value = '" + userType1 + "'></p>");
        
        
        out.println("<p><input type='submit'  name='subButton' value='提交'>"
        +
        "&nbsp;<input type='reset' name='resButton' value='重置'></p>");
        
        
        out.println("</form>");
           
        break;
        
        case 4:
        
          UserDaoImpl userOper2 = new UserDaoImpl();//用户操作类
          
          if(userOper2.deleteUser(userId))
          {
            response.sendRedirect("testuserdao.jsp");
          }
          else
          {
            out.println("<font color = 'red'>删除出现问题，请检查！</font>");
          }
           
     }
     
     
     
     
    %>
  </body>
</html>
