<%@ page language="java" import="java.util.*,edu.bdu.dao.RadioItemDaoImpl,edu.bdu.entity.RadioItem" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加单选项</title>
    
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
     RadioItem item = null;//默认单选项为空
     
     RadioItemDaoImpl radioOp = new RadioItemDaoImpl();//单选项操作类实例
     
     if(request.getParameter("radioownerid") != null)
     {
        item = new RadioItem();//获得单选项的实例
        
        int ownerid = Integer.parseInt(request.getParameter("radioownerid"));
        
        item.setRadioOwnerID(ownerid);//所属调查项编号
     }
     
     if(request.getParameter("radioindex") != null)
     {
        int index = Integer.parseInt(request.getParameter("radioindex"));//获得排序号
       
        item.setRadioIndex(index);//所属调查项编号
     }
     
     if(request.getParameter("radiocaption") != null)
     {
       item.setRadioCaption(request.getParameter("radiocaption"));//单选项的内容
     }
     
     if(item != null)
     {
        if(radioOp.InsertRadioItem(item))
        {
          response.sendRedirect("updateitem.jsp?itemid=" + item.getRadioOwnerID());//重新跳回修改页
        }
        else
        {
          out.println("添加单选项失败，请检查！");
        }
     }
     else
     {
       out.println("添加出现异常，请检查！");
     }
   
    %>
  </body>
</html>
