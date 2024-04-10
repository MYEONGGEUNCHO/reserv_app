package app;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
	private Scanner scanner = new Scanner(System.in);
	private Connection conn;
	
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
			case "2" -> create_account();
		}
	}
	public void login() {
		System.out.println("로그인");
	}
	
	public void create_account() {
		System.out.println("회원가입");
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// presentation layer
		Main main = new Main();
		main.home();
	}

}
