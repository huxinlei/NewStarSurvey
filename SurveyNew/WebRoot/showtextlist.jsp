<%@ page language="java" import="java.util.*,edu.bdu.dao.*,edu.bdu.entity.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>调查项回答列表</title>
    
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
          int itemid = Integer.parseInt(request.getParameter("itemid"));//获得调查项编号
          
          ItemDaoImpl itemOp = new ItemDaoImpl();//获得调查项数据库操作类 
          
          TextItemDaoImpl textOp = new TextItemDaoImpl();//文字答案项操作类
          
          Item item = itemOp.getItemByItemId(itemid);//根据调查项编号查询调查项
          
          if(item != null)
          {
             out.println("<p>");
             out.println("调查项名称：" + item.getItemCaption());
             out.println("</p>");
             out.println("<hr>");
             
             out.println("<table bgcolor='green' border='1'>");
             
             List textlist = textOp.getTextListByOwner(item.getItemID());//根据调查项编号查询文字答案项列表
             
             if(textlist.size() == 0)
             {
               out.println("<tr><td>当前还没有人回答此调查项！</td></tr>");
             }
             
             for(int i = 0; i < textlist.size(); i++)
             {
                TextItem text = (TextItem)textlist.get(i);//获得文字项实例
                
                out.println("<tr>");
                
                out.println("<td>" + text.getTextContent() + "</td>");
                
                out.println("</tr>");
             }
             
             out.println("</table>");
          }
          else
          {
            out.println("查询调查项不存在！");
          }
        }
        else
        {
          out.println("参数传递出错，请检查！");
        }
       %>
  </body>
</html>
