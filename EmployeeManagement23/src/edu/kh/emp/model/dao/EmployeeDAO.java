package edu.kh.emp.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static edu.kh.emp.common.JDBCTemplate.*;

import edu.kh.emp.model.vo.Employee;

public class EmployeeDAO {

	// JDBC 객체 참조변수 선언
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public EmployeeDAO() {
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("query.xml"));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	/** 전체 사원 정보 조회 DAO
	 * @param conn
	 * @return list
	 */
	public List<Employee> selectAll(Connection conn) throws Exception{
		
		// 결과 저장용 변수 선언
		List<Employee> list = new ArrayList<Employee>();

		try {
			String sql = prop.getProperty("selectAll");
			
			// Statement 객체 생성
			stmt = conn.createStatement();
			
			// SQL 수행 후 결과(ResultSet) 반환 받기
			rs = stmt.executeQuery(sql);
			
			
			// 조회 결과를 얻어와 한 행씩 접근하여
			// Employee 객체 생성 후 컬럼값 담기
			while(rs.next()) {
				
				int empId = rs.getInt("EMP_ID");
				// EMP_ID 컬럼은 문자열 컬럼이지만
				// 저장된 값들은 모두 숫자형태
				// -> DB에서 자동으로 형변환 진행해서 얻어옴
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String departmentTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
						
				list.add(new Employee(empId, empName, empNo, email, phone, departmentTitle, jobName, salary));
				
				
			} // while문 종료
			
			
		} finally {
			
			close(rs);
			close(stmt);
			
		}
		return list;
	}

	/** 사원 정보 추가 DAO
	 * @param conn
	 * @param emp
	 * @return result (1/0)
	 */
	public int insertEmployee(Connection conn, Employee emp) throws Exception{
		
		int result = 0;
		
		try {
			
			// SQL 작성
			
			String sql = prop.getProperty("insertEmployee");
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// ? (위치홀더)에 알맞은 값 대입
			
			pstmt.setInt(1, emp.getEmpId());	// 사번
			pstmt.setString(2, emp.getEmpName()); // 이름
			pstmt.setString(3, emp.getEmpNo()); // 주민등록번호
			pstmt.setString(4, emp.getEmail()); // 이메일
			pstmt.setString(5, emp.getPhone()); // 전화번호
			pstmt.setString(6, emp.getDeptCode()); // 부서 코드
			pstmt.setString(7, emp.getJobCode()); // 직급 코드
			pstmt.setString(8, emp.getSalLevel()); // 급여 코드
			pstmt.setInt(9, emp.getSalary()); // 급여
			pstmt.setDouble(10, emp.getBonus()); // 보너스
			pstmt.setInt(11, emp.getManagerId()); // 사수 번호
			
			
			result = pstmt.executeUpdate();

		} finally {
			
			close(pstmt);
			
		}

		return result;
	}

	/** 사번이 일치하는 사원 정보 조회
	 * @param conn
	 * @param empId
	 * @return emp
	 */
	public Employee selectEmpId(Connection conn, int input) throws Exception{
		
		Employee emp = null;
		
		
		
		try {
			
			String sql = prop.getProperty("selectEmpId");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, input);
			
			
			// executeQuery - Select -> ResultSet
			// executeUpdate - DML(Update/Delete/Insert) -> int(성공한 행의 갯수)
			rs = pstmt.executeQuery();
			
			
			
			if(rs.next()) {

				emp = new Employee(input,
						rs.getString("EMP_NAME"), 
						rs.getString("EMP_NO"), 
						rs.getString("EMAIL"),
						rs.getString("PHONE"),
						rs.getString("DEPT_TITLE"),
						rs.getString("JOB_NAME"), 
						rs.getInt("SALARY"));
				
			}
			
		}finally {
			
			close(rs);
			close(pstmt);

		}

