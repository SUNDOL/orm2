package com.orm2.server.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
	
	Optional<Member> findBymEmail(String mEmail);

}
