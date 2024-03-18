package edu.kh.jdbc.main.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.member.model.dto.Member;
import static edu.kh.jdbc.common.JDBCTemplate.*;



public class MainDAO {

	// JDBC 객체 참조 변수
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	private Properties prop;
	
	// 기본생성자 DAO 객체가 생성될 때 xml 파일 읽어와 Properties 객체 저장
	public MainDAO() {
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("main-sql.xml"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public int idDuplicationCheck(Connection conn, String memberId) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("idUplicationCheck");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			
		}finally {
			
		}
		
		return result;
	}




	/** 로그인 기능
	 * @param memberId
	 * @param memberPw
	 * @param conn
	 * @return Member
	 * @throws Exception
	 */
	public Member login(String memberId, String memberPw, Connection conn) throws Exception{
		
		Member member = new Member();
		
		try {
			
			String sql = prop.getProperty("login");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			pstmt.setString(2, memberPw);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				member.setMemberNo(rs.getInt("MEMBER_NO"));
				member.setMemberId(rs.getString("MEMBER_ID"));
				member.setMemberName(rs.getString("MEMBER_NM"));
				member.setMemberGender(rs.getString("MEMBER_GENDER"));
				member.setEnrollDate(rs.getString("ENROLL_DT"));

			}
			
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return member;
	}




	/** 중복 확인
	 * @param memberId
	 * @param conn
	 * @return
	 */
	public int idDuplicationCheck(String memberId, Connection conn) throws Exception{
		
		int resultId = 0;
		
		try {
			
			String sql = prop.getProperty("idDuplicationCheck");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				resultId = rs.getInt(1);
				
			}
			
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return resultId;
	}




	public int signUp(Member member, Connection conn) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("signUp");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberGender());
			
			result = pstmt.executeUpdate();
			
			
			
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}






	
}
