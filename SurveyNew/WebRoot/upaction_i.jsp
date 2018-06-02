<%@ page language="java" import="java.util.*,edu.bdu.dao.ItemDaoImpl,edu.bdu.entity.Item" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>调查项更新</title>
    
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
      
      Item item = null;//初始化调查项
      
      if(request.getParameter("itemid") != null)
      {
         item = new Item();//实例化调查项
         
         int itemid = Integer.parseInt(request.getParameter("itemid"));//调查项编号
         
         item.setItemID(itemid);//设置调查项编号
      }
      
      if(request.getParameter("itemCaption") != null)
      {
          String itemCaption = request.getParameter("itemCaption");//调查项问题
          
          item.setItemCaption(itemCaption);//调查项问题
      }
      
      if(item != null)
      {
         ItemDaoImpl itemOp = new ItemDaoImpl();//调查项操作类
         
         item.setRadioCount(0);//将回答次数设为零
         
         if(itemOp.updateItem(item))
         {
         
            response.sendRedirect("updateitem.jsp?itemid=" + item.getItemID());//返回更新页
            
         }
         else
         {
            out.println("更新出现异常，请检查！");
         }
      }
      else
      {
         out.println("更新出现异常，请检查！");//出现异常
      }
      
     %>
  </body>
</html>
