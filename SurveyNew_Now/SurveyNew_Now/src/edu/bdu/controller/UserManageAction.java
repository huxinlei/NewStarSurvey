package edu.bdu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.bdu.dao.SurveyDaoImpl;
import edu.bdu.dao.UserDaoImpl;
import edu.bdu.dao.VisitorDaoImpl;
import edu.bdu.entity.Survey;
import edu.bdu.entity.User;


public class UserManageAction extends HttpServlet {
	
   /**
     * 初始化
     */
	private UserDaoImpl userOp;//调查问卷操作类
	
	private int userCount = 0;//获得调查问卷总数
	
	private int pageSize = 15;//一页显示10个调查问卷
	
	private int pageFirstIndex = 0;//默认当前第一条调查问卷
	
	private int currentPageNum = 1;//默认显示第一页
	
	private int pageCount = 0;//调查问卷总页数
	
	private int pageListSize = 7;//7页一个菜单栏
	
	private int pageListIndex = 1;//默认显示的第一个页码
	
	private int currentPageListNum = 1;//默认显示第一个菜单栏
	
	private int pageListCount = 0;//总菜单栏个数
	
	private int textPageSize = 5;//默认文本答案项的长度
	
	private int searchPageSize = 10;//一页显示十条
	
	private int titleSize = 18;//标题显示的最大字符数
	
	private int linkSize = 18;//连接显示的最大字符数
	
	private int nameSize = 18;//用户名显示的最大字符数


	public UserManageAction() {
		super();
	}


	public void destroy() {
		super.destroy(); 

	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        doPost(request,response);//调用doPost方法进行处理
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");//设置编码格式
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int commType = Integer.parseInt(request.getParameter("comType"));//获得操作类型码
		
		switch(commType)
		{
		case 1:
			
			int pageNum = Integer.parseInt(request.getParameter("pageNum"));//获得当前请求页码
			
			int pageListNum = Integer.parseInt(request.getParameter("pageListNum"));//获得当前请求菜单码
			
	        initParameters(pageNum,pageListNum);//调查问卷页和菜单页信息初始化
			
			out.println(getPageByNum(pageNum,pageListNum));//获得调查问卷列表
			
			out.println(getPageBottomByNum(pageNum,pageListNum));//显示底部的标题
	        
			break;
		case 2:
			
			out.print(getUserCount());//输出普通管理员总数
			
			break;
		case 3:
			
	        userOp = new UserDaoImpl();//普通管理员操作类
	        
			String userNum2 = request.getParameter("userId");
			
			int userId2 = Integer.parseInt(userNum2);//获得普通管理员编号
			
	        User user2 = userOp.getUser(userId2);
	        
	        String userInfo = "{\"userID\":\"" + user2.getUserID() + "\","   +
	        		"\"userName\":\"" + user2.getUserName() + "\"," +
	        		"\"userPass\":\"" + user2.getUserPassword() + "\"," +
	        	    "\"userType\":\"" + user2.getUserType() + "\"}";
	        
	        if(user2 != null)
	           out.print(userInfo);//获得用户名称
	        else
	           out.print("usererror");//用户身份不正确
	        
			break;

		case 4:
			
			String userNum = request.getParameter("userId");
			
			int userId = Integer.parseInt(userNum);//获得普通管理员编号
			
	        userOp = new UserDaoImpl();//普通管理员操作类
	           
	        if(userOp.deleteUser(userId))//删除访问者信息
	        {
	           out.println("<script type='text/javascript'>alert('管理员信息已成功删除!');getUserList();getUserCount();</script>");
	        }
	        else
	        {
	           out.println("<script type='text/javascript'>alert('管理员信息由于异常没能成功删除!');</script>");
	         }
			
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
	        

	           
	        user1.setUserType(0);//为用户类型赋值

	        
	        this.userOp = new UserDaoImpl();//用户操作类
	        
	        if(userOp.InsertUser(user1))
	        {
	          out.print("success");//输出成功标志
	        }
	        else
	        {
	          out.println("fail");//输出失败标志
	        }
			
			break;	
			
		case 6:
			
	        User user3 = new User();//创建用户对象
	        
	        if(request.getParameter("userid")!= null)
	        {
	           int userid = Integer.parseInt(request.getParameter("userid"));//获得用户编号
	           
	           user3.setUserID(userid);//用户编号赋值
	        }
	        
	        if(request.getParameter("username")!=null)
	        {
	            String username = request.getParameter("username");
	            
	            user3.setUserName(username);//为用户名称赋值
	        }
	        
	        if(request.getParameter("userpassword")!=null)
	        {
	            String userpassword = request.getParameter("userpassword");
	            
	            user3.setUserPassword(userpassword);//为用户密码赋值 
	        }
	        
	        if(request.getParameter("usertype")!= null)
	        {
	           String usertype = request.getParameter("usertype");
	           
	           int usertypeId = Integer.parseInt(usertype);
	           
	           user3.setUserType(usertypeId);//为用户类型赋值
	        }
	        
	        this.userOp = new UserDaoImpl();//用户操作类
	        
	        if(userOp.updateUser(user3))
	        {
	          //response.sendRedirect("testuserdao.jsp");
	          out.print("success");//输出成功标志
	        }
	        else
	        {
	          out.println("fail");//输出失败标志
	        }
			
			break;
			
		case 7:
			String userNum7 = request.getParameter("userId");
			
			int userId7 = Integer.parseInt(userNum7);//获得普通管理员编号
			
	        userOp = new UserDaoImpl();//普通管理员操作类
	           
	        if(userOp.deleteUser(userId7))//删除访问者信息
	        {
	           out.println("<script type='text/javascript'>alert('管理员信息已成功删除!');getUserListTen();</script>");
	        }
	        else
	        {
	           out.println("<script type='text/javascript'>alert('管理员信息由于异常没能成功删除!');</script>");
	         }			
			break;
			
		case 8:
	        userOp = new UserDaoImpl();//普通管理员操作类
	        
			String userNum8 = request.getParameter("userId");
			
			int userId8 = Integer.parseInt(userNum8);//获得普通管理员编号
			
	        User user8 = userOp.getUser(userId8);
	        
	        SurveyDaoImpl surveyOp8 = new SurveyDaoImpl();//获得调查问卷操作类
	        
	        List surveyList = surveyOp8.getServeyList(userId8);
	        
	        String userInfo8 = "<table width='100%' height='100%' style='color:#0099CC;font-size:12px;border-top:1px solid #0099CC;border-bottom:1px solid #0099CC;'>";
	        userInfo8 += "<tr><td>用户编号</td><td>" + user8.getUserID() + "</td></tr>"; 
	        userInfo8 += "<tr><td>用户名称</td><td>" + user8.getUserName() + "</td></tr>";
	        userInfo8 += "<tr><td>用户密码</td><td>" + user8.getUserPassword() + "</td></tr>"; 
	        userInfo8 += "<tr><td>用户类型</td><td>普通管理员</td></tr>"; 
	        userInfo8 += "<tr><td>创建调查问卷总数</td><td>" + surveyList.size() + "</td></tr>";	
	        if(surveyList.size() > 0)
	        {
	          Survey survey8 = (Survey)surveyList.get(0);//获得最近修改的调查问卷	
		      String surveyName = survey8.getSurveyTitle().length()<= nameSize?survey8.getSurveyTitle():survey8.getSurveyTitle().substring(0,nameSize);
		      userInfo8 += "<tr><td>最近修改的调查问卷</td><td>" + surveyName + "</td></tr>";
	        }
            userInfo8 += "</table>";
	        
	        if(user8 != null)
	           out.print(userInfo8);//获得用户名称
	        else
	           out.print("usererror");//用户身份不正确
			break;
		default:
			break;
		}

		out.flush();
		out.close();
	}

