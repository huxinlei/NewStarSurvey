package edu.bdu.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import edu.bdu.dao.SurveyDaoImpl;//导入调查问卷操作类
import edu.bdu.entity.Survey;//导入调查问卷实体类
import edu.bdu.entity.User;//导入用户类
import edu.bdu.dao.UserDaoImpl;//导入用户操作类
import edu.bdu.dao.TextItemDaoImpl;//导入文本回答项操作类
import edu.bdu.entity.TextItem;//导入文本实体类

public class AnyUserVisitAction extends HttpServlet {

    /**
     * 初始化
     */
	private SurveyDaoImpl surveyOp;//调查问卷操作类
	
	private int surveyCount = 0;//获得调查问卷总数
	
	private int pageSize = 10;//一页显示10个调查问卷
	
	private int pageFirstIndex = 0;//默认当前第一条调查问卷
	
	private int currentPageNum = 1;//默认显示第一页
	
	private int pageCount = 0;//调查问卷总页数
	
	private int pageListSize = 7;//7页一个菜单栏
	
	private int pageListIndex = 1;//默认显示的第一个页码
	
	private int currentPageListNum = 1;//默认显示第一个菜单栏
	
	private int pageListCount = 0;//总菜单栏个数
	
	private int textPageSize = 5;//默认文本答案项的长度
	
	private int searchPageSize = 10;//一页显示十条
	
	public AnyUserVisitAction() {
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
			
	        out.println(getNewListFive());//获得当前最新五条调查问卷
	        
			break;
		case 2:
			
			int pageNum = Integer.parseInt(request.getParameter("pageNum"));//获得当前请求页码
			
			int pageListNum = Integer.parseInt(request.getParameter("pageListNum"));//获得当前请求菜单码
			
	        initParameters(pageNum,pageListNum);//调查问卷页和菜单页信息初始化
			
			out.println(getPageByNum(pageNum,pageListNum));//获得调查问卷列表
			
			out.println(getPageBottomByNum(pageNum,pageListNum));//显示底部的标题
			
			break;
		case 3:
			
	        out.println(getNewListFiveSearch());//获得当前最新五条调查问卷		
	        
			break;
		case 4:
			
			String condition = request.getParameter("searchCondition");//获得搜索条件
			
			if(!condition.equals(""))
			{
				int pageNum1 = Integer.parseInt(request.getParameter("pageNum"));//获得当前请求页
				
				out.println(getSurveyListByCondition(condition,pageNum1));//输出搜索结果
			}
			else
			{
				out.println("<div><a href='surveyList.jsp'>搜索条件不允许为空!</a></div>");//输出搜索结果				
			}
			
			break;
			
		case 5:
			int itemId = Integer.parseInt(request.getParameter("itemid"));//获得文本问题编号
			
			int tPageNum = 1;//获得文本答案项显示页码
			
			if(request.getParameter("pageNum")!=null)
			{
				tPageNum = Integer.parseInt(request.getParameter("pageNum"));//获得当前要显示的页码
				
			}
			
			out.println(getTextList(itemId,tPageNum));//返回文本答案列表
			
			break;
			
		default:
			break;
		}
		
