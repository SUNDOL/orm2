package com.orm2.server.member;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {
	
	private Long mId;
	
	private String mEmail;
	
	private String mPassword;
	
	private String mName;

}
