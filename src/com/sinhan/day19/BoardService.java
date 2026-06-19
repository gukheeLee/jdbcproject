package com.sinhan.day19;

import java.util.List;

/**
 * 작성자 : 국희
 *작성일 : 2026. 6. 11.
 *설명: BoardService
 */

//service : 비지니스 로직을 담당
//A계좌에서 B계좌로 이체
//1)A계좌에서 인출
//2)B계좌로 입금
//1),2)작업은 DAO에 있음
//여러 작업을 하나로 묶는 것 -> (이체작업) =서비스 , 1),2) -> DAO
public class BoardService {
	
	BoardDAO boardDAO = new BoardDAO();
	
	public int deleteService(int bno) {
		int result = boardDAO.deleteBoard(bno);
		
		return result;
		
	}
	public int updateSevice(BoardDTO board) {
		int result = boardDAO.updateBoard(board);
		
		return result;
		
	}
	public int insertService(BoardDTO board) {
		
	int result = boardDAO.insertBoard(board);
	
	return result;
	}
	public  BoardDTO selectByIdService(int bno) {
		BoardDTO board = boardDAO.selectById(bno);
		return board;
	}
	public List<BoardDTO> selectAllService(){
		List<BoardDTO> boardList = boardDAO.selectALL();
		return boardList;
	}
	
}
















