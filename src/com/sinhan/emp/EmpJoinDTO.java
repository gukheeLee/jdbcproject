package com.sinhan.emp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 작성자 : 국희
 *작성일 : 2026. 6. 15.
 *설명: EmpJoinDTO
 */
@AllArgsConstructor @Getter @Builder
@Setter @NoArgsConstructor @ToString
public class EmpJoinDTO {
	private String first_name;
	private String last_name;
	private Double salary;
	private String department_name;
	private String job_title;
}
