<%@ page language="java" import="java.util.*,edu.bdu.dao.*,edu.bdu.entity.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>调查项管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
　　 <script language="javascript">
     
      function updateItem(checkitem)
      {
        var str = checkitem.split('#');
        
        window.open("updateitem.jsp?itemid=" + str[1]);
      }
      
      function update()
      {
        var elements = document.all;
        
        for(var i = 0;i < elements.length;i++ )
        {
          element = elements[i];
          if(element.type=="checkbox" && element.checked)
          {
            updateItem(element.name);
          }
        }
      }
     
    </script>
  </head>
  
  <body>
    
    <%
    
       String ip = request.getRemoteAddr();//获得访问者ip
       
       out.println(ip);
       
       if(request.getParameter("surveyid") != null)//调查卷编号不为空
       {
          int surveyid = Integer.parseInt(request.getParameter("surveyid"));//将调查卷编号
          
          ItemDaoImpl itemOp = new ItemDaoImpl();//获得调查项管理类
          
          List items = itemOp.getItemList(surveyid);//获得此调查问卷的调查项列表
          
          out.println("<table border='1'>");
          out.println("<tr>");
          out.println("<tr><td><a href='additem.jsp?surveyid=" + surveyid + "'>添加调查项</a></td>");
          
          out.println("<tr><td><a href='javascript:update()'>修改选中调查项</a></td>");
          //out.println("<td><select name='itemtype'>"+
          //"<option value='0'>单选项</option>"+
          //"<option value='1'>多选项</option>"+
          //"<option value='2'>文字项</option>"+
          //"</select></td>");
          
          out.println("</tr>");
          out.println("</table>");
          
          out.println("<table border='1'>");
          out.println("<tr>");
          out.println("<td>调查项编号</td>");
          out.println("<td>调查项标题</td>");
          out.println("<td>调查项属性</td>");
          out.println("<td>调查项创建者</td>");
          out.println("<td>所属调查问卷编号</td>");
          out.println("<td>调查项类型</td>");
          out.println("<td>调查项点击次数</td>");
          out.println("<td>调查项选择</td>");
          out.println("<td>调查项操作</td>");
          out.println("</tr>");
          
          for(int i = 0; i < items.size(); i++)//循环输出所有调查项信息
          {
            Item item = (Item)items.get(i);//实例化调查项   
            out.println("<tr>");
            out.println("<td>" + item.getItemID() + "</td>");//调查项编号
            out.println("<td>" + item.getItemCaption() + "</td>");//调查项标题
            out.println("<td>" + item.getItemAttribute() + "</td>");//调查项属性
            out.println("<td>" + item.getItemOwnerID() + "</td>");//调查项创建者编号
            out.println("<td>" + item.getItemOwnerSurveyID() + "</td>");//所属调查问卷编号
            out.println("<td>" + item.getItemType() + "</td>");//调查项类型
            out.println("<td>" + item.getRadioCount() + "</td>");//调查项点击次数
            out.println("<td><input type = 'checkbox' name = 'checkbox#" + item.getItemID() + "'></td>");
            out.println("<td><a href='deleteitem.jsp?itemid=" + item.getItemID() + "'>删除</a></td>");
            out.println("</tr>");
            
          }
          
          out.println("</table>");
       }
       
       out.println("<a href='testserveydao.jsp'>返回调查问卷列表</a>");//返回调查问卷列表
      
     %>
  </body>
</html>
