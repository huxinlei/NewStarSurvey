<%@ page language="java" import="java.util.*,edu.bdu.dao.RadioItemDaoImpl,edu.bdu.entity.RadioItem" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>更新单选项</title>
    
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
     
     RadioItemDaoImpl radioOp = new RadioItemDaoImpl();//实例化单选项操作类
     
     if(request.getParameter("commType") == null)//为空的话更新单选项
     {
       RadioItem item = null;//单选项实例化
       
       if(request.getParameter("radioid")!= null)
       {
          item = new RadioItem();//获得单选项实例
           
          item.setRadioID(Integer.parseInt(request.getParameter("radioid")));//单选项编号
       }
       
       if(request.getParameter("radioindex") != null)
       {
         item.setRadioIndex(Integer.parseInt(request.getParameter("radioindex")));//单选项顺序
       }
       
       if(request.getParameter("radioownerid") != null)
       {
         item.setRadioOwnerID(Integer.parseInt(request.getParameter("radioownerid")));//单选所属调查项
       }
       
       if(request.getParameter("selectcount") != null)
       {
         item.setSelectCount(Integer.parseInt(request.getParameter("selectcount")));//单选项点击次数
       }
       
       if(request.getParameter("radiocaption") != null)
       {
         item.setRadioCaption(request.getParameter("radiocaption"));//单选项内容
       }
       
       if(item != null)
       {
         if(radioOp.updateRadioItem(item))
         {
           response.sendRedirect("updateitem.jsp?itemid=" + item.getRadioOwnerID());//跳转调查项
         }
         else
         {
           out.println("数据更新异常，请检查！");
         }
       }
       else
       {
         out.println("参数传递异常，请检查！");//打印失败信息
       }
     }
     else
     {
       int radioid = -1;//单选项编号
       
       if(request.getParameter("radioid") != null)
       {
         radioid = Integer.parseInt(request.getParameter("radioid"));//单选项编号
         
         if(radioOp.deleteRadioItem(radioid))
         {
           out.println("删除成功！");
         }
         else
         {
           out.println("删除出现异常，请检查！");
         }
       }
       else
       {
         out.println("单选项编号出现异常，请检查！");
       }
     }
    %>
  </body>
</html>
