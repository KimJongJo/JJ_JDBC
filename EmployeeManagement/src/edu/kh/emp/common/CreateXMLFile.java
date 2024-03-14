package edu.kh.emp.common;

import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Scanner;

public class CreateXMLFile {
	public static void main(String[] args) {
	
		try {
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("생성할 파일 : ");
			String fileName = sc.nextLine();
			
			Properties prop = new Properties();
			
			FileOutputStream fos = new FileOutputStream(fileName + ".xml");
			
			prop.storeToXML(fos, fileName + ".xml file!!");
			
			System.out.println("파일 생성 완료!");
			
			
		} catch (Exception e) {
			System.out.println("파일 생성 오류");
			e.printStackTrace();
		}

		
	}
	
		
	
}
