<%@ page language="java" import="java.util.*,edu.bdu.dao.SurveyDaoImpl,edu.bdu.entity.Survey" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>调查问卷更新页面</title>
    
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
        Survey survey = new Survey();//创建用户对象
        
        request.setCharacterEncoding("UTF-8");//设置编码格式
        
        if(request.getParameter("surveyid")!= null)
        {
           int surveyid = Integer.parseInt(request.getParameter("surveyid"));//获得调查问卷编号
           
           survey.setSurveyID(surveyid);//调查问卷编号赋值
        }
        
        if(request.getParameter("surveytitle")!=null)
        {
            String surveytitle = request.getParameter("surveytitle");
            
            survey.setSurveyTitle(surveytitle);//调查问卷标题赋值
             
        }
        
        if(request.getParameter("surveylink")!=null)
        {
            String surveylink = request.getParameter("surveylink");
            
            survey.setSurveyLink(surveylink);//调查问卷说明连接赋值
        }
        
        if(request.getParameter("surveyownerid")!= null)
        {
           
           int surveyownerid = Integer.parseInt(request.getParameter("surveyownerid"));
           
           survey.setSurveyOwnerID(surveyownerid);//为调查问卷创建者用户编号
        }
        
        if(request.getParameter("surveyexpiration") != null)
        {
           String surveyexpiration = request.getParameter("surveyexpiration");//获得过期时间设置
           
           if(surveyexpiration.trim().equals("")&&surveyexpiration.trim().equals("无限制"))
           {
              survey.setSurveyExpirationDate(null);//如果过期时间为空或者为无限制则设置过期时间为空
              
           }
           else
           {
             survey.setSurveyExpirationDate(surveyexpiration);//设置过期时间
           }
        }
        
        SurveyDaoImpl surveyOp = new  SurveyDaoImpl();//调查问卷操作类
        
        if(surveyOp.updateSurvey(survey))//更新调查问卷
        {
          response.sendRedirect("testserveydao.jsp");
        }
        else
        {
          out.println("<font color = 'red'>用户修改失败，请检查！</font>");
        }
        
                     
       %>
  </body>
</html>
