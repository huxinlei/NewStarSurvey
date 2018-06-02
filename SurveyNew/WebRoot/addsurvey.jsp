<%@ page language="java" import="java.util.*,edu.bdu.entity.User" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>调查问卷添加页</title>
    
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
        User user = null;
  
        if(session.getAttribute("user") != null)
         user = (User)session.getAttribute("user");//获得当前用户的引用
         
         if(user != null)
         {
             out.println("<form name='myform' action='addaction_s.jsp' method='post'>");
        
             //out.println("<p>调查问卷编号：<input type = 'text' name = 'surveyid' readonly='true' value = '" +  "'></p>");
        
             out.println("<p>调查问卷标题：<input type = 'text' name = 'surveytitle' value = '" + "'></p>");
        
             out.println("<p>调查问卷说明连接：<input type = 'text' name = 'surveylink' value = '" + "'></p>");
        
            // out.println("<p>调查问卷创建者编号：<input type = 'hidden' name = 'surveyownerid' readonly='true' value = '" + user.getUserID() + "'></p>");
        
             out.println("<p>过期时间：<input type = 'text' name = 'surveyexpiration' value = '"  + "'></p>");
        
      
        
               out.println("<p><input type='submit'  name='subButton' value='提交'>"
                 +
                 "&nbsp;<input type='reset' name='resButton' value='重置'></p>");
        
        
               out.println("</form>");
               
             
        }
        else
        {
              out.println("<a href='login.jsp' >请先登录！</a>");
              
             // out.println("<p><a href='testserveydao.jsp'>返回调查问卷列表</a></p>");
        }
        
          out.println("<p><a href='testserveydao.jsp'>返回调查问卷列表</a></p>");//返回调查问卷列表
    %>
  </body>
</html>
