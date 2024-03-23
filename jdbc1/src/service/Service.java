package service;

import java.sql.Connection;
import java.util.List;

import dao.DAO;
import dto.Member;

import static edu.kh.jdbc1.common.JDBCTemplate.*;

public class Service {
	
	private DAO dao = new DAO();

	public List<Member> selectAll() throws Exception{
		
		Connection conn = getConnection();
		
		List<Member> list = dao.selectAll(conn);
		
		close(conn);
		
		return list;
	}

	public int updatePassword(int input, String pw) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.updatePassword(conn, input, pw);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		return result;
	}


}
