package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class UsersExample {
	private Scanner scanner = new Scanner(System.in);
	private Connection conn;
	
	public UsersExample() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			conn=DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe",
					"testuser",
					"test1234"
				);
		} catch(Exception e) {
			e.printStackTrace();
			exit();
		}
	}
	
	public void list() {
		System.out.println();
		System.out.println("좌석 리스트");
		System.out.println("R: [1, 2, 3, 4, 5]");
		System.out.println("S: [1, 2, 3, 4, 5]");
		System.out.println("A: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]");
		System.out.println("B: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]");
		mainMenu();
	}
	
	public void mainMenu() {
		System.out.println();
		System.out.println("----------------------------------");
		System.out.println("1. 로그인 | 2. 회원가입");
		System.out.print("선택: ");
		String menuNo = scanner.nextLine();
		System.out.println();
		
		switch(menuNo) {
			//로그인
			case "1"->login();
			//회원가입
			case "2"->create();
		}
	}
	
	public void login() {
		System.out.println("*** login() 메소드 실행됨");
		list();
	}
		
	public void create() {
		User user = new User();
		System.out.println("[회원 가입]");
		
		//6개 항목 입력 받기
		System.out.print("id: ");
		user.setId(scanner.nextLine());
		System.out.println("pw: ");
		user.setPw(scanner.nextLine());
		System.out.println("username: ");
		user.setUsername(scanner.nextLine());
		System.out.println("address: ");
		user.setAddress(scanner.nextLine());
		System.out.println("tel: ");
		user.setTel(scanner.nextLine());
		System.out.println("email: ");
		user.setEmail(scanner.nextLine());
		
		try {
			String sql=""+
				"INSERT INTO USERS (user_no, id, pw, username, address, tel, email) "+
				"VALUES (user_no_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPw());
			pstmt.setString(3, user.getUsername());
			pstmt.setString(4, user.getAddress());
			pstmt.setString(5, user.getTel());
			pstmt.setString(6, user.getEmail());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			exit();
		}
		mainMenu();
	}
	
	public void exit() {
		System.exit(0);
	}
	
	public static void main(String[] args) {
		UsersExample usersExample = new UsersExample();
		usersExample.list();
	}
}