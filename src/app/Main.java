package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

import app.domain.User;

public class Main {
	private Scanner scanner = new Scanner(System.in);
	private Connection conn;
	
	
	public Main() {
		try {
			//JDBC Driver 등록
			Class.forName("oracle.jdbc.OracleDriver");
			
			//연결하기
			conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521/xe", 
				"sqld", 
				"1234"
			);
		} catch(Exception e) {
			e.printStackTrace();
			exit();
		}
	}
	
	public void reservation() {
		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("메인 메뉴: 1.예약 | 2.예약확인 | 3.예약수정 | 4.예약삭제 | 5. 나가기");
		System.out.print("메뉴 선택: ");
		String menuNo = scanner.nextLine();
		System.out.println();
		
		switch(menuNo) {
			case "1" -> create_rvt();
			case "2" -> read_rvt();
			case "3" -> update_rvt();
			case "4" -> delete_rvt();
			case "5" -> exit();
		}
	}
	
	public void create_rvt() {
		System.out.println("예약");
	}
	public void read_rvt() {
		System.out.println("예약확인");
	}
	public void update_rvt() {
		System.out.println("예약수정");
	}
	public void delete_rvt() {
		System.out.println("예약삭제");
	}
	
	
	
	
	
	public void home() {
		System.out.println("hello world");
		
		System.out.println();
		System.out.println("<Sign in | Sign up>");
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("선택: 1.로그인 | 2.회원가입 ");
		System.out.println("선택");
		String menuNo = scanner.nextLine();
		System.out.println();
		
		switch(menuNo) {
		case "1" -> login();
		case "2" -> create_user();
		}
	}
	public void login() {
		System.out.println("로그인");
	}
	
	public void create_user() {
		System.out.println("회원가입");
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
	
	public void exit() {
		System.exit(0);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// presentation layer
		Main main = new Main();
		main.reservation();
		
	}

}
