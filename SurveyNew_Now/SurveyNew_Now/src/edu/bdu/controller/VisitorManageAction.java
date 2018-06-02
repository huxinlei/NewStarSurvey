package edu.bdu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.bdu.dao.UserDaoImpl;
import edu.bdu.dao.VisitorDaoImpl;
import edu.bdu.entity.User;
import edu.bdu.entity.Visitor;
import edu.bdu.dao.SurveyDaoImpl;
import edu.bdu.entity.Survey;

public class VisitorManageAction extends HttpServlet {

	 /**
     * 初始化
     */
	private  VisitorDaoImpl visitorOp;//访问者操作类
	
	private int visitorCount = 0;//获得访问者总数
	
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
	
	private int visitDataSize = 18;//答案项显示的最大字符数
	
	private int titleSize = 13;//连接显示的最大字符数
	
	private int nameSize = 9;//用户名显示的最大字符数

	public VisitorManageAction() {
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
			
			out.print(getVisitorCount());//输出普通管理员总数
			
			break;
		case 3:
				
			String condition = request.getParameter("searchCondition");//获得搜索条件
			
			if(!condition.equals(""))
			{
				int pageNum1 = Integer.parseInt(request.getParameter("pageNum"));//获得当前请求页
				
				out.println(getVisitorListByCondition(condition,pageNum1));//输出搜索结果
			}
			else
			{
				out.println("<div><a href='surveyList.jsp'>搜索条件不允许为空!</a></div>");//输出搜索结果				
			}
			
			break;
		case 4:
			
			String visitorNum = request.getParameter("visitorNumber");//获得搜索条件
			
			int visitorNumber = Integer.parseInt(visitorNum);//获得访问者编号
			
	        VisitorDaoImpl visitorOp = new VisitorDaoImpl();//访问者操作类
	           
	        if(visitorOp.deleteVisitor(visitorNumber))//删除访问者信息
	        {
	           out.println("<script type='text/javascript'>alert('访问者信息已成功删除!');getVisitorList();getVisitorCount();</script>");
	        }
	        else
	        {
	           out.println("<script type='text/javascript'>alert('访问者信息由于异常没能成功删除!');</script>");
	         }
			
			break;
			
		case 5:

			
			break;
			
		default:
			break;
		}

