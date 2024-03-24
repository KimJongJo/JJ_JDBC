package service;

import java.sql.Connection;
import java.util.List;

import dao.DAO;
import dto.Member;

import static edu.kh.jdbc1.common.JDBCTemplate.*;

public class Service {

	private DAO dao = new DAO();
	
	public List<Member> selectAll() {
		
		Connection conn = getConnection();
		
		List<Member> memberList = dao.selectAll(conn);
		
		close(conn);
		
		return memberList;
	}
	
	
	
}
