<%@ page language="java" import="java.util.*,edu.bdu.entity.User" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>创建调查项</title>
    
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
     if(session.getAttribute("user")!= null)
     {
       if(request.getParameter("surveyid")!=null)
       {
         int surveyid = Integer.parseInt(request.getParameter("surveyid"));//获得调查问卷编号
         
         User user = (User)session.getAttribute("user");//获得当前用户信息
         
         %>
         
         
         <form name="myform" action="addaction_i.jsp" method="post">
         <%
          out.println("<input type='hidden' name='surveyid' value='" + surveyid + "'>");//设置调查问卷编号
          out.println("<input type='hidden' name='ownerid' value='" + user.getUserID() + "'>");//设置调查创建者编号
          %>
          <p>
              问题题目：<input type="text" name="itemCaption" value="">
          </p>
          
          <p>
              问题类型：
            <select name='itemtype'>
              <option value='0'>单选项</option>
              <option value='1'>多选项</option>
              <option value='2'>文字项</option>
            </select>
          </p>
          
          <p>
            <input type="submit" name="addbutton" value="添加">
            &nbsp;
            <input type="reset" name="resbutton" value="清空">
          </p>
         </form>
         
         <% 
         
       }
       else
       {
         response.sendRedirect("testserveydao.jsp");//没有调查问卷编号则跳转到调查问卷列表
       }
      
     }
     else
     {
       response.sendRedirect("login.jsp");///登陆页面
     }
   
    %>
  </body>
</html>
