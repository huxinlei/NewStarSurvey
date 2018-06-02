<%@ page language="java" import="java.util.*,edu.bdu.dao.UserDaoImpl,edu.bdu.entity.User" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加用户执行代码</title>
    
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
    <div align="center"><%
       request.setCharacterEncoding("UTF-8");//设置编码格式为utf-8
       
       User user = new User();//创建用户对象
       
       UserDaoImpl userOp = new UserDaoImpl();//用户操作类
        
        request.setCharacterEncoding("UTF-8");//设置编码格式
        
       //用户编号自动生成，此程序此段代码未用
        if(request.getParameter("userid")!= null)
        {
           int userid = Integer.parseInt(request.getParameter("userid"));//获得用户编号
           
           user.setUserID(userid);//用户编号赋值
        }
        
        if(request.getParameter("username")!=null)
        {
            String username = request.getParameter("username");
            
            user.setUserName(username);//为用户名称赋值
        }
        if(userOp.getUserList(user).size()==0){
               if(request.getParameter("userpassword")!=null)
               {
                    String userpassword = request.getParameter("userpassword");
            
                    user.setUserPassword(userpassword);//为用户密码赋值 
               }
        
               if(request.getParameter("usertype")!= null)
               {
                    String usertype = request.getParameter("usertype");
           
                    int usertypeId = usertype.equals("系统管理员")?1:0;
           
                   user.setUserType(usertypeId);//为用户类型赋值
               }
        
        
        
              if(userOp.InsertUser(user))
              {
                   response.sendRedirect("testuserdao.jsp");
               }
               else
               {
                 out.println("<font color = 'red'>用户修改失败，请检查！</font>");
               }
        }
        else
        {
             out.println("此用户名已经存在，请重新添加不存在的用户！<br />");
             
             out.println("<a href='adduser.jsp'>重新添加</a>");
        }
     %></div>
  </body>
</html>
