<%@ page language="java" import="java.util.*,edu.bdu.dao.SurveyDaoImpl,edu.bdu.entity.Survey,edu.bdu.entity.User" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>调查问卷添加</title>
    
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
       request.setCharacterEncoding("UTF-8");//设置编码格式为utf-8
       
        User user = null;
        
        if(session.getAttribute("user") != null)
        
         user = (User)session.getAttribute("user");//获得当前用户的引用
         
       
       Survey survey = new Survey();//创建调查问卷对象
       
       SurveyDaoImpl surveyOp = new SurveyDaoImpl();//调查问卷操作类
        
        //request.setCharacterEncoding("UTF-8");//设置编码格式
        
       //用户编号自动生成，此程序此段代码未用
        if(request.getParameter("surveytitle")!= null)
        {
           String surveytitle = request.getParameter("surveytitle");//获得调查问卷标题
           
           survey.setSurveyTitle(surveytitle);//设置调查问卷标题
        }
        
        if(request.getParameter("surveylink")!=null)
        {
            String surveylink = request.getParameter("surveylink");//获得调查问卷连接
            
           survey.setSurveyLink(surveylink);//设置调查问卷连接
        }
        if(surveyOp.getServeyList(survey).size()==0){
              // if(request.getParameter("surveyownerid")!=null)
             //  {
                  //  int surveyownerid = Integer.parseInt(request.getParameter("surveyownerid"));//获得调查问卷创建者编号
                    
                 //   survey.setSurveyOwnerID(surveyownerid);//调查问卷创建者编号
             //  }
               survey.setSurveyOwnerID(user.getUserID());//获得用户编号
        
               if(request.getParameter("surveyexpiration")!= null)
               {
                    String surveyexpiration = request.getParameter("surveyexpiration");//获得调查问卷过期日期设置
                    
                    
                    survey.setSurveyExpirationDate(surveyexpiration);//为调查问卷过期时间赋值
               }
        
        
        
              if(surveyOp.InsertSurvey(survey))
              {
                   //response.sendRedirect("testserveydao.jsp");
                   
                   out.println("<a href='testitemdao.jsp?surveyid=" + survey.getSurveyID() + "'>调查项管理</a>");//设置调查项管理连接
                   
                   out.println("<a href='testserveydao.jsp'>返回调查问卷列表</a>");//返回调查问卷列表
                   
                   
               }
               else
               {
                 out.println("<font color = 'red'>用户修改失败，请检查！</font><br>");
                 
                 out.println("<a href='addsurvey.jsp'>重新添加</a>");
               }
        }
        else
        {
             out.println("此调查问卷标题已经存在，请重新添加不存在的调查问卷标题！<br />");
             
             out.println("<a href='addsurvey.jsp'>重新添加</a>");
        }
     %>
  </body>
</html>
