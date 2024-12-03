package com.orm2.server.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orm2.server.common.Result;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@Operation(summary = "테스트", description = "테스트")
	@GetMapping("/test")
	public String test() {
		return "Happy Hacking!";
	}
	
	@PostMapping("/register")
	public Result register(@RequestBody MemberDTO.Register data) {
		return service.register(data);
	}
	
	@PostMapping("/login")
	public Result login(@RequestBody MemberDTO.Login data) {
		return service.login(data);
	}
	
	@PostMapping("/logout")
	public String logout() {
		return "Logout";
	}
	
}