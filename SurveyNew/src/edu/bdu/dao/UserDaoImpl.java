package edu.bdu.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.bdu.entity.User;

/**
 * 制作日期：2011年8月19日
 * 版本 1.0
 * @author lenov
 *
 */
public class UserDaoImpl extends BaseJdbcDao {
	
	/**
	 * 用户登录验证方法
	 * @param user1
	 * @return
	 */
	public User loginCheck(User user1)
	{
        User user = new User();//创建用户实例
        
        if(user1 == null)
        {
           return null;//传过来的用户是空值
        }
		
		String sql = "select * from UserTable where UserName = ?";//查询语句
		
		super.openConn();//打开数据库连接
		
		try
		{
		  super.pstmt = conn.prepareStatement(sql);//调用sql预处理对象执行查询
		  
		  pstmt.setString(1,user1.getUserName());//为用户查询id赋值
		  
		  super.rs = super.pstmt.executeQuery();//调用执行查询用户
		  
		  if(super.rs.next())
		  {
				user.setUserID(rs.getInt("UserID"));//获得用户id
				
				user.setUserName(rs.getString("UserName"));//获得用户名称
				
				user.setUserPassword(rs.getString("UserPassword"));//获取用户密码
				
				user.setUserType(rs.getInt("UserType"));//获取用户类型
		  }
		  else
		  {
			 user = null;//如果查询结果为零，则用户赋值为空 
		  }
		}
		catch(SQLException e)
		{
			
		   e.printStackTrace();//错误信息打印
		   
		}
		
		super.closeAll();//关闭数据库对象和数据库连接
		
		if(user == null)
		{
			return null;//返回空值
		}
		
		if(!user.getUserPassword().equals(user1.getUserPassword()))//判断密码一致吗
		{
		    user = null;//用户赋空值 
		}
		
		return user;//将用户返回
	}
	
	/***
	 * 根据条件获得模块列表信息
	 * @param condition
	 * @return
	 */
	
	public List getUserList(User condition)
	{
		List retList = new ArrayList();
		
		String sql = "select * from UserTable where 1 = 1";
		
		if(null != condition)
		{
			if(condition.getUserID() != 0) //当模块id不是0时，按模块进行查询
			{
				sql += " and UserID = " + condition.getUserID();
			}
			
			if(!isNullOrEmpty(condition.getUserName()))
			{
				sql += " and UserName = '" + condition.getUserName() + "'";
			}
			
		}
		
		super.openConn();
		try {
			super.stmt = conn.createStatement();//获取数据库操作类
			
			super.rs = super.stmt.executeQuery(sql);
			
			while(super.rs.next()) {
				
				User item = new User();
				
				item.setUserID(rs.getInt("UserID"));//获得用户id
				
				item.setUserName(rs.getString("UserName"));//获得用户名称
				
				item.setUserPassword(rs.getString("UserPassword"));//获取用户密码
				
				item.setUserType(rs.getInt("UserType"));//获取用户类型
				
				
				retList.add(item);//添加用户项 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//关闭数据库连接和操作类
		
		return retList;//返回用户列表
	}
	
	/**
	 * 根据用户id获取用户信息
	 * @param userID
	 * @return User
	 */
	public User getUser(int userID)
	{
		User user = new User();//创建用户实例
		
		String sql = "select * from UserTable where UserID = ?";//查询语句
		
		super.openConn();//打开数据库连接
		
		try
		{
		  super.pstmt = conn.prepareStatement(sql);//调用sql预处理对象执行查询
		  
		  pstmt.setInt(1,userID);//为用户查询id赋值
		  
		  super.rs = super.pstmt.executeQuery();//调用执行查询用户
		  
		  if(super.rs.next())
		  {
				user.setUserID(rs.getInt("UserID"));//获得用户id
				
				user.setUserName(rs.getString("UserName"));//获得用户名称
				
				user.setUserPassword(rs.getString("UserPassword"));//获取用户密码
				
				user.setUserType(rs.getInt("UserType"));//获取用户类型
		  }
		  else
		  {
			 user = null;//如果查询结果为零，则用户赋值为空 
		  }
		}
		catch(SQLException e)
		{
			
		   e.printStackTrace();//错误信息打印
		   
		}
		
		super.closeAll();//关闭数据库对象和数据库连接
		
		return user;//将用户返回
	}
	
	/**
	 * 根据用户id修改用户信息
	 * @param User
	 * @return boolean
	 */
	
	public boolean updateUser(User user)
	{
	  boolean flag = false;//修改结果标记
	  
	  String updateSql = "update UserTable set UserName = ?,UserPassword = ?," +
	  		"UserType = ? where UserID = ?";//更新的sql语句
	  
	  super.openConn();//打开数据库连接
		
		try
		{
		  super.pstmt = conn.prepareStatement(updateSql);//调用sql预处理对象执行查询
		  
		  super.pstmt.setString(1, user.getUserName());//为sql语句用户名赋值
		  
		  super.pstmt.setString(2, user.getUserPassword());//为sql语句密码赋值
		  
		  super.pstmt.setInt(3,user.getUserType());//为用户类型赋值
		  
		  super.pstmt.setInt(4, user.getUserID());//为用户id赋值
		 
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
	 * 根据用户id删除用户信息
	 * @param userID
	 * @return boolean
	 */
	
	public boolean deleteUser(int userID)
	{
		 boolean flag = false;//修改结果标记
		  
		  String deleteSql = "delete from UserTable where UserID = ?";//更新的sql语句
		  
		  super.openConn();//打开数据库连接
			
			try
			{
			  super.pstmt = conn.prepareStatement(deleteSql);//调用sql预处理对象执行查询
			  
			  super.pstmt.setInt(1, userID);//为sql语句用户id赋值
			  
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
	 * 根据添加用户信息
	 * @param User
	 * @return boolean
	 */
	
	public boolean InsertUser(User user)
	{
	  boolean flag = false;//添加结果标记
	  
	  String insertSql = "insert into UserTable(UserPassword,UserName,UserType) values(?,?,?)";//添加的sql语句
	  
	  super.openConn();//打开数据库连接
		
		try
		{
		  super.pstmt = conn.prepareStatement(insertSql);//调用sql预处理对象执行查询
		  
		  super.pstmt.setString(1, user.getUserPassword());//为sql语句用户名赋值
		  
		  super.pstmt.setString(2, user.getUserName());//为sql语句密码赋值
		  
		  super.pstmt.setInt(3,user.getUserType());//为用户类型赋值
		 
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
