package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
	private Scanner scanner = new Scanner(System.in);
	private Connection conn;

	public Main() {
		try {
			// JDBC Driver 등록
			Class.forName("oracle.jdbc.OracleDriver");

			// 연결하기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "testuser", "test1234");
		} catch (Exception e) {
			e.printStackTrace();
			exit();
		}
	}

	public void list() {
		// seat_type 테이블 정보를 가져와서 출력하기
		try {
			System.out.println();
			System.out.println("좌석리스트");
			System.out.println("-----------------------------------------------------------------------");

			String sql = "" + "SELECT * " + "FROM seattype ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			List<String> list = new ArrayList<>();
			HashMap<String, List<String>> seat_list = new HashMap<>();
			seat_list.put("R", new ArrayList<>());
			seat_list.put("S", new ArrayList<>());
			seat_list.put("A", new ArrayList<>());
			seat_list.put("B", new ArrayList<>());
			while (rs.next()) {
				SeatType st = new SeatType();
//				st.setSeat_no(rs.getInt("seat_no"));
//				System.out.println(rs.getInt("seat_no"));
				String k = rs.getString("type");
//				System.out.println(rs.getString("type"));
				Integer v = rs.getInt("num");
				int flag = rs.getInt("check_flag");// System.out.println(rs.getInt("num"));
//				st.setCheck_flag(rs.getString("check_flag"))
				if (flag == 0) {
					seat_list.get(k).add(Integer.toString(v));
				} else {
					seat_list.get(k).add("X");
				}
			}
			rs.close();
			pstmt.close();

			// 타이틀 및 컬럼명 출력
			System.out.print("R: ");
			System.out.println(seat_list.get("R").toString());
			System.out.print("S: ");
			System.out.println(seat_list.get("S").toString());
			System.out.print("A: ");
			System.out.println(seat_list.get("A").toString());
			System.out.print("B: ");
			System.out.println(seat_list.get("B").toString());
			System.out.println("-----------------------------------------------------------------------");

		} catch (SQLException e) {
			e.printStackTrace();
			exit();
		}

		home();
	}

	public void list2() {
		// seat_type 테이블 정보를 가져와서 출력하기
		try {
			System.out.println();
			System.out.println("좌석리스트");
			System.out.println("-----------------------------------------------------------------------");

			String sql = "" + "SELECT * " + "FROM seattype ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			List<String> list = new ArrayList<>();
			HashMap<String, List<String>> seat_list = new HashMap<>();
			seat_list.put("R", new ArrayList<>());
			seat_list.put("S", new ArrayList<>());
			seat_list.put("A", new ArrayList<>());
			seat_list.put("B", new ArrayList<>());
			while (rs.next()) {
				SeatType st = new SeatType();
//						st.setSeat_no(rs.getInt("seat_no"));
//						System.out.println(rs.getInt("seat_no"));
				String k = rs.getString("type");
//						System.out.println(rs.getString("type"));
				Integer v = rs.getInt("num");
				int flag = rs.getInt("check_flag");// System.out.println(rs.getInt("num"));
//						st.setCheck_flag(rs.getString("check_flag"))
				if (flag == 0) {
					seat_list.get(k).add(Integer.toString(v));
				} else {
					seat_list.get(k).add("X");
				}
			}
			rs.close();
			pstmt.close();

			// 타이틀 및 컬럼명 출력
			System.out.print("R: ");
			System.out.println(seat_list.get("R").toString());
			System.out.print("S: ");
			System.out.println(seat_list.get("S").toString());
			System.out.print("A: ");
			System.out.println(seat_list.get("A").toString());
			System.out.print("B: ");
			System.out.println(seat_list.get("B").toString());
			System.out.println("-----------------------------------------------------------------------");

		} catch (SQLException e) {
			e.printStackTrace();
			exit();
		}
	}

	public void home() {

		System.out.println();
		System.out.println("<Sign in | Sign up>");
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("선택: 1.로그인 | 2.회원가입 ");
		System.out.println("선택");
		String menuNo = scanner.nextLine();
		System.out.println();

		switch (menuNo) {
		case "1" -> login();
		case "2" -> create_user();
		}

		SeatType st = new SeatType();
	}

	public void login() {
		// 6개 항목 입력 받기
		System.out.print("id: ");
//		String id = scanner.nextLine();
		String id = "user1";
		System.out.println(id);
		System.out.print("pw: ");
//		String pw = scanner.nextLine();
		String pw = "password1";
		System.out.println(pw);

		System.out.println("로그인 성공");
		User user = new User();
		try {
			String sql = "" + "SELECT * " + "FROM users " + "WHERE id=? and pw=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				user.setUser_no(rs.getInt("user_no"));
				user.setId(rs.getString("id"));
				user.setPw(rs.getString("pw"));
				user.setUsername(rs.getString("username"));
				user.setTel(rs.getString("tel"));
				user.setAddress(rs.getString("address"));
				user.setEmail(rs.getString("email"));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			exit();
		}

		reservation(user);
	}

	public void create_user() {
		System.out.println("회원가입");
		User user = new User();

		// 6개 항목 입력 받기
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
			String sql = "" + "INSERT INTO USERS (user_no, id, pw, username, address, tel, email) "
					+ "VALUES (user_no_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
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

		home();
	}

	public void read_user() {
		System.out.println("회원정보조회");
	}

	public void update_user() {
		System.out.println("회원정보수정");
	}

	public void delete_user() {
		System.out.println("회원탈퇴");
	}

	public void exit() {
		System.exit(0);
	}

	public void reservation(User user) {

		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("메인 메뉴: 1.예약하기 | 2.예약확인 | 3. 나가기");
		System.out.print("메뉴 선택: ");
		String menuNo = scanner.nextLine();
		System.out.println();

		switch (menuNo) {
		case "1" -> create_rvt(user);
		case "2" -> read_rvt(user);
		case "3" -> exit();
		}
	}

	public void create_rvt(User user) {
		Reservation rv = new Reservation();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("좌석종류: 1.R석 | 2.S석 | 3.A석 | 4.B석");
		System.out.print("좌석선택: ");
		int type = Integer.parseInt(scanner.nextLine());

		switch (type) {
		case 1 -> rv.setType("R");
		case 2 -> rv.setType("S");
		case 3 -> rv.setType("A");
		case 4 -> rv.setType("B");
		}
		String seatType = rv.getType();
		System.out.print("좌석번호: ");
		int seatNum = Integer.parseInt(scanner.nextLine());
		rv.setNum(seatNum);

		int seat_no = 0;

		// 보조 메뉴 출력
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("보조 메뉴: 1.Ok | 2.Cancel");
		System.out.print("메뉴 선택: ");
		String menuNo1 = scanner.nextLine();
		if (menuNo1.equals("1")) {
			// reservation 테이블에 게시물 정보 저장
			try {
				String sql = "" + "INSERT INTO reservation (reserve_no, user_no, type, num) "
						+ "VALUES (RESERVE_NO_SEQ.NEXTVAL, ?, ?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.setInt(1, user.getUser_no());
				pstmt.setInt(1, 1);
				pstmt.setString(2, seatType);
				pstmt.setInt(3, seatNum);
				pstmt.executeUpdate();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
				exit();
			}
		}

		// 좌석상태 변경 코드
		try {
			SeatType st = new SeatType();
			String sql = "" + "UPDATE seattype SET check_flag=1" + "WHERE type=? and num=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, seatType);
			pstmt.setInt(2, seatNum);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			exit();
		}

		System.out.println("예약완료");

		reservation(user);
	}

	public void read_rvt(User user) {
		System.out.println("나의 예약");
		int user_no = user.getUser_no();
		String username = user.getUsername();
		System.out.println("예약자: " + username);

		// boards 테이블에서 해당 게시물을 가져와 출력
		try {
			String sql = "" + "SELECT * " + "FROM reservation " + "WHERE user_no=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Reservation rv = new Reservation();
				rv.setReserve_no(rs.getInt("reserve_no"));
				rv.setUser_no(rs.getInt("user_no"));
				rv.setType(rs.getString("type"));
				rv.setNum(rs.getInt("num"));
				System.out.println("-------------------------------------------------------------------");
				System.out.println("예약번호: " + rv.getReserve_no());
				System.out.println("예약자: " + username);
				System.out.println("좌석 타입: " + rv.getType());
				System.out.println("좌석 번호: " + rv.getNum());
				// 보조 메뉴 출력
//				System.out.println("-------------------------------------------------------------------");
//				System.out.println("보조 메뉴: 1.Update | 2.Delete | 3.List");
//				System.out.print("메뉴 선택: ");
//				String menuNo = scanner.nextLine();
//				System.out.println();
//				
//				if(menuNo.equals("1")) {
//					update(board);
//				} else if(menuNo.equals("2")) {
//					delete(board);
//				}
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			exit();
		}

		reservation(user);
	}

	public void update_rvt(SeatType st, Users user) {
		System.out.println("예약수정");
	}

	public void delete_rvt(SeatType st, Users user) {
		System.out.println("예약삭제");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// presentation layer
		Main main = new Main();
		main.list();
	}
}