		out.flush();
		out.close();
	}
	//获得文本问题答案列表
	private String getTextList(int itemId,int pageNum)
	{
		String results = "";//文本问题列表
		
        TextItemDaoImpl textOp = new TextItemDaoImpl();//文字答案项操作类
		
        List textList = textOp.getTextListByOwner(itemId);//根据调查项编号查询文字答案项列表
        
		int textPageCount = (int)Math.ceil(textList.size() / (double)this.textPageSize);//获得当前总页数
		
		int textPageFirst = (pageNum - 1) * this.textPageSize;//默认显示页当前的第一条答案项下标
		
		int listSize = this.textPageSize;//默认显示一页条数
		
        if(textList.size() == 0)
        {
          results += "<div style='width:790px;'>当前还没有人回答此调查项！</div>";
        }
        
		if(pageNum == textPageCount)
		{
			int remainNum = textList.size() % this.textPageSize;//获得最后页答案项总数
			
			listSize = remainNum < this.textPageSize ? remainNum:this.textPageSize;
		}
		
		if(listSize == 0 && textList.size() != 0)
		{
			listSize = this.textPageSize;//最后一页刚好满页时处理
		}
		
		if(textList.size()== 0)
		{
			listSize = 0;//如果调查项不存在回答列表
		}
            
        for(int i = textPageFirst; i < textPageFirst + listSize; i++)
        {
           TextItem text = (TextItem)textList.get(i);//获得文字项实例
                
           results += "<div  style='width:800px;line-height:28px;border-bottom:1px solid #CCCC00;padding-left:12px;'>" + getNewString(text.getTextContent(),200) + "</div>";  
        }
        
        //显示上一页按钮
		if(pageNum == 1)
		{
			results += "<div style='margin-left:2px;margin-right:2px;height:26px;float:left;display:inline-block;'><img src='image/uppage1.png'></div>";
		}
		else
		{
	        
	        int pageNum1 = pageNum - 1;//获得菜单显示开始页
			
			results += "<div style='margin-left:2px;margin-right:2px;height:26px;float:left;display:inline-block;cursor:pointer;'><img src='image/uppage.png' onclick='getTextList(" + itemId + "," + pageNum1 + ")'></div>";
		}
		
		//显示下一页按钮
		if(pageNum == textPageCount || textPageCount == 0)
		{
			results += "<div style='margin-left:2px;margin-right:2px;height:26px;float:left;display:inline-block;'><img src='image/downpage1.png'></div>";			
		}
		else
		{
	        
	        int pageNum2 = pageNum + 1;//获得菜单显示开始页
			
			results += "<div style='margin-left:2px;margin-right:2px;height:26px;float:left;display:inline-block;cursor:pointer;'><img src='image/downpage.png' onclick='getTextList(" + itemId + "," + pageNum2 + ")'></div>";
		}
		
		//显示关闭按钮
		results += "<div style='line-height:26px;color:red;text-decoration:none;'><a href='javascript:textListHidden()'>[关闭]</a></div>";//关闭当前层
		
		return results;
	}
	//根据标题搜索调查问卷
	private String getSurveyListByCondition(String condition,int pageNum1)
	{
		String results = "";//设置访问结果
		
		this.surveyOp = new SurveyDaoImpl();//实例化调查问卷操作类
		
		Survey conditionS = new Survey();//实例化条件调查问卷
		
		conditionS.setSurveyTitle(condition);//设置调查问卷标题
		
		List surveyList = this.surveyOp.getServeyList(conditionS);//获得调查问卷列表
		
		int searchPageCount = (int)Math.ceil(surveyList.size() / (double)this.searchPageSize);//获得当前总页数
		
		int searchPageFirst = (pageNum1 - 1) * this.searchPageSize;//默认显示页当前的第一条答案项下标
		
		int listSize = this.searchPageSize;//默认显示一页条数
		
		
		if(pageNum1 == searchPageCount)
		{
			int remainNum = surveyList.size() % this.searchPageSize;//获得最后页答案项总数
			
			listSize = remainNum < this.searchPageSize ? remainNum:this.searchPageSize;
		}
		
		if(listSize == 0 && surveyList.size() != 0)
		{
			listSize = this.searchPageSize;//最后一页刚好满页时处理
		}
		
		if(surveyList.size() == 0)
		{
			results += "<div>当前系统没有您想查找的调查问卷!</div>";
		}
		else
		{
			for(int i = searchPageFirst; i < searchPageFirst + listSize; i++)
			{
				Survey survey = (Survey)surveyList.get(i);//获得调查问卷实体
				
				UserDaoImpl userOp = new UserDaoImpl();//获得用户操作类
				
				User user = userOp.getUser(survey.getSurveyOwnerID());//获得当前调查问卷的发布人
				
				results += "<div>" ;
				
				results += "<div style='width:540px;margin-top:5px;margin-bottom:10px;' align='center'><a href='showsurvey.jsp?surveyId=" + survey.getSurveyID() + "'>";
				
				results += getNewString(survey.getSurveyTitle(),70);
				
				results += "</a></div>"; 
				
				results += "<div style='width:540px;margin-top:5px;margin-bottom:10px;padding-left:15px;' align='left'>";
				
				results += getNewString(survey.getSurveyLink(),80);
				
				results += "</div>";
				
				results += "<div style='width:540px;margin-top:10px;margin-bottom:10px;padding-left:15px;padding-bottom:10px;border-bottom:1px dotted #0066CC;' align='left'>";
				
				results += "<span>" + user.getUserName()+ "</span><span style='margin-left:10px;margin-right:10px;'>发布于</span><span>" + survey.getSurveyCreateDate().toString() + "</span>";
				
				results += "</div>";
				
				results += "</div>";
			}
			
			
			results += "<div style='width:540px;margin-top:10px;margin-bottom:10px;'>";
			
	        //显示上一页按钮
			if(pageNum1 == 1)
			{
				results += "<div style='margin-left:2px;margin-top:10px;margin-bottom:10px;margin-right:2px;height:26px;float:left;display:inline-block;'><img src='image/uppage1.png'></div>";
			}
			else
			{
		        
		        int pageNum2 = pageNum1 - 1;//获得菜单显示开始页
				
				results += "<div style='margin-left:2px;margin-top:10px;margin-bottom:10px;margin-right:2px;height:26px;float:left;display:inline-block;cursor:pointer;'><img src='image/uppage.png' onclick=\"getSearchList(\'" + condition + "\'," + pageNum2 + ")\"></div>";
			}
			
			//显示下一页按钮
			if(pageNum1 == searchPageCount)
			{
				results += "<div style='margin-left:2px;margin-top:10px;margin-bottom:10px;margin-right:2px;height:26px;float:left;display:inline-block;'><img src='image/downpage1.png'></div>";			
			}
			else
			{
		        
		        int pageNum3 = pageNum1 + 1;//获得菜单显示开始页
				
				results += "<div style='margin-left:2px;margin-top:10px;margin-bottom:10px;margin-right:2px;height:26px;float:left;display:inline-block;cursor:pointer;'><img src='image/downpage.png' onclick=\"getSearchList(\'" + condition + "\'," + pageNum3 + ")\"></div>";
			}
			
			results += "</div>";//上下翻页功能
		}
		
		return results;//返回调查问卷列表
	}
	//根据当前页码和菜单码确定要显示的菜单
	private String getPageBottomByNum(int pageNum,int pageListNum)
	{
		
		
		String results = "<div style='height:27px;width:348px;display:inline-block;'>";//调查问卷
		
		if(this.currentPageListNum == 1)
		{
			results += "<div style='margin-left:2px;margin-right:2px;height:26px;float:left;display:inline-block;'><img src='image/uppage1.png'></div>";
		}
		else
		{
	        int listNum1 = pageListNum - 1 < 1?1:pageListNum - 1;//获得页码
	        
	        int pageNum1 = (listNum1 - 1) * this.pageListSize + 1;//获得菜单显示开始页
			
			results += "<div style='cursor:pointer;margin-left:2px;margin-right:2px;height:26px;float:left;display:inline-block;'><img src='image/uppage.png' onclick='showBtn(" + pageNum1 + "," + listNum1 + ")'></div>";
		}
		
		for(int i = this.pageListIndex; i < this.pageListIndex + this.pageListSize; i++)
		{
			//如果页码是当前页或者是最后一页则灰色显示
			if(i == this.currentPageNum || i > this.pageCount)
			{
				  results += "<div style='margin-left:2px;margin-right:2px;background:url(image/numpage.png);line-height:26px;height:26px;width:26px;color:#CCCCCC;float:left;display:inline-block;'>" + i + "</div>";
			}
			else
			{
			      results += "<div style='margin-left:2px;margin-right:2px;background:url(image/numpage.png);line-height:26px;height:26px;width:26px;float:left;display:inline-block;'><a href='javascript:showBtn(" + i + ", " + this.currentPageListNum + ")'>" + i + "</a></div>";
			}
		}
		
		if(pageListNum == this.pageListCount)
		{
			results += "<div style='margin-left:2px;margin-right:2px;height:26px;float:left;display:inline-block;'><img src='image/downpage1.png'></div>";			
		}
		else
		{
	        int listNum2 = pageListNum + 1 > this.pageListCount?this.pageListCount:pageListNum + 1;//获得页码
	        
	        int pageNum2 = (listNum2 - 1) * this.pageListSize + 1;//获得菜单显示开始页
			
			results += "<div style='cursor:pointer;margin-left:2px;margin-right:2px;height:26px;float:left;display:inline-block;'><img src='image/downpage.png'  onclick='showBtn(" + pageNum2 + "," + listNum2 + ")'></div>";			
		}
		
		results += "</div>";
		
		return results;
	}
	
	//根据当前页码和菜单栏码确定显示要显示的调查问卷
	private String getPageByNum(int pageNum,int pageListNum)
	{
		String results = "";//设置访问结果
		
        initParameters(pageNum,pageListNum);//调查问卷页和菜单页信息初始化
		
		this.surveyOp = new SurveyDaoImpl();//实例化调查问卷操作类
		
		List surveyList = this.surveyOp.getServeyList();//获得调查问卷列表
		
		int listNum = this.pageSize;//默认显示列表总数
		
		int lastNum = this.surveyCount % this.pageSize;//获得最后一页调查问卷个数
		
		if(this.pageCount == this.currentPageNum)
		{
		   if(lastNum < listNum)
		   {
			   listNum = lastNum;//设置列表为当前页的调查问卷个数
		   }
		}
		
		//调查问卷总数刚好是每页调查问卷数的整数倍
		if(listNum == 0 && this.surveyCount != 0)
		{
			listNum = this.pageSize;//显示列表总数
		}
		
		if(this.surveyCount == 0)
		{
			listNum = 0;
		}
		for(int i = this.pageFirstIndex; i < this.pageFirstIndex + listNum; i++)
		{
			Survey survey = (Survey)surveyList.get(i);//获得调查问卷实体
			
			UserDaoImpl userOp = new UserDaoImpl();//获得用户操作类
			
			User user = userOp.getUser(survey.getSurveyOwnerID());//获得当前调查问卷的发布人
			
			results += "<div>" ;
			
			results += "<div style='width:540px;margin-top:5px;margin-bottom:10px;' align='center'><a href='showsurvey.jsp?surveyId=" + survey.getSurveyID() + "'>";
			
			results += getNewString(survey.getSurveyTitle(),70);
			
			results += "</a></div>"; 
			
			results += "<div style='width:540px;margin-top:5px;margin-bottom:10px;padding-left:15px;' align='left'>";
			
			results += getNewString(survey.getSurveyLink(),80);
			
			results += "</div>";
			
			results += "<div style='width:540px;margin-top:10px;margin-bottom:10px;padding-left:15px;padding-bottom:10px;border-bottom:1px dotted #0066CC;' align='left'>";
			
			results += "<span>" + user.getUserName()+ "</span><span style='margin-left:10px;margin-right:10px;'>发布于</span><span>" + survey.getSurveyCreateDate().toString() + "</span>";
			
			results += "</div>";
			
			results += "</div>";
		}
		
		results += "<div>调查问卷总数：" + this.surveyCount + " 调查问卷总页数：" + this.pageCount + " 调查问卷总菜单栏数：" + this.pageListCount + "</div>";
		
		return results;
	}
	

	private String getNewListFiveSearch()
	{
		this.surveyOp = new SurveyDaoImpl();//实例化调查问卷操作类
		
		List surveyList = this.surveyOp.getServeyList();//获得调调查问卷
		
		String results = "";
		
		int currentCount = 5;//调查问卷前五条
		
		if(currentCount > surveyList.size())
		{
			currentCount = surveyList.size();//获得当前调查问卷个数
		}
		
		//如果当前不存在调查问卷，显示没有调查问卷
		if(surveyList.size() == 0)
		{
		  results = "<div>当前不存在调查问卷！</div>";
		}
		else
		{
			//循环显示调查项
			for(int sIndex = 0; sIndex < currentCount;sIndex++)
			{
				Survey survey = (Survey)surveyList.get(sIndex);//获得当前调查问卷
				
				String titleString = "";
				
				if(survey.getSurveyTitle().length() < 8)
				{
					titleString = survey.getSurveyTitle();//获得数据
				}
				else
				{
				    titleString = survey.getSurveyTitle().substring(0, 7);	
				}
				
				results += "<div style='margin-top:8px;margin-bottom:8px;'><a href='showsurvey.jsp?surveyId=" + survey.getSurveyID() + "'><span style='margin-left:30px;'>" 
					+ titleString + "</span><span style='margin-left:30px;'>" + 
					survey.getSurveyCreateDate().toString() + "</span></a></div>";//获得调查问卷
			}
		}

	  
	  return results;
	}
	
	//获得当前最近五条
	private String getNewListFive()
	{
		this.surveyOp = new SurveyDaoImpl();//实例化调查问卷操作类
		
		List surveyList = this.surveyOp.getServeyList();//获得调调查问卷
		
		String results = "";
		
		int currentCount = 5;//调查问卷前五条
		
		if(currentCount > surveyList.size())
		{
			currentCount = surveyList.size();//获得当前调查问卷个数
		}
		
		//如果当前不存在调查问卷，显示没有调查问卷
		if(surveyList.size() == 0)
		{
		  results = "<div>当前不存在调查问卷！</div>";
		}
		else
		{
			//循环显示调查项
			for(int sIndex = 0; sIndex < currentCount;sIndex++)
			{
				Survey survey = (Survey)surveyList.get(sIndex);//获得当前调查问卷
				
				String titleString = "";
				
				if(survey.getSurveyTitle().length() < 8)
				{
					titleString = survey.getSurveyTitle();//获得数据
				}
				else
				{
				    titleString = survey.getSurveyTitle().substring(0, 7);	
				}
				
				results += "<div><a href='showsurvey.jsp?surveyId=" + survey.getSurveyID() + "'><span style='margin-left:30px;'>" 
					+ titleString + "</span><span style='margin-left:30px;'>" + 
					survey.getSurveyCreateDate().toString() + "</span></a></div>";//获得调查问卷
			}
		}

	  
	  return results;
	}
	
	//获得指定大小带换行符的字符串
    private String getNewString(String oldString,int size)
    {
    	String newString = "";
    	
    	for(int i = 0; i < oldString.length(); i = i + size)
    	{
    	  int endI = i + size - 1;
    	  
    	  if(endI >= oldString.length())
    	  {
    		  endI = oldString.length();
    	  }
    	  
    	  newString += oldString.substring(i,endI);	
    	  
    	  newString += "<br>";//添加换行
    	}
    	
    	return newString;
    }
	//获得调查问卷总数
	private int getSurveyCount()
	{
		this.surveyOp = new SurveyDaoImpl();
		
		this.surveyCount = this.surveyOp.getServeyList().size();//获得调查问卷总数
		
		return this.surveyCount;//返回调查问卷总数
	}
	
	//获得调查问卷总页数
	private int getPageCount()
	{
		getSurveyCount();//获得当前调查问卷总数
		
		this.pageCount = (int)Math.ceil(this.surveyCount / (double)this.pageSize);//获得当前总页数
		
		return this.pageCount;//返回当前总页数
	}
	
	//获得调查问卷总菜单数
	private int getPageListCount()
	{
		
		getPageCount();//获得当前调查问卷总页数
		
		this.pageListCount = (int)Math.ceil(this.pageCount / (double)this.pageListSize);//获得菜单栏总数
		
		return this.pageListCount;//返回当前总菜单数
	}
	//调查参数初始化
	private void initParameters(int pageNum,int pageListNum)
	{
		getPageListCount();//初始化surveyCount、pageCount、pageListCount三项参数
		
		this.currentPageNum = pageNum;//设置当前显示页码
		
		this.currentPageListNum = pageListNum;//设置当前显示菜单码
		
		this.pageFirstIndex = (this.currentPageNum - 1) * this.pageSize;//获得当前页码中的第一条调查问卷下标
			
		this.pageListIndex = (this.currentPageListNum - 1) * this.pageListSize + 1;//获得当前菜单中显示的页码

	}

	public void init() throws ServletException {
		
          initParameters(1,1);//初始化调查问卷页和菜单页

	}

}
