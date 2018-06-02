package edu.bdu.dao;
/**
 * 制作日期：2011年8月21日
 * 版本 1.0
 * @author lenov
 *
 */
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import edu.bdu.entity.TextItem;
public class TextItemDaoImpl extends BaseJdbcDao{
	
	/**
	 * 获得文字调查项列表
	 * @return
	 */
	public List getTextList()
	{
	   List retList = new ArrayList();//查询文字调查项列表
	   
		String sql = "select * from TextTable";//文字调查答案项查询
		
		super.openConn();
		try {
			super.stmt = conn.createStatement();//获取数据库操作类
			
			super.rs = super.stmt.executeQuery(sql);
			
			while(super.rs.next()) {
				
				TextItem item = new TextItem();//实例化调查项答案项
				
				item.setTextID(rs.getInt("TextID"));//获得文字答案项编号
				
				item.setTextOwnerID(rs.getInt("TextOwnerID"));//获得文字项编号
				
				item.setTextCaption(rs.getString("TextCaption"));//获得文字答案项标题
				
				item.setTextContent(rs.getString("TextContent"));//获得文字答案项内容
				
				retList.add(item);//添文字答案项 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//关闭数据库连接和操作类
		
	   return retList;//返回文字项列表
	}

	/**
	 * 根据文字项编号查询文字项
	 * @param TextID
	 * @return item
	 */
	public TextItem getText(int TextID)
	{
		   TextItem item = null;//文字项答案项
		   
			String sql = "select * from TextTable where TextID = " + TextID;//文字调查答案项编号查询
	
			super.openConn();
			try {
				super.stmt = conn.createStatement();//获取数据库操作类
				
				super.rs = super.stmt.executeQuery(sql);
				
				if(super.rs.next()) {
					
					item = new TextItem();//实例化调查项答案项
					
					item.setTextID(rs.getInt("TextID"));//获得文字答案项编号
					
					item.setTextOwnerID(rs.getInt("TextOwnerID"));//获得文字项编号
					
					item.setTextCaption(rs.getString("TextCaption"));//获得文字答案项标题
					
					item.setTextContent(rs.getString("TextContent"));//获得文字答案项内容
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			super.closeAll();//关闭数据库连接和操作类
			
		   return item;//返回文字项
	}
	
	/**
	 * 根据文字调查所属调查问卷编号
	 * @param TextOwnerID
	 * @return
	 */
	public List getTextListByOwner(int TextOwnerID)
	{
		List retList = new ArrayList();//查询文字调查项列表
		   
		String sql = "select * from TextTable where TextOwnerID = " + TextOwnerID;//文字调查答案项编号查询
		
		super.openConn();
		try {
			super.stmt = conn.createStatement();//获取数据库操作类
			
			super.rs = super.stmt.executeQuery(sql);
			
			while(super.rs.next()) {
				
				TextItem item = new TextItem();//实例化调查项答案项
				
				item.setTextID(rs.getInt("TextID"));//获得文字答案项编号
				
				item.setTextOwnerID(rs.getInt("TextOwnerID"));//获得文字项编号
				
				item.setTextCaption(rs.getString("TextCaption"));//获得文字答案项标题
				
				item.setTextContent(rs.getString("TextContent"));//获得文字答案项内容
				
				retList.add(item);//添文字答案项 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//关闭数据库连接和操作类
		
	   return retList;//返回文字项列表
	}
    
	/**
	 * 根据文字答案项编号更新文字答案项
	 * @param item
	 * @return
	 */
	public boolean updateTextItem(TextItem item)
	{
		 boolean flag = false;//修改结果标记
		  
		  String updateSql = "update TextTable set TextCaption = ?,TextContent = ?" +
		  		" where TextID = ?";//更新的sql语句
		  
		  super.openConn();//打开数据库连接
			
			try
			{
			  super.pstmt = conn.prepareStatement(updateSql);//调用sql预处理对象执行查询
			 
			  super.pstmt.setString(1, item.getTextCaption());//设置文字答案项标题
			  
			  super.pstmt.setString(2, item.getTextContent());//设置文字答案项内容
			  
			  super.pstmt.setInt(3, item.getTextID());//更新文字答案项编号
			  
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
     * 根据文字项编号删除文字项
     * @param TextID
     * @return
     */
	public boolean deleteTextItem(int TextID)
	{
		 boolean flag = false;//修改结果标记
		  
		  String deleteSql = "delete from TextTable where TextID = ?";//删除文字项的sql语句
		  
		  super.openConn();//打开数据库连接
			
			try
			{
			  super.pstmt = conn.prepareStatement(deleteSql);//调用sql预处理对象执行查询
			  
			  super.pstmt.setInt(1, TextID);//为sql语句文字项id赋值
			  
			  int deleteRows = super.pstmt.executeUpdate();//执行更新语句
			  
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
	/***
	 * 根据文字项所属调查问卷编号删除调查项
	 * @param TextOwnerID
	 * @return
	 */
	public boolean deleteTextItemByOwner(int TextOwnerID)
	{
		 boolean flag = false;//修改结果标记
		  
		  String deleteSql = "delete from TextTable where TextOwnerID = ?";//删除文字项的sql语句
		  
		  super.openConn();//打开数据库连接
			
			try
			{
			  super.pstmt = conn.prepareStatement(deleteSql);//调用sql预处理对象执行查询
			  
			  super.pstmt.setInt(1, TextOwnerID);//为sql语句文字项所属调查问卷编号赋值
			  
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
	 * 添加文字项
	 * @param item
	 * @return
	 */
    public boolean InsertTextItem(TextItem item)
    {
      boolean flag = false;//添加结果标记
  	  
  	  String insertSql = "insert into TextTable(TextOwnerID,TextCaption,TextContent) values(?,?,?)";//添加的sql语句
  	  
  	  super.openConn();//打开数据库连接
  		
  		try
  		{
  		  super.pstmt = conn.prepareStatement(insertSql);//调用sql预处理对象执行查询
  		  
  		  super.pstmt.setInt(1, item.getTextOwnerID());//设置文字项所属调查问卷编号
  		 
  		  super.pstmt.setString(2,item.getTextCaption());//设置文字项编号
  		  
  		  super.pstmt.setString(3, item.getTextContent());//设置文字项内容
  		  
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
