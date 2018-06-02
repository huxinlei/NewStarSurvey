//关闭贴字条窗口
function close_window(){
    showCheckCodeClear();//设置验证码显示框不显示
    
    initialzation();
}

//打开贴字条窗口
function loadScripAdd_window(){
/*****************显示其他内容不许点击的图层(用于锁定许愿墙中的内容)***********************/
     document.getElementById("notClickDiv").style.display="block";
     var w = document.body.clientWidth;
     var h = document.body.clientHeight;
     document.getElementById("notClickDiv").style.width=w;
     document.getElementById("notClickDiv").style.height=h;
     rsCount=document.getElementById("hRsCount").value;
     document.getElementById("notClickDiv").style.zIndex=rsCount+101;
     
     /********显示添加字条的图层**************/
     var width=screen.width;
     var height=screen.height;
     document.getElementById("scrip_add").style.display="block";
     document.getElementById("scrip_add").style.top=(height-310-240)/2;
     document.getElementById("scrip_add").style.left=(width-500)/2-120;
     document.getElementById("scrip_add").style.zIndex=rsCount+102;
     
     /*****************显示预览字条的图层*******************/
     document.getElementById("preview").style.display = "block";
     document.getElementById("preview").setAttribute("class", "Style0");
     document.getElementById("preview").style.top=(height-310-240)/2;
     document.getElementById("preview").style.left=(width-240)/2+290;
     document.getElementById("preview").style.zIndex=rsCount+103;
}

function InputInfo(OriInput, GoalArea){
     document.getElementByIf(GoalArea).innerHTML = OriInput.value;
}

/********************限制字条字节数**********************/

var LastCount=0;

function CountStrByte(info, Total, Used, Remain){//字节统计
   var ByteCount=0;
   var StrValue=info.value;
   var StrLength=info.value.length;
   var MaxValue=Total.value;
   if(LastCount != StrLength){//在此判断，减少循环次数
      for(i = 0; i < StrLength; i++){//计算祝福文字个数，英文数字占1个字符，汉字占2个字符
          ByteCount = (StrValue.charCodeAt(i) <= 256)?ByteCount + 1: ByteCount + 2;
          
          if(ByteCount>MaxValue){
           
            info.value=StrValue.substring(0,i);
              
              document.getElementById("pContext").innerHTML = info.value;//重新设置预览字条上的内容 
              
              alert("字条内容最多不能超过" + MaxValue + "个字节！\n注意：一个汉字为两字节。");
              
              ByteCount=MaxValue;
              
              break;
          }
      }
      
      Used.value = ByteCount;//已用字节
      Remain.value=MaxValue-ByteCount;//剩余字节
      LastCount=StrLength;
      document.getElementById("pContext").innerHTML=StrValue;//将字条内容时显示到预览字条上
   }
}

/******************************************/

function ColorChoose(n){
    //修改字条背景
    var ClassName = "Style" + n;
    
    document.getElementById("preview").setAttribute("class", ClassName);
    document.getElementById("preview").setAttribute("className", ClassName);
    scripForm.color.value=n;
}

function faceChoose(n){
     //修改心情图案
     var Url = "images/face/face_"+n+".gif";
     
     document.getElementById("pFace").src=Url;
     
     scripForm.face.value=n;
}

//初始化添加字条的表单
function initialization(){
        scripForm.wisMan.value="";
        scripForm.wellWisher.value="匿名";
        scripForm.color.value=0;
        scripForm.face.value=0;
        scripForm.content.value="";
        scripForm.used.value=0;
        scripForm.remain.value=200;
        
        /***********初始化验证码******************************/
        var loader1 = new net.AjaxRequest("getPictureCheckCode.jsp?nocache="+ new Date().getTime(), deal_getCheckCode, onerror, "GET");//刷新验证码
        
        scripForm.checkCode.value="";
        document.getElementById("messageImg").src="images/tishi2.gif";
        document.getElementById("resultMessage").removeChild(document.getElementById("resultMessage").childNodes[0]);
        document.getElementById("resultMessage").appendChild(document.createTextNode("温馨提示：单击验证码输入框，获取验证码！"));
        
        /**************************************************/
        document.getElementById("btn_Submit").disabled=true;//设置"保存"按钮不可用
        document.getElementById("preview").style.display="none"; //隐藏字条预览窗口
        document.getElementById("scrip_add").style.display="none";//隐藏贴字条窗口
        document.getElementById("notClickDiv").style.display="none";//隐藏显示锁定许愿墙内容的图层
        /***********************初始化字条预览窗口*********************/
        document.getElementById("preview").setAttribute("class", "Style0");
        document.getElementById("preview").setAttribute("className", "Style0");
        document.getElementById("pFace").src="images/face/face_0.gif";
        document.getElementById("pWishMan").innerHTML="";
        document.getElementById("pWellWisher").innerHTML="匿名";
        document.getElementById("pContext").innerHTML="";
        
}

