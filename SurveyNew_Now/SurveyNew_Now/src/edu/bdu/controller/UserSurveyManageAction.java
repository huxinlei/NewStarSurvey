package edu.bdu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.bdu.dao.CheckboxItemDaoImpl;
import edu.bdu.dao.ItemDaoImpl;
import edu.bdu.dao.RadioItemDaoImpl;
import edu.bdu.dao.SurveyDaoImpl;
import edu.bdu.dao.TextItemDaoImpl;
import edu.bdu.dao.UserDaoImpl;
import edu.bdu.entity.CheckboxItem;
import edu.bdu.entity.Item;
import edu.bdu.entity.RadioItem;
import edu.bdu.entity.Survey;
import edu.bdu.entity.User;
import edu.bdu.tools.OperatorTools;

public class UserSurveyManageAction extends HttpServlet {

    /**
     * 初始化
     */
	private SurveyDaoImpl surveyOp;//调查问卷操作类
	
	private int surveyCount = 0;//获得调查问卷总数
	
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
	
	private int nameSize = 9;//用户名显示的最大字符数
	
	private User user;//保存当前用户信息

	public UserSurveyManageAction() {
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
		HttpSession session = request.getSession();//获得请求session
		this.user = (User)session.getAttribute("user");//获得当前用户信息
		
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
			
			out.print(getSurveyCount());//输出调查问卷总数
			
			break;
		case 3:
				
	        
			break;
		case 4:
			
			String surveyNum = request.getParameter("surveyId");
			
			int surveyId = Integer.parseInt(surveyNum);//获得普通管理员编号
			
	        surveyOp = new SurveyDaoImpl();//普通管理员操作类
	           
	        if(surveyOp.deleteSurvey(surveyId))//删除访问者信息
	        {
	           out.println("<script type='text/javascript'>alert('此调查问卷已成功删除!');getSurveyList();getSurveyCount();</script>");
	        }
	        else
	        {
	           out.println("<script type='text/javascript'>alert('此调查问卷由于异常没能成功删除!');</script>");
	         }
			
			break;
			
		case 5:
			String surveyNum5 = request.getParameter("surveyId");
			
			int surveyId5 = Integer.parseInt(surveyNum5);//获得普通管理员编号
			
	        surveyOp = new SurveyDaoImpl();//普通管理员操作类
	           
	        if(surveyOp.deleteSurvey(surveyId5))//删除访问者信息
	        {
	           out.println("<script type='text/javascript'>alert('此调查问卷已成功删除!');getSurveyListTen();</script>");
	        }
	        else
	        {
	           out.println("<script type='text/javascript'>alert('此调查问卷由于异常没能成功删除!');</script>");
	         }
			
			
			break;
			
		case 6:
			//创建调查问卷获得调查问卷，获得调查问卷编号0，创建调查问卷编号1，调查问卷编号在session中进行保存，根据用户
	        
			if(session.getAttribute("surveyId") != null)
			{
				String surveyIdStr = (String)session.getAttribute("surveyId");
		        int surveyid = Integer.parseInt(surveyIdStr);//获得调查问卷编号
		        
		        SurveyDaoImpl surveyOp = new SurveyDaoImpl();//调查问卷操作类
		        
		        ItemDaoImpl itemOp = new ItemDaoImpl();//调查项操作类
		        
		        RadioItemDaoImpl radioOp = new RadioItemDaoImpl();//单选项操作类
		        
		        CheckboxItemDaoImpl checkOp = new CheckboxItemDaoImpl();//多选项操作类
		        
		        TextItemDaoImpl textOp = new TextItemDaoImpl();//文字项操作类
		        
		        Survey survey = surveyOp.getSurvey(surveyid);//根据调查问卷编号获取调查问卷
		        
		        OperatorTools opTool = new OperatorTools();//获得工具类
		        
		        String surveyObject = "";//json对象的开始
		        
		        String surveyInfo = null;
		        String itemList = null;
		        String itemInfo = null;
		        String answerList = null;
		        String radioItem = null;
		        String checkboxItem = null;
		        String textItem = null;
		        
		        
		        surveyObject = "{";
		        
		        if(survey != null)
		        {
		        	surveyInfo = "\"surveyInfo\":{";
		        	
		        	surveyInfo += "\"surveyId\":" + survey.getSurveyID() + ",";
		        	
		        	surveyInfo += "\"surveyTitle\":\"" + opTool.getStringReplace(survey.getSurveyTitle()) + "\",";
		        	surveyInfo += "\"surveyOwnerID\":" + survey.getSurveyOwnerID() + ",";
		        	surveyInfo += "\"surveyLink\":\"" + opTool.getStringReplace(survey.getSurveyLink()) + "\",";
		        	surveyInfo += "\"surveyCreateDateTime\":\"" + survey.getSurveyCreateDate() + "\",";
		        	surveyInfo += "\"surveyExpirationDateTime\":\"" + survey.getSurveyExpirationDate() + "\"}";
		        	
		          surveyObject +=  surveyInfo;//获得调查问卷信息
		          surveyObject += ",";
		          surveyObject += "\"sItemList\":";
		        	
		          List items = itemOp.getItemList(survey.getSurveyID());//根据调查问卷编号查询调查项列表
		          
		          itemList = "[";
		          
		          for(int i = 0; i < items.size(); i++)
		          {
		              Item item = (Item)items.get(i);//获得调查项内容
		              
		              itemInfo = "\"itemInfo\":{";
		              itemInfo += "\"itemID\":" + item.getItemID() +",";
		              itemInfo += "\"itemCaption\":\"" + item.getItemCaption() + "\",";
		              itemInfo += "\"itemType\":" + item.getItemType() +",";
		              itemInfo += "\"itemAttribute\":" + item.getItemAttribute() +",";
		              itemInfo += "\"itemOwnerID\":" + item.getItemOwnerID() +",";
		              itemInfo += "\"itemOwnerSurveyID\":" + item.getItemOwnerSurveyID() +",";
		              itemInfo += "\"radioCheckboxCount\":" + item.getRadioCount() +"}";
		              
		              itemList += "{";
		              itemList += itemInfo;
		              itemList += ",\"answerList\":";
		              List answers = new ArrayList();
		              

		              switch(item.getItemType())//调查项类型
		              {
		                
		                case 0:
		                   answers = radioOp.getRadioList(item.getItemID());//根据调查项编号查询单选答案项
		                   
		                   answerList = "[";
		                   
		                   for(int ri = 0; ri < answers.size(); ri++)
		                   {
		                     RadioItem rItem = (RadioItem)answers.get(ri);//获得答案项
		             
	
		                     radioItem = "{";
		                     radioItem += "\"radioId\":" + rItem.getRadioID() +",";
		                     radioItem += "\"radioOwnerId\":" + rItem.getRadioOwnerID() +",";
		                     radioItem += "\"radioIndex\":" + rItem.getRadioIndex() +",";
		                     radioItem += "\"radioCaption\":\"" + rItem.getRadioCaption() + "\",";
		                     radioItem += "\"defaultSelected\":" + rItem.getDefaultSelected() +",";
		                     radioItem += "\"selectedCount\":" + rItem.getSelectCount() +"}";
		                     
				              answerList += radioItem;	
				              
				              if((ri + 1) != answers.size())
				              {
				            	  answerList += ",";
				              }
		                    
		                   }
		                   answerList += "]";
		                                     
		                   break;
		                   
		                case 1:
		                
		                   answers = checkOp.getCheckboxList(item.getItemID());//根据调查项编号查询单选答案项
		                   answerList = "[";		                   
		                   for(int ri = 0; ri < answers.size(); ri++)
		                   {
		                     CheckboxItem cItem = (CheckboxItem)answers.get(ri);//获得答案项
		                     
		                     checkboxItem = "{";
		                     checkboxItem += "\"checkboxId\":" + cItem.getCheckboxID() +",";
		                     checkboxItem += "\"checkboxOwnerID\":" + cItem.getCheckboxOwnerID() +",";
		                     checkboxItem += "\"checkboxIndex\":" + cItem.getCheckboxIndex() +",";
		                     checkboxItem += "\"checkboxCaption\":\"" + cItem.getCheckboxCaption() + "\",";
		                     checkboxItem += "\"defaultSelected\":" + cItem.getDefaultSelected() +",";
		                     checkboxItem += "\"selectedCount\":" + cItem.getSelectCount() +"}";
		                     
				              answerList += checkboxItem;	
				              
				              if((ri + 1) != answers.size())
				              {
				            	  answerList += ",";
				              }
				              	
		                   }
		                   
			               answerList += "]";	
			               
		                   break;
		                   
		                case 2:
		                
	                        answerList = "null";
		                   
		                   break;
		                
		              }
		              
		              itemList += answerList;
		              itemList += "}";
		              
		              if((i + 1) != items.size())
		              {
		            	  itemList += ",";
		              }
		          }
		          itemList += "]";
		          surveyObject += itemList;
		          surveyObject += "}";
		          
		          out.print(surveyObject);
		        }
			}
			else
			{

                out.print("fail");//调查问卷插入出错

			}
			
			break;
		case 7:
			//创建调查问卷编号session变量
			String surveyNum7 = request.getParameter("surveyId");
			
			session.setAttribute("surveyId",surveyNum7);//保存当前调查问卷编号
			
			out.print("success");//输出正常结束
			
			break;
			
		case 8:
			//创建调查问卷
	        User user8 = null;
	        
	        if(session.getAttribute("user") != null)
	        {
	           user8 = (User)session.getAttribute("user");//获得当前用户的引用
	         
	           Survey survey = new Survey();//创建调查问卷对象
	       
	           SurveyDaoImpl surveyOp = new SurveyDaoImpl();//调查问卷操作类
	        
	           //用户编号自动生成，此程序此段代码未用
	           if(request.getParameter("surveytitle")!= null)
	           {
	        	   String surveytitle = request.getParameter("surveytitle");//获得调查问卷标题
	           
	        	   survey.setSurveyTitle(surveytitle);//设置调查问卷标题
	           }
	           
	           if(request.getParameter("surveylink")!=null)
	           {
	               String surveylink = request.getParameter("surveylink");//获得调查问卷连接
	               
	              survey.setSurveyLink(surveylink);//设置调查问卷连接
	           }
	           
	           if(surveyOp.getServeyByTitle(survey.getSurveyTitle()) == null){
	        	   
	               survey.setSurveyOwnerID(user8.getUserID());//获得用户编号
	               
	               if(surveyOp.InsertSurvey(survey))
	               {
	  
	                    survey = surveyOp.getServeyByTitle(survey.getSurveyTitle());
	                    
	                    String surveyIdStr = String.valueOf(survey.getSurveyID());
	                    
	        			session.setAttribute("surveyId",surveyIdStr);//保存当前调查问卷编号
	        			
	                    out.print("success");//返回调查问卷列表
	        			
	                }
	                else
	                {
	                  out.print("fail");
	                  
	                }
	        	   
	           }
	           else
	           {
	        	   out.print("surveyexist");
	           }
	        }
	        else
	        {
	        	out.print("usererror");
	        }
	        
			break;
			
		case 9:
			   //更改调查问卷信息
	           Survey survey = new Survey();//创建调查问卷对象
		       
	           SurveyDaoImpl surveyOp = new SurveyDaoImpl();//调查问卷操作类
	           
	           if(request.getParameter("surveyId") != null)
	           {
	             int surveyId9 = Integer.parseInt(request.getParameter("surveyId"));//获得调查问卷编号
	             
	             survey = surveyOp.getSurvey(surveyId9);//获得调查问卷
	           }
	           //用户编号自动生成，此程序此段代码未用
	           if(request.getParameter("surveytitle")!= null)
	           {
	        	   String surveytitle = request.getParameter("surveytitle");//获得调查问卷标题
	           
	        	   survey.setSurveyTitle(surveytitle);//设置调查问卷标题
	           }
	           
	           if(request.getParameter("surveylink")!=null)
	           {
	               String surveylink = request.getParameter("surveylink");//获得调查问卷连接
	               
	              survey.setSurveyLink(surveylink);//设置调查问卷连接
	           }
	           
	           if(surveyOp.getServeyByTitle(survey.getSurveyTitle()) == null){
	        	   
	               
	               if(surveyOp.updateSurvey(survey))
	               {
	  
	                    survey = surveyOp.getServeyByTitle(survey.getSurveyTitle());
	                    
	                    String surveyIdStr = String.valueOf(survey.getSurveyID());
	                    
	        			session.setAttribute("surveyId",surveyIdStr);//保存当前调查问卷编号
	        			
	                    out.print("success");//返回调查问卷列表
	        			
	                }
	                else
	                {
	                  out.print("fail"); 
	                }
	        	   
	           }
	           else
	           {
	        	   out.print("surveyexist");
	           }
			break;
		case 10:
			 //删除调查项
		     if(request.getParameter("itemId") != null)
		     {
		       
		       int itemid = Integer.parseInt(request.getParameter("itemId"));
		       
		       ItemDaoImpl itemOp = new ItemDaoImpl();//调查项操作类
		       
		       if(itemOp.deleteItem(itemid))
		       {
		         out.print("success");
		       }
		       else
		       {
		         out.print("fail");
		       }
		      
		     }
		     else
		     {
		       out.print("error");//调查项编号为空
		     }
			break;
			
		case 11:
			//删除答案项
			   RadioItemDaoImpl radioOp = new RadioItemDaoImpl();
			   CheckboxItemDaoImpl checkboxOp = new CheckboxItemDaoImpl();
			   
			   int type = Integer.parseInt(request.getParameter("type"));//获得调查项类型
			   
			   if(type == 0)
			   {
			   
				   if(request.getParameter("id") != null)
				   {
					   int radioid = Integer.parseInt(request.getParameter("id"));//单选项编号
					   
					   if(radioOp.deleteRadioItem(radioid))
					   {
						   out.print("success");
					   }
					   else
					   {
						   out.print("fail");
					   }
				   }
			   }
			   else if(type == 1)
			   {
				   if(request.getParameter("id") != null)
				   {
					   int checkboxid = Integer.parseInt(request.getParameter("id"));//单选项编号
					   
					   if(checkboxOp.deleteCheckboxItem(checkboxid))
					   {
						   out.print("success");
					   }
					   else
					   {
						   out.print("fail");
					   }
				   } 
			   }
			
			break;
			
		case 12:
			
			//更新插入调查项
			int type12 = Integer.parseInt(request.getParameter("type"));//获得调查项类型
			
			String idStr = request.getParameter("idstr");//获得编号字符串
			
			String captionStr = request.getParameter("captionStr");
			
		    ItemDaoImpl itemOp12 = new ItemDaoImpl();//调查项操作类
		    
		    RadioItemDaoImpl radioOp12 = new RadioItemDaoImpl();//单选项操作类实例
		    
		    CheckboxItemDaoImpl checkboxOp12 = new CheckboxItemDaoImpl();//多选项操作类实例
		    
			String surveyIdStr = (String)session.getAttribute("surveyId");
			
	        int surveyid12 = Integer.parseInt(surveyIdStr);//获得调查问卷编号
	        
	        User user12 = (User)session.getAttribute("user");//获得当前用户信息
			
			switch(type12)
			{
			case 0:
			
			    String []idR = idStr.split(",");//获得调查项编号 
			
			    String []captionR = captionStr.split(",");//获得标题列表
			    
			    int itemId120 = Integer.parseInt(idR[0]);
			    
			    if(itemId120 == -1)
			    {
			    	Item item120 = new Item();//新建调查项
			    	
			    	item120.setItemOwerID(user12.getUserID());
			    	
			    	item120.setItemCaption(captionR[0]);
			    	
			    	item120.setItemType(0);//文本调查项
			    	
			    	item120.setItemOwnerSurveyID(surveyid12);
			    	
			    	item120.setItemAttribute(2);//设置正在添加标记
			    	
		             if(itemOp12.InsertItem(item120))
		              {
		            	 
                          List items = itemOp12.getItemList(item120);//获得调查项
                          
                          if(items.size() == 1)
                          {
                        	  
                        	  item120 = (Item)items.get(0);//获得第一个调查项
                        	  item120.setItemAttribute(1);//清除插入标记
                        	  itemOp12.updateItem(item120);//更新调查项
                        	  
                        	  
                        	  
                        	  for(int rI1 = 1; rI1 < idR.length;rI1 ++)
                        	  {
                        		  RadioItem rItem = new RadioItem();
                        		  
                        		  rItem.setRadioOwnerID(item120.getItemID());//所属调查项编号
                        		  
                        		  rItem.setRadioIndex(0);//所属调查项编号
                        		  
                        		  rItem.setRadioCaption(captionR[rI1]);//单选项的内容
                        		  
                        		  radioOp12.InsertRadioItem(rItem);//插入调查项
                        		  
                        	  }
                        	  
                        	  out.print("success");//输出成功标志
                        	  
                          }
                          else
                          {
                        	  out.print("fail");
                          }
		                   //out.print("success");//调查项插入成功
		                   
		              }
		             else
		             {
		            	out.print("fail"); //调查项插入失败
		             }
			    	
			    }
			    else
			    {
			    	Item item1220 = itemOp12.getItemByItemId(itemId120);//获得原有调查项信息
			    	
			    	item1220.setItemCaption(captionR[0]);//设置调查项标题
			    	
			    	if(itemOp12.updateItem(item1220))
			    	{
			    		
                  	  for(int rI1 = 1; rI1 < idR.length;rI1 ++)
                	  {
                  		  int rId1 = Integer.parseInt(idR[rI1]);//获得答案项编号
                  		  
                		  RadioItem rItem = new RadioItem();
                		  
                		  if(rId1 != -1)
                		  {
                			  rItem = radioOp12.getRadio(rId1);//获得调查项
                			  
                			  rItem.setRadioCaption(captionR[rI1]);//重新设置标题
                			  
                			  radioOp12.updateRadioItem(rItem);//更新调查项
                		  }
                		  else
                		  {
                		  
                			  rItem.setRadioOwnerID(item1220.getItemID());//所属调查项编号
                		  
                			  rItem.setRadioIndex(0);//所属调查项编号
                		  
                			  rItem.setRadioCaption(captionR[rI1]);//单选项的内容
                		  
                			  radioOp12.InsertRadioItem(rItem);//插入调查项
                		  }
                		  
                	  }
                  	  
			    	 out.print("success");
			    	}
			    	else
			    	{
			    		out.print("fail");
			    	}
			    		
			    }
			    
			    
			   break;
			   
			case 1:
				String []idC = idStr.split(",");
				
				String []captionC = captionStr.split(",");
				
			    int itemId121 = Integer.parseInt(idC[0]);
			    
			    if(itemId121 == -1)
			    {
			    	Item item121 = new Item();//新建调查项
			    	
			    	item121.setItemOwerID(user12.getUserID());
			    	
			    	item121.setItemCaption(captionC[0]);
			    	
			    	item121.setItemType(1);//文本调查项
			    	
			    	item121.setItemOwnerSurveyID(surveyid12);
			    	
			    	item121.setItemAttribute(2);//设置正在添加标记
			    	
		             if(itemOp12.InsertItem(item121))
		              {
          
                         List items = itemOp12.getItemList(item121);//获得调查项
                         
                         if(items.size() == 1)
                         {
                       	  
                       	  item121 = (Item)items.get(0);//获得第一个调查项
                       	  item121.setItemAttribute(1);//清除插入标记
                       	  itemOp12.updateItem(item121);//更新调查项
                       	  
                       	  
                       	  
                       	  for(int rI1 = 1; rI1 < idC.length;rI1 ++)
                       	  {
                       		  CheckboxItem rItem = new CheckboxItem();
                       		  
                       		  rItem.setCheckboxOwnerID(item121.getItemID());//所属调查项编号
                       		  
                       		  rItem.setCheckboxIndex(0);//所属调查项编号
                       		  
                       		  rItem.setCheckboxCaption(captionC[rI1]);//单选项的内容
                       		  
                       		  checkboxOp12.InsertCheckboxItem(rItem);//插入调查项
                       		  
                       	  }
                       	  
                       	  out.print("success");//输出成功标志
                         }
                         else
                         {
                        	 
                       	  out.print("fail");
                       	  
                         }
		                   
		              }
		             else
		             {
		            	out.print("fail"); //调查项插入失败
		             }
			    	
			    }
			    else
			    {
			    	Item item1221 = itemOp12.getItemByItemId(itemId121);//获得原有调查项信息
			    	
			    	item1221.setItemCaption(captionC[0]);//设置调查项标题
			    	
			    	if(itemOp12.updateItem(item1221))
			    	{
	                 	  for(int rI1 = 1; rI1 < idC.length;rI1 ++)
	                	  {
	                  		  int rId1 = Integer.parseInt(idC[rI1]);//获得答案项编号
	                  		  
	                  		  CheckboxItem rItem = new CheckboxItem();
	                		  
	                		  if(rId1 != -1)
	                		  {
	                			  rItem = checkboxOp12.getCheckbox(rId1);//获得调查项
	                			  
	                			  rItem.setCheckboxCaption(captionC[rI1]);//重新设置标题
	                			  
	                			  checkboxOp12.updateCheckboxItem(rItem);//更新调查项
	                		  }
	                		  else
	                		  {
	                		  
	                       		  rItem.setCheckboxOwnerID(item1221.getItemID());//所属调查项编号
	                       		  
	                       		  rItem.setCheckboxIndex(0);//所属调查项编号
	                       		  
	                       		  rItem.setCheckboxCaption(captionC[rI1]);//单选项的内容
	                       		  
	                       		  checkboxOp12.InsertCheckboxItem(rItem);//插入调查项
	                		  }
	                		  
	                	  }
	                  	  
				    	 out.print("success");
			    	}
			    	else
			    	{
			    		out.print("fail");
			    	}
			    		
			    }
				break;
				
			case 2:
				
			    int itemId12 = Integer.parseInt(idStr);
			    
			    if(itemId12 == -1)
			    {
			    	Item item122 = new Item();//新建调查项
			    	
			    	item122.setItemOwerID(user12.getUserID());
			    	
			    	item122.setItemCaption(captionStr);
			    	
			    	item122.setItemType(2);//文本调查项
			    	
			    	item122.setItemOwnerSurveyID(surveyid12);
			    	
		             if(itemOp12.InsertItem(item122))
		              {
          
		                   out.print("success");//调查项插入成功
		                   
		              }
		             else
		             {
		            	out.print("fail"); //调查项插入失败
		             }
			    	
			    }
			    else
			    {
			    	Item item1221 = itemOp12.getItemByItemId(itemId12);//获得原有调查项信息
			    	
			    	item1221.setItemCaption(captionStr);//设置调查项标题
			    	
			    	if(itemOp12.updateItem(item1221))
			    	{
			    	 out.print("success");
			    	}
			    	else
			    	{
			    		out.print("fail");
			    	}
			    		
			    }
			    
			  break;

			}
			
			
			break;			
			
		default:
			break;
		}
		
