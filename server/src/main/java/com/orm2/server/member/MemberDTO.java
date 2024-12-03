package com.orm2.server.member;

import lombok.Getter;
import lombok.Setter;

public class MemberDTO {
	
	@Getter
	@Setter
	public static class Register {
		private String email;
		private String password;
		private String name;
	}
	
	@Getter
	@Setter
	public static class Login {
		private String email;
		private String password;
	}

}
