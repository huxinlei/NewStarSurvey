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
import edu.bdu.entity.Item;
import edu.bdu.entity.Survey;
public class ItemDaoImpl extends BaseJdbcDao{
	
	/**
	 * 无条件查询所有调查项列表
	 * @return
	 */
    public List getItemList()
	{
		  List retList = new ArrayList();
			
		  String sql = "select * from ItemTable";//查询所有的调查项列表
		  
			super.openConn();
			try {
				super.stmt = conn.createStatement();//获取数据库操作类
				
				super.rs = super.stmt.executeQuery(sql);
				
				while(super.rs.next()) {
					
					Item item = new Item();//实例化调查项
					
					item.setItemID(rs.getInt("ItemID"));//获得调查项编号
					
					item.setItemCaption(rs.getString("ItemCaption"));//获得调查项问题
					
					item.setItemType(rs.getInt("ItemType"));//获得调查项的类型
					
					item.setItemAttribute(rs.getInt("ItemAttribute"));//获得调查项类型，此字段当前没有使用
					
					item.setItemOwerID(rs.getInt("ItemOwnerID"));//获得调查项创建者编号
					
					item.setItemOwnerSurveyID(rs.getInt("ItemOwnerSurveyID"));//获得调查项所属调查问卷编号 
					
					item.setRadioCount(rs.getInt("RadioCheckboxCount"));//获得调查项的回答次数
					
					retList.add(item);//添加调查项 
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			super.closeAll();//关闭数据库连接和操作类
			
			return retList;//返回调查项列表
	}
	/**
	 * 根据调查问卷编号查询调查项列表
	 * @param itemOwnerSurveyID
	 * @return
	 */
  public List getItemList(int itemOwnerSurveyID)
  {
	  List retList = new ArrayList();
		
	  String sql = "select * from ItemTable where ItemOwnerSurveyID = " 
			+ itemOwnerSurveyID;//根据调查项所属调查问卷编号生成sql
		
	  
		super.openConn();
		try {
			super.stmt = conn.createStatement();//获取数据库操作类
			
			super.rs = super.stmt.executeQuery(sql);
			
			while(super.rs.next()) {
				
				Item item = new Item();//实例化调查项
				
				item.setItemID(rs.getInt("ItemID"));//获得调查项编号
				
				item.setItemCaption(rs.getString("ItemCaption"));//获得调查项问题
				
				item.setItemType(rs.getInt("ItemType"));//获得调查项的类型
				
				item.setItemAttribute(rs.getInt("ItemAttribute"));//获得调查项类型，此字段当前没有使用
				
				item.setItemOwerID(rs.getInt("ItemOwnerID"));//获得调查项创建者编号
				
				item.setItemOwnerSurveyID(rs.getInt("ItemOwnerSurveyID"));//获得调查项所属调查问卷编号 
				
				item.setRadioCount(rs.getInt("RadioCheckboxCount"));//获得调查项的回答次数
				
				retList.add(item);//添加调查项 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//关闭数据库连接和操作类
		
		return retList;//返回调查项列表
  }
  
  /***
   * 根据调查项的条件查询调查项
   * @param itemID
   * @return
   */
  
  public Item getItemByItemId(int itemID)
  {
	  Item item = null;//默认查询出的结果是空值
		
	  String sql = "select * from ItemTable where ItemID = " 
			+ itemID;//根据调查项的编号生成sql
		
	  
		super.openConn();
		try {
			super.stmt = conn.createStatement();//获取数据库操作类
			
			super.rs = super.stmt.executeQuery(sql);
			
			if(super.rs.next()) {
				
				item = new Item();//实例化调查项
				
				item.setItemID(rs.getInt("ItemID"));//获得调查项编号
				
				item.setItemCaption(rs.getString("ItemCaption"));//获得调查项问题
				
				item.setItemType(rs.getInt("ItemType"));//获得调查项的类型
				
				item.setItemAttribute(rs.getInt("ItemAttribute"));//获得调查项类型，此字段当前没有使用
				
				item.setItemOwerID(rs.getInt("ItemOwnerID"));//获得调查项创建者编号
				
				item.setItemOwnerSurveyID(rs.getInt("ItemOwnerSurveyID"));//获得调查项所属调查问卷编号 
				
				item.setRadioCount(rs.getInt("RadioCheckboxCount"));//获得调查项的回答次数
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//关闭数据库连接和操作类
		
		return item;//返回查询处的结果 
  }
  
  /**
   * 根据查询条件
   * @param condition
   * @return
   */
  public List getItemList(Item condition)
  {
	  List retList = new ArrayList();
		
	  String sql = "select * from ItemTable where ItemOwnerSurveyID where 1 = 1";//根据调查项所属调查问卷编号生成sql
		
	  
	  //设置调查项查询条件
	  if(condition != null)
	  {
	     if(condition.getItemID() != 0)
	     {
	    	 sql += " and ItemID = " + condition.getItemID();//设置调查项编号
	     }
	     
	     if(!isNullOrEmpty(condition.getItemCaption()))
	     {
	    	 sql += " and ItemCaption like '%" + condition.getItemCaption() + "%'";//设置调查项标题
	     }
	     
	     if(condition.getItemOwnerSurveyID() != 0)
	     {
	    	 sql += " and ItemOwnerSurveyID = " + condition.getItemOwnerSurveyID();//设置调查项所属调查问卷编号
	     }
	     
	     if(condition.getItemOwnerID() != 0)
	     {
	    	 sql += " and ItemOwnerID = " + condition.getItemOwnerID();//调查项创建
	     }
	  }
	  
		super.openConn();
		try {
			super.stmt = conn.createStatement();//获取数据库操作类
			
			super.rs = super.stmt.executeQuery(sql);
			
			while(super.rs.next()) {
				
				Item item = new Item();//实例化调查项
				
				item.setItemID(rs.getInt("ItemID"));//获得调查项编号
				
				item.setItemCaption(rs.getString("ItemCaption"));//获得调查项问题
				
				item.setItemType(rs.getInt("ItemType"));//获得调查项的类型
				
				item.setItemAttribute(rs.getInt("ItemAttribute"));//获得调查项类型，此字段当前没有使用
				
				item.setItemOwerID(rs.getInt("ItemOwnerID"));//获得调查项创建者编号
				
				item.setItemOwnerSurveyID(rs.getInt("ItemOwnerSurveyID"));//获得调查项所属调查问卷编号 
				
				item.setRadioCount(rs.getInt("RadioCheckboxCount"));//获得调查项的回答次数
				
				retList.add(item);//添加调查项 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//关闭数据库连接和操作类
		
		return retList;//返回调查项列表
  }

  /**
   * 根据调查项编号修改调查项
   * @param item
   * @return
   */
  public boolean updateItem(Item item)
  {
      boolean flag = false;//修改结果标记
	  
	  String updateSql = "update ItemTable set ItemCaption = ?," +
	  		"RadioCheckboxCount = ? where ItemID = ?";//更新的sql语句
	  
	  super.openConn();//打开数据库连接
		
		try
		{
		  super.pstmt = conn.prepareStatement(updateSql);//调用sql预处理对象执行查询
		  
		  super.pstmt.setString(1,item.getItemCaption());//设置调查项问题内容
		  
		  super.pstmt.setInt(2, item.getRadioCount());//设置调查项回答次数
		  
		  super.pstmt.setInt(3,item.getItemID());//设置调查项编号
          	     
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
   * 根据调查项编号删除调查项
   * @param itemID
   * @return
   */
  public boolean deleteItem(int itemID)
  {
		 boolean flag = false;//修改结果标记
		  
		  String deleteSql = "delete from ItemTable where ItemID = ?";//删除调查问卷的sql语句
		  
		  super.openConn();//打开数据库连接
			
			try
			{
			  super.pstmt = conn.prepareStatement(deleteSql);//调用sql预处理对象执行查询
			  
			
			  super.pstmt.setInt(1,itemID);//为sql语句调查问卷创建者编号
			  
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
   * 根据调查项所属调查问卷编号删除调查项
   * @param itemSurveyOwnerID
   * @return
   */
  public boolean deleteItemBySurveyId(int itemSurveyOwnerID)
  {
	  boolean flag = false;//修改结果标记
	  
	  String deleteSql = "delete from ItemTable where ItemOwnerSurveyID = ?";//删除调查问卷的sql语句
	  
	  super.openConn();//打开数据库连接
		
		try
		{
		  super.pstmt = conn.prepareStatement(deleteSql);//调用sql预处理对象执行查询
		  
		
		  super.pstmt.setInt(1,itemSurveyOwnerID);//为sql语句调查问卷创建者编号
		  
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
   * 添加调查项
   * @param item
   * @return
   */  
  public boolean InsertItem(Item item)
  {
      boolean flag = false;//添加结果标记
	  
	  String insertSql = "insert into ItemTable(ItemCaption," +
	  		"ItemType,ItemAttribute,ItemOwnerID," +
	  		"ItemOwnerSurveyID,RadioCheckboxCount)" +
	  		" values(?,?,0,?,?,0)";//添加的sql语句
	  
	  super.openConn();//打开数据库连接
		
		try
		{
		  super.pstmt = conn.prepareStatement(insertSql);//调用sql预处理对象执行查询
		  
		  super.pstmt.setString(1, item.getItemCaption());//设置问题题目内容
		  
		  super.pstmt.setInt(2,item.getItemType());//设置调查项的类型
		  
		  super.pstmt.setInt(3,item.getItemOwnerID());//设置调查项的创建者编号
		  
		  super.pstmt.setInt(4, item.getItemOwnerSurveyID());//设置调查项的调查问卷编号
		  
		 // super.pstmt.setInt(5,item.getRadioCount());//设置调查项的回答次数记录
		 
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
