package edu.bdu.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.bdu.entity.CheckboxItem;

/**
 * 制作日期：2011年8月21日
 * 版本 1.0
 * @author lenov
 *
 */
public class CheckboxItemDaoImpl extends BaseJdbcDao{
	
	/**
	 * 无条件返回多选项答案项列表
	 * @return
	 */
	public List getCheckboxList()
	{
		   List retList = new ArrayList();//查询多选项列表
		   
			String sql = "select * from CheckboxTable";//多选调查答案项查询
			
			super.openConn();
			
			try {
				super.stmt = conn.createStatement();//获取数据库操作类
				
				super.rs = super.stmt.executeQuery(sql);
				
				while(super.rs.next()) {
					
					CheckboxItem item = new CheckboxItem();//多选答案项实例化
					
					item.setCheckboxID(rs.getInt("CheckboxID"));//获得多选答案项编号
					
					item.setCheckboxOwnerID(rs.getInt("CheckboxOwnerID"));//获得多选答案项所属调查项编号
					
					item.setCheckboxIndex(rs.getInt("CheckboxIndex"));//获得多选答案项所属调查项中的编号
					
					item.setCheckboxCaption(rs.getString("CheckboxCaption"));//获得多选项标题
					
					item.setDefaultSelected(rs.getInt("DefaultSelected"));//获得多选项是否默认，此阶段未用
					
					item.setSelectCount(rs.getInt("SelectedCount"));//获得多选项被选择次数
					
					retList.add(item);//添多选答案项
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			super.closeAll();//关闭数据库连接和操作类
			
		   return retList;//返回多选项列表
	}
	
	
	/**
	 * 根据多选项所属调查项的编号查询多选项
	 * @param CheckboxOwnerID
	 * @return
	 */
	public List getCheckboxList(int CheckboxOwnerID)
	{
		    List retList = new ArrayList();//查询多选项列表
		   
			String sql = "select * from CheckboxTable where CheckboxOwnerID = " + CheckboxOwnerID;//多选调查答案项查询
			
			super.openConn();
			try {
				super.stmt = conn.createStatement();//获取数据库操作类
				
				super.rs = super.stmt.executeQuery(sql);
				
				while(super.rs.next()) {
					
					CheckboxItem item = new CheckboxItem();//多选答案项实例化
					
					item.setCheckboxID(rs.getInt("CheckboxID"));//获得多选答案项编号
					
					item.setCheckboxOwnerID(rs.getInt("CheckboxOwnerID"));//获得多选答案项所属调查项编号
					
					item.setCheckboxIndex(rs.getInt("CheckboxIndex"));//获得多选答案项所属调查项中的编号
					
					item.setCheckboxCaption(rs.getString("CheckboxCaption"));//获得多选项标题
					
					item.setDefaultSelected(rs.getInt("DefaultSelected"));//获得多选项是否默认，此阶段未用
					
					item.setSelectCount(rs.getInt("SelectedCount"));//获得多选项被选择次数
					
					retList.add(item);//添多选答案项 
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			super.closeAll();//关闭数据库连接和操作类
			
		   return retList;//返回多选项列表
	}
	
	/**
	 * 根据多选项编号查询多选项
	 * @param CheckboxID
	 * @return
	 */
	public CheckboxItem getCheckbox(int CheckboxID)
	{
		CheckboxItem item = null;//多选项初始化
		
		String sql = "select * from CheckboxTable where CheckboxID = " + CheckboxID;//多选调查答案项查询
		
		super.openConn();
		try {
			super.stmt = conn.createStatement();//获取数据库操作类
			
			super.rs = super.stmt.executeQuery(sql);
			
			while(super.rs.next()) {
				
				item = new CheckboxItem();//多选答案项实例化
				
				item.setCheckboxID(rs.getInt("CheckboxID"));//获得多选答案项编号
				
				item.setCheckboxOwnerID(rs.getInt("CheckboxOwnerID"));//获得多选答案项所属调查项编号
				
				item.setCheckboxIndex(rs.getInt("CheckboxIndex"));//获得多选答案项所属调查项中的编号
				
				item.setCheckboxCaption(rs.getString("CheckboxCaption"));//获得多选项标题
				
				item.setDefaultSelected(rs.getInt("DefaultSelected"));//获得多选项是否默认，此阶段未用
				
				item.setSelectCount(rs.getInt("SelectedCount"));//获得多选项被选择次数
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//关闭数据库连接和操作类
		
		return item;//返回多选项实例
	}

    /**
     * 根据多选项编号修改多选项
     * @param item
     * @return
     */
	public boolean updateCheckboxItem(CheckboxItem item)
    {
		 boolean flag = false;//修改结果标记
		  
		  String updateSql = "update CheckboxTable set CheckboxIndex = ?,CheckboxCaption = ?,SelectedCount = ?" +
		  		" where CheckboxID = ?";//更新的sql语句
		  
		  super.openConn();//打开数据库连接
			
			try
			{
			  super.pstmt = conn.prepareStatement(updateSql);//调用sql预处理对象执行查询
			 
			  super.pstmt.setInt(1, item.getCheckboxIndex());//设置多选项编号
			  
			  super.pstmt.setString(2, item.getCheckboxCaption());//设置多选项标题
			  
			  super.pstmt.setInt(3, item.getSelectCount());//多选项选择次数
			  
			  super.pstmt.setInt(4, item.getCheckboxID());//多选项编号
			  
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
	 * 根据多选项编号删除多选项
	 * @param CheckboxID
	 * @return
	 */
	public boolean deleteCheckboxItem(int CheckboxID)
	{
		 boolean flag = false;//修改结果标记
		  
		  String deleteSql = "delete from CheckboxTable where CheckboxID = ?";//删除多选项的sql语句
		  
		  super.openConn();//打开数据库连接
			
			try
			{
			  super.pstmt = conn.prepareStatement(deleteSql);//调用sql预处理对象执行查询
			  
			  super.pstmt.setInt(1, CheckboxID);//为sql语句多选项id赋值
			  
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
	 * 根据多选项所属调查项编号删除多选项
	 * @param CheckboxOwnerID
	 * @return
	 */
	public boolean deleteCheckboxItemByOwner(int CheckboxOwnerID)
	{
		 boolean flag = false;//修改结果标记
		  
		  String deleteSql = "delete from CheckboxTable where CheckboxOwnerID = ?";//删除多选项的sql语句
		  
		  super.openConn();//打开数据库连接
			
			try
			{
			  super.pstmt = conn.prepareStatement(deleteSql);//调用sql预处理对象执行查询
			  
			  super.pstmt.setInt(1, CheckboxOwnerID);//为sql语句多选项id赋值
			  
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
	 * 添加多选项
	 * @param item
	 * @return
	 */
	public boolean InsertCheckboxItem(CheckboxItem item)
	{
      boolean flag = false;//添加结果标记
  	  
  	  String insertSql = "insert into CheckboxTable(CheckboxOwnerID,CheckboxIndex,CheckboxCaption,DefaultSelected," +
  	  		"SelectedCount) values(?,?,?,0,0)";//添加的sql语句
  	  
  	  super.openConn();//打开数据库连接
  		
  		try
  		{
  		  super.pstmt = conn.prepareStatement(insertSql);//调用sql预处理对象执行查询
  		  
  		  super.pstmt.setInt(1, item.getCheckboxOwnerID());//设置多选项所属调查问卷编号
  		 
  		  super.pstmt.setInt(2,item.getCheckboxIndex());//设置多选项顺序号
  		  
  		  super.pstmt.setString(3, item.getCheckboxCaption());//设置多选项标题
  		  
  		  //super.pstmt.setInt(4, item.getSelectCount());//设置多选项点击次数
  		  
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
