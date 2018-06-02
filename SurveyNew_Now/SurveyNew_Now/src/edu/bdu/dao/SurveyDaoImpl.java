package edu.bdu.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.bdu.entity.Survey;




/**
 * 制作日期：2011年8月19日
 * 版本 1.0
 * @author lenov
 *
 */
public class SurveyDaoImpl extends BaseJdbcDao{
	
	/**
	 * 获得所有调查问卷列表
	 * @return
	 */
	public List getServeyList()
	{
		List retList = new ArrayList();
		
		String sql = "select * from SurveyTable order by SurveyCreateDateTime desc";
		
	    //sql += " and SurveyOwnerID = " + surveyOwnerID;//获得调查问卷创建用户编号
		
		super.openConn();
		
		try {
			super.stmt = conn.createStatement();//获取数据库操作类
			
			super.rs = super.stmt.executeQuery(sql);
			
			while(super.rs.next()) {
				
				Survey item = new Survey();
				
				item.setSurveyID(rs.getInt("SurveyID"));//获得调查问卷id
				
				item.setSurveyTitle(rs.getString("SurveyTitle"));//获得调查问卷标题
				
				item.setSurveyOwnerID(rs.getInt("SurveyOwnerID"));//获得调查问卷创建用户编号
				
				item.setSurveyLink(rs.getString("SurveyLink"));//获取调查问卷连接
				
				item.setSurveyCreateDate(rs.getDate("SurveyCreateDateTime"));//获取调查问卷创建时间
				
				item.setSurveyExpirationDate(rs.getString("SurveyExpirationDateTime"));//获取调查问卷过期时间
				
				retList.add(item);//添加调查问卷项 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//关闭数据库连接和操作类
		
		return retList;//返回调查问卷列表
	}
	
	
	/**
	 * 根据调查问卷创建者编号查找调查问卷
	 * @param surveyOwnerID
	 * @return surveylist
	 */
	public List getServeyList(int surveyOwnerID)
	{
		List retList = new ArrayList();
		
		String sql = "select * from SurveyTable where 1 = 1";
		
	    sql += " and SurveyOwnerID = " + surveyOwnerID;//获得调查问卷创建用户编号
		
		super.openConn();
		try {
			super.stmt = conn.createStatement();//获取数据库操作类
			
			super.rs = super.stmt.executeQuery(sql);
			
			while(super.rs.next()) {
				
				Survey item = new Survey();
				
				item.setSurveyID(rs.getInt("SurveyID"));//获得调查问卷id
				
				item.setSurveyTitle(rs.getString("SurveyTitle"));//获得调查问卷标题
				
				item.setSurveyOwnerID(rs.getInt("SurveyOwnerID"));//获得调查问卷创建用户编号
				
				item.setSurveyLink(rs.getString("SurveyLink"));//获取调查问卷连接
				
				item.setSurveyCreateDate(rs.getDate("SurveyCreateDateTime"));//获取调查问卷创建时间
				
				item.setSurveyExpirationDate(rs.getString("SurveyExpirationDateTime"));//获取调查问卷过期时间
				
				retList.add(item);//添加调查问卷项 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//关闭数据库连接和操作类
		
		return retList;//返回调查问卷列表
	}
	
	/**
	 * 根据调查问卷的创建者id和条件查询调查问卷列表
	 * @param surveyOwnerID
	 * @param condition
	 * @return
	 */
	public List getServeyList(int surveyOwnerID,Survey condition)
	{
		List retList = new ArrayList();
		
		String sql = "select * from SurveyTable where 1 = 1";
		
		if(surveyOwnerID != 0)
		{
		   sql += " and SurveyOwnerID = " + surveyOwnerID;//获得调查问卷创建用户编号
		}
		
		
		if(null != condition)
		{
			if(condition.getSurveyID() != 0) 
			{
				sql += " and SurveyID = " + condition.getSurveyID();//获得调查问卷编号
			}
			
			if(!isNullOrEmpty(condition.getSurveyTitle()))
			{
				sql += " and SurveyTitle like '%" + condition.getSurveyTitle() + "%'";//获取调查问卷标题
			}
			
	
			if(!isNullOrEmpty(condition.getSurveyLink()))
			{
				sql += " and SurveyLink = '" + condition.getSurveyLink() + "'";//获得调查问卷连接设置
			}
			
			if(condition.getSurveyCreateDate() != null)
			{
				Date date = condition.getSurveyCreateDate();
				
				String strDate = date.getYear() + "-" + date.getMonth() + "-" + date.getDate();//格式化日期，精确到日
				
			    sql += " and SurveyCreateDateTime = '" + strDate + "'";//获得调查问卷创建的时间
			}
			
			if(!isNullOrEmpty(condition.getSurveyExpirationDate()))
			{
				
				String strDate = condition.getSurveyExpirationDate();//获得过期日期
				
				
				
				sql += " and SurveyExpirationDateTime ='" + strDate + "'";//获得调查问卷设置的过期时间
			}
		}
		
		super.openConn();
		try {
			super.stmt = conn.createStatement();//获取数据库操作类
			
			super.rs = super.stmt.executeQuery(sql);
			
			while(super.rs.next()) {
				
				Survey item = new Survey();
				
				item.setSurveyID(rs.getInt("SurveyID"));//获得调查问卷id
				
				item.setSurveyTitle(rs.getString("SurveyTitle"));//获得调查问卷标题
				
				item.setSurveyOwnerID(rs.getInt("SurveyOwnerID"));//获得调查问卷创建用户编号
				
				item.setSurveyLink(rs.getString("SurveyLink"));//获取调查问卷连接
				
				item.setSurveyCreateDate(rs.getDate("SurveyCreateDateTime"));//获取调查问卷创建时间
				
				item.setSurveyExpirationDate(rs.getString("SurveyExpirationDateTime"));//获取调查问卷过期时间
				
				retList.add(item);//添加调查问卷项 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//关闭数据库连接和操作类
		
		return retList;//返回调查问卷列表
	}
	/***
	 * 根据条件获得调查问卷列表信息
	 * @param condition
	 * @return
	 */
	
	public List getServeyList(Survey condition)
	{
		List retList = new ArrayList();
		
		String sql = "select * from SurveyTable where 1 = 1";
		
		if(null != condition)
		{
			if(condition.getSurveyID() != 0) 
			{
				sql += " and SurveyID = " + condition.getSurveyID();//获得调查问卷编号
			}
			
			if(!isNullOrEmpty(condition.getSurveyTitle()))
			{
				sql += " and SurveyTitle like '%" + condition.getSurveyTitle() + "%'";//获取调查问卷标题
			}
			
			if(condition.getSurveyOwnerID() != 0)
			{
			   sql += " and SurveyOwnerID = " + condition.getSurveyOwnerID();//获得调查问卷创建用户编号
			}
			
			if(!isNullOrEmpty(condition.getSurveyLink()))
			{
				sql += " and SurveyLink = '" + condition.getSurveyLink() + "'";//获得调查问卷连接设置
			}
			
			if(condition.getSurveyCreateDate() != null)
			{
				Date date = condition.getSurveyCreateDate();
				
				String strDate = date.getYear() + "-" + date.getMonth() + "-" + date.getDate();//格式化日期，精确到日
				
			    sql += " and SurveyCreateDateTime = '" + strDate + "'";//获得调查问卷创建的时间
			}
			
			if(!isNullOrEmpty(condition.getSurveyExpirationDate()))
			{
				String strDate = condition.getSurveyExpirationDate();//获得过期日期
				
				sql += " and SurveyExpirationDateTime ='" + strDate + "'";//获得调查问卷设置的过期时间
			}
		}
		
		super.openConn();
		try {
			super.stmt = conn.createStatement();//获取数据库操作类
			
			super.rs = super.stmt.executeQuery(sql);
			
			while(super.rs.next()) {
				
				Survey item = new Survey();
				
				item.setSurveyID(rs.getInt("SurveyID"));//获得调查问卷id
				
				item.setSurveyTitle(rs.getString("SurveyTitle"));//获得调查问卷标题
				
				item.setSurveyOwnerID(rs.getInt("SurveyOwnerID"));//获得调查问卷创建用户编号
				
				item.setSurveyLink(rs.getString("SurveyLink"));//获取调查问卷连接
				
				item.setSurveyCreateDate(rs.getDate("SurveyCreateDateTime"));//获取调查问卷创建时间
				
				item.setSurveyExpirationDate(rs.getString("SurveyExpirationDateTime"));//获取调查问卷过期时间
				
				retList.add(item);//添加调查问卷项 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//关闭数据库连接和操作类
		
		return retList;//返回调查问卷列表
	}
	
	/**
	 * 根据调查问卷id获取调查问卷信息
	 * @param SurveyID
	 * @return Survey
	 */
	public Survey getSurvey(int surveyID)
	{
		Survey survey = null;//创建调查问卷实例
		
		String sql = "select * from SurveyTable where SurveyID = ?";//查询语句
		
		super.openConn();//打开数据库连接
		
		try
		{
		  super.pstmt = conn.prepareStatement(sql);//调用sql预处理对象执行查询
		  
		  pstmt.setInt(1,surveyID);//为调查问卷d赋值
		  
		  super.rs = super.pstmt.executeQuery();//执行调查问卷的查询
		  
		  if(super.rs.next())
		  {
			  
			survey = new Survey();//初始化调查问卷
			
			survey.setSurveyID(rs.getInt("SurveyID"));//获取调查问卷编号
			
			survey.setSurveyTitle(rs.getString("SurveyTitle"));//获得调查问卷的标题
			
			survey.setSurveyLink(rs.getString("SurveyLink"));//获得调查问卷的说明连接
			
			survey.setSurveyOwnerID(rs.getInt("SurveyOwnerID"));//获得调查问卷所属用户的编号
			
			survey.setSurveyCreateDate(rs.getDate("SurveyCreateDateTime"));//获得调查问卷创建日期
			
			survey.setSurveyExpirationDate(rs.getString("SurveyExpirationDateTime"));//获得调查问卷过期日期设置
		  }
		  
		}
		catch(SQLException e)
		{
			
		   e.printStackTrace();//错误信息打印
		   
		}
		
		super.closeAll();//关闭数据库对象和数据库连接
		
		return survey;//将调查问卷类实例返回
	}
	
	
	/**
	 * 根据调查问卷id获取调查问卷信息
	 * @param SurveyID
	 * @return Survey
	 */
	public Survey getServeyByTitle(String surveyTitle)
	{
		Survey survey = null;//创建调查问卷实例
		
		String sql = "select * from SurveyTable where SurveyTitle = ?";//查询语句
		
		super.openConn();//打开数据库连接
		
		try
		{
		  super.pstmt = conn.prepareStatement(sql);//调用sql预处理对象执行查询
		  
		  pstmt.setString(1,surveyTitle);//为调查问卷d赋值
		  
		  super.rs = super.pstmt.executeQuery();//执行调查问卷的查询
		  
		  if(super.rs.next())
		  {
			  
			survey = new Survey();//初始化调查问卷
			
			survey.setSurveyID(rs.getInt("SurveyID"));//获取调查问卷编号
			
			survey.setSurveyTitle(rs.getString("SurveyTitle"));//获得调查问卷的标题
			
			survey.setSurveyLink(rs.getString("SurveyLink"));//获得调查问卷的说明连接
			
			survey.setSurveyOwnerID(rs.getInt("SurveyOwnerID"));//获得调查问卷所属用户的编号
			
			survey.setSurveyCreateDate(rs.getDate("SurveyCreateDateTime"));//获得调查问卷创建日期
			
			survey.setSurveyExpirationDate(rs.getString("SurveyExpirationDateTime"));//获得调查问卷过期日期设置
		  }
		  
		}
		catch(SQLException e)
		{
			
		   e.printStackTrace();//错误信息打印
		   
		}
		
		super.closeAll();//关闭数据库对象和数据库连接
		
		return survey;//将调查问卷类实例返回
	}
	
	/**
	 * 根据调查问卷id修改调查问卷信息
	 * @param Survey
	 * @return boolean
	 */
	
	public boolean updateSurvey(Survey survey)
	{
	  boolean flag = false;//修改结果标记
	  
	  String updateSql = "update SurveyTable set SurveyTitle = ?,SurveyLink = ?," +
	  		"SurveyExpirationDateTime = ? where SurveyID = ?";//更新的sql语句
	  
	  super.openConn();//打开数据库连接
		
		try
		{
		  super.pstmt = conn.prepareStatement(updateSql);//调用sql预处理对象执行查询
		  
		  super.pstmt.setString(1, survey.getSurveyTitle());//为sql语句调查问卷标题赋值
		  
		  super.pstmt.setString(2, survey.getSurveyLink());//为sql语句调查问卷说明连接赋值
		  
		  String expiration = survey.getSurveyExpirationDate();//获得调查问卷过期日期设置
		  if(expiration != null && !expiration.equals(""))//如果过期日期不为空
		  {
			  String strDate = survey.getSurveyExpirationDate();//获得日期的引用
			  
			  
			  super.pstmt.setString(3, strDate);//为调查问卷过期时间设置 
		  }
		  else
		  {
			  super.pstmt.setDate(3,null);//如果创建日期是空值的话赋空值
		  }
		  
		  super.pstmt.setInt(4, survey.getSurveyID());//为调查问卷id赋值
		 
		  int updateRows = super.pstmt.executeUpdate();//执行更新语句
		  
		  if(updateRows == 1)
		  {
		    flag = true;//标志更新成功
		  }
		  
		}
		catch(SQLException e)
		{
			
		   e.printStackTrace();//错误信息打印 
		}
		
		super.closeAll();//关闭数据库对象和数据库连接
	  
	   return flag;//返回结果标记
	  
	}
	
	
	/**
	 * 根据调查问卷创建者编号删除调查问卷
	 * @param surveyOwnerID
	 * @return 返回操作结果
	 */
	public boolean deleteSurveyByOwner(int surveyOwnerID)
	{
		 boolean flag = false;//修改结果标记
		  
		  String deleteSql = "delete from SurveyTable where　SurveyOwnerID = ?";//删除调查问卷的sql语句
		  
		  super.openConn();//打开数据库连接
			
			try
			{
			  super.pstmt = conn.prepareStatement(deleteSql);//调用sql预处理对象执行查询
			  
			
			  super.pstmt.setInt(1, surveyOwnerID);//为sql语句调查问卷创建者编号
			  
			  int deleteRows = super.pstmt.executeUpdate();//执行更新语句
			  
			  if(deleteRows >= 1)
			  {
			    flag = true;//标志删除成功
			  }
			  
			}
			catch(SQLException e)
			{
			   e.printStackTrace();//错误信息打印 
			}
			
			super.closeAll();//关闭数据库对象和数据库连接
		  
		   return flag;//返回结果标记
	}
	
	/**
	 * 根据调查问卷id删除调查问卷信息
	 * @param userID
	 * @return boolean
	 */
	
	public boolean deleteSurvey(int surveyID)
	{
		 boolean flag = false;//修改结果标记
		  
		  String deleteSql = "delete from SurveyTable where SurveyID = ?";//删除调查问卷的sql语句
		  
		  super.openConn();//打开数据库连接
			
			try
			{
			  super.pstmt = conn.prepareStatement(deleteSql);//调用sql预处理对象执行查询
			  
			  super.pstmt.setInt(1, surveyID);//为sql语句调查问卷id赋值
			  
			  int deleteRows = super.pstmt.executeUpdate();//执行删除语句
			  
			  if(deleteRows == 1)
			  {
			    flag = true;//标志删除成功
			  }
			  
			}
			catch(SQLException e)
			{
			   e.printStackTrace();//错误信息打印 
			}
			
			super.closeAll();//关闭数据库对象和数据库连接
		  
		   return flag;//返回结果标记
	}
	
	/**
	 * 添加调查问卷信息
	 * @param Survey
	 * @return boolean
	 */
	
	public boolean InsertSurvey(Survey survey)
	{
	  boolean flag = false;//添加结果标记
	  
	  String insertSql = "insert into SurveyTable(SurveyTitle,SurveyLink,SurveyOwnerID,SurveyExpirationDateTime) values(?,?,?,?)";//添加的sql语句
	  
	  super.openConn();//打开数据库连接
		
		try
		{
		  super.pstmt = conn.prepareStatement(insertSql);//调用sql预处理对象执行查询
		  
		  super.pstmt.setString(1, survey.getSurveyTitle());//为sql语句调查问卷标题
		  
		  super.pstmt.setString(2, survey.getSurveyLink());//为sql语句调查问卷说明连接赋值
		  
          super.pstmt.setInt(3, survey.getSurveyOwnerID());//创建调查问卷创建者id
		  
          ///////////////设置过期时间/////////////////
		  if(survey.getSurveyExpirationDate() != null)//如果过期日期不为空
		  {
			  super.pstmt.setString(4, survey.getSurveyExpirationDate());//为调查问卷过期时间设置 
		  }
		  else
		  {
			  super.pstmt.setString(4,null);//如果创建日期是空值的话赋空值
		  }
		 
		  int insertRows = super.pstmt.executeUpdate();//执行更新语句
		  
		  if(insertRows == 1)
		  {
		    flag = true;//标志插入成功
		  }
		  
		}
		catch(SQLException e)
		{
			
		   e.printStackTrace();//错误信息打印 
		}
		
		super.closeAll();//关闭数据库对象和数据库连接
	  
	  return flag;//返回结果标记
	  
	}
	
	
	
	


}
