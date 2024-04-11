package app;

import lombok.Data;

@Data
public class Reservation {
	private int reserve_no;
	private int user_no;
	private String type;
	private int num;
	
	public void create_rvt() {
		System.out.println("예약완료");
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
}
