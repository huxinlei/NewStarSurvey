package edu.bdu.dao;

import java.util.Properties;
import java.io.InputStream;

public class ProReader extends Properties{
	
	private static final long serialVersionUID = 1L;
	
	private static ProReader proReader = null;//定义proReader的实例
	
	/***
	 * 公共取得实例的方法
	 * return proReader
	 */
	public static ProReader getProReader()
	{
		if(proReader == null)
		{
			makeProReader();
		}
		
		return proReader;//返回proReader对象
	}
	
	/**
	 * 同过调用私有构造方法获得唯一ProReader实例
	 */
	private static synchronized void makeProReader()
	{
		if(proReader == null)
		{
			try{
				proReader = new ProReader();//通过本类的私有构造方法获得proReader的实例
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 私有构造方法,确保实例的唯一性
	 */
	private ProReader() throws Exception
	{
		InputStream is = getClass().getResourceAsStream("db.properties");//获得配置文件的输入流
		
		try
		{
			load(is);//加载输入流
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
