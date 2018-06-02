package edu.bdu.dao;

import java.sql.SQLException;
import java.util.*;
import edu.bdu.entity.Visitor;

/**
 * 制作时间：2011年8月23日
 * @author lenov
 *
 */

public class VisitorDaoImpl extends BaseJdbcDao{
	
	/**
	 * 无条件查询所有访问者信息
	 * @return
	 */
	public List getVisitorList()
	{
		List retList = new ArrayList();
		
		String sql = "select * from VisitorTable";//查询全部访问者
		
		super.openConn();
		
		try {
			super.stmt = conn.createStatement();//获取数据库操作类
			
			super.rs = super.stmt.executeQuery(sql);
			
			while(super.rs.next()) {
				
				Visitor item = new Visitor();//访问者实例化
				
				item.setVisitorNumber(rs.getInt("VisitorNumber"));//获得访问者编号
				
				item.setVisitorIP(rs.getString("VisitorIP"));//访问者ip地址
				
				item.setVisiteSurveyID(rs.getInt("VisiteSurveyID"));//访问者访问的调查问卷编号
				
				item.setVisiteDateTime(rs.getDate("visiteDateTime"));//访问者访问时间
				
				item.setVisiteSurveyData(rs.getString("VisiteSurveyData"));//访问者提交的单选和多选项答案值
				
				retList.add(item);//
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//关闭数据库连接和操作类
		
		return retList;//访问者列表
	}
	
	/**
	 * 根据调查问卷编号查询访问者信息
	 * @param visiteSurveyID
	 * @return
	 */
	public List getVisitorList(int visiteSurveyID)
	{
	
		List retList = new ArrayList();
		
		String sql = "select * from VisitorTable where VisiteSurveyID = " + visiteSurveyID;//根据调查问卷编号查询全部访问者
		
		super.openConn();
		
		try {
			super.stmt = conn.createStatement();//获取数据库操作类
			
			super.rs = super.stmt.executeQuery(sql);
			
			while(super.rs.next()) {
				
				Visitor item = new Visitor();//访问者实例化
				
				item.setVisitorNumber(rs.getInt("VisitorNumber"));//获得访问者编号
				
				item.setVisitorIP(rs.getString("VisitorIP"));//访问者ip地址
				
				item.setVisiteSurveyID(rs.getInt("VisiteSurveyID"));//访问者访问的调查问卷编号
				
				item.setVisiteDateTime(rs.getDate("visiteDateTime"));//访问者访问时间
				
				item.setVisiteSurveyData(rs.getString("VisiteSurveyData"));//访问者提交的单选和多选项答案值
				
				retList.add(item);//
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//关闭数据库连接和操作类
		
		return retList;//访问者列表
	}
	
	/**
	 * 根据访问者编号查询访问者
	 * @param visitorNumber
	 * @return
	 */
	public Visitor getVisitor(int visitorNumber)
	{
		Visitor item = null;//访问者初始化 
		
		String sql = "select * from VisitorTable where VisitorNumber =" + visitorNumber;//根据访问者编号查询访问者
		
		super.openConn();
		
		try {
			super.stmt = conn.createStatement();//获取数据库操作类
			
			super.rs = super.stmt.executeQuery(sql);
			
			while(super.rs.next()) {
				
				item = new Visitor();//访问者实例化
				
				item.setVisitorNumber(rs.getInt("VisitorNumber"));//获得访问者编号
				
				item.setVisitorIP(rs.getString("VisitorIP"));//访问者ip地址
				
				item.setVisiteSurveyID(rs.getInt("VisiteSurveyID"));//访问者访问的调查问卷编号
				
				item.setVisiteDateTime(rs.getDate("visiteDateTime"));//访问者访问时间
				
				item.setVisiteSurveyData(rs.getString("VisiteSurveyData"));//访问者提交的单选和多选项答案值
				
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//关闭数据库连接和操作类
		
		return item;//访问者对象
	}
	
	/**
	 * 根据访问者ip地址查询访问者列表
	 * @param visitorIP
	 * @return
	 */
	public List getVisitorList(String visitorIP)
	{
	
		List retList = new ArrayList();
		
		String sql = "select * from VisitorTable where VisitorIP = '" + visitorIP + "'";//根据ip地址查询全部访问者
		
		super.openConn();
		
		try {
			super.stmt = conn.createStatement();//获取数据库操作类
			
			super.rs = super.stmt.executeQuery(sql);
			
			while(super.rs.next()) {
				
				Visitor item = new Visitor();//访问者实例化
				
				item.setVisitorNumber(rs.getInt("VisitorNumber"));//获得访问者编号
				
				item.setVisitorIP(rs.getString("VisitorIP"));//访问者ip地址
				
				item.setVisiteSurveyID(rs.getInt("VisiteSurveyID"));//访问者访问的调查问卷编号
				
				item.setVisiteDateTime(rs.getDate("visiteDateTime"));//访问者访问时间
				
				item.setVisiteSurveyData(rs.getString("VisiteSurveyData"));//访问者提交的单选和多选项答案值
				
				retList.add(item);//
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//关闭数据库连接和操作类
		
		return retList;//访问者列表
	}
	
	
	/**
	 * 根据访问者编号修改访问者内容，此方法当前阶段不使用，以防管理者有特殊需要
	 * @param visitor
	 * @return
	 */
	public boolean updateVisitor(Visitor visitor)
	{
	  boolean flag = false;//修改结果标记
	  
	  String updateSql = "update VisitorTable set VisitorIP = ?,VisiteSurveyID = ?," +
	  		"VisiteSurveyData = ? where VisitorNumber = ?";//更新的sql语句
	  
	  super.openConn();//打开数据库连接
		
		try
		{
		  super.pstmt = conn.prepareStatement(updateSql);//调用sql预处理对象执行查询
		  
		  super.pstmt.setString(1, visitor.getVisitorIP());//为sql语句访问者ip地址
		  
		  super.pstmt.setInt(2, visitor.getVisiteSurveyID());//为sql语句访问者访问的调查问卷编号
		  
	      super.pstmt.setString(3, visitor.getVisiteSurveyData());//访问者内容
		  
		  super.pstmt.setInt(4, visitor.getVisitorNumber());//为访问者编号
		 
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
	 * 根据访问者编号删除访问者信息
	 * @param visitorNumber
	 * @return
	 */
	public boolean deleteVisitor(int visitorNumber)
	{
		  boolean flag = false;//修改结果标记
		  
		  String deleteSql = "delete from VisitorTable where VisitorNumber = ?";//删除的sql语句
		  
		  super.openConn();//打开数据库连接
			
			try
			{
			  super.pstmt = conn.prepareStatement(deleteSql);//调用sql预处理对象执行查询
			  
			  super.pstmt.setInt(1, visitorNumber);//为sql语句访问者编号赋值
			  
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
		  
		  return flag;//返回修改结果
	}
	
	/**
	 * 根据访问者访问的调查问卷的编号删除访问者信息
	 * @param visiteSurveyID
	 * @return
	 */
	public boolean deleteVisitorsBySurveyId(int visiteSurveyID)
	{
		  boolean flag = false;//修改结果标记
		  
		  String deleteSql = "delete from VisitorTable where VisiteSurveyID = ?";//删除的sql语句
		  
		  super.openConn();//打开数据库连接
			
			try
			{
			  super.pstmt = conn.prepareStatement(deleteSql);//调用sql预处理对象执行查询
			  
			  super.pstmt.setInt(1, visiteSurveyID);//为sql语句访问者编号赋值
			  
			  int deleteRows = super.pstmt.executeUpdate();//执行删除语句
			  
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
		  
		  return flag;//返回修改结果
	}
	
	/**
	 * 添加访问者 
	 * @param visitor
	 * @return
	 */
	public boolean InsertVisitor(Visitor visitor)
	{
		  boolean flag = false;//添加结果标记
		  
		  String insertSql = "insert into VisitorTable(VisitorIP,VisiteSurveyID,VisiteSurveyData) values(?,?,?)";//添加的sql语句
		  
		  super.openConn();//打开数据库连接
			
			try
			{
			  super.pstmt = conn.prepareStatement(insertSql);//调用sql预处理对象执行查询
			
			  super.pstmt.setString(1, visitor.getVisitorIP());//设置访问者ip地址
			  
			  super.pstmt.setInt(2, visitor.getVisiteSurveyID());;//设置调查问卷编号
			  
			  super.pstmt.setString(3, visitor.getVisiteSurveyData());//调查问卷回答答案记录
			
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
