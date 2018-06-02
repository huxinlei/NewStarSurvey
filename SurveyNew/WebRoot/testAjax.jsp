<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Ajax技术测试</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script language="javascript">
	   var xmlHttpRequest;
	   
	   function createXmlHttpRequest(){
	   
	     try{
	         return new ActiveXObject('Msxml2.XMLHTTP');
	     }catch(e){
	     
	        try{
	            return new ActiveXObject('Microsoft.XMLHTTP');
	        }catch(e1){
	            
	            try{
	            
	               return new XMLHttpRequest();
	            }catch(e2){}
	        }
	        
	     }
	   
	   }
	   
	   function getResult(){
	       var option = document.getElementById("result").value;
	       
	       var url = "testAjax1.jsp";
	       
	       xmlHttpRequest = createXmlHttpRequest();
	       
	       xmlHttpRequest.onreadystatechange = showlist;
	       
	       xmlHttpRequest.open("GET", url, true);
	       
	       xmlHttpRequest.send(null);
	   }
	   
	   function showlist(){
	   
	      if(xmlHttpRequest.readyState == 4){
	        if(xmlHttpRequest.status == 200){
	           var inhtml = xmlHttpRequest.responseText;
	           
	           document.getElementById("result").innerHTML = inhtml;
	        }
	        else
	        {
	             alert("请求发送异常，异常编号：" + xmlHttpRequest.status);
	        }
	      }
	   }
	   
	   function init(){
	      window.setInterval("getResult()", 1000);
	   }
	   
	   function showtitle(){
	      window.alert("hhhhhhh");
	   }
	</script>

  </head>
  
  <body onLoad="init()">
    <div id="result"></div>
  </body>
</html>
