<%@ page language="java" import="java.util.*,edu.bdu.dao.*,edu.bdu.entity.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>调查问卷展示</title>
    
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
     
     if(request.getParameter("surveyId") != null)
     {
        int surveyid = Integer.parseInt(request.getParameter("surveyId"));//获得调查问卷编号
        
        SurveyDaoImpl surveyOp = new SurveyDaoImpl();//调查问卷操作类
        
        ItemDaoImpl itemOp = new ItemDaoImpl();//调查项操作类
        
        RadioItemDaoImpl radioOp = new RadioItemDaoImpl();//单选项操作类
        
        CheckboxItemDaoImpl checkOp = new CheckboxItemDaoImpl();//多选项操作类
        
        TextItemDaoImpl textOp = new TextItemDaoImpl();//文字项操作类
        
        Survey survey = surveyOp.getSurvey(surveyid);//根据调查问卷编号获取调查问卷
        
        if(survey != null)
        {
          out.println("<div>");
          out.println("调查问卷名称：" + survey.getSurveyTitle());//打印调查问卷标题
          out.println("<a href='" + survey.getSurveyLink() + "'><img border='0' src='images/info.png'>查看说明</a>");//调查问卷说明连接
          out.println("</div>");
          
          out.println("<hr>");//打印一个行号
          
          List items = itemOp.getItemList(survey.getSurveyID());//根据调查问卷编号查询调查项列表
          
          if(items.size() == 0)
          {
            out.println("不存在调查项！");//显示调查项
          }
          
          out.println("<form name='myform' action='servlet/SurveyVisitAction' method='post'>");//表单名称
          out.println("<input type='hidden' name='surveyid' value='" + survey.getSurveyID() + "'");//调查问卷编号
          out.println("<div>");
          
          for(int i = 0; i < items.size(); i++)
          {
              Item item = (Item)items.get(i);//获得调查项内容
              
              out.println((i+1) + ". " + item.getItemCaption());
              
              out.println("<br>");
              List answers = new ArrayList();
              switch(item.getItemType())//调查项类型
              {
                
                case 0:
                   answers = radioOp.getRadioList(item.getItemID());//根据调查项编号查询单选答案项
                   
                   for(int ri = 0; ri < answers.size(); ri++)
                   {
                     RadioItem rItem = (RadioItem)answers.get(ri);//获得答案项
                     
                     out.println("<input type='radio' name='radio" + item.getItemID() + "' value='" + rItem.getRadioID() + "'>");
                     
                     out.println(rItem.getRadioCaption());//打印答案项标题
                     
                     out.println("<br />");
                   }
                   
                   out.println("<br />");
                   
                   break;
                   
                case 1:
                
                   answers = checkOp.getCheckboxList(item.getItemID());//根据调查项编号查询单选答案项
                   
                   for(int ri = 0; ri < answers.size(); ri++)
                   {
                     CheckboxItem cItem = (CheckboxItem)answers.get(ri);//获得答案项
                     
                     out.println("<input type='checkbox' name='checkbox" + item.getItemID() + "' value='" + cItem.getCheckboxID() + "'>");
                     
                     out.println(cItem.getCheckboxCaption());//打印答案项标题
                     
                     out.println("<br />");
                   }
                   
                   
                   out.println("<br />");
                   break;
                   
                case 2:
                
                   out.println("<textarea name='text" + item.getItemID() + "' rows='6' cols='30'></textarea>");//打印
                   
                   out.println("<br />");
                   
                   out.println("<br />");
                   
                   break;
                
              }
          }
          
          out.println("</div>");
          
          if(items.size() != 0)
          {
            out.println("<div align='center'>");
            out.println("<hr>");
            out.println("<input type='submit' name='subButton' value='提交'>");
            out.println("<input type='reset' name='resButton' value='清空'>");
            out.println("</div>"); 
          }
          
          out.println("</form>");
          
          out.println("<a href='CreateResult?surveyid=" + surveyid + "'>查看调查问卷结果</a>");//查看调查问卷结果
        }
        else
        {
          out.println("调查问卷不存在，请检查！");
        }
     
     }
     else
     {
       out.println("参数传递出错，没有调查问卷编号，请检查！");
     }
     
     

     out.println("<a href='testserveydao.jsp'>返回调查问卷列表</a>");//返回调查问卷列表
    %>
  </body>
</html>
