package com.sinhan.emp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sinhan.util.DBUtil;

/**
 * 작성자 : 국희
 *작성일 : 2026. 6. 12.
 *설명: EmpDAO
 */
//실행 로직
public class EmpDAO {
	
	Connection conn;
	Statement st;
	PreparedStatement pst;
	ResultSet rs;
	
	
	
	
	
	
	//부서, 직원 JOIN후 조회
	public Map<String,Object>  join_emp_dept_job(int deptid) {
		String sql = """
				select first_name, last_name, salary, department_name, job_title
				from employees join departments using(department_id)
				            	join jobs using(job_id)
				where department_id = ?
				""";
		
		return null;
	}
	public List<EmpJoinDTO>  join_emp_dept_job1(int deptid) {
		List<EmpJoinDTO> emplist = new ArrayList<>();
		
		String sql = """
				select first_name, last_name, salary, department_name, job_title
				from employees join departments using(department_id)
				            	join jobs using(job_id)
				where department_id = ?
				""";
		conn = DBUtil.dbConnect();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, deptid);
			rs = pst.executeQuery();
			while(rs.next()) {
				EmpJoinDTO emp = EmpJoinDTO.builder()
						.department_name(rs.getString(4))
						.first_name(rs.getString(1))
						.last_name(rs.getString(2))
						.salary(rs.getDouble(3))
						.job_title(rs.getString(5))
						.build();
				
				
				emplist.add(emp);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		
		return emplist;
	}
	public List<Map<String,Object>>  join_emp_dept_job2(int deptid) {
		List<Map<String,Object>> emplist = new ArrayList<>();
		
		String sql = """
				select first_name, last_name, salary, department_name, job_title
				from employees join departments using(department_id)
				            	join jobs using(job_id)
				where department_id = ?
				""";
		conn = DBUtil.dbConnect();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, deptid);
			rs = pst.executeQuery();
			while(rs.next()) {
				Map<String,Object> map = new HashMap<>();
				map.put("first_name", rs.getObject(1));
				map.put("last_name", rs.getObject(2));
				map.put("salary", rs.getObject(3));
				map.put("department_name", rs.getObject(4));
				map.put("job_title", rs.getObject(5));
				
				emplist.add(map);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		
		return emplist;
	}
	
