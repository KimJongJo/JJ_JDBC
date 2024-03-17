package edu.kh.emp.view;

import java.util.Scanner;

import edu.kh.emp.model.service.EmployeeService;

public class View {
	
	EmployeeService service = new EmployeeService();

	public void display() {
		
		Scanner sc = new Scanner(System.in);
		
		int input = 0;
		
		do {
			
			System.out.println("=-=-=-= EMPLOYEE_COP 테이블 =-=-=-=");
			System.out.println("1. 전체 조회");
			System.out.println("2. 사원 수정");
			System.out.println("2. 사원 삭제");
			System.out.println("0. 테이블 종료");
			System.out.print("수정할 사원의 번호를 입력해주세요 : ");
			
			
			
			
			try {
				
				input = sc.nextInt();
				
				switch(input) {
				case 1 : selectAll(); break;
				case 2 : updatePerson(); break;
				case 3 : deletePerson(); break;
				case 0 : System.out.println("테이블 종료...");
				default : System.out.println("없는 메뉴 입니다.");
				}
				
			}catch(Exception e) {
				System.out.println("잘못 입력 하셨습니다.");
				sc.nextLine();
				input = -1;
			}
		
		}while(input != 0);

	}

	private void deletePerson() {
		// TODO Auto-generated method stub
		
	}

	private void updatePerson() {  
		// TODO Auto-generated method stub
		
	}

	private void selectAll() {
		System.out.println("<회원 테이블>");
		service.selectAll();
		
	}

}
