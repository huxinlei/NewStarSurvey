<%@ page language="java" import="java.util.*,edu.bdu.dao.SurveyDaoImpl,edu.bdu.entity.Survey" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>调查问卷操作类</title>
    
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
     String comm;//命令类型
     
     int commID = 0;//命令代号默认为零，没有任何操作
     
     int surveyId ;//用户id
     
     if(request.getParameter("comm") != null && request.getParameter("surveyId") != null)
     {
       comm = request.getParameter("comm");//获得操作类型
     
       surveyId = Integer.parseInt(request.getParameter("surveyId"));//获得用户id
     
       commID = 1;//命令代号为1，表示存在操作，具体操作还不知道
     }
     else
     {
       comm = null;//命令为空
       
       commID = 0;//命令不存在
       
       surveyId = 0;//用户id为零
     }
     
     if(commID != 0)
     {
      
       if(comm.equals("see"))
       {
          commID = 2;//查看调查问卷的详细信息
       }
       
       if(comm.equals("upd"))
       {
          commID = 3;//删除调查问卷信息
       }
       
       if(comm.equals("del"))
       {
          commID = 4;//更新调查问卷信息
       }
       
      
     }
     
     switch(commID)
     {
       case 0:
           out.println("<font color = 'red'>您的访问存在问题，没有出现任何命令!</font>");
           
           break;
           
       case 1:
           out.println("<font color = 'red'>您的命令出现了问题，请检查！</font>");
           break;
           
           
      case 2:
      
        SurveyDaoImpl surveyOper = new SurveyDaoImpl();//操作类
        
        Survey survey = surveyOper.getSurvey(surveyId);//根据调查问卷编号查找调查问卷
        
        out.println("<p>调查问卷编号：" + survey.getSurveyID() + "</p>");
        
        out.println("<p>调查问卷标题：" + survey.getSurveyTitle() + "</p>");
        
        out.println("<p>调查问卷创建者编号：" + survey.getSurveyOwnerID() + "</p>");
        
        out.println("<p>调查问卷说明连接：" + survey.getSurveyLink() + "</p>");
        
        //out.println("<p>调查问卷创建者编号：" + survey.getSurveyOwnerID() + "</p>");
    
        Date date = survey.getSurveyCreateDate();//调查问卷创建时间
      
       if(date != null)
         out.println("<p>创建时间：" + date.toString() + "</p>");
       else
         out.println("<p>创建时间：" + "创建时间出现异常" + "</p>");
       
       
       String expiration = survey.getSurveyExpirationDate();//过期时间设置
       
       if(!expiration.equals("")&&expiration != null)
         out.println("<p>创建时间：" + expiration + "</p>");
       else
         out.println("<p>创建时间：" + "无限制" + "</p>");
         
         out.println("<a href='testitemdao.jsp?surveyid=" + survey.getSurveyID() + "'>调查项管理</a>");//设置调查项管理连接
        
         out.println("<a href='testvisitordao.jsp?surveyid=" + survey.getSurveyID() + "'>访问者管理</a>");//访问者信息管理
        
         out.println("<a href='testserveydao.jsp'>返回调查问卷列表</a>");//返回调查问卷列表
           
        break;
        
       case 3:
       
        SurveyDaoImpl surveyOper1 = new SurveyDaoImpl();//操作类
        
        Survey survey1 = surveyOper1.getSurvey(surveyId);//根据调查问卷编号查找调查问卷
        
        out.println("<form name='myform' action='updatesurvey.jsp' method='post'>");
        
         out.println("<p>调查问卷编号：<input type = 'text' name = 'surveyid' readonly='true' value = '" + survey1.getSurveyID() + "'></p>");
        
        out.println("<p>调查问卷标题：<input type = 'text' name = 'surveytitle' value = '" + survey1.getSurveyTitle() + "'></p>");
        
        out.println("<p>调查问卷说明连接：<input type = 'text' name = 'surveylink' value = '" + survey1.getSurveyLink() + "'></p>");
        
        out.println("<p>调查问卷创建者编号：<input type = 'text' name = 'surveyownerid' readonly='true' value = '" + survey1.getSurveyOwnerID() + "'></p>");
        
         Date date1 = survey1.getSurveyCreateDate();//调查问卷创建时间
       
        if(date1 != null)
           out.println("<p>创建时间：<input type = 'text' name = 'surveycreate' readonly='true' value = '" + date1.toString() + "'></p>");
        else
           out.println("<p>创建时间：<input type = 'text' name = 'surveycreate' readonly='true' value = '" + "创建时间出现异常" + "'></p>");
       
       
        
      
        
       String expiration1 = survey1.getSurveyExpirationDate();//过期时间设置
       
       if(!expiration1.equals("")&&expiration1 != null)
         out.println("<p>过期时间：<input type='text' name = 'surveyexpiration' value = '" + expiration1 + "'></p>");
       else
         out.println("<p>过期时间：<input type='text' name = 'surveyexpiration' value = '" + "无限制" + "'></p>");
        
        
      
        
        out.println("<p><input type='submit'  name='subButton' value='提交'>"
        +
        "&nbsp;<input type='reset' name='resButton' value='重置'></p>");
        
        
        out.println("</form>");
        
          out.println("<a href='testitemdao.jsp?surveyid=" + survey1.getSurveyID() + "'>调查项管理</a>");//设置调查项管理连接
          
          out.println("<a href='testvisitordao.jsp?surveyid=" + survey1.getSurveyID() + "'>访问者管理</a>");//访问者信息管理
        
          out.println("<a href='testserveydao.jsp'>返回调查问卷列表</a>");//返回调查问卷列表
           
        break;
        
        case 4:
        
          SurveyDaoImpl surveyOper2 = new SurveyDaoImpl();//用户操作类
          
          if(surveyOper2.deleteSurvey(surveyId))
          {
            response.sendRedirect("testserveydao.jsp");
          }
          else
          {
             out.println("<font color = 'red'>删除出现问题，请检查！</font><br />");
            
             out.println("<a href='testserveydao.jsp'>返回调查问卷列表</a>");//返回调查问卷列表
          }
           
     }
     
     
     
     
    %>
  </body>
</html>
