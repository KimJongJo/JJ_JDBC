package edu.kh.emp.model.service;

import edu.kh.emp.model.dao.EmployeeDAO;
import static edu.kh.emp.common.JDBCTemplate.*;

import java.sql.Connection;

public class EmployeeService {
	
	EmployeeDAO dao = new EmployeeDAO();

	
	
	public void selectAll() {
		
		Connection conn = getConnection();
		
		dao.selectAll(conn);
		
		close(conn);
		
		
	}



	public int deletePerson(int empNo) {
		
		Connection conn = getConnection();
		
		int result = dao.deletePerson(conn, empNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
		
		
	}
	
	

}
