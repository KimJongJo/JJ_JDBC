package edu.kh.jdbc.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static edu.kh.jdbc.common.JDBCTemplate.*;

import edu.kh.jdbc.member.model.dto.Member;

public class MemberDAO {
	
	
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	private Properties prop;
	
	public MemberDAO() {
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("member-sql.xml"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public List<Member> selectMemberList(Connection conn) throws Exception{
		
		List<Member> memberList = new ArrayList<Member>();
		
		try {
			String sql = prop.getProperty("selectMemberList");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				Member member = new Member();
				
				// 컬럼 값을 Member 객체에 저장
				member.setMemberId(rs.getString("MEMBER_ID"));
				member.setMemberName(rs.getString("MEMBER_NM"));
				member.setMemberGender(rs.getString("성별"));
				
				// Member 객체를 List에 추가
				memberList.add(member);

			}
			
		}finally {
			close(rs);
			close(stmt);
		}
		
		return memberList;
	}


	/** 회원 정보 수정 SQL 수행 DAO
	 * @param conn
	 * @param memberName
	 * @param memberGender
	 * @param memberNo
	 * @return result
	 */
	public int updateMember(Connection conn, String memberName, String memberGender, int memberNo) throws Exception{
		
		// 1. 결과 저장용 변수 선언
		int result = 0;

		try {
			// 2. SQL 작성, 수행
			String sql = prop.getProperty("updateMember");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberName);
			pstmt.setString(2, memberGender);
			pstmt.setInt(3, memberNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			// 3. JDBC 객체 자원 반환
			close(pstmt);
		}

		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
