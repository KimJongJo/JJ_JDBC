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
	
	
	/** 비밀번호 수정
	 * @throws Exception
	 */
	public void updatePassword() throws Exception{
		
		System.out.print("변경할 memberNo 입력 : ");
		int input = sc.nextInt();
		sc.nextLine();
		
		System.out.print("새 비밀번호 입력 : ");
		String password = sc.nextLine();
		
		int result = service.updatePassword(input, password);
		
		if(result > 0) {
			System.out.println("비밀번호가 변경되었습니다.");
		}else {
			System.out.println("비밀번호 변경 오류");
		}
		
		selectAll();
		
	}
	
	
}
