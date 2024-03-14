package edu.kh.emp.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class EmployeeDAO {
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	private Properties prop;
	
	public EmployeeDAO(){
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("query.xml"));
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
