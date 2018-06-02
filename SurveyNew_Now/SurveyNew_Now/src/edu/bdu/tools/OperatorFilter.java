package edu.bdu.tools;

import javax.servlet.Filter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import edu.bdu.dao.UserDaoImpl;
import edu.bdu.entity.User;

public class OperatorFilter implements Filter {
	
	private FilterConfig fc;
	
    UserDaoImpl userOper;//获得用户数据库类
    /**
     * 根据用户的请求，判断访问者是否是调查问卷的管理者
     */
	public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain chain)
	throws IOException,ServletException{
	   
		HttpServletRequest request = (HttpServletRequest)sRequest;//获得用户请求信息
		
		HttpServletResponse response = (HttpServletResponse)sResponse;//获得响应对象
		
		userOper = new UserDaoImpl();//实例化用户操作类
		
		
		HttpSession session = request.getSession();
		
		Object user = session.getAttribute("user");//获得当前用户信息
		
		if(user == null && !(user instanceof edu.bdu.entity.User))
		{
			PrintWriter out1 = response.getWriter();//获得response文本输出流
			
			out1.print("usererror");//当前用户身份不合法
		}
		else
		{
		  User userCheck = (User)user;//将用户转换成用户对象 
		  
		  if(userOper.loginCheck(userCheck) != null && userCheck.getUserType() == 1)
		  {
			chain.doFilter(sRequest, sResponse);//身份验证成功并且是超级管理员身份后传递用户请求
		  }
		  else
		  {
		    PrintWriter out2 = response.getWriter();//获得response文本输出流 
		    
		    out2.print("usererror");//用户身份验证错误
		  }
		}
	}
	
	/**
	 * 初始化
	 */
	public void init(FilterConfig fc) throws ServletException{
		this.fc = fc;
	}
	/**
	 * 销毁
	 */
	public void destroy(){
		this.fc = null;
	}

}
