<%@ page language="java" import="java.util.*,edu.bdu.dao.VisitorDaoImpl,edu.bdu.entity.Visitor" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>删除访问者信息</title>
    
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
        
        if(request.getParameter("visitorNumber") != null)
        {
           int visitorNumber = Integer.parseInt(request.getParameter("visitorNumber"));//获得访问者编号
           
           VisitorDaoImpl visitorOp = new VisitorDaoImpl();//访问者操作类
           
           if(visitorOp.deleteVisitor(visitorNumber))//删除访问者信息
           {
             out.println("访问者信息已成功删除！");
           }
           else
           {
             out.println("访问者信息由于异常没能成功删除！");
           }
           
        }
        else
        {
           out.println("参数传递错误，请检查！");
        }
      
        out.println("<a href='testserveydao.jsp'>返回调查问卷列表</a>");//返回调查问卷列表
     %>
  </body>
</html>
