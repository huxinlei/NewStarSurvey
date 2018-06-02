<%@ page language="java" import="java.util.*,edu.bdu.dao.VisitorDaoImpl,edu.bdu.entity.Visitor" pageEncoding="UTF-8"%>

    <%
        request.setCharacterEncoding("UTF-8");//设置编码格式
        
        if(request.getParameter("visitorNumber") != null)
        {
           int visitorNumber = Integer.parseInt(request.getParameter("visitorNumber"));//获得访问者编号
           
           VisitorDaoImpl visitorOp = new VisitorDaoImpl();//访问者操作类
           
           if(visitorOp.deleteVisitor(visitorNumber))//删除访问者信息
           {
             out.println("<script type='text/javascript'>alert('访问者信息已成功删除!');</script>");
           }
           else
           {
             out.println("<script type='text/javascript'>alert('访问者信息由于异常没能成功删除!');</script>");
           }
           
        }
        else
        {
           out.println("<script type='text/javascript'>alert('参数传递错误，请检查!');</script>");
        }
     %>

