package com.sinhan.day19;

import java.util.List;
import java.util.Scanner;

/**
 * 작성자 : 국희
 *작성일 : 2026. 6. 11.
 *설명: BoardContoller
 */
public class BoardContoller {
	static Scanner sc = new Scanner(System.in);
	static BoardService boardService = new BoardService();
	
	public static void main(String[] args) {
		boolean isStop = false;
		while(!isStop) {
			BoardView.menuDisplay();
			int job = sc.nextInt();
			switch(job) {
			case 1->{f_selectAll();}
			case 2->{f_selectById();}
			case 3->{f_insert();}
			case 4->{f_update();}
			case 5->{f_delete();}
			case 9->{isStop = true;}
			}
		}
		System.out.println("================♥BYE♥================");
	}
	private static void f_delete() {
		sc.nextLine(); //작업 선택 후 뒷부분 enter 버리기
		System.out.print("삭제할 bno >");
		int bno = Integer.parseInt(sc.nextLine());


		
		BoardDTO board = BoardDTO.builder()
				.bno(bno)
				.build();
		
		int result = boardService.deleteService(bno);
		BoardView.print("삭제됨 " ,result);
	}
	private static void f_update() {
		sc.nextLine(); //작업 선택 후 뒷부분 enter 버리기
		System.out.print("수정할 bno >");
		int bno = Integer.parseInt(sc.nextLine());

		System.out.print("수정할 title >");
		String title = sc.nextLine();
		System.out.print("수정할 contents >");
		String contents = sc.nextLine();
		System.out.print("수정한 writer >");
		String writer = sc.nextLine();
		
		BoardDTO board = BoardDTO.builder()
				.bno(bno)
				.title(title)
				.contents(contents)
				.writer(writer)
				.build();
		
		int result = boardService.updateSevice(board);
		BoardView.print("입력됨 " ,result);
	}
	
	private static void f_insert() {
		sc.nextLine(); //작업 선택 후 뒷부분 enter 버리기
		System.out.print("title >");
		String title = sc.nextLine();
		System.out.print("contents >");
		String contents = sc.nextLine();
		System.out.print("writer >");
		String writer = sc.nextLine();
		
		BoardDTO board = BoardDTO.builder()
				.title(title)
				.contents(contents)
				.writer(writer)
				.build();
		
		int result = boardService.insertService(board);
		BoardView.print("입력됨 " ,result);
	}
	private static void f_selectById() {
		System.out.print("조회 할 bno>>");
		int bno = sc.nextInt();
		BoardDTO board = boardService.selectByIdService(bno);
		BoardView.print(board);
	}

	
	private static void f_selectAll() {
		List<BoardDTO> blist = boardService.selectAllService();
		BoardView.print(blist);
	}
	

}
