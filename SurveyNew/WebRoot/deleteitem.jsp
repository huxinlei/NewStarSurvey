<%@ page language="java" import="java.util.*,edu.bdu.dao.ItemDaoImpl" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>调查项删除操作</title>
    
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
     if(request.getParameter("itemid") != null)
     {
       
       int itemid = Integer.parseInt(request.getParameter("itemid"));
       
       ItemDaoImpl itemOp = new ItemDaoImpl();//调查项操作类
       
       if(itemOp.deleteItem(itemid))
       {
         out.println("删除成功！");
       }
       else
       {
         out.println("删除失败，出现数据库操作异常！");
       }
       
       
       
     }
     else
     {
       out.println("调查项编号为空，请检查！");//调查项编号为空
     }
    %>
  </body>
</html>
