package dto;

public class Member {

	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberName;

	
	
	public Member() {}
	
	
	
	
	
	







	public Member(int memberNo, String memberId, String memberPw, String memberName) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
	}













	public int getMemberNo() {
		return memberNo;
	}




	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}




	public String getMemberId() {
		return memberId;
	}




	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}




	public String getMemberPw() {
		return memberPw;
	}




	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}




	public String getMemberName() {
		return memberName;
	}




	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}




	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPw=" + memberPw + ", memberName="
				+ memberName + "]";
	}

	
	
	
	
}
