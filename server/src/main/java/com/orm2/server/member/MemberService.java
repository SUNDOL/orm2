package com.orm2.server.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.orm2.server.common.ErrorResponse;
import com.orm2.server.common.Result;
import com.orm2.server.enumpkg.ServiceResult;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public Result register(MemberDTO.Register data) {
		Result result = new Result();
		boolean isPresent = repository.findByMemberEmail(data.getEmail());
		if (!isPresent) {
			try {
				Member member = new Member();
				member.setMEmail(data.getEmail());
				member.setMPassword(passwordEncoder.encode(data.getPassword()));
				member.setMName(data.getName());
				
			} catch (Exception e) {
				result.setResponse(new ErrorResponse(ServiceResult.FAIL.toString()));
			}			
		} else {
			result.setResponse(new ErrorResponse(ServiceResult.FAIL.toString()));
		}
		return result;
	}

}