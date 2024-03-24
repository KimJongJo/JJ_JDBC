package view;


import java.util.List;
import java.util.Scanner;

import dto.Member;
import service.Service;

public class View {

	private Scanner sc = new Scanner(System.in);
	
	private Service service = new Service();
	
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

		List<Member> memberList = service.selectAll();
		
		for(Member member : memberList) {
			System.out.printf("%d\t%s\t%s\t%s\n",
					member.getMemberNo(), member.getMemberId(),member.getMemberPw(),member.getMemberName());
		}
		
		
	}
	
	
	public void updatePassword() throws Exception{

	}
	
	
}
