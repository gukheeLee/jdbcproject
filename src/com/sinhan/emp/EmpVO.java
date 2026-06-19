package com.sinhan.emp;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.NonFinal;

/**
 * 작성자 : 국희 작성일 : 2026. 6. 12. 설명: empDTO
 */
//DTO(데이터 전송 목적), VO(데이터 저장 목적), Entity(DB에서 가져온 data),Beans(객체) 
@NoArgsConstructor
@AllArgsConstructor
@Builder @Setter @Getter @ToString
public class EmpVO {
	// sql plus 에서 desc table명 한 후 Alt누르고 드래그 후 복붙, ztrl+shift+y 눌러서 소문자화
	// not null 항목은 final 붙이기
	private Integer employee_id;
	private String first_name;
	private String last_name;
	private String email;
	private String phone_number;
	private Date hire_date;
	private String job_id;
	private Double salary;
	private Double commission_pct;
	private Integer manager_id;
	private Integer department_id;
	/**
	 * @return
	 */
	

}