	//根据当前页码和菜单栏码确定显示要显示的管理员
	private String getPageByNum(int pageNum,int pageListNum)
	{
		String results = "";//设置访问结果
		
        initParameters(pageNum,pageListNum);//管理员和菜单页信息初始化
		
		this.userOp = new UserDaoImpl();//实例化管理员操作类
		
		List userList = this.userOp.getUsersNotAdmin();//获得管理员列表
		
		int listNum = this.pageSize;//默认显示列表总数
		
		int lastNum = this.userCount % this.pageSize;//获得最后一页管理员个数
		
		if(this.pageCount == this.currentPageNum)
		{
		   if(lastNum < listNum)
		   {
			   listNum = lastNum;//设置列表为当前页的管理员个数
		   }
		}
		
		//管理员总数刚好是每页管理员数的整数倍
		if(listNum == 0 && this.userCount != 0)
		{
			listNum = this.pageSize;//显示列表总数
		}
		
		if(this.userCount == 0)
		{
		  listNum = 0;
		}
		
		
		//表格显示内容
	     results +="<div style='margin-top:10px;margin-bottom:10px;'><table border='0' cellpadding='0' cellspacing='0' class='tableClass'>";
	     results +="<tr>";
	     results +="<td align='center' class='tdHeader'>用户编号</td>";
	     results +="<td align='center' class='tdHeader'>用户名称</td>";
	     results +="<td align='center' class='tdHeader'>用户密码</td>";
	     results +="<td align='center' class='tdHeader'>用户类型</td>";
	     results +="<td align='center' class='tdHeader'>操作</td>";
	     results +="<td align='center' class='tdHeader'>操作</td>";
	     results +="<td align='center' class='tdHeader'>操作</td>";
	     results +="</tr>";
	     
		
		for(int i = this.pageFirstIndex; i < this.pageFirstIndex + listNum; i++)
		{
		       User user = (User)userList.get(i);//获得用户信息
		       
		       results += "<tr class='trContent'>";
		       
		       results += "<td class='tdContent' align='center'>" + user.getUserID() + "</td>";
		       
		       String userName = user.getUserName().length()<= nameSize?user.getUserName():user.getUserName().substring(0,nameSize);
		       
		       results += "<td class='tdContent' align='center'>" + userName + "</td>";
		       
		       String userPass = user.getUserPassword().length()<= nameSize?user.getUserPassword():user.getUserPassword().substring(0,nameSize);
		       
		       results += "<td class='tdContent' align='center'>" + userPass + "</td>";
		       
		       String userType = user.getUserType()==1?"系统管理员":"普通管理员";
		       
		       results += "<td class='tdContent' align='center'>" + userType + "</td>";
		       results += "<td class='tdContent' align='center'>" + "<a href='javascript:getUserDataById(" + user.getUserID() + ")'>查看</a>" + "</td>";
		       results += "<td class='tdContent' align='center'>" + "<a href='javascript:getUserDataById(" + user.getUserID() + ")'>修改</a>" + "</td>";
		       results += "<td class='tdContent' align='center'>" + "<a href='javascript:deleteUserDataById(" + user.getUserID() + ")'>删除</a>" + "</td>";
		       
		       results += "</tr>";
		       
		}
		
	    results +="</table></div>";
		
		results += "<div>管理员总数：" + this.userCount + " 普通管理员总页数：" + this.pageCount + " 普通管理员总菜单栏数：" + this.pageListCount + "</div>";
		
		return results;
	}

