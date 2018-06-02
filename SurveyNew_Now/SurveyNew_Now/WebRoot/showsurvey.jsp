<%@ page language="java" import="java.util.*,edu.bdu.dao.*,edu.bdu.entity.*,edu.bdu.tools.OperatorTools" pageEncoding="UTF-8"%>
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
	
	    <link href="JS/css/start/jquery-ui.css" rel="stylesheet" />
	<script type="text/javascript" src="JS/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="JS/js/jquery-ui-1.10.2.custom.js"></script>
    <script type="text/javascript">
      $(function(){
      
             $("#indexDiv").mouseover(function () {
                if ($(this).hasClass("nav1")) {
                    $(this).removeClass("nav1").addClass("nav1_1"); //鼠标到按钮上时改成样式nav1_1
                }
            });
            $("#indexDiv").mouseout(function () {
                if ($(this).hasClass("nav1_1")) {
                    $(this).removeClass("nav1_1").addClass("nav1");//当鼠标离开按钮时样式改成nav1
                }
            });

            $("#surveyDiv").mouseover(function () {
                if ($(this).hasClass("nav2")) {
                    $(this).removeClass("nav2").addClass("nav2_1"); //鼠标到按钮上时改成样式nav1_1
                }
            });

            $("#surveyDiv").mouseout(function () {
                if ($(this).hasClass("nav2_1")) {
                    $(this).removeClass("nav2_1").addClass("nav2"); //当鼠标离开按钮时样式改成nav1
                }
            });

            $("#helpDiv").mouseover(function () {
                if ($(this).hasClass("nav3")) {
                    $(this).removeClass("nav3").addClass("nav3_1"); //鼠标到按钮上时改成样式nav1_1
                }
            });

            $("#helpDiv").mouseout(function () {
                if ($(this).hasClass("nav3_1")) {
                    $(this).removeClass("nav3_1").addClass("nav3"); //当鼠标离开按钮时样式改成nav1
                }
            });
            
            //按钮背景变换
            $("#searchBtn").mouseover(function () {
                if ($(this).hasClass("searchBtn1")) {
                    $(this).removeClass("searchBtn1").addClass("searchBtn1_1"); //鼠标到按钮上时改成样式nav1_1
                }
            });
            
            });
            
    </script>
  
   <style type="text/css">
    body
    {
      margin:0px;
      padding:0px;
      background-color:#f5f5f5;
    }
     .nav1
     {
      float:right;
      margin-bottom:0px;
      height:70px;
      line-height:70px;
      width:159px;
      background:url(image/index1.png);
      cursor:pointer;

      }
      .nav1 a
      {
        font-size:18px;
        color:#000000;
        text-decoration:none;
      }
      .nav1_1
      {
      float:right;
      margin-bottom:0px;
      height:70px;
      line-height:70px;      
      width:159px;
      background:url(image/index1_1.png);
      cursor:pointer;

      }
      
      .nav1_1 a
      {
        color:#000000;
        text-decoration:none;
      }
      .nav2
      {
      float:right;
      margin-bottom:0px;
      font-size:18px;
      height:70px;
      line-height:70px;
      width:159px;
      background:url(image/index2.png);
      cursor:pointer;  
      }
      
      .nav2 a
      {
        font-size:18px;
        color:#000000;
        text-decoration:none;
      }
      .nav2_1
      {
      float:right;
      margin-bottom:0px;
      height:70px;
      line-height:70px;      
      width:159px;
      background:url(image/index2_1.png);
      cursor:pointer; 
      }
      
      .nav2_1 a
      {
        color:#000000;
        text-decoration:none;
      }
      
      .nav3
      {
      float:right;
      margin-bottom:0px;
      font-size:18px;
      height:70px;
      line-height:70px;      
      width:159px;
      background:url(image/index3.png);
      cursor:pointer;            
      }
      
      .nav3 a
      {
        font-size:18px;
        color:#000000;
        text-decoration:none;
      }
      
      .nav3_1
      {
      float:right;
      margin-bottom:0px;
      height:70px;
      line-height:70px;      
      width:159px;
      background:url(image/index3_1.png);
      cursor:pointer;  
      }
      
      .nav3_1 a
      {
        color:#000000;
        text-decoration:none;
      }
      .surN1
      {
       float:left;
       margin-left:6px;
       margin-top:5px;
       padding-top:5px;
       line-height:30px;
       padding-left:17px;
       width:300px;
       height:200px;
       text-align:left;
       background:url(image/surveyN1.png);
      }
  .foot
  {
      height:75px;
      width:920px;
      background:url(image/footbg.gif);
      margin-top:5px;
  }
    .foot a
    {
     font-size:12px;
     color:#000;
     text-decoration:none;
    }
    #sectionContent a
    {
     font-size:13px;
     color:#000;
     text-decoration:none;
    }
    #sectionContent a:hover,#sectionContent a:active
    {
     font-size:13px;
     color:red;
     text-decoration:none;
    }
    
    #newContent a
    {
     font-size:13px;
     color:#000;
     text-decoration:none;
    }
    #newContent a:hover,#sectionContent a:active
    {
     font-size:13px;
     color:red;
     text-decoration:none;
    }
    
    #surveyContent
    {
      width:920px;
      margin-top:2px;
      margin-bottom:2px;
      border:6px solid #CCCC00;
      display:inline-block;
    }    
    #surveyContent a
    {
     font-size:13px;
     color:#000;
     text-decoration:none;
    }
    #surveyContent a:hover,#sectionContent a:active
    {
     font-size:13px;
     color:red;
     text-decoration:none;
    }
    
   .foot a:hover,.foot a:active
    {
     font-size:12px;
     color:#003333;
     text-decoration:none;
    }
    
    .login
    {
     padding-top:2px;
     float:left;
     width:46px;
     height:23px;
     background:url(image/reg_btn.gif) no-repeat;
    }
    .login a,.login a:active
    {
     color:white;
     font-size:16px;
     text-decoration:none;
    }
    .login a:hover
    {
     color:white;
     font-size:16px;
     text-decoration:none;
     cursor:pointer;
    }
    
    .help
    {
      float:left;
    }
   .help a,.help a:active
    {
    color:black;
    font-size:16px;
    text-decoration:none;
    }
    .help a:hover
    {
    color:#cc3300;
    font-size:16px;
    text-decoration:none;
    cursor:pointer;
    }
    #surveyTitle
    {
        width:760px;
        height:30px;
        color:#0099CC;
        font-size:18px;
        margin-top:20px;
        margin-bottom:10px;
        font-weight:bold;
    }
    
    #surveyDes
    {
        width:760px;
        color:#0099CC;
        margin-bottom:20px;
        font-size:12px;
    }
    #newTitle
    {
        color:Blue;
        font-size:18px;
        font-style:italic;
    }
    #sectionTitle
    {
        color:Blue;
        font-size:18px;
        font-style:italic;
    }
    #surveyLine1
    {
      margin-left:0px;
      margin-right:0px;
      margin-top:5px;
      margin-bottom:5px;
    }
     #surveyLine2
    {
      margin-left:0px;
      margin-right:0px;
      margin-top:5px;
      margin-bottom:5px;
    }
    #searchTitle{
	 border:2px solid #0099CC;
	 width:520px;
	 height:34px;
    }
    
    .searchBtn1
    {
     height:34px;
     line-height:34px;
     color:#FFFFFF;
     font-size:16px;
     font-weight:bold;
     width:130px;
     border:0px;
     background:url(image/btn1_1.png);
     cursor:pointer;
    }
    
    .searchBtn1_1
    {
      height:34px;
      line-height:34px;
      color:#FFFFFF;
      border:0px;
      font-size:16px;
      font-weight:bold;
      width:130px;
      background:url(image/btn1.png);  
      cursor:pointer; 
    }
    
    #surveyContentReal
    {
      color:#003333;
      font-size:13px;
      width:920px;
      display:inline-block;
    }
    
    #surveyList
    {
     float:left;
     width:600px;
     border:1px dotted #0099CC;
     display:inline-block;
    }
    
    #surveyList a
    {
      font-weight:bold;
      color:#006633;
      font-size:16px;
    }
    
    #surveyList a:hover,#surveyList a:active
    {
      font-weight:bold;
      color:red;
      font-size:16px;
    }
    
    #surveyNew
    {
     float:left;
     width:305px;
     border:0px dotted #0099CC;
     height:500px;
     margin-left:10px;
     display:inline-block;
    }
    
    #surveyTopFive
    {
     width:300px;
     height:170px;
     margin-top:0px;
     margin-left:8px;
     border:1px solid #009966;
    }
    
    #cooperationDiv
    {
      width:300px;
      border:1px solid #009966;
      margin-top:10px;
      margin-left:8px;
      margin-bottom:5px;
    }
  </style>          

  </head>
  
  <body>
  
   <div align="center" style="margin:0px;padding:0px;width:100%;height:100%;">
   
   <!--导航部分-->
       <div style="height:130px;width:920px;padding-top:0px;background:url(image/bg.gif);">
          <div style="float:left;padding-top:10px;padding-left:20px;">
             <img src="image/logo1.png"/>
          </div>
          <div style="float:right;">
            <div style="margin:0px;padding:0px;height:60px;">
              <div style="float:right;margin-top:10px;margin-right:20px;">
                  <div style="padding:0px;margin:0px;float:left;"><img src="image/login.gif" /></div>
                  <div class="login"><a href="login.jsp">登录</a></div>
                  <div style="float:left;margin-left:20px;"><img src="image/kefu.gif" /></div>
                  <div class="help"><a href="help.html"><b>帮助中心</b></a></div>
              </div>
            </div>
            <div>
              <div class="nav3" id="helpDiv"><b><a href="help.html">帮助中心</a></b></div>
              <div class="nav2" id="surveyDiv"><b><a href='surveyList.jsp'>调查问卷列表</a></b></div>
              <div class="nav1" id="indexDiv"><b><a href='index.jsp'>首页</a></b></div>
            </div>
         </div>
       </div> 
       
     <!--导航部分-->
     
    
 <!--调查问卷内容部分--> 
  <div id='surveyContent'>
   <%
   
     request.setCharacterEncoding("UTF-8");//设置编码格式
     
     OperatorTools tools = new OperatorTools();//工具操作类
     
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
          out.println("<div id = 'surveyTitle'>");
          out.println(tools.getNewString(survey.getSurveyTitle(),120));//打印调查问卷标题
          out.println("</div>");
          out.println("<div id = 'surveyDes' align='left'>");
          out.println(tools.getNewString(survey.getSurveyLink(),180));//打印调查问卷解释说明
          out.println("</div>");
          
          List items = itemOp.getItemList(survey.getSurveyID());//根据调查问卷编号查询调查项列表
          
          if(items.size() == 0)
          {
            out.println("<div style='color:#0099CC;width:759px;height:30px;line-height:29px;font-size:16px;font-weight:bold;' align='center'>不存在调查项!</div>");//显示调查项
          }
          
          out.println("<div style='width:760px;' align='left'>");
          
          out.println("<form name='myform' action='SurveyVisitAction' method='post'>");//表单名称
          
          out.println("<div><input type='hidden' name='surveyid' value='" + survey.getSurveyID() + "'></div>");//调查问卷编号
          
          for(int i = 0; i < items.size(); i++)
          {
              Item item = (Item)items.get(i);//获得调查项内容
              
              out.println("<div style='width:760px;border-bottom:1px dotted #CCCC00;padding-bottom:10px;padding-top:10px;'>");//调查问卷项层
              
              out.println("<div style='color:#0099CC;width:759px;height:30px;line-height:29px;font-size:16px;font-weight:bold;' align='left'>");
              
              out.println((i+1) + ". " + item.getItemCaption());
              
              out.println("</div>");
              
              List answers = new ArrayList();
              
              switch(item.getItemType())//调查项类型
              {
                
                case 0:
                   answers = radioOp.getRadioList(item.getItemID());//根据调查项编号查询单选答案项
                   
                   for(int ri = 0; ri < answers.size(); ri++)
                   {
                     RadioItem rItem = (RadioItem)answers.get(ri);//获得答案项
                     
                     out.println("<div style='color:#0099CC;width:759px;height:30px;font-size:13px;' align='left'>");
                     
                     if(ri == 0)
                     {
                      out.println("<input type='radio' name='radio" + item.getItemID() + "' value='" + rItem.getRadioID() + "' checked='checked'>");                    
                     }
                     else
                     {
                      out.println("<input type='radio' name='radio" + item.getItemID() + "' value='" + rItem.getRadioID() + "'>");                    
                     }
                     
                     out.println(rItem.getRadioCaption());//打印答案项标题
                     
                     out.println("</div>");
                   }
                                     
                   break;
                   
                case 1:
                
                   answers = checkOp.getCheckboxList(item.getItemID());//根据调查项编号查询单选答案项
                   
                   for(int ri = 0; ri < answers.size(); ri++)
                   {
                     CheckboxItem cItem = (CheckboxItem)answers.get(ri);//获得答案项
                     
                     out.println("<div style='color:#0099CC;width:759px;height:30px;line-height:29px;font-size:13px;' align='left'>");
                     
                     if(ri == 0)
                     {
                        out.println("<input type='checkbox' name='checkbox" + item.getItemID() + "' value='" + cItem.getCheckboxID() + "' checked='checked'>");
                     }
                     else
                     {
                       out.println("<input type='checkbox' name='checkbox" + item.getItemID() + "' value='" + cItem.getCheckboxID() + "'>");                     
                     }
                     
                     out.println(cItem.getCheckboxCaption());//打印答案项标题
                     
                     out.println("</div>");
                   }
                   
                   break;
                   
                case 2:
                
                   out.println("<div style='color:#0099CC;width:759px;line-height:29px;font-size:13px;' align='left'>");   
                              
                   out.println("<textarea name='text" + item.getItemID() + "' rows='4' cols='80'>请回答</textarea>");//打印
                   
                   out.println("</div>");
                   
                   break;
                
              }
              
              out.println("</div>");//调查问卷项层
          }
          
          if(items.size() != 0)
          {

            out.println("<div style='color:#0099CC;width:759px;height:30px;line-height:29px;font-size:13px;margin-top:10px;' align='center'>");  
            
            out.println("<input type='submit' name='subButton' value='提交'>");
            out.println("<input type='reset' name='resButton' value='清空'>");
            
            out.println("</div>");
            
          }

          out.println("</form>");
          
          out.println("</div>");
          
          out.println("<div style='margin-top:10px;margin-bottom:10px;width:760px;'>");
          out.println("<a href='showresult.jsp?surveyid=" + surveyid + "'>查看调查问卷结果</a>");//查看调查问卷结果
          out.println("<a href='surveyList.jsp'>返回调查问卷列表</a>");//返回调查问卷列表
          out.println("</div>");
        }
        else
        {
          out.println("调查问卷不存在，请检查！");
        }
     
     }
     else
     {
       out.println("参数传递出错，没有调查问卷编号，请检查!");
     }
     
    %>
  </div>
  <!--调查问卷内容部分-->
    
     <!--结尾部分--> 
       <div class="foot">
           
           <div style="height:52px;margin:0px;" align="center">
           
             <div style="width:25%;float:left;margin-top:5px;">
                 <div style="width:100px;">
                     <div style="float:left;"><img src="image/contact_1.gif"/></div>
                     <div style="float:left;margin-left:8px;height:36px;line-height:36px;vertical-align:middle;">
                        <a href="help.html">购买系统</a>
                     </div>
                  </div>
              </div>
              
              <div style="width:25%;float:left;margin-top:5px;">
               <div style="width:100px;">
                   <div style="float:left;"><img src="image/contact_2.gif"/></div>
                   <div style="float:left;margin-left:8px;height:36px;line-height:36px;vertical-align:middle;">
                     <a href="help.html">客户合作</a>
                  </div>
                </div>
              </div>
              
              <div style="width:25%;float:left;margin-top:5px;" align="center">
                 <div style="width:100px;">
                     <div style="float:left;"><img src="image/contact_3.gif"/></div>
                     <div style="float:left;margin-left:8px;height:36px;line-height:36px;vertical-align:middle;">
                       <a href="help.html">技术支持</a>
                     </div>
                 </div>
              </div>
              
              <div style="width:25%;float:left;margin-top:5px;" align="center">
                <div style="width:100px;">
                    <div style="float:left;"><img src="image/contact_4.gif"/></div>
                    <div style="float:left;margin-left:8px;height:36px;line-height:36px;vertical-align:middle;">
                      <a href="help.html">关于我们</a>
                    </div>
                </div>
              </div>
              
           </div>
           
           <div style="height:14px;margin:0px;">
             <a href="help.html">版权所有&copy;然然工作室</a>
           </div>
       </div>
       <!--结尾部分--> 
      </div>
  </body>
</html>
