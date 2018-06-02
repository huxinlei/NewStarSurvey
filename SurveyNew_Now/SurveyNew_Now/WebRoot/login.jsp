<%@ page language="java" import="java.util.*,edu.bdu.dao.UserDaoImpl,edu.bdu.entity.User" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户登录验证</title>
    <link href="JS/css/start/jquery-ui.css" rel="stylesheet" />
	<script type="text/javascript" src="JS/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="JS/js/jquery-ui-1.10.2.custom.js"></script>
  <script language="javascript">
    
    //处理按钮样式显示
    $(function(){
    
    function showErrorMessage(code)
    {
      switch(code)
      {
        case 1:
          $("#errorMessage1").css("display","inline");
          //document.getElementById("errorMessage1").style.display = "inline";
          //alert($("#errorMessage1").css("display"));
          
          break;
        case 2:
          $("#errorMessage2").css("display","inline");    
          //document.getElementById("errorMessage2").style.display = "inline";          
          //alert($("#errorMessage2").css("width"));
             
          break;
      }
    }
           //登录按钮变色
           $("#subBtn").mouseover(function () {
                if ($(this).hasClass("loginBtn")) {
                    $(this).removeClass("loginBtn").addClass("loginBtn1"); //鼠标到按钮上时改成样式loginBtn1
                }
            });
            
            $("#subBtn").mouseout(function () {
                if ($(this).hasClass("loginBtn1")) {
                    $(this).removeClass("loginBtn1").addClass("loginBtn");//当鼠标离开按钮时样式改成loginBtn
                }
            });
            
            //重置按钮变色
           $("#resBtn").mouseover(function () {
                if ($(this).hasClass("loginBtn")) {
                    $(this).removeClass("loginBtn").addClass("loginBtn1"); //鼠标到按钮上时改成样式loginBtn1
                }
            });
            
            $("#resBtn").mouseout(function () {
                if ($(this).hasClass("loginBtn1")) {
                    $(this).removeClass("loginBtn1").addClass("loginBtn");//当鼠标离开按钮时样式改成loginBtn
                }
            });
            
            /*$("#errorMessage1").mouseout(function(){
               $("#errorMessage1").html("我的测试");
               showErrorMessage(1);
               alert($("#errorMessage1").css("display"));
            });*/
            
      <%
     String errorCode = request.getParameter("errorCode");//获得错误代码
     
     if(errorCode != null)
     {
        int code = Integer.parseInt(errorCode);//获得错误代码
        
        switch(code)
        {
          case 1:

              out.println("showErrorMessage(1);");//用户名或者密码错误
              
              break;
              
         case 2:

             out.println("showErrorMessage(2);");//验证码错误
                          
             break;
          
        }
     }
  %>            
            
            
    });
    
    /*
    function dyniFrameSize(down)
    {
      var pTar = null;
      
      if(document.getElementById)
      {
        pTar = document.getElementById(down);
      }
      else
      {
       eval('pTar = ' + down + ';');
      }
      
      if(pTar && !window.opera)
      {
       pTar.style.display = 'block';
       
       if(pTar.contentDocument && pTar.contentDocument.body.offsetHeight)
       {
          pTar.height = pTar.contentDocument.body.offsetHeight;
          
          pTar.width = pTar.contentDocument.body.scrollWidth;
       }
       else if(pTar.Document && pTar.Document.scrollHeight)
       {
         pTar.height = pTar.Document.body.scrollHeight;
         pTar.width = pTar.Document.body.scrollWidth;
       }
       
      }
      
    }*/
    
    //输出错误提示
    /*function showErrorMessage(code)
    {
      switch(code)
      {
        case 1:
          //$("#errorMessage1").css("display","inline");
          //document.getElementById("errorMessage1").style.display = "inline";
          alert($("#errorMessage1"));
          
          break;
        case 2:
          //$("#errorMessage2").css("display","inline");    
          //document.getElementById("errorMessage2").style.display = "inline";          
          alert($("#errorMessage2").css("width"));
             
          break;
      }
    }*/
    //showErrorMessage(2);

  </script>
  
      <style type="text/css">
       .adminbg
       {
         width:100%;
         height:100px;
         padding:0px;
         margin-top:0px;
         margin-left:0px;
         margin-right:0px;
         background:url(image/adminbg3.png) repeat-x;
         display:inline-block;
       }
       body
       {
         margin:0px;
         padding:0px;
        
       }
       .adminM
       {
         height:30px;
         margin-top:59px;
         margin-left:0px;
         margin-bottom:0px;
       }
       .adminM a
       {

         font-size:16px;
         color:#DDDDDD;
         text-decoration:none;
       }
       .adminM a:hover
       {
         font-size:16px;
         color:#FFFFFF;
         text-decoration:none;
       }
       .adminM a:active
       {
         font-size:16px;
         color:#DDDDDD;
         text-decoration:none;
       }
       .adminManager
       {
         margin-top:10px;
         width:100%;
         display:inline-block;
       }
       
    .foot
    {

    }
    .foot a
    {
     font-size:13px;
     color:#696969;
     text-decoration:none;
    }
    
    .foot a:hover,.foot a:active
    {
     font-size:13px;
     color:#003333;
     text-decoration:none;
    }
    
    .loginBtn
    {
     width:99px;
     height:32px;
     border:0px;
     font-size:16px;
     color:#FFFFFF;
     background:url(image/v_t_line2bnt.gif) no-repeat;
     cursor:pointer;
    }
    
    .loginBtn1
    {
     width:99px;
     height:32px;
     border:0px; 
     font-size:18px;
     color:#FFFFFF;    
     background:url(image/v_t_line2bnt_hover.gif) no-repeat;
     cursor:pointer;
    }
    
    #errorMessage1
    {
       width:478;
       height:38px;
       line-height:38px;
       font-size:14px;
       color:red;
       display:none;
    }
    
    #errorMessage2
    {
       width:478;
       height:38px;
       line-height:38px;
       font-size:14px;
       color:red;
       display:none;   
    }
    </style>
    
  </head>
  
  <body>
  
      <div id="adminMain" style="width:100%;">
         <div class="adminbg" align="center">
            <div style="float:left;margin-left:50px;width:300px;height:82px;margin-top:4px;">
              <img src="image/adminlogo.png" />
            </div>
            <div style="float:left;height:99px;width:600px;">
              <div class="adminM">
              </div> 
            </div>
         </div>
       
         <div id="adminManager" class="adminManager" align="center">
         
         <div style="width:500px;height:400px;background:url(image/adminCN1.png) no-repeat;">
         
             <div style="width:499px;height:40px;color:#FFFFFF;line-height:38px;font-size:18px;font-weight:bold;">
                用户登录
             </div>
             
             <div style="">
               <form name='myform' action='loginaction.jsp' method='post'>
                     
                     <table width="482" border="0" style='color:#0099CC;font-size:16px;font-weight:bold;'>
                     
                      <tr width="480" height="40">
                       <td  align="center">用户名称：<input type = 'text' style='width:200px;' name = 'username' value = ''></td>
                      </tr>
                      <tr width="480" height="40">
                       <td  align="center">用户密码：<input type = 'password' style='width:200px;' name = 'userpassword' value = ''></td>
                      </tr>
                      <tr width="480" height="80">
                       <td align="center"><span style='margin-top:20px;margin-bottom:20px;'>验证码:<input type='text' style='width:60px;' name='checkcode' value=''></span>
                         <span><iframe name="codeframe" src= "ImageCode" scrolling="auto" width="120" height="60" marginheight="0" marginwidth="0" frameborder="0"></iframe></span>
                       </td>
                      </tr> 
        
                      <tr width="480" height="30">
                        <td align="center">
                           <a href="ImageCode" target="codeframe">看不清，换一张</a> 
                        </td>
                      </tr>
                      <tr  width="480" height="40">
                         <td align="center">
                            <input type='submit'id="subBtn" class="loginBtn" name='subButton' value='登录'>
                            <input type='reset' id="resBtn" class="loginBtn" name='resButton' value='重置'>
                         </td>
                       </tr>
                       <tr width="480" height="40">
                         <td>
                            <div id="errorMessage1" align="center">用户名和密码错误!</div>
                            <div id="errorMessage2" align="center">验证码错误!</div>
                         </td>
                       </tr>
                     </table>
                  </form>
                </div>

        <!--登录div-->
           </div>
         </div>
         
         
         <div id="adminFooter" align="center" class="foot">
           <div style="width:920px;line-height:29px;margin-top:30px;height:30px;border-top:1px dotted black;" align="left">
          
              <span><a href="help.html">购买系统</a></span>
              <span><a href="help.html">客户合作</a></span>
              <span><a href="help.html">技术支持</a></span>
              <span><a href="help.html">关于我们</a></span>
            
           </div>
           
           <div style="width:920px;height:16px;border-top:1px dotted black;">
                        <a href="help.html">版权所有&copy;然然工作室</a>
           </div>
         </div>
      </div>
  </body>
</html>
