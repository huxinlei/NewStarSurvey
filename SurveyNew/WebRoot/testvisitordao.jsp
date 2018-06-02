<%@ page language="java" import="java.util.*,edu.bdu.dao.VisitorDaoImpl,edu.bdu.entity.Visitor" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>访问者信息管理</title>
    
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
     
     if(request.getParameter("surveyid") != null)
     {
        int surveyid = Integer.parseInt(request.getParameter("surveyid"));//获得调查问卷的编号
        
       
        
        VisitorDaoImpl visitorOp = new VisitorDaoImpl();//访问者操作类
        
        List visitors = visitorOp.getVisitorList(surveyid);//根据调查问卷编号获得访问者信息
        
        out.println("<table bgcolor='red' border='1'>");
        out.println("<tr>");
        out.println("<td>访问者编号</td>");
        out.println("<td>访问者ip地址</td>");
        out.println("<td>调查问卷编号</td>");
        out.println("<td>访问者回答内容</td>");
        out.println("<td>访问时间记录</td>");
        out.println("<td>操作</td>");
        out.println("</tr>");
        
        for(int i = 0; i < visitors.size(); i++)
        {
          Visitor visitor = (Visitor)visitors.get(i);//获得访问者信息
          
          out.println("<tr>");
          
          out.println("<td>" + visitor.getVisitorNumber() + "</td>");//访问者编号
          out.println("<td>" + visitor.getVisitorIP() + "</td>");//访问者ip地址
          out.println("<td>" + visitor.getVisiteSurveyID() + "</td>");//访问的调查问卷编号
          out.println("<td>" + visitor.getVisiteSurveyData() + "</td>");//访问者回答的信息
          out.println("<td>" + visitor.getVisiteDateTime().toString() + "</td>");//访问者访问时间
          
          out.println("<td><a href='deletevisitor.jsp?visitorNumber=" + visitor.getVisitorNumber() + "'>删除</a></td>");
          
          out.println("<tr>");
          
        }
        
        out.println("</table>");
     }
     else
     {
       out.println("参数传递不正确，请检查！");
     }
     
     out.println("<a href='testserveydao.jsp'>返回调查问卷列表</a>");//返回调查问卷列表
    %>
  </body>
</html>