	//여러 조건으로 직원들을 조회(부서,직책,급여>=,입사일>=)
	public List<EmpVO>  selectByCondition(int deptid, String jobid, double salary, Date hiredate) {
		//String sql = "select * from employees where department_id = ? and job_id = ? and salary >= ? and hire_date >= ?";
		String sql = """
				select * from employees where department_id = ? and job_id = ? 
				and salary >= ? and hire_date >= ?
				""";
		List<EmpVO> emplist = new ArrayList<>();
		conn=DBUtil.dbConnect();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, deptid);
			pst.setString(2, jobid);
			pst.setDouble(3, salary);
			pst.setDate(4, hiredate);
			rs = pst.executeQuery();
			while(rs.next()) {
				EmpVO emp = makeEmp(rs);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		

		return emplist;
	}
	
	
	//직책(job_id)로 직원을 조회 (where job-id = 'IT_PROG') ->statemaent
	//직책(job_id)로 직원을 조회 (where job-id = ?) ->preparedStatement
	public List<EmpVO> selectByjobid(String jobid) {
		List<EmpVO> emplist = new ArrayList<>();
		String sql="select * from employees where job_id = '"+jobid+"'" ; 
		//String이기 때문에 ''로 감싸줘야함!!!
		conn = DBUtil.dbConnect();//DB연결 DBUtil에 만들어놓은거 호출
		try {
			st = conn.createStatement();//전달 통로 생성
			rs = st.executeQuery(sql);
			while(rs.next()) {
				EmpVO emp = makeEmp(rs);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		
		return emplist;
	}
	
	//부서의 직원들을 조회(where department_id=?)
	public List<EmpVO> selectByDept(int deptid) {
		List<EmpVO> emplist = new ArrayList<>();
		String sql="select * from employees where department_id = " + deptid;
		conn = DBUtil.dbConnect();//DB연결 DBUtil에 만들어놓은거 호출
		try {
			st = conn.createStatement();//전달 통로 생성
			rs = st.executeQuery(sql);
			while(rs.next()) {
				EmpVO emp = makeEmp(rs);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		
		return emplist;
	}
	
	public List<EmpVO> selectAll() {
		List<EmpVO> emplist = new ArrayList<>();
		String sql="select * from employees";
		conn = DBUtil.dbConnect();//DB연결 DBUtil에 만들어놓은거 호출
		try {
			st = conn.createStatement();//전달 통로 생성
			rs = st.executeQuery(sql);
			while(rs.next()) {
				EmpVO emp = makeEmp(rs);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		
		return emplist;
	}

	
	private EmpVO makeEmp(ResultSet rs) throws SQLException {
		//DB에서 가져온 1건을 가지고 VO(DTO)만든다.
		EmpVO emp = EmpVO.builder()
				.commission_pct(rs.getDouble("commission_pct"))
				.department_id(rs.getInt("department_id"))
				.email(rs.getString("email"))
				.employee_id(rs.getInt("employee_id"))
				.first_name(rs.getString("first_name"))
				.hire_date(rs.getDate("hire_date"))
				.job_id(rs.getString("job_id"))
				.last_name(rs.getString("last_name"))
				.manager_id(rs.getInt("manager_id"))
				.phone_number(rs.getString("phone_number"))
				.salary(rs.getDouble("salary"))
				.build();
		
		
		
		return emp;
	}

	/**
	 * @param empid
	 * @return
	 */
	public EmpVO selectById(int empid) {
		EmpVO emp = null;
		List<EmpVO> emplist = new ArrayList<>();
		String sql="select * from employees where employee_id = ?";
		conn = DBUtil.dbConnect();//DB연결 DBUtil에 만들어놓은거 호출
		try {
			pst = conn.prepareStatement(sql);//전달 통로 생성
			pst.setInt(1, empid);
			rs = pst.executeQuery();
			while(rs.next()) {
				emp = makeEmp(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		
		return emp;
		
	}

	/**
	 * @param emp
	 * @return
	 */
	public int insert(EmpVO emp) {
		int resultCount = 0;
		String sql="insert into employees values(?,?,?,?,?,?,?,?,?,?,?)";
		conn = DBUtil.dbConnect();//DB연결 DBUtil에 만들어놓은거 호출
		try {
			pst = conn.prepareStatement(sql);//전달 통로 생성
			pst.setObject(1, emp.getEmployee_id());
			pst.setObject(2, emp.getFirst_name());
			pst.setObject(3, emp.getLast_name());
			pst.setObject(4, emp.getEmail());
			pst.setObject(5, emp.getPhone_number());
			pst.setObject(6, emp.getHire_date());
			pst.setObject(7, emp.getJob_id());
			pst.setObject(8, emp.getSalary());
			pst.setObject(9, emp.getCommission_pct());
			pst.setObject(10, emp.getManager_id());
			pst.setObject(11, emp.getDepartment_id());
			
			resultCount = pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return resultCount;
	}

	private void makeUpdate(StringBuffer sb, List<Object> params, String colname, Object value) {
		if(value != null) {
			sb.append(colname);
			sb.append("=?,");
			params.add(value);
		}
		
		
	}
	public int update(EmpVO emp) {
		int resultCount = 0;
		StringBuffer sb = new StringBuffer("update employees set ");
		List<Object> params = new ArrayList<>();
		makeUpdate(sb,params,"First_name",emp.getFirst_name());
		makeUpdate(sb,params,"Last_name",emp.getLast_name());
		makeUpdate(sb,params,"Email",emp.getEmail());
		makeUpdate(sb,params,"Phone_number",emp.getPhone_number());
		makeUpdate(sb,params,"Hire_date",emp.getHire_date());
		makeUpdate(sb,params,"Job_id",emp.getJob_id());
		makeUpdate(sb,params,"Salary",emp.getSalary());
		makeUpdate(sb,params,"Commission_pct",emp.getCommission_pct());
		makeUpdate(sb,params,"Manager_id",emp.getManager_id());
		makeUpdate(sb,params,"Department_id",emp.getDepartment_id());
		
		String sql = sb.toString();
		sql = sql.substring(0,sql.length()-1);
		sql+=" where employee_id =?";
		conn = DBUtil.dbConnect();
	
		System.out.println(sql);
		
		try {
			pst = conn.prepareStatement(sql);//전달 통로 생성
			int i=0;
			for(;i<params.size();i++) {
				pst.setObject(i+1,params.get(i));
			}
			pst.setObject(i+1,emp.getEmployee_id());
			resultCount = pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		return resultCount;
	}


	//특정 직원 삭제(where employee_id = ?)
	public int delete(int empid) {
		int resultCount = 0;
		String sql="delete from employees where employee_id = ?";
		conn = DBUtil.dbConnect(); //Auto Commit 설정되어있음
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, empid);
			resultCount = pst.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
		
		
		return resultCount;
	}


	public int spcall_raise_salary(int empid, double comm) {
		String sql = "{call raise_salary(?,?)}";
		int resultCount = 0;
		CallableStatement cst = null;
		conn = DBUtil.dbConnect();
		try {
			cst = conn.prepareCall(sql);
			cst.setInt(1, empid);
			cst.setDouble(2, comm);
			//true if the first result is a ResultSet object; 
			//false if the first result is an update count or there is no result
			resultCount = cst.execute()?0:1;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.dbDisconnect(conn, cst, rs);
		}
		return resultCount;
	}
}
