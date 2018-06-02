<%@ page language="java" import="java.util.*,edu.bdu.dao.CheckboxItemDaoImpl,edu.bdu.entity.CheckboxItem" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>多选项更新</title>
    
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
       if(request.getParameter("checkid") != null)
       {
          int radioid = Integer.parseInt(request.getParameter("checkid"));//获得多选项编号
          
          CheckboxItemDaoImpl radioOp = new CheckboxItemDaoImpl();//多选项操作类
          
          CheckboxItem item = radioOp.getCheckbox(radioid);//根据多选项编号查询单选项
          
          if(item != null)
          {
            %>
            
            <form name="myform" action="upaction_c.jsp" method="post">
                     <p>
                          <input type="hidden" name="checkid" value="<%=item.getCheckboxID() %>">
                          <input type="hidden" name="checkindex" value="<%=item.getCheckboxIndex() %>">
                          <input type="hidden" name="checkownerid" value="<%=item.getCheckboxOwnerID() %>">
                          <input type="hidden" name="selectcount" value="<%=item.getSelectCount() %>">
                              选项内容：<input type="text" name="checkcaption" value="<%=item.getCheckboxCaption() %>">
                     </p>
                     <p>
                       <input type="submit" name="subButton" value="更新">
                        &nbsp;
                       <input type="reset" name="resButton" value="重置">
                     </p>
                     <p>
                       <a href="upaction_c.jsp?commType=1&checkid=<%=item.getCheckboxID() %>">删除</a>
                     </p>
   
           </form>
            <%
          }
          else
          {
            out.println("此多选项不存在，请检查！");
          }
          
       }
       else
       {
         out.println("多选调查项编号不存在，请检查！");//输出错误提示
       }
      
     %>
  </body>
</html>
