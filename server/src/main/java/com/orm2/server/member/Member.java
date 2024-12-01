package com.orm2.server.member;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {
	
	private Long mId;
	
	private String mEmail;
	
	private String mPassword;
	
	private String mName;
	
	private LocalDateTime mRegisteredDate;
	
	private LocalDateTime mModifiedDate;

}