   	//根据当前页码和菜单码确定要显示的菜单
	private String getPageBottomByNum(int pageNum,int pageListNum)
	{
		
		
		String results = "<div style='height:27px;width:348px;display:inline-block;'>";//管理员
		
		if(this.currentPageListNum == 1)
		{
			results += "<div style='margin-left:2px;margin-right:2px;height:26px;float:left;display:inline-block;'><img src='../image/uppage1.png'></div>";
		}
		else
		{
	        int listNum1 = pageListNum - 1 < 1?1:pageListNum - 1;//获得页码
	        
	        int pageNum1 = (listNum1 - 1) * this.pageListSize + 1;//获得菜单显示开始页
			
			results += "<div style='cursor:pointer;margin-left:2px;margin-right:2px;height:26px;float:left;display:inline-block;'><img src='../image/uppage.png' onclick='showBtn(" + pageNum1 + "," + listNum1 + ")'></div>";
		}
		
		for(int i = this.pageListIndex; i < this.pageListIndex + this.pageListSize; i++)
		{
			//如果页码是当前页或者是最后一页则灰色显示
			if(i == this.currentPageNum || i > this.pageCount)
			{
				  results += "<div style='margin-left:2px;margin-right:2px;background:url(../image/numpage.png);line-height:26px;height:26px;width:26px;color:#CCCCCC;float:left;display:inline-block;'>" + i + "</div>";
			}
			else
			{
			      results += "<div style='margin-left:2px;margin-right:2px;background:url(../image/numpage.png);line-height:26px;height:26px;width:26px;float:left;display:inline-block;'><a href='javascript:showBtn(" + i + ", " + this.currentPageListNum + ")'>" + i + "</a></div>";
			}
		}
		
		if(pageListNum == this.pageListCount || this.pageListCount == 0)
		{
			results += "<div style='margin-left:2px;margin-right:2px;height:26px;float:left;display:inline-block;'><img src='../image/downpage1.png'></div>";			
		}
		else
		{
	        int listNum2 = pageListNum + 1 > this.pageListCount?this.pageListCount:pageListNum + 1;//获得页码
	        
	        int pageNum2 = (listNum2 - 1) * this.pageListSize + 1;//获得菜单显示开始页
			
			results += "<div style='cursor:pointer;margin-left:2px;margin-right:2px;height:26px;float:left;display:inline-block;'><img src='../image/downpage.png'  onclick='showBtn(" + pageNum2 + "," + listNum2 + ")'></div>";			
		}
		
		results += "</div>";
		
		return results;
	}
	
    //获得调查问卷总数
	private int getUserCount()
	{
		this.userOp = new UserDaoImpl();
		
		this.userCount = userOp.getUsersNotAdmin().size();//获得用户列表
		
		return this.userCount;//返回管理员总数
	}
	
    //获得管理员总页数
	private int getPageCount()
	{
		getUserCount();//获得当前管理员总数
		
		this.pageCount = (int)Math.ceil(this.userCount / (double)this.pageSize);//获得当前总页数
		
		return this.pageCount;//返回当前总页数
	}
	
	//获得管理员总菜单数
	private int getPageListCount()
	{
		
		getPageCount();//获得当前管理员总页数
		
		this.pageListCount = (int)Math.ceil(this.pageCount / (double)this.pageListSize);//获得菜单栏总数
		
		return this.pageListCount;//返回当前总菜单数
	}
	
    //调查参数初始化
	private void initParameters(int pageNum,int pageListNum)
	{
		getPageListCount();//初始化userCount、pageCount、pageListCount三项参数
		
		this.currentPageNum = pageNum;//设置当前显示页码
		
		this.currentPageListNum = pageListNum;//设置当前显示菜单码
		
		this.pageFirstIndex = (this.currentPageNum - 1) * this.pageSize;//获得当前页码中的第一条调查问卷下标
			
		this.pageListIndex = (this.currentPageListNum - 1) * this.pageListSize + 1;//获得当前菜单中显示的页码

	}

	public void init() throws ServletException {
		
		initParameters(1,1);//初始化管理员页和菜单页

	}

}
