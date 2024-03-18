package edu.kh.jdbc.main.model.service;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.main.model.dao.MainDAO;
import edu.kh.jdbc.member.model.dto.Member;

import static edu.kh.jdbc.common.JDBCTemplate.*;

public class MainService {
	
	private MainDAO dao = new MainDAO();

	/** 회원가입
	 * @param member
	 * @return
	 */
	public int signUp(Member member) throws Exception{
		
		Connection conn = getConnection();
		
		// DAO 호출
		int result = dao.signUp(member, conn);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		
		return result;
	}

	/** 로그인 기능
	 * @param memberId
	 * @param memberPw
	 * @return
	 * @throws Exception
	 */
	public Member login(String memberId, String memberPw) throws Exception{
		
		Connection conn = getConnection();
		
		Member member = dao.login(memberId, memberPw, conn);
		
		close(conn);
		
		
		return member;
	}

	

	
	
	
	public int idDuplicationCheck(String memberId) throws Exception{
		
		int result = 0;
		
		Connection conn = getConnection();
		
		result = dao.idDuplicationCheck(memberId, conn);
		
		close(conn);
		
		
		return result;
		
	}




	
}
