package com.sinhan.day19;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sinhan.util.DBUtil;

/**
 * 작성자 : 국희
 *작성일 : 2026. 6. 11.
 *설명: BoardDAO
 */

//영속성 영역을 담당
//DAO(Data Access Object)
//= Repository
public class BoardDAO {
	
	Connection conn; //DB연결
	Statement st; //SQL문 보내기 위한 통로
	PreparedStatement ps;//SQL문 보내기 위한 통로, 바인딩변수(?)가능
	ResultSet rs; //select 결과를 받음
	int resultCount; //DML결과, 영향을 받은 건 수
	
	public int deleteBoard(int bno) {
		//삭제
		
		String sql ="delete from board where bno=?";
		conn = DBUtil.dbConnect();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,bno);
			resultCount = ps.executeUpdate();
			

		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.dbDisconnect(conn, ps, rs);
		}
		
		return resultCount;
	}
	
	public int updateBoard(BoardDTO board) {
		//수정
		
		String sql ="update Board set "
				+ "title = ?,contents = ?, writer = ?, update_date = sysdate "
				+ "where bno = ?";
		conn = DBUtil.dbConnect();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContents());
			ps.setString(3, board.getWriter());
			ps.setInt(4, board.getBno());
			resultCount = ps.executeUpdate();
			

		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.dbDisconnect(conn, ps, rs);
		}
		
		return resultCount;
	}
	public int insertBoard(BoardDTO board) {
		//입력
		
		String sql ="insert into board values(seq_boardno.nextVal,?,?,?,sysdate,null)";
		conn = DBUtil.dbConnect();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContents());
			ps.setString(3, board.getWriter());
			resultCount = ps.executeUpdate();
			

		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.dbDisconnect(conn, ps, rs);
		}
		
		return resultCount;
	}
	
	public BoardDTO selectById(int bno) {
		//PK로 조회하기
		BoardDTO board = null;
		String sql ="select * from board where bno = ?";
		conn = DBUtil.dbConnect();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bno);
			rs = ps.executeQuery();
			while(rs.next()) {
				board = makeBoard(rs);
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.dbDisconnect(conn, ps, rs);
		}
		
		
		
		return board;
	}
	
	public List<BoardDTO> selectALL() {
		//전부 조회
		List<BoardDTO> boardList = new ArrayList<>();
		String sql ="select * from board";
		conn = DBUtil.dbConnect();
		try {
			st = conn.createStatement();
			rs =st.executeQuery(sql);
			while(rs.next()) {
			BoardDTO board = makeBoard(rs);
			boardList.add(board);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		
		
		
		return boardList;
	}
//	public static void main(String[] args) {
//		new BoardDAO().deleteBoard(3);
//	}
	//작동되나 확인 위해 main 만듬 확인 후 지우기
//	public static void main(String[] args) {
//		new BoardDAO().selectALL().stream().forEach(System.out::println);
//	} 

	private BoardDTO makeBoard(ResultSet rs) throws SQLException {
		BoardDTO board = new BoardDTO();
		board.setBno(rs.getInt(1));
		board.setContents(rs.getString("Contents"));
		board.setReg_date(rs.getDate("Reg_date"));
		board.setTitle(rs.getString("Title"));
		board.setUpdate_date(rs.getDate("update_date"));
		board.setWriter(rs.getString("Writer"));
		
		return board;
	}
}













