package edu.bdu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManager {
	
    private static final String DRIVER_CLASS = ProReader.getProReader().getProperty("DRIVER_CLASS");//数据库驱动
	
	private static final String DRIVER_URL = ProReader.getProReader().getProperty("DRIVER_URL");//连接的数据库
	
	private static final String DATABASE_USER = ProReader.getProReader().getProperty("DATABASE_USER");//登陆数据的用户
	
	private static final String DATABASE_PASSWORD = ProReader.getProReader().getProperty("DATABASE_PASSWORD");//sql身份登录时的密码
	
	/**
	 * 获得数据库连接的实体类
	 * @return dbConnection
	 */
	public static Connection getConnection() throws Exception
	{
		Connection dbConnection = null;//连接数据对象
		try
		{
			Class.forName(DRIVER_CLASS);//加载数据库连接
			
			dbConnection = DriverManager.getConnection(DRIVER_URL,DATABASE_USER,DATABASE_PASSWORD);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return dbConnection;//返回数据库连接
	}
}
