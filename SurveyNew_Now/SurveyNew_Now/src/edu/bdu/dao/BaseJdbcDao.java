package edu.bdu.dao;

import java.sql.*;
public abstract class BaseJdbcDao {
	
    protected Connection conn = null;
    protected Statement stmt = null;
    protected PreparedStatement pstmt = null;
    protected ResultSet rs = null;
    
    /**
     * 得到数据库连接
     * @throws ClassNotFoundException
     * @throws SQLException
     * @return 数据库连接
     */
    public Connection openConn(){
    	
		try{
			this.conn = ConnectionManager.getConnection();			
		}catch(Exception sqlexception){
			sqlexception.printStackTrace();
		}
		return conn;
    }
    
    
    /**
     * 释放资源
     * @param conn 数据库连接
     * @param pstmt PreparedStatement对象
     * @param rs 结果集
     */
    public void closeAll() {
        /*  如果rs不空，关闭rs  */
        if(rs != null){
            try { rs.close();} catch (SQLException e) {e.printStackTrace();}
        }
        /*  如果pstmt不空，关闭pstmt  */
        if(pstmt != null){
            try { pstmt.close();} catch (SQLException e) {e.printStackTrace();}
        }
        /*  如果stmt不空，关闭stmt  */
        if(stmt != null){
            try { stmt.close();} catch (SQLException e) {e.printStackTrace();}
        }
        /*  如果conn不空，关闭conn  */
        if(conn != null){
            try { conn.close();} catch (SQLException e) {e.printStackTrace();}
        }
    }
    
    
    /**
	 * 判断字符串是否为null或者空字符串
	 * @param title
	 * @return
	 */
	public boolean isNullOrEmpty(String str) {
		boolean ret = false;
		if (null==str) {
			ret = true;
		}else {
			if (str.trim().equals("")) {
				ret = true;
			}
		}	
		return ret;
	}
	


}