		return emp;
	}

	
	
	/** 사번이 일치하는 사원 정보 수정
	 * @param conn
	 * @param emp
	 * @return reslut
	 */
	public int upateEmployee(Connection conn, Employee emp) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("updateEmployee");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, emp.getEmail());
			pstmt.setString(2, emp.getPhone());
			pstmt.setInt(3, emp.getSalary()); 
			pstmt.setInt(4, emp.getEmpId());
			
			result = pstmt.executeUpdate();

		} finally {
			
			close(pstmt);
			
		}
		
		return result;
	}

	
	
	/** 사번이 일치하는 사원 정보 삭제
	 * @param conn
	 * @param empId
	 * @return result
	 */
	public int deleteEmplyee(Connection conn, int empId) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteEmployee");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, empId);
			
			result = pstmt.executeUpdate();
		} finally {
			
			close(pstmt);
			
		}
		return result;
	}
	
	

	/** 입력 받은 부서와 일치하는 모든 사원 정보 조회
	 * @param conn
	 * @param departmentTitle
	 * @return list
	 */
	public List<Employee> selectDeptEmp(Connection conn, String departmentTitle) throws Exception{

		List<Employee> list = new ArrayList<Employee>();
		
		try {
			String sql = prop.getProperty("selectDeptEmp");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, departmentTitle);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				list.add(new Employee(rs.getInt("EMP_ID"),
						rs.getString("EMP_NAME"),
						rs.getString("EMP_NO"),
						rs.getString("EMAIL"),
						rs.getString("PHONE"),
						departmentTitle,
						rs.getString("JOB_NAME"),
						rs.getInt("SALARY")));
				
			}
			
		}finally {
			close(rs);
			close(pstmt);
			
		}
		
		return list;
	}
	
	
	
	

	/** 입력 받은 급여 이상을 받는 모든 사원 정보 조회
	 * @param conn
	 * @param salary
	 * @return
	 */
	public List<Employee> selectSalaryEmp(Connection conn, int salary) throws Exception{

		List<Employee> list = new ArrayList<Employee>();
		
		try {
			String sql = prop.getProperty("selectSalaryEmp");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, salary);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				list.add(new Employee(rs.getInt("EMP_ID"),
						rs.getString("EMP_NAME"),
						rs.getString("EMP_NO"),
						rs.getString("EMAIL"),
						rs.getString("PHONE"),
						rs.getString("DEPT_TITLE"),
						rs.getString("JOB_NAME"),
						salary));
				
			}
			
		}finally {
			close(rs);
			close(pstmt);
			
		}
		
		return list;
	}

	
	
	
	/** 부서별 급여 합 전체 조회
	 * @param conn
	 * @return
	 */
	public Map<String, Integer> selectDeptTotalSalary(Connection conn) throws Exception{
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		try {
			
			String sql = prop.getProperty("selectDeptTotalSalary");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				map.put(rs.getString("DEPT_CODE"), rs.getInt("SALARY"));
				
			}

		}finally {
			close(rs);
			close(stmt);
		}

		return map;
	}

	public Employee selectEmpNo(Connection conn, String empNo) throws Exception{
		
		Employee emp = null;
		
		try {
			String sql = prop.getProperty("selectEmpNo");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, empNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				emp = new Employee(rs.getInt("EMP_ID"),
						rs.getString("EMP_NAME"),
						empNo,
						rs.getString("EMAIL"),
						rs.getString("PHONE"),
						rs.getString("DEPT_TITLE"),
						rs.getString("JOB_NAME"),
						rs.getInt("SALARY"));
			}
			
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return emp;
	}

	
	
	
	public Map<String, Double> selectJobAvgSalary(Connection conn) throws Exception{
		
		
		
		Map<String, Double> map = new LinkedHashMap<String, Double>();
		
		try {
			
			String sql = prop.getProperty("selectJobAvgSalary");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				map.put(rs.getString("JOB_NAME"), rs.getDouble("SALARY"));
				
			}

		}finally {
			close(rs);
			close(stmt);
		}

		return map;
	}
	
}
