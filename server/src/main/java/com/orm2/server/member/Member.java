package com.orm2.server.member;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "member")
@NoArgsConstructor
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long mId;
	
	@Column(name = "member_email", unique = true, nullable = false, updatable = false)
	private String mEmail;
	
	@Column(name = "member_password", nullable = false)
	private String mPassword;
	
	@Column(name = "member_name", nullable = false)
	private String mName;
	
	@Column(name = "member_registered_date", updatable = false)
	private LocalDateTime mRegisteredDate;
	
	@Column(name= "member_updated_date")
	private LocalDateTime mUpdatedDate;
	
	@PrePersist
	protected void onCreate() {
		this.mRegisteredDate = LocalDateTime.now();
		this.mUpdatedDate = LocalDateTime.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.mUpdatedDate = LocalDateTime.now();
	}

}
