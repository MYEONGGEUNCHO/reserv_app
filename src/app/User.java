package app;

import lombok.Data;

@Data
public class User {
	private int user_no;
	private String id;
	private String pw;
	private String username;
	private String address;
	private String tel;
	private String email;
	//private int check_flag;
}
