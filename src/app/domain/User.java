package app.domain;

public class User {
	private int user_no;
	private String id;
	private String pw;
	private String user_name;
	private String address;
	private String tel;
	private String email;
	private boolean field;
	
	public void create_user() {
		System.out.println("회원가입");
//		User user = new User();
	}
	
	public void read_user() {
		System.out.println("회원정보조회");
	}
	
	public void update_user() {
		System.out.println("회원정보수정");
	}
	
	public void delete_user () {
		System.out.println("회원탈퇴");
	}
}