function getTime(){
   //获得当前时间
   var ThisTime = new Date();
   return ThisTime.getFullYear() + "-" + (ThisTime.getMonth() + 1) + "-" + ThisTime.getDate();
   
}

/*****************保存字条**************************/
function scripSubmit(){
      
      if(scripForm.wishMan.value==""){//验证祝福对象是否为空
         alert("请输入祝福对象！");
         scripForm.wishMan.focus();
         return false;
         
      }
      
      if(scripForm.wellWisher.value==""){//验证祝福者是否为空，如果为空将其赋值为"匿名"
         scripForm.wellWisher.value="匿名";         
      }
      
      if(scripForm.content.value==""){//验证字条内容是否为空 
         alert("请输入字条内容！");
         scripForm.content.focus();
         return false;
      }
      
      var param="wishMan" + scripForm.wishMan.value + "&wellWisher=" + scripForm.wellWhisher.value + "&color=" + scripForm.face.value + "&content=" + scripForm.content.value;//将许愿字条的内容连接成字符串，作为发送请求的参数
      
      var loader=new net.AjaxRequest("scrip.do?action=scripAdd", deal_s, onerror, "POST", param);
}

function onerror(){
   alert("您的操作有误");
}

function deal_s(){

   /*******************获取字条编号***************************/
   
    var h = this.req.responseText;
    
    h=h.replace(/\s/g,"");//去除字符串中的Unicode空白符
    
    var id = h.substr(h.indexOf("[")+1,h.indexOf("]")-h.indexOf("[")-1);
    
    /***********************************************/
    
    if(h!="很抱歉，您的字条发送失败！"){
    
      alert(h);//输出提示信息
      
      createNewScrip(id);//将新添加的字条显示到许愿墙上
      
      Show(id, "shadeDiv");//设置添加的字条突出显示
    }
    
    initialization();//初始化添加字条窗口
}

/******************生成验证码并检测验证码是否正确*****************************/
function getCheckCodeFun(showCheckCode, checkCode){
        
        showCheckCode.style.display='';
        
        checkCode.focus();
}

function getCheckCode1(showCheckCode, checkCode){

        var loader1 = new net.AjaxRequest("getPictureCheckCode.jsp?nocache=" + new Date().getTime(), deal_getCheckCode, onerror, "GET");
        
        showCheckCode.style.display='';
        
        checkCode.focus();
}


function deal_getCheckCode(){
       document.getElementById("showCheckCode").innerHTML=this.req.responseText;
}

function showCheckCodeClearr(){
       showCheckCode.style.display='none';
}

/*******************验证验证码是否正确**********************/
function checkCheckCode(inCheckCode){

   if(inCheckCode != "")
   {
       var loader = new net.AjaxRequest("checkCheckCode.jsp?inCheckCode=" + inCheckCode + "&nocache=" + new Date().getTime(), deal_checkCheckCode, onerror, "GET");
   }
}

function deal_checkCheckCode(){

  var resultValue = this.req.responseText;
  
  if(resultValue == 1){//返回值为1时，表示验证码正确
  
     document.getElementById("resultMessage").removeChild(document.getElementById("resultMessage").childNodes[0]);
     document.getElementById("resultMessage").appendChild(document.createTextNode(" "));
     document.getElementById("messageImg").src="images/dui2.gif";
     document.getElementById("resultMessage").removeChild(document.getElementById("resultMessage").childNotes[0]);
     document.getElementById("resultMessage").appendChild(document.createTextNode("验证码正确！"));
     ducument.getElementById("btn_Submit").disabled=false;
  }
  else
  {
     document.getElementById("messageImg").src="images/cuo2.gif";
     document.getElementById("resultMessage").removeChild(document.getElementById("resultMessage").childNodes[0]);
     document.getElementById("resultMessage").appendChild(document.createTextNode("您输入的验证码不正确！"));
     
  }
}

/*************************************************/