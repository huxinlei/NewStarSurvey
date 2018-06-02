<%@ page language="java" import="java.util.*,edu.bdu.dao.TextItemDaoImpl" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>删除文字项</title>
    
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
     request.setCharacterEncoding("UTF-8");//设置编码格式
     
     if(request.getParameter("textid") != null)
     {
        int textid = Integer.parseInt(request.getParameter("textid"));//获得文字项编号
        
        TextItemDaoImpl textOp = new TextItemDaoImpl();//文字项操作类
        
        if(textOp.deleteTextItem(textid))
        {
          out.println("删除成功！");
        }
        else
        {
          out.println("由于数据库操作原因删除失败，请检查！");
        }
     }
     else
     {
       out.println("参数传递错误，请检查！");
     }
     
     if(request.getParameter("itemid") != null)
     {
       int itemid = Integer.parseInt(request.getParameter("itemid"));//获得调查项编号
       
       out.println("<p>");
       
       out.println("<a href='textlist.jsp?itemid=" + itemid + "'>返回调查项管理</a>");
       
       out.println("</p>");
     }
   %>
  </body>
</html>
