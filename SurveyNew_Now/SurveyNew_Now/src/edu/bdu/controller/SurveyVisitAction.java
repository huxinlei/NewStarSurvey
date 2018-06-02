package edu.bdu.controller;

import edu.bdu.dao.*;
import edu.bdu.entity.*;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 制作时间：2011年8月23日
 * @author lenov
 *
 */

public class SurveyVisitAction extends HttpServlet {
	
	private SurveyDaoImpl surveyOp;//调查问卷操作类
	
	private ItemDaoImpl itemOp;//调查项操作类
	
	private RadioItemDaoImpl radioOp;//单选项操作类
	
	private CheckboxItemDaoImpl checkOp;//多选项操作类
	
	private TextItemDaoImpl textOp;//文字项操作类
	
	private VisitorDaoImpl visitorOp;//访问者操作类
   
	
    /**
     * 
     */
	public SurveyVisitAction() {
		super();
	}
    /**
     * 
     */
	public void destroy() {
		super.destroy();
	}

	/**
	 * 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        doPost(request ,response);//当访问方式为doGet方法跳到doPost方法
	}

	/**
	 * 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		
		if(request.getParameter("surveyid") != null)
		{
			int surveyid = Integer.parseInt(request.getParameter("surveyid"));//获得调查问卷的编号
			
			List items = itemOp.getItemList(surveyid);//根据调查问卷的编号查询调查项列表
			
			String visitorData = "";//访问者答案
			
			boolean flag = false;//没有调查项被处理，调查项是不是被处理标记
			
			for(int i = 0; i < items.size(); i++)
			{
				Item item = (Item)items.get(i);//获得调查项
				
				switch(item.getItemType())
				{
				  case 0:
					  String radioStr = "radio" + item.getItemID();//单选项获得调查结果的字符串
					  
					  if(request.getParameter(radioStr) != null)
					  {
						  int radioid = Integer.parseInt(request.getParameter(radioStr));//获得选择答案项编号
						  
						  RadioItem radio = radioOp.getRadio(radioid);//获得答案项
						  
						  if(radio != null)
						  {
							 int count = radio.getSelectCount();//获得选取的次数
							 
							 count = count + 1;
							 
							 radio.setSelectCount(count);//设置新的点击次数
							 
							 
							 if(radioOp.updateRadioItem(radio))
						     {
								 String radioV = item.getItemID() + "#" + radio.getRadioID()+ "###";//获得答案项
								 
								 visitorData += radioV;//保存到临时字符串中
								 
								 flag = true;//调查项被处理标记
						     }
						  }
					  }
					 
					  break;
					
				  case 1:
					  
					  String checkStr = "checkbox" + item.getItemID();//多选项获得调查结果的字符串
					  
					  if(request.getParameterValues(checkStr) != null)
					  {
						  String[] checkItems = request.getParameterValues(checkStr);
						  
						 for(int ci = 0; ci < checkItems.length; ci++)
						 {
						  int checkid = Integer.parseInt(checkItems[ci]);//获得选择答案项编号
						  
						  CheckboxItem checkbox = checkOp.getCheckbox(checkid);//获得答案项
						  
						  if(checkbox != null)
						  {
							 int count = checkbox.getSelectCount();//获得选取的次数
							 
							 count = count + 1;
							 
							 checkbox.setSelectCount(count);//设置新的点击次数
							 
							 
							 if(checkOp.updateCheckboxItem(checkbox))
						     {
								 String checkV = item.getItemID() + "#" + checkbox.getCheckboxID()+ "###";//获得答案项
								 
								 visitorData += checkV;//保存到临时字符串中
								 
								 flag = true;//调查项被处理标记
						     }
						  }
						 }
					  }
					 
					  break;
					  
					  
				  case 2:
					  
                      String textStr = "text" + item.getItemID();//文字项获得调查结果的字符串
					  
					  if(request.getParameter(textStr) != null)
					  {
						String textContent = request.getParameter(textStr);//获得回答内容
						
						TextItem text = new TextItem();//实例化文字答案项
						
						text.setTextCaption("访问者回答内容");//文字项标题默认为空
						
						text.setTextOwnerID(item.getItemID());//设置调查项编号
						
						text.setTextContent(textContent);//设置回答者的内容
						
						if(textOp.InsertTextItem(text))
						 {
							 flag = true;//调查项被处理标记
						 }
					  }
					  
					  break;
				}
			}
			
			if(flag)
			{
				Visitor visitor = new Visitor();//实例化访问者
				
				String ipStr = request.getRemoteAddr();//获得访问者ip地址
				
				visitor.setVisitorIP(ipStr);//设置ip字符串地址
				
				visitor.setVisiteSurveyID(surveyid);//设置调查问卷标题
				
				visitor.setVisiteSurveyData(visitorData);//设置访问者回答单选、多选项答案
				
				if(visitorOp.InsertVisitor(visitor))
				{
					//ServletContext sc = getServletContext();//获得当前servlet的容器
					
					//RequestDispatcher rd = sc.getRequestDispatcher("/surveyList.jsp"); //定向的页面
					
					response.sendRedirect("showresult.jsp?surveyid=" + surveyid);
					
					
				}
				else
				{
					out.println("您的提交出现了异常，请检查！");
				}
				
				
			}
			else
			{
				out.println("您的提交存在问题，请检查！");
			}
		}
		else
		{
		   out.println("参数传递出现错误，请检查！");
		}

		
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * 
	 */
	public void init() throws ServletException {
		
		this.surveyOp = new SurveyDaoImpl();//实例化调查问卷
		
		this.itemOp = new ItemDaoImpl();//实例化调查项操作类
		
		this.radioOp = new RadioItemDaoImpl();//实例化单选项操作类
		
		this.checkOp = new CheckboxItemDaoImpl();//实例化多选项操作类
		
		this.textOp = new TextItemDaoImpl();//实例化文字项操作类
		
		this.visitorOp = new VisitorDaoImpl();//实例化访问者操作类
		
	}

}
