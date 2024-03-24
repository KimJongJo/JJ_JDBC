package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static edu.kh.jdbc1.common.JDBCTemplate.*;

import dto.Member;

public class DAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Properties prop;
	
	public DAO() {
		
		try {
			
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("main-sql.xml"));
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public List<Member> selectAll(Connection conn) {
		
		List<Member> memberList = new ArrayList<Member>();
		
		try {
			
			String sql = prop.getProperty("selectAll");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				Member member = new Member();
				
				member.setMemberNo(rs.getInt(1));
				member.setMemberId(rs.getString(2));
				member.setMemberPw(rs.getString(3));
				member.setMemberName(rs.getString(4));
				
				memberList.add(member);
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		
		return memberList;
	}
	
}
