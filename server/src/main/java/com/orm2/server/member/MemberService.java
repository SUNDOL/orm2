package com.orm2.server.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.orm2.server.common.ErrorResponse;
import com.orm2.server.common.Result;
import com.orm2.server.enumpkg.ServiceResult;
import com.orm2.server.jwt.JwtTokenProvider;

@Service
public class MemberService {

	@Autowired
	private MemberRepository repository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Value("${jwt.secret}")
	private String secretKey;

	public Result register(MemberDTO.Register data) {
		Result result = new Result();
		boolean isPresent = repository.findBymEmail(data.getEmail()).isPresent();
		if (!isPresent) {
			try {
				Member member = new Member();
				member.setMEmail(data.getEmail());
				member.setMPassword(passwordEncoder.encode(data.getPassword()));
				member.setMName(data.getName());
				repository.save(member);
				result.setResponse(new ErrorResponse(ServiceResult.OK.toString()));
			} catch (Exception e) {
				result.setResponse(new ErrorResponse(ServiceResult.FAIL.toString()));
			}
		} else {
			result.setResponse(new ErrorResponse(ServiceResult.FAIL.toString()));
		}
		return result;
	}

	public Result login(MemberDTO.Login data) {
		Result result = new Result();
		try {
			Authentication authentication = authManager
					.authenticate(new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword()));
			String token = tokenProvider.generateToken(authentication.getName());
			result.setResponse(new ErrorResponse(ServiceResult.OK.toString()));
			Map<String, String> responseData = new HashMap<>();
			responseData.put("token", token);
			result.setData(responseData);
		} catch (Exception e) {
			result.setResponse(new ErrorResponse(ServiceResult.FAIL.toString()));
		}
		return result;
	}

}
