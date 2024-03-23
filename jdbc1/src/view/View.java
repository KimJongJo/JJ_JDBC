package view;


import java.util.List;
import java.util.Scanner;

import dto.Member;
import service.Service;

public class View {

	private Service service = new Service();
	private Scanner sc = new Scanner(System.in);
	
	public void mainView() throws Exception{
		
		
		
		System.out.println("메뉴");
		System.out.println("1. 전체 조회하기");
		System.out.println("2. 비밀번호 수정하기");
		System.out.print(" >>> ");
		int input = sc.nextInt();
		
		if(input == 1) {
			selectAll();
		}else if(input == 2) {
			updatePassword();
		}
		
	}
	
	
	
	public void selectAll() throws Exception{
		
		List<Member> list = service.selectAll();
		
		if(!list.isEmpty()) {
			for(Member m : list) {
				System.out.printf("%d\t%s\t%s\t%s \n",
						m.getMemberNo(), m.getMemberId(),m.getMemberPw(), m.getMemberName());
			}
		}
		
	}
	
	
	public void updatePassword() throws Exception{
		
		System.out.print("비밀번호를 변경할 MEMBER 번호 입력 : ");
		int input = sc.nextInt();
		sc.nextLine();
		System.out.print("변경하실 비밀번호 입력 : ");
		String pw = sc.nextLine();
		
		int result = service.updatePassword(input, pw);
		
		if(result > 0) {
			System.out.println("비밀번호 변경 완료");
		}
		else {
			System.out.println("비밀번호 변경 오류");
		}
		
		selectAll();
		
	}
	
	
}
