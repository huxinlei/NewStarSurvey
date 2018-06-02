package edu.bdu.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.bdu.dao.SurveyDaoImpl;
import edu.bdu.dao.UserDaoImpl;
import edu.bdu.entity.User;
import edu.bdu.entity.Survey;
import java.util.List;
import java.util.Date;
import edu.bdu.entity.User;

public class AdminIndexAction extends HttpServlet {
    /**
     * 初始化
     */
	private SurveyDaoImpl surveyOp;//调查问卷操作类
	
	private UserDaoImpl userOp;//用户操作类
	
	private int titleSize = 9;//标题显示的最大字符数
	
	private int linkSize = 9;//连接显示的最大字符数
	
	private int nameSize = 9;//用户名显示的最大字符数
	
	private int passSize = 9;//用户密码显示的最大字符数

	public AdminIndexAction() {
		super();
	}


	public void destroy() {
		super.destroy();

	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        doPost(request,response);//调用doPost方法进行处理
		/*response.setContentType("text/html");
		PrintWriter out = response.getWriter();//获取输出流
		out.println("hello");
		out.flush();
		out.close();*/
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");//设置编码格式
		response.setContentType("text/html");
		HttpSession session = request.getSession();//获得请求session
		PrintWriter out = response.getWriter();//获取输出流
		
		int commType = Integer.parseInt(request.getParameter("comType"));//获得操作类型码
		
		switch(commType)
		{
		case 1:
			
	        out.println(getSurveyListTen());//获得调查问卷列表
	        
			break;
		case 2:
			
			out.println(getUserListTen());//获得最新添加的用户列表
			
			break;
		case 3:
			
	        User user = (User)session.getAttribute("user");//获得当前用户信息
	        
	        String userInfo = "{\"userID\":\"" + user.getUserID() + "\","   +
	        		"\"userName\":\"" + user.getUserName() + "\"," +
	        		"\"userPass\":\"" + user.getUserPassword() + "\"," +
	        	    "\"userType\":\"" + user.getUserType() + "\"}";
	        
	        if(user != null)
	           out.print(userInfo);//获得用户名称
	        else
	           out.print("usererror");//用户身份不正确
	        
			break;
			
		case 4:
			
            session.invalidate();//用户session设置失效
            
            out.print("loginout");//用户退出成功
			
			break;
			
		case 5:
	        User user1 = new User();//创建用户对象
	        
	        if(request.getParameter("userid")!= null)
	        {
	           int userid = Integer.parseInt(request.getParameter("userid"));//获得用户编号
	           
	           user1.setUserID(userid);//用户编号赋值
	        }
	        
	        if(request.getParameter("username")!=null)
	        {
	            String username = request.getParameter("username");
	            
	            user1.setUserName(username);//为用户名称赋值
	        }
	        
	        if(request.getParameter("userpassword")!=null)
	        {
	            String userpassword = request.getParameter("userpassword");
	            
	            user1.setUserPassword(userpassword);//为用户密码赋值 
	        }
	        
	        if(request.getParameter("usertype")!= null)
	        {
	           String usertype = request.getParameter("usertype");
	           
	           int usertypeId = Integer.parseInt(usertype);
	           
	           user1.setUserType(usertypeId);//为用户类型赋值
	        }
	        
	        this.userOp = new UserDaoImpl();//用户操作类
	        
	        if(userOp.updateUser(user1))
	        {
	          session.setAttribute("user",user1);//将新的用户信息放入session
	          //response.sendRedirect("testuserdao.jsp");
	          out.print("success");//输出成功标志
	        }
	        else
	        {
	          out.println("fail");//输出失败标志
	        }
			
			break;
			
		default:
			break;
		}
		
		out.flush();
		out.close();
	}
	