		out.flush();
		out.close();
	}

	//根据当前页码和菜单码确定要显示的菜单
	private String getPageBottomByNum(int pageNum,int pageListNum)
	{
		
		
		String results = "<div style='height:27px;width:348px;display:inline-block;'>";//调查问卷
		
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
	
	//根据当前页码和菜单栏码确定显示要显示的调查问卷
	private String getPageByNum(int pageNum,int pageListNum)
	{
		String results = "";//设置访问结果
		
        initParameters(pageNum,pageListNum);//调查问卷页和菜单页信息初始化
		
		this.surveyOp = new SurveyDaoImpl();//实例化调查问卷操作类
		
		List surveyList = this.surveyOp.getServeyList(user.getUserID());//获得调查问卷列表
		
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
			listNum = 0;//显示列表总数
		}
		
		//表格显示内容
	     results +="<div style='margin-top:10px;margin-bottom:10px;'><table border='0' cellpadding='0' cellspacing='0' class='tableClass'>";
	     results +="<tr>";
	     results +="<td align='center' class='tdHeader'>问卷标题</td>";
	     results +="<td align='center' class='tdHeader'>问卷说明</td>";
	     results +="<td align='center' class='tdHeader'>创建时间</td>";
	     results +="<td align='center' class='tdHeader'>创建者</td>";
	     results +="<td align='center' class='tdHeader'>操作</td>";
	     results +="<td align='center' class='tdHeader'>操作</td>";
	     results +="<td align='center' class='tdHeader'>操作</td>";
	     results +="</tr>";
	     
		UserDaoImpl userOp = new UserDaoImpl();//获得用户操作类
		
		for(int i = this.pageFirstIndex; i < this.pageFirstIndex + listNum; i++)
		{
			Survey survey = (Survey)surveyList.get(i);//获得调查问卷实体
			
			   User user = userOp.getUser(survey.getSurveyOwnerID());//获得当前调查问卷的发布人
			
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
		       
		       String userName = user.getUserName().length()<= nameSize?user.getUserName():user.getUserName().substring(0,nameSize);
		       
		       results += "<td class='tdContent' align='center'>" + userName + "</td>";
		         
		       results +="<td class='tdContent' width='30' align='center'>" + "<a href='javascript:updateSurveyDataById(" + survey.getSurveyID() + ")'>修改</a>" + "</td>";
		       results +="<td class='tdContent' width='30' align='center'>" + "<a href='javascript:deleteSurveyDataById(" + survey.getSurveyID() + ")'>删除</a>" + "</td>";
		       results +="<td class='tdContent' width='30' align='center'>" + "<a href='../showsurvey.jsp?surveyId=" + survey.getSurveyID() + "'>预览</a>" + "</td>";
		       results +="</tr>";
		       
		}
		
	    results +="</table></div>";
		
		results += "<div>调查问卷总数：" + this.surveyCount + " 调查问卷总页数：" + this.pageCount + " 调查问卷总菜单栏数：" + this.pageListCount + "</div>";
		
		return results;
	}
	//获得调查问卷总数
	private int getSurveyCount()
	{
		this.surveyOp = new SurveyDaoImpl();
		
		this.surveyCount = this.surveyOp.getServeyList(user.getUserID()).size();//获得调查问卷总数
		
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
	}

}