		out.flush();
		out.close();
	}

	//根据当前页码和菜单栏码确定显示要显示的访问者
	private String getPageByNum(int pageNum,int pageListNum)
	{
		String results = "";//设置访问结果
		
        initParameters(pageNum,pageListNum);//访问者和菜单页信息初始化
		
		this.visitorOp = new VisitorDaoImpl();//实例化访问者操作类
		
		List visitorList = this.visitorOp.getVisitorList();//获得访问者列表
		
		int listNum = this.pageSize;//默认显示列表总数
		
		int lastNum = this.visitorCount % this.pageSize;//获得最后一页访问者个数
		
		if(this.pageCount == this.currentPageNum)
		{
		   if(lastNum < listNum)
		   {
			   listNum = lastNum;//设置列表为当前页的访问者个数
		   }
		}
		
		//访问者总数刚好是每页访问者数的整数倍
		if(listNum == 0 && this.visitorCount != 0)
		{
			listNum = this.pageSize;//显示列表总数
		}
		
		if(this.visitorCount == 0)
		{
			listNum = 0;
		}
		
		//表格显示内容
	     results +="<div style='margin-top:10px;margin-bottom:10px;'><table border='0' cellpadding='0' cellspacing='0' class='tableClass'>";
	     results +="<tr>";
	     results +="<td align='center' class='tdHeader'>访问者编号</td>";
	     results +="<td align='center' class='tdHeader'>访问者ip地址</td>";
	     results +="<td align='center' class='tdHeader'>访问的调查问卷</td>";
	     results +="<td align='center' class='tdHeader'>访问者回答内容</td>";
	     results +="<td align='center' class='tdHeader'>访问时间记录</td>";
	     results +="<td align='center' class='tdHeader'>操作</td>";
	     results +="</tr>";
	     
	     SurveyDaoImpl surveyOp = new SurveyDaoImpl();//新建调查问卷操作类
	     
		for(int i = this.pageFirstIndex; i < this.pageFirstIndex + listNum; i++)
		{
	          Visitor visitor = (Visitor)visitorList.get(i);//获得访问者信息 
	          
	          results += "<tr class='trContent'>";
	          
	          results += "<td class='tdContent' align='center'>" + visitor.getVisitorNumber() + "</td>";//访问者编号
	          results += "<td class='tdContent' align='center'>" + visitor.getVisitorIP() + "</td>";//访问者ip地址
	          
	          String surveyTitle = surveyOp.getSurvey(visitor.getVisiteSurveyID()).getSurveyTitle();
	          surveyTitle = surveyTitle.length()<= titleSize?surveyTitle:surveyTitle.substring(0,titleSize);
	          results += "<td class='tdContent' align='center'>" + surveyTitle + "</td>";//访问的调查问卷编号
	          
	          String visitorData =  visitor.getVisiteSurveyData();//防止出现空串
	          
	          if(visitorData == null || visitorData.equals(""))
	          {
	            visitorData = "&nbsp;&nbsp;";
	          }
	          
	          visitorData =  visitorData.length() <= visitDataSize?visitorData:visitorData.substring(0,visitDataSize);
	          
	          
	          results += "<td class='tdContent' align='center'>" + visitorData + "</td>";//访问者回答的信息
	          results += "<td class='tdContent' align='center'>" + visitor.getVisiteDateTime().toString() + "</td>";//访问者访问时间
	          
	          results += "<td class='tdContent' align='center'><a href='javascript:deleteVisitorDataById(" + visitor.getVisitorNumber() + ")'>删除</a></td>";
	          
	          results += "<tr>";
		       
		}
		
	    results +="</table></div>";
		
		results += "<div>访问者总数：" + this.visitorCount + " 访问者总页数：" + this.pageCount + " 访问者总菜单栏数：" + this.pageListCount + "</div>";
		
		return results;
	}

   	//根据当前页码和菜单码确定要显示的菜单
	private String getPageBottomByNum(int pageNum,int pageListNum)
	{
		
		
		String results = "<div style='height:27px;width:348px;display:inline-block;'>";//访问者
		
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
	
    //获得访问者总数
	private int getVisitorCount()
	{
		this.visitorOp = new VisitorDaoImpl();
		
		this.visitorCount = visitorOp.getVisitorList().size();//获得用户列表
		
		return this.visitorCount;//返回访问者总数
	}
	
    //获得访问者总页数
	private int getPageCount()
	{
		getVisitorCount();//获得当前访问者总数
		
		this.pageCount = (int)Math.ceil(this.visitorCount / (double)this.pageSize);//获得当前总页数
		
		return this.pageCount;//返回当前总页数
	}
	
	//获得访问者总菜单数
	private int getPageListCount()
	{
		
		getPageCount();//获得当前访问者总页数
		
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
	
	
	//根据标题搜索访问者信息
	private String getVisitorListByCondition(String condition,int pageNum1)
	{
		String results = "";//设置访问结果
		
		this.visitorOp = new VisitorDaoImpl();//实例化访问者操作类
		
		SurveyDaoImpl surveyOp = new SurveyDaoImpl();//实例化调查问卷操作类
		
		Survey conditionS = surveyOp.getServeyByTitle(condition);//实例化条件调查问卷
		
		if(conditionS == null)
		{
		  results = "<div>不存在此调查问卷!</div>";
		  
		  return results;//返回调查问卷结果
		}
		
		List visitorList = visitorOp.getVisitorList(conditionS.getSurveyID());//获得调查问卷列表
		
		int searchPageCount = (int)Math.ceil(visitorList.size() / (double)this.searchPageSize);//获得当前总页数
		
		int searchPageFirst = (pageNum1 - 1) * this.searchPageSize;//默认显示页当前的第一条答案项下标
		
		int listSize = this.searchPageSize;//默认显示一页条数
		
		
		if(pageNum1 == searchPageCount)
		{
			int remainNum = visitorList.size() % this.searchPageSize;//获得最后页答案项总数
			
			listSize = remainNum < this.searchPageSize ? remainNum:this.searchPageSize;
		}
		
		if(listSize == 0 && visitorList.size() != 0)
		{
			listSize = this.searchPageSize;//最后一页刚好满页时处理
		}
		
		if(visitorList.size() == 0)
		{
			results += "<div>当前系统没有您想查找的调查问卷!</div>";
		}
		else
		{
			results += visitorList.size() + "*";//记录访问者个数
			//表格显示内容
		     results +="<div style='margin-top:10px;margin-bottom:10px;'><table border='0' cellpadding='0' cellspacing='0' class='tableClass'>";
		     results +="<tr>";
		     results +="<td align='center' class='tdHeader'>访问者编号</td>";
		     results +="<td align='center' class='tdHeader'>访问者ip地址</td>";
		     results +="<td align='center' class='tdHeader'>访问的调查问卷</td>";
		     results +="<td align='center' class='tdHeader'>访问者回答内容</td>";
		     results +="<td align='center' class='tdHeader'>访问时间记录</td>";
		     results +="<td align='center' class='tdHeader'>操作</td>";
		     results +="</tr>";
		     
			for(int i = searchPageFirst; i < searchPageFirst + listSize; i++)
			{
		          Visitor visitor = (Visitor)visitorList.get(i);//获得访问者信息 
		          
		          results += "<tr class='trContent'>";
		          
		          results += "<td class='tdContent' align='center'>" + visitor.getVisitorNumber() + "</td>";//访问者编号
		          results += "<td class='tdContent' align='center'>" + visitor.getVisitorIP() + "</td>";//访问者ip地址
		          
		          String surveyTitle = surveyOp.getSurvey(visitor.getVisiteSurveyID()).getSurveyTitle();
		          surveyTitle = surveyTitle.length()<= titleSize?surveyTitle:surveyTitle.substring(0,titleSize);
		          results += "<td class='tdContent' align='center'>" + surveyTitle + "</td>";//访问的调查问卷编号
		          
		          String visitorData =  visitor.getVisiteSurveyData();//防止出现空串
		          
		          if(visitorData == null || visitorData == "")
		          {
		            visitorData = " ";
		          }
		          
		          visitorData =  visitorData.length() <= visitDataSize?visitorData:visitorData.substring(0,visitDataSize);
		          
		          
		          results += "<td class='tdContent' align='center'>" + visitorData + "</td>";//访问者回答的信息
		          results += "<td class='tdContent' align='center'>" + visitor.getVisiteDateTime().toString() + "</td>";//访问者访问时间
		          
		          results += "<td class='tdContent' align='center'><a href='javascript:deleteVisitorDataById(" + visitor.getVisitorNumber() + ")'>删除</a></td>";
		          
		          results += "<tr>";
			}
			
		    results +="</table></div>";
			
			results += "<div style='width:540px;margin-top:10px;margin-bottom:10px;'>";
			
	        //显示上一页按钮
			if(pageNum1 == 1)
			{
				results += "<div style='margin-left:2px;margin-top:10px;margin-bottom:10px;margin-right:2px;height:26px;float:left;display:inline-block;'><img src='../image/uppage1.png'></div>";
			}
			else
			{
		        
		        int pageNum2 = pageNum1 - 1;//获得菜单显示开始页
				
				results += "<div style='margin-left:2px;margin-top:10px;margin-bottom:10px;margin-right:2px;height:26px;float:left;display:inline-block;cursor:pointer;'><img src='../image/uppage.png' onclick=\"getSearchList(\'" + condition + "\'," + pageNum2 + ")\"></div>";
			}
			
			//显示下一页按钮
			if(pageNum1 == searchPageCount)
			{
				results += "<div style='margin-left:2px;margin-top:10px;margin-bottom:10px;margin-right:2px;height:26px;float:left;display:inline-block;'><img src='../image/downpage1.png'></div>";			
			}
			else
			{
		        
		        int pageNum3 = pageNum1 + 1;//获得菜单显示开始页
				
				results += "<div style='margin-left:2px;margin-top:10px;margin-bottom:10px;margin-right:2px;height:26px;float:left;display:inline-block;cursor:pointer;'><img src='../image/downpage.png' onclick=\"getSearchList(\'" + condition + "\'," + pageNum3 + ")\"></div>";
			}
			
			results += "</div>";//上下翻页功能
		}
		
		return results;//返回访问者列表
	}

	public void init() throws ServletException {
		initParameters(1,1);//初始化访问者页和菜单页
	}

}
