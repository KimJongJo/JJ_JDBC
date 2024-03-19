package edu.kh.jdbc.board.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.board.model.dto.Board;

import static edu.kh.jdbc.common.JDBCTemplate.*;

public class BoardDAO {

	
	// JDBC 객체 참조 변수
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// XML에 작성된 SQL을 읽어와 저장할 객체를 참조하는 변수
	private Properties prop;
	
	public BoardDAO() { // 기본생성자
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("board-sql.xml"));
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public List<Board> selectAllBoard(Connection conn) throws Exception{
		
		// 결과 저장용 객체 생성
		List<Board> boardList = new ArrayList<Board>();
		
		try {
			// SQL 작성
			String sql = prop.getProperty("selectAllBoard");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				Board board = new Board();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setMemberName(rs.getString("MEMBER_NM"));
				board.setCreateDate(rs.getString("CREATE_DT"));
				board.setReadCount(rs.getInt("READ_COUNT"));
				board.setCommentCount(rs.getInt("COMMENT_COUNT"));
				
				boardList.add(board);
				
			}
			
			
			
		}finally {
			close(rs);
			close(stmt);
		}
		
		return boardList;
	}

	
	
	/** 게시글 상세 조회 SQL 수행 DAO
	 * @param conn
	 * @param input
	 * @param memberNo
	 * @return
	 */
	public Board selectBoard(Connection conn, int input, int memberNo) throws Exception{
		
		Board board = null;
		
		try {
			
			String sql = prop.getProperty("selectBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, input);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				board = new Board();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setMemberName(rs.getString("MEMBER_NM"));
				board.setReadCount(rs.getInt("READ_COUNT"));
				board.setCreateDate(rs.getString("CREATE_DT"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setMemberNo(rs.getInt("MEMBER_NO"));
				
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return board;
	}

	
	
	/** 조회수 증가 SQL 수행 DAO
	 * @param conn
	 * @param input
	 * @return result
	 */
	public int updateReadCount(Connection conn, int input) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateReadCount");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, input);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
