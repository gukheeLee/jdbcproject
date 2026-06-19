package com.sinhan.day19;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 작성자 : 국희
 *작성일 : 2026. 6. 11.
 *설명: BoardDTO
 */
//javaBeans기술을 사용하는 JSP,Mybatis, Spring에서 칼럼이름이 같으면 mapping이 귑다.
//DTO(Data Transfer Object) : data전송 목적으로 만듦
@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter @Setter @ToString
public class BoardDTO {
	private int bno ;         
	private String title;        
	private	String contents;     
	private String writer  ;     
	private Date reg_date ;    
	private Date update_date;  
}





