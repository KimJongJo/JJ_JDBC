package edu.kh.emp.view;

import java.util.Scanner;

import edu.kh.emp.model.service.EmployeeService;

public class EmployeeView {
	
	EmployeeService service = new EmployeeService();
	
	
	public void display() {
		
		Scanner sc = new Scanner(System.in);
		
		int input = 0;

		do {
			
			
			System.out.println(" =-=-=-=-=-=-=-=-=-=-=-=-= Employee 테이블 =-=-=-=-=-=-=-=-=-=-=-=-= ");
			System.out.println("1. 전체 조회");
			System.out.println("2. 사원 추가");
			System.out.println("3. 사원 정보 수정");
			System.out.println("4. 사원 삭제");
			System.out.println("5. 조건 검색");
			System.out.print("번호 입력 >>> ");
			
			
			try {
				input = sc.nextInt();
				
				
				switch(input) {
					case 1 : service.selectAll(); break;
					case 2 : service.addPerson(); break;
					case 3 : service.updatePerson(); break;
					case 4 : service.deletePerson(); break;
					case 5 : service.selectSearch(); break;
				default : System.out.println("번호 입력이 오류");
				}
			}catch(Exception e) {
				System.out.println("잘못된 입력입니다.");
				sc.nextLine();
				input = -1;
			}
			
			
			
			
			
		}while(input != 0);

		
		
	}

	
}
