<%@ page language="java" import="java.util.*,edu.bdu.dao.RadioItemDaoImpl,edu.bdu.entity.RadioItem" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>单选项更新</title>
    
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
       if(request.getParameter("radioid") != null)
       {
          int radioid = Integer.parseInt(request.getParameter("radioid"));//获得单选项编号
          
          RadioItemDaoImpl radioOp = new RadioItemDaoImpl();//单选项操作类
          
          RadioItem item = radioOp.getRadio(radioid);//根据单选项编号查询单选项
          
          if(item != null)
          {
            %>
            
            <form name="myform" action="upaction_r.jsp" method="post">
                     <p>
                          <input type="hidden" name="radioid" value="<%=item.getRadioID() %>">
                          <input type="hidden" name="radioindex" value="<%=item.getRadioIndex() %>">
                          <input type="hidden" name="radioownerid" value="<%=item.getRadioOwnerID() %>">
                          <input type="hidden" name="selectcount" value="<%=item.getSelectCount() %>">
                              选项内容：<input type="text" name="radiocaption" value="<%=item.getRadioCaption() %>">
                     </p>
                     <p>
                       <input type="submit" name="subButton" value="更新">
                        &nbsp;
                       <input type="reset" name="resButton" value="重置">
                     </p>
                     <p>
                       <a href="upaction_r.jsp?commType=1&radioid=<%=item.getRadioID() %>">删除</a>
                     </p>
   
           </form>
            <%
          }
          else
          {
            out.println("此单选项不存在，请检查！");
          }
          
       }
       else
       {
         out.println("单选调查项编号不存在，请检查！");//输出错误提示
       }
      
     %>
  </body>
</html>
