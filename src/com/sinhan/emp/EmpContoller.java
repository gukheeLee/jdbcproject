package com.sinhan.emp;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 작성자 : 국희 작성일 : 2026. 6. 12. 설명: EmpContoller
 */
//요철릉 controller가 받음
public class EmpContoller {
	static Scanner sc = new Scanner(System.in);
	static EmpService empService = new EmpService();
	public static void main(String[] args) {
		boolean isStop = false;
		while(!isStop) {
			EmpView.menuDisplay();
			String job = sc.nextLine();
			switch(job) {
			case "1" ->{f_selectAll();}
			case "2" ->{f_selectById();}
			case "3" ->{f_insert();}
			case "4" ->{f_update();}
			case "5" ->{f_delete();}
			case "6" ->{f_selectByDept();}
			case "7" -> {f_selectByJobid();}
			case "8" ->{f_selectBycondition();}
			case "9" -> {f_sp();}
			case "10" ->{f_join2();}
			case "11" ->{f_join1();}
			case "99" ->{isStop = true;}
			default ->{System.out.println("작업선택 오류. 다시 선택");}
			}
		}
		System.out.println("======bye======");
	}
	private static void f_join1() {
		String deptid = getData("조회 할 부서 입력 >>");
		List<EmpJoinDTO> emplist = empService.join_emp_dept_job1(Integer.parseInt(deptid));
		for(EmpJoinDTO emp :emplist) {
			System.out.println(emp);
		}
		System.out.println("========bye============");
	}
	
	
	private static void f_join2() {
		String deptid = getData("조회 할 부서 입력 >>");
		List<Map<String,Object>> emplist = empService.join_emp_dept_job2(Integer.parseInt(deptid));
		for(Map<String,Object> map :emplist) {
			System.out.println("===========");
			for(String key:map.keySet()) {
				System.out.println(key +"==>" + map.get(key));
			}
		}
		System.out.println("========bye============");
	}



	//스타트 프로시져, 프로시저 시작 함수
	private static void f_sp() {
		String empid = getData("급여를 수정 할 직원 >>");
		String comm = getData("커미션 >>");
		int result = empService.spcall_raise_salary(Integer.parseInt(empid),Double.parseDouble(comm));
		
		EmpView.print(" 급여인상 ",result);
	}



	private static void f_selectBycondition() {
		
		String deptid = getData("조회할 deptid >>");
		String jobid = getData("조회할 job_id>>");
		String salary = getData("조회할 salary>>");
		String hiredate = getData("조회할 hiredate>>");
		List<EmpVO> emplist = empService.selectByConditionService(Integer.parseInt(deptid),jobid,Double.parseDouble(salary),Date.valueOf(hiredate));
		EmpView.print(emplist);
		
	}



	private static void f_selectByJobid() {
		System.out.print("조회할 job_id >>");
		String jobid = sc.nextLine();
		List<EmpVO> emplist = empService.selectByJobIdService(jobid);
		EmpView.print(emplist);
		
	}



	private static void f_selectByDept() {
		System.out.print("조회할 dept >>");
		int dmpid = Integer.parseInt(sc.nextLine());
		List<EmpVO> emplist = empService.selectByDeptService(dmpid);
		EmpView.print(emplist);
	}


	private static void f_delete() {
		// 삭제
		System.out.print("삭제할 empid >>");
		int empid = Integer.parseInt(sc.nextLine());
		int result = empService.deleteService(empid);
		EmpView.print("삭제",result);

		
	}

	
	private static void f_update() {
		// 수정
		System.out.println("==========수정할 data 입력===========");
		EmpVO emp = inputEmp();
		int result = empService.updateService(emp);
		EmpView.print("수정",result);

	}

	
	


	private static void f_insert() {
		// 입력
		EmpVO emp = inputEmp();
		int result = empService.insertService(emp);
		EmpView.print("입력",result);
		
	}

	private static String getData(String message) {
		System.out.print(message);
		return sc.nextLine().trim();
	}
	
	private static EmpVO inputEmp() {
		String empid = getData("직원번호>>");
		String fname = getData("이름>>");
		String lname = getData("성(필수)>>");
		String comm = getData("Commission_pct>>");
		String deptid = getData("department_id>>");
		String email = getData("email(필수)>>");
		String hdate = getData("입사일(필수 2026/02/03), default>>");
		String  job_id= getData("job_id(필수),default>>");
		String  mid= getData("manager_id>>");
		String  phone= getData("phone_number>>");
		String  salary = getData("salary>>");
		
		
		EmpVO emp = EmpVO.builder()
				.commission_pct(comm.isEmpty()? null:Double.parseDouble(comm))
				.department_id(deptid.isEmpty()? null:Integer.parseInt(deptid))//FK
				.email(email.isEmpty()?null:email)//UNIQUE
				.employee_id(empid.isEmpty() ? null:Integer.parseInt(empid))
				.first_name(fname.isEmpty() ? null : fname)
				.hire_date(hdate.isEmpty()? null:Date.valueOf(hdate))
				.job_id(job_id.isEmpty()?null : job_id)//FK
				.last_name(lname.isEmpty() ? null : lname)
				.manager_id(mid.isEmpty() ? null:Integer.parseInt(mid))//FK
				.phone_number(phone.isEmpty()? null:phone)
				.salary(salary.isEmpty()?null:Double.parseDouble(salary))
				.build();
		System.out.println(emp);
		return emp;
	}


	private static void f_selectById() {
		// emp Id 조회
		System.out.print("조회할 직원 번호 >>");
		int empId = Integer.parseInt(sc.nextLine().trim()) ;
		EmpVO emp = empService.selectByIdService(100);
		EmpView.print(emp);
	}

	private static void f_selectAll() {
		// 조회
		List<EmpVO> emplist = empService.selectAllService();
		EmpView.print(emplist);
	}
}
