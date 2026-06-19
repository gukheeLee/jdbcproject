package com.sinhan.day17;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 작성자 : 국희 작성일 : 2026. 6. 9. 설명: DBConnectExample
 */
public class DBConnectExample {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// <DB사용하려면>
		// 1.JDBCDrive load
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("1.JDBCDrive load success");
		// 2.Connection
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "hr";
		String password = "hr";
		Connection conn = DriverManager.getConnection(url, username, password);
		System.out.println("2.Connection success");
		// 3.SQL문장을 보낼 통로 만들기
		//Statement st = conn.createStatement(); -> ?를 지원하지 않음
		int deptid = 60;
		String sql = """
				select *
				from employees
				where department_id = ?
								""";
		
		PreparedStatement pst = conn.prepareStatement(sql);
		// 4.SQL문을 보낸다.
		pst.setInt(1, deptid);//첫번째 ?에 값을 세팅
		ResultSet rs = pst.executeQuery(); //select 해서 sr에 저장
		// 5.실행 결과를 가져온다
		while(rs.next()) {
			System.out.print(rs.getInt(1));
			System.out.print(rs.getString("first_name"));
			System.out.println(rs.getDouble("salary"));
		}
		// 6.자원 반남(DB연결 해제)
		rs.close();
		pst.close();
		conn.close();
	}

}
