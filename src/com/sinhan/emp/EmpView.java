package com.sinhan.emp;

import java.util.List;

/**
 * 작성자 : 국희
 *작성일 : 2026. 6. 11.
 *설명: BoardView
 */
//View : 나중에 웹전환
public  class EmpView {
	
	public static void menuDisplay() {
		System.out.println("----------------------------------------");
		System.out.println("1.조회 2.bno조회 3.입력 4.수정 5.삭제 99.EXIT");
		System.out.println("6.부서로 조회 7.job으로 조회 8.조건4개로 조회 9.sp호풀 99.EXIT");
		System.out.println("----------------------------------------");
		System.out.print("작업선택 >>");
	}
	
	
	
	public static void print(String message,int resultCount) {
		System.out.println("[알림]" + resultCount +"건" + message);
	}
	
	public static void print(EmpVO emp) {
		System.out.println("========== 1건 보여주기 ============");
		if(emp ==null) {
			System.out.println("해당 정보가 없습니다.");
			return;
		}
		System.out.println("Phone_number : " + emp.getPhone_number());
		System.out.println("Commission: " + emp.getCommission_pct());
		System.out.println("Department_id " + emp.getDepartment_id());
		System.out.println("Email: " + emp.getEmail());
		System.out.println("Employee_id : " + emp.getEmployee_id());
		System.out.println("First_name : " + emp.getFirst_name());
		System.out.println("Job_id : " + emp.getJob_id());
		System.out.println("Last_name : " + emp.getLast_name());
		System.out.println("Manager_id : " + emp.getManager_id());
		System.out.println("Phone_number : " + emp.getPhone_number());
		System.out.println("Hire_date : " + emp.getHire_date());
	}
	public static void print(List<EmpVO> emplist) {
		System.out.println("========== 여러건 보여주기 ============");
//		blist.stream().forEach(board -> System.out.println(board));
		emplist.stream().forEach(System.out::println);
		
	}

}
