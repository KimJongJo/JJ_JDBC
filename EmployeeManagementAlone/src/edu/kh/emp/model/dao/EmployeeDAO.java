package edu.kh.emp.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class EmployeeDAO {
	
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	private Properties prop; 
	
	
	public EmployeeDAO() {
		
		try {
			
			prop = new Properties();
			
			prop.loadFromXML(new FileInputStream("query.xml"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	public void selectAll(Connection conn) {
		
		try {
			
			String sql = prop.getProperty("selectAll");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				String empId = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String Phone = rs.getString("PHONE");
				String deptTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				
				System.out.printf("사번 : %s / 이름 : %s / 주민등록번호 : %s / 이메일 : %s / 전화번호 : %s / 부서이름 : %s / 직급명 : %s / 급여 : %d \n",
						empId, empName, empNo, email, Phone, deptTitle, jobName, salary);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}

	public int deletePerson(Connection conn, int empNo) {
		
		int result = 0;
		
		try {
		
			String sql = prop.getProperty("deletePerson");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, empNo);
			
			result = pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		

		return result;
	}
	
}