	//获得最新添加的用户
	private String getUserListTen()
	{
		 String results = "";//获得头十条调查问卷
		
	     UserDaoImpl userop = new UserDaoImpl();//用户数据库操作类
	     
	     List userlist = userop.getUsersNotAdmin();//获得用户列表
	     
	     int userListSize = 10;//显示调查问卷条数
	     

	     results += "<table border='0' cellpadding='0' cellspacing='0' class='tableClass'>";
	     results += "<tr align='center' class='tdHeader'>";
	     results += "<td align='center' class='tdHeader'>用户编号</td>";
	     results += "<td align='center' class='tdHeader'>用户名称</td>";
	     results += "<td align='center' class='tdHeader'>用户密码</td>";
	     results += "<td align='center' class='tdHeader'>操作</td>";
	     results += "<td align='center' class='tdHeader'>操作</td>";
	     results += "<td align='center' class='tdHeader'>操作</td>";
	     results += "</tr>";
	     
	     if(userListSize > userlist.size())
	     {
	    	 userListSize = userlist.size();//获得用户列表
	     }
	     
	     for(int i = 0;i < userListSize;i++)
	     {
	         User user = (User)userlist.get(i);//获得用户信息
	         
	         results += "<tr class='trContent'>";
	         
	         results += "<td class='tdContent' align='center'>" + user.getUserID() + "</td>";
	         
		     String userName = user.getUserName().length()<= nameSize?user.getUserName():user.getUserName().substring(0,nameSize);
	         
	         results += "<td class='tdContent' align='center'>" + userName + "</td>";
	         
		     String userPass = user.getUserPassword().length()<= passSize?user.getUserPassword():user.getUserPassword().substring(0,passSize);	         
	         
	         results += "<td class='tdContent' align='center'>" + userPass + "</td>";
	       
	         results += "<td class='tdContent' width='30' align='center'>" + "<a href='javascript:getUserDataById1(" + user.getUserID() + ")'>查看</a>" + "</td>";
	         results += "<td class='tdContent' width='30' align='center'>" + "<a href='javascript:getUserDataById(" + user.getUserID() + ")'>修改</a>" + "</td>";
	         results += "<td class='tdContent' width='30' align='center'>" + "<a href='javascript:deleteUserDataById(" + user.getUserID() + ")'>删除</a>" + "</td>";
	         
	         results += "</tr>";
	       
	     }
	     results +="</table>";
		
		return results;
	}
	//获得最新十条调查问卷
	private String getSurveyListTen()
	{
		String results = "";//获得头十条调查问卷
		
	     SurveyDaoImpl surveyOp = new SurveyDaoImpl();//调查问卷操作类
	     
	     List surveylist = surveyOp.getServeyList();//获得调查问卷列表
	     
	     int surveyListSize = 10;//显示调查问卷条数
	     
	     if(surveyListSize > surveylist.size())
	     {
	    	 surveyListSize = surveylist.size();//获得调查列表的实际大小
	     }
	     
	     results +="<table border='0' cellpadding='0' cellspacing='0' class='tableClass'>";
	     results +="<tr>";
	     results +="<td align='center' class='tdHeader'>问卷标题</td>";
	     results +="<td align='center' class='tdHeader'>问卷说明</td>";
	     results +="<td align='center' class='tdHeader'>创建时间</td>";
	     results +="<td align='center' class='tdHeader'>操作</td>";
	     results +="<td align='center' class='tdHeader'>操作</td>";
	     results +="<td align='center' class='tdHeader'>操作</td>";
	     results +="</tr>";
	     for(int i = 0;i < surveyListSize;i++)
	     {
	       Survey survey = (Survey)surveylist.get(i);//获得用户信息
	       
	       results +="<tr class='trContent'>";
	       
	       String surveyTitle = survey.getSurveyTitle().length()<= titleSize?survey.getSurveyTitle():survey.getSurveyTitle().substring(0,titleSize);
	       
	       results +="<td class='tdContent' align='center'>" + surveyTitle + "</td>";
	       
	       String surveyLink = survey.getSurveyLink().length()<=linkSize?survey.getSurveyLink():survey.getSurveyLink().substring(0,linkSize);
	       results +="<td class='tdContent' align='center'>" + surveyLink + "</td>";
	       
	       Date date = survey.getSurveyCreateDate();//调查问卷创建时间
	       
	       if(date != null)
	         results +="<td class='tdContent' align='center'>" + date.toString() + "</td>";
	       else
	         results +="<td class='tdContent' align='center'>" + "创建时间异常" + "</td>";
	       
	     
	         
	       results +="<td class='tdContent' width='30' align='center'>" + "<a href='javascript:updateSurveyDataById(" + survey.getSurveyID() + ")'>修改</a>" + "</td>";
	       results +="<td class='tdContent' width='30' align='center'>" + "<a href='javascript:deleteSurveyDataById(" + survey.getSurveyID() + ")'>删除</a>" + "</td>";
	       results +="<td class='tdContent' width='30' align='center'>" + "<a href='../showsurvey.jsp?surveyId=" + survey.getSurveyID() + "'>预览</a>" + "</td>";
	       results +="</tr>";
	       
	     }
	     results +="</table>";
		
		return results;
	}
	
	
    //初始化
	public void init() throws ServletException {
		
	}

}
