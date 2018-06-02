package edu.bdu.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.bdu.entity.RadioItem;

/**
 * 制作日期：2011年8月21日
 * 版本 1.0
 * @author lenov
 *
 */
public class RadioItemDaoImpl extends BaseJdbcDao{
	/**
	 * 无条件返回单选项答案项列表
	 * @return
	 */
	public List getRadioList()
	{
		   List retList = new ArrayList();//查询单选项列表
		   
			String sql = "select * from RadioTable";//单选调查答案项查询
			
			super.openConn();
			try {
				super.stmt = conn.createStatement();//获取数据库操作类
				
				super.rs = super.stmt.executeQuery(sql);
				
				while(super.rs.next()) {
					
					RadioItem item = new RadioItem();//单选答案项实例化
					
					item.setRadioID(rs.getInt("RadioID"));//获得单选答案项编号
					
					item.setRadioOwnerID(rs.getInt("RadioOwnerID"));//获得单选答案项所属调查项编号
					
					item.setRadioIndex(rs.getInt("RadioIndex"));//获得单选答案项所属调查项中的编号
					
					item.setRadioCaption(rs.getString("RadioCaption"));//获得单选项标题
					
					item.setDefaultSelected(rs.getInt("DefaultSelected"));//获得单选项是否默认，此阶段未用
					
					item.setSelectCount(rs.getInt("SelectedCount"));//获得单选项被选择次数
					
					retList.add(item);//添单选答案项 
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			super.closeAll();//关闭数据库连接和操作类
			
		   return retList;//返回单选项列表
	}

	/**
	 * 根据单选项编号查询单选项
	 * @param RadioID
	 * @return
	 */
	public RadioItem getRadio(int RadioID)
	{   
		String sql = "select * from RadioTable where RadioID = " + RadioID;//单选调查答案项查询
		
		RadioItem item = null;//实例化单选项
		
		super.openConn();
		try {
			super.stmt = conn.createStatement();//获取数据库操作类
			
			super.rs = super.stmt.executeQuery(sql);
			
			if(super.rs.next()) {
				
				item = new RadioItem();//单选答案项实例化
				
				item.setRadioID(rs.getInt("RadioID"));//获得单选答案项编号
				
				item.setRadioOwnerID(rs.getInt("RadioOwnerID"));//获得单选答案项所属调查项编号
				
				item.setRadioIndex(rs.getInt("RadioIndex"));//获得单选答案项所属调查项中的编号
				
				item.setRadioCaption(rs.getString("RadioCaption"));//获得单选项标题
				
				item.setDefaultSelected(rs.getInt("DefaultSelected"));//获得单选项是否默认，此阶段未用
				
				item.setSelectCount(rs.getInt("SelectedCount"));//获得单选项被选择次数
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//关闭数据库连接和操作类
		
	   return item;//返回单选项
	}

	/**
	 * 根据单选项所属调查项编号查询单选列表
	 * @param RadioOwnerID
	 * @return
	 */
	public List getRadioList(int RadioOwnerID)
	{
		    List retList = new ArrayList();//查询单选项列表
		   
			String sql = "select * from RadioTable where RadioOwnerID =" + RadioOwnerID + 
			" order by RadioIndex";//单选调查答案项查询
			
			super.openConn();
			try {
				super.stmt = conn.createStatement();//获取数据库操作类
				
				super.rs = super.stmt.executeQuery(sql);
				
				while(super.rs.next()) {
					
					RadioItem item = new RadioItem();//单选答案项实例化
					
					item.setRadioID(rs.getInt("RadioID"));//获得单选答案项编号
					
					item.setRadioOwnerID(rs.getInt("RadioOwnerID"));//获得单选答案项所属调查项编号
					
					item.setRadioIndex(rs.getInt("RadioIndex"));//获得单选答案项所属调查项中的编号
					
					item.setRadioCaption(rs.getString("RadioCaption"));//获得单选项标题
					
					item.setDefaultSelected(rs.getInt("DefaultSelected"));//获得单选项是否默认，此阶段未用
					
					item.setSelectCount(rs.getInt("SelectedCount"));//获得单选项被选择次数
					
					retList.add(item);//添单选答案项 
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			super.closeAll();//关闭数据库连接和操作类
			
		   return retList;//返回单选项列表
	}

	/**
	 * 根据单选项编号更新单选项
	 * @param item
	 * @return
	 */
    public boolean updateRadioItem(RadioItem item)
    {
		 boolean flag = false;//修改结果标记
		  
		  String updateSql = "update RadioTable set RadioIndex = ?,RadioCaption = ?,SelectedCount = ?" +
		  		" where RadioID = ?";//更新的sql语句
		  
		  super.openConn();//打开数据库连接
			
			try
			{
			  super.pstmt = conn.prepareStatement(updateSql);//调用sql预处理对象执行查询
			 
			  super.pstmt.setInt(1, item.getRadioIndex());//设置单选项编号
			  
			  super.pstmt.setString(2, item.getRadioCaption());//设置单选项标题
			  
			  super.pstmt.setInt(3, item.getSelectCount());//单选项选择次数
			  
			  super.pstmt.setInt(4, item.getRadioID());//单选项编号
			  
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
     * 根据单选项编号删除单选项
     * @param RadioID
     * @return
     */
    public boolean deleteRadioItem(int RadioID)
    {
		 boolean flag = false;//修改结果标记
		  
		  String deleteSql = "delete from RadioTable where RadioID = ?";//删除单选项的sql语句
		  
		  super.openConn();//打开数据库连接
			
			try
			{
			  super.pstmt = conn.prepareStatement(deleteSql);//调用sql预处理对象执行查询
			  
			  super.pstmt.setInt(1, RadioID);//为sql语句单选项id赋值
			  
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
    
    /**
     * 根据调查项编号删除单选项
     * @param RadioOwnerID
     * @return
     */
    public boolean deleteRadioItemByOwner(int RadioOwnerID)
    {
		 boolean flag = false;//修改结果标记
		  
		  String deleteSql = "delete from RadioTable where RadioOwnerID = ?";//删除单选项的sql语句
		  
		  super.openConn();//打开数据库连接
			
			try
			{
			  super.pstmt = conn.prepareStatement(deleteSql);//调用sql预处理对象执行查询
			  
			  super.pstmt.setInt(1, RadioOwnerID);//为sql语句单选项所属调查项编号赋值
			  
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
     * 添加单选项
     * @param item
     * @return
     */
    public boolean InsertRadioItem(RadioItem item)
    {
        boolean flag = false;//添加结果标记
    	  
    	  String insertSql = "insert into RadioTable(RadioOwnerID,RadioIndex,RadioCaption,DefaultSelected," +
    	  		"SelectedCount) values(?,?,?,0,0)";//添加的sql语句
    	  
    	  super.openConn();//打开数据库连接
    		
    		try
    		{
    		  super.pstmt = conn.prepareStatement(insertSql);//调用sql预处理对象执行查询
    		  
    		  super.pstmt.setInt(1, item.getRadioOwnerID());//设置单选项所属调查问卷编号
    		 
    		  super.pstmt.setInt(2,item.getRadioIndex());//设置单选项顺序号
    		  
    		  super.pstmt.setString(3, item.getRadioCaption());//设置单选项标题
    		  
    		  //super.pstmt.setInt(4, item.getSelectCount());//设置单选项点击次数
    		  
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
