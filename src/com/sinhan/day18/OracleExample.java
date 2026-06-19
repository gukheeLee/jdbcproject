package com.sinhan.day18;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 작성자 : 국희
 *작성일 : 2026. 6. 10.
 *설명: OracleExample
 */
public class OracleExample {

	
	public static void main(String[] args) {
		//1.jdbc Driver load(먼저 classpath에 jdbc library가 존재해야함)
		Connection conn =null; //DB연결에 사용
		Statement st = null;//SQL문을 보내는 통로, Statement : 바인딩 변수 사용 불가능
		//PreparedStatement : 바인딩 변수(?) 사용 가능
		ResultSet rs = null;//Select문 결과가 들어온다
		String url="jdbc:oracle:thin:@localhost:1521:xe"; //본인 DB id입력
		String username ="hr", password = "hr";
		String sql = """ 
				select job_id,max(employee_id) max_empid, min(first_name)min_name, sum(salary)sum_sal,min(hire_date)min_hiredate --5
				from employees  
				where department_id >= 10
				group by job_id 
				having sum(salary)>=10000 
				order by 1
				"""; //검증된 문장을 가지고 와야함, 세미콜론이 안에 들어가면 안된다.
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1.jdbc Driver load");
			conn = DriverManager.getConnection(url,username,password);
			System.out.println("2.DB연결 성송");
			st = conn.createStatement();//비어있는 통로 생성
			rs = st.executeQuery(sql);//rs는 테이블 형태
			System.out.println("3.SQL문 전송하고 결과 받음(java App memory에 있음)");
			while(rs.next()) {
				String job_id = rs.getString(1); //칼람의 순서 사용 가능
				int empid = rs.getInt(2);
				String fname = rs.getString("min_name"); // 칼럼이름, 별명(Alias)
				double sal = rs.getDouble("sum_sal");
				Date hdate = rs.getDate("min_hiredate");
				System.out.printf("%s %d %s %f %s\n",job_id,empid,fname,sal,hdate);
				
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("===main end===");
	}

}
