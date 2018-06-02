<%@ page language="java" import="java.util.*,edu.bdu.dao.SurveyDaoImpl,edu.bdu.entity.Survey" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>调查问卷搜索页</title>
    
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
   <center>
    <form name="myform" action="search_s.jsp" method="post">
    <table border="1" width="80%">
      <tr>
        <td>调查问卷标题</td>
        <td><input type="text" size="16" name="surveytitle" value=""></td>
        <td>调查创建者编号</td>
        <td><input type="text" size="16" name="surveyownerid" value=""></td>
        <td><input type="submit" value="查找" name="selectButton"></td>
        <td><input type="reset" value="重置" name="resetButton"></td>
      </tr>
    </table>
   </form>
  </center>
    <%
     
     request.setCharacterEncoding("UTF-8");//设置文件的编码格式
     
     Survey condition = new Survey();//生成一个调查问卷实例，用来存放查询条件
     
     if(request.getParameter("surveytitle")!=null)
     {
        condition.setSurveyID(0);//调查问卷编号
        
        condition.setSurveyTitle(request.getParameter("surveytitle"));//调查问卷编号赋值
     }
     
     if(request.getParameter("surveyownerid")!= null)
     {
         String surveyowner = request.getParameter("surveyownerid");//调查问卷创建者编号
         
         if(surveyowner.trim().equals(""))
         {
           condition.setSurveyOwnerID(0);//如果编号是空值,创建者编号为空
         }
         else
         {
           int surveyownerid = Integer.parseInt(surveyowner);//获得调查问卷创建者编号
           
           condition.setSurveyOwnerID(surveyownerid);//调查问卷创建者编号赋值
         }
     }
     
    SurveyDaoImpl surveyOp = new SurveyDaoImpl();//调查问卷操作类
     
     List surveylist = surveyOp.getServeyList(condition);//获得调查问卷列表
     
     out.println("<table color='red' border='1'>");
     out.println("<tr><td><a href='addsurvey.jsp'>添加调查问卷</a></td><tr>");
     //out.println("<tr><td><a href='search_s.jsp'>查找调查问卷</a></td><tr>");
     out.println("</table>");
     out.println("<table color = 'red' border='1'>");
     out.println("<tr bgcolor='#F1F3F5'>");
     out.println("<td width = '100'>调查问卷编号</td>");
     out.println("<td width = '200'>调查问卷标题</td>");
     out.println("<td width = '200'>调查问卷创建用户编号</td>");
     out.println("<td width = '200'>调查问卷说明连接</td>");
     out.println("<td width = '200'>调查问卷创建时间</td>");
     out.println("<td width = '200'>调查问卷过期日期</td>");
     out.println("<td width = '100'>用户操作</td>");
     out.println("<td width = '100'>用户操作</td>");
     out.println("<td width = '100'>用户操作</td>");
     out.println("</tr>");
     for(int i = 0;i < surveylist.size();i++)
     {
       Survey survey = (Survey)surveylist.get(i);//获得用户信息
       
       out.println("<tr>");
       
       out.println("<td>" + survey.getSurveyID() + "</td>");
       
       out.println("<td>" + survey.getSurveyTitle() + "</td>");
       
       out.println("<td>" + survey.getSurveyOwnerID() + "</td>");
       
       out.println("<td><a href='#'>" + survey.getSurveyLink() + "</a></td>");
       
       Date date = survey.getSurveyCreateDate();//调查问卷创建时间
       
       if(date != null)
         out.println("<td>" + date.toString() + "</td>");
       else
         out.println("<td>" + "创建时间出现异常" + "</td>");
       
     
       String expiration = survey.getSurveyExpirationDate();//过期时间设置
       
       if(!expiration.equals("")&&expiration != null)
         out.println("<td>" + expiration + "</td>");
       else
         out.println("<td>" + "无限制" + "</td>");
         
         
       out.println("<td>" + "<a href='surveyoperator.jsp?comm=see&surveyId=" + survey.getSurveyID() + "'>查看</a>" + "</td>");
       out.println("<td>" + "<a href='surveyoperator.jsp?comm=upd&surveyId=" + survey.getSurveyID() + "'>修改</a>" + "</td>");
       out.println("<td>" + "<a href='surveyoperator.jsp?comm=del&surveyId=" + survey.getSurveyID() + "'>删除</a>" + "</td>");
       
       out.println("</tr>");
       
     }
     out.println("</table>");
     %>
   
  </body>
</html>
