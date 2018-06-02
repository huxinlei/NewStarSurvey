<%@ page language="java" import="java.util.*,edu.bdu.dao.TextItemDaoImpl,edu.bdu.entity.TextItem" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>文字调查项回答列表</title>
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
     
     if(request.getParameter("itemid") != null)
     {
       
       int itemid = Integer.parseInt(request.getParameter("itemid"));//获得调查项的编号
       
       TextItemDaoImpl textOp = new TextItemDaoImpl();//获得文字项的操作类
       
       List texts = textOp.getTextListByOwner(itemid);//根据调查项编号获得文字回答项
       
        out.println("<table bgcolor='red' border='1'>");
        out.println("<tr>");
        out.println("<td>文字项编号</td>");
        out.println("<td>文字项标题</td>");
        out.println("<td>文字项内容</td>");
        out.println("<td>所属调查项编号</td>");
        out.println("<td>文字项操作</td>");
        out.println("</tr>");
       
       for(int i = 0; i < texts.size(); i++)
       {
          TextItem text = (TextItem)texts.get(i);//获得回答项
          
          out.println("<tr>");
          
          out.println("<td>" + text.getTextID() + "</td>");
          out.println("<td>" + text.getTextCaption() + "</td>");
          out.println("<td>" + text.getTextContent() + "</td>");
          out.println("<td>" + text.getTextOwnerID() + "</td>");
          out.println("<td><a href='deletetext.jsp?textid=" + text.getTextID() + "&itemid=" + itemid + "'>删除</a></td>");
        
          out.println("</tr>");
       }
       
       out.println("</table>");
     
     }
     else
     {
       out.println("调查项编号有问题，请检查！");
     }
     
    %>
  </body>
</html>
