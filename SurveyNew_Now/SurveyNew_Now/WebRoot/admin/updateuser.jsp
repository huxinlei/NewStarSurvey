<%@ page language="java" import="java.util.*,edu.bdu.dao.UserDaoImpl,edu.bdu.entity.User" pageEncoding="UTF-8"%>

      
      <%
        User user = new User();//创建用户对象
        
        request.setCharacterEncoding("UTF-8");//设置编码格式
        
        if(request.getParameter("userid")!= null)
        {
           int userid = Integer.parseInt(request.getParameter("userid"));//获得用户编号
           
           user.setUserID(userid);//用户编号赋值
        }
        
        if(request.getParameter("username")!=null)
        {
            String username = request.getParameter("username");
            
            user.setUserName(username);//为用户名称赋值
        }
        
        if(request.getParameter("userpassword")!=null)
        {
            String userpassword = request.getParameter("userpassword");
            
            user.setUserPassword(userpassword);//为用户密码赋值 
        }
        
        if(request.getParameter("usertype")!= null)
        {
           String usertype = request.getParameter("usertype");
           
           int usertypeId = usertype.equals("系统管理员")?1:0;
           
           user.setUserType(usertypeId);//为用户类型赋值
        }
        
        UserDaoImpl userOp = new UserDaoImpl();//用户操作类
        
        if(userOp.updateUser(user))
        {
          //response.sendRedirect("testuserdao.jsp");
          out.print("success");//输出成功标志
        }
        else
        {
          out.println("fail");//输出失败标志
        }
        
                     
       %>

