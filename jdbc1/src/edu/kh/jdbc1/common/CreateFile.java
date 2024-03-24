package edu.kh.jdbc1.common;

import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Scanner;

public class CreateFile {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		try {
			
			System.out.print("생성하실 파일 이름 입력 : ");
			String filename = sc.nextLine();
			
			Properties prop = new Properties();
			
			FileOutputStream fos = new FileOutputStream(filename + ".xml");
			
			prop.storeToXML(fos, filename + ".xml test");
			
			System.out.println("파일 생성 완료");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
			
	}
	
}
