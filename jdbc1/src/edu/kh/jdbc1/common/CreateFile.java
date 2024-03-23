package edu.kh.jdbc1.common;

import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Scanner;

public class CreateFile {
	
	public static void main(String[] args) {
		
		
		try {
			
			Scanner sc = new Scanner(System.in);
			
			Properties prop = new Properties();
			
			System.out.print("생성할 파일 이름 : ");
			String fileName = sc.nextLine();
			
			FileOutputStream fos = new FileOutputStream(fileName + ".xml");
			
			prop.storeToXML(fos, fileName + ".xml file!!");
			
			System.out.println("파일 생성 완료!!");
			
		}catch(Exception e) {
			System.out.println("파일 생성중 에러 발생");
			e.printStackTrace();
		}
		
	}
	
}
