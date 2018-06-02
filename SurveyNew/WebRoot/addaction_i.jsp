<%@ page language="java" import="java.util.*,edu.bdu.dao.ItemDaoImpl,edu.bdu.entity.Item" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>调查项添加</title>
    
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
             request.setCharacterEncoding("UTF-8");//设置编号格式
      
             Item item = new Item();//实例化调查项
       
             ItemDaoImpl itemOp = new ItemDaoImpl();//调查项数据库操作类
        
             if(request.getParameter("surveyid") != null)
             {
               int surveyid = Integer.parseInt(request.getParameter("surveyid"));//获得调查项所属调查问卷编号
               
               item.setItemOwnerSurveyID(surveyid);//调查问卷编号
             }
             
             if(request.getParameter("ownerid") != null)
             {
               int ownerid = Integer.parseInt(request.getParameter("ownerid"));//获得创建者编号
               
               item.setItemOwerID(ownerid);//创建者编号
             }
             
             if(request.getParameter("itemCaption") != null)
             {
               String itemCaption = request.getParameter("itemCaption");//获得调查项标题
               
               item.setItemCaption(itemCaption);//调查项标题
             }
             
             if(request.getParameter("itemtype") != null)
             {
               int itemtype = Integer.parseInt(request.getParameter("itemtype"));//获得调查项类型
               
               item.setItemType(itemtype);//调查项类型
             }
             
        
              if(itemOp.InsertItem(item))
              {
                   //response.sendRedirect("testserveydao.jsp");
                   
                   out.println("<a href='testitemdao.jsp?surveyid=" + item.getItemOwnerSurveyID() + "'>调查项管理</a>");//设置调查项管理连接
                   
                   
               }
               else
               {
                 out.println("<font color = 'red'>用户修改失败，请检查！</font><br>");
                 
                 out.println("<a href='additem.jsp'>重新添加</a>");
               }
     %>
  </body>
</html>
