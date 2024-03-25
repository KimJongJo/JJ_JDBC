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

	public int updatePassword(int input, String password) {
		
		Connection conn = getConnection();
		
		int result = dao.updatePassword(conn, input, password);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	
	
}
