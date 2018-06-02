<%@ page language="java" import="java.util.*,edu.bdu.dao.CheckboxItemDaoImpl,edu.bdu.entity.CheckboxItem" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改多选项</title>
    
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
     
     CheckboxItemDaoImpl checkOp = new CheckboxItemDaoImpl();//实例化多选项操作类
     
     if(request.getParameter("commType") == null)//为空的话更新多选项
     {
       CheckboxItem item = null;//多选项实例化
       
       if(request.getParameter("checkid")!= null)
       {
          item = new CheckboxItem();//获得多选项实例
           
          item.setCheckboxID(Integer.parseInt(request.getParameter("checkid")));//多选项编号
       }
       
       if(request.getParameter("checkindex") != null)
       {
         item.setCheckboxIndex(Integer.parseInt(request.getParameter("checkindex")));//多选项顺序
       }
       
       if(request.getParameter("checkownerid") != null)
       {
         item.setCheckboxOwnerID(Integer.parseInt(request.getParameter("checkownerid")));//多选所属调查项
       }
       
       if(request.getParameter("selectcount") != null)
       {
         item.setSelectCount(Integer.parseInt(request.getParameter("selectcount")));//多选项点击次数
       }
       
       if(request.getParameter("checkcaption") != null)
       {
         item.setCheckboxCaption(request.getParameter("checkcaption"));//多选项内容
       }
       
       if(item != null)
       {
         if(checkOp.updateCheckboxItem(item))
         {
           response.sendRedirect("updateitem.jsp?itemid=" + item.getCheckboxOwnerID());//跳转调查项
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
       int checkid = -1;//多选项编号
       
       if(request.getParameter("checkid") != null)
       {
         checkid = Integer.parseInt(request.getParameter("checkid"));//多选项编号
         
         if(checkOp.deleteCheckboxItem(checkid))
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
         out.println("多选项编号出现异常，请检查！");
       }
     }
    %>
  </body>
</html>
