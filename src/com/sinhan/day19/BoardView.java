package com.sinhan.day19;

import java.util.List;

/**
 * 작성자 : 국희
 *작성일 : 2026. 6. 11.
 *설명: BoardView
 */
//View : 나중에 웹전환
public  class BoardView {
	
	public static void menuDisplay() {
		System.out.println("---------------------------------");
		System.out.println("1.조회 2.bno조회 3.입력 4.수정 5.삭제 99.EXIT");
		System.out.println("6.부서로 조회 7.job으로 조회 8.조건4개로 조회 9.sp호풀 99.EXIT");
		System.out.println("---------------------------------");
		System.out.print("작업선택 >>");
	}
	
	
	
	public static void print(String message,int resultCount) {
		System.out.println("[알림]" + resultCount +"건" + message);
	}
	
	public static void print(BoardDTO board) {
		System.out.println("========== 1건 보여주기 ============");
		if(board ==null) {
			System.out.println("해당 정보가 없습니다.");
			return;
		}
		System.out.println("번호 :" + board.getBno());
		System.out.println("제목 : " + board.getTitle());
		System.out.println("내용 : " + board.getContents());
		System.out.println("작성자 : " + board.getWriter());
		System.out.println("등록일 : " + board.getReg_date());
		System.out.println("수정일 : " + board.getUpdate_date());
	}
	public static void print(List<BoardDTO> blist) {
		System.out.println("========== 여러건 보여주기 ============");
//		blist.stream().forEach(board -> System.out.println(board));
		blist.stream().forEach(System.out::println);
		
	}

}
