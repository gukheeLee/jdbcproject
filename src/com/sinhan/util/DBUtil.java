package com.sinhan.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 작성자 : 국희
 *작성일 : 2026. 6. 10.
 *설명: DBUtil
 */
public class DBUtil {

	
	public static Connection dbConnect() {
		Connection conn =null;
		String url="jdbc:oracle:thin:@localhost:1521:xe"; //본인 DB id입력
		String username ="hr", password = "hr";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		return conn;
	}
	public static void dbDisconnect(Connection conn,Statement st, ResultSet rs) {
		try {
			if(rs!=null) rs.close();
			if(st!=null) st.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
}
