package com.orm2.server.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orm2.server.common.Result;

@RestController
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@GetMapping("/test")
	public String test() {
		return "Happy Hacking!";
	}
	
	@PostMapping("/register")
	public Result register(@RequestBody MemberDTO.Register data) {
		return service.register(data);
	}
	
	@PostMapping("/login")
	public String login() {
		return "Login";
	}
	
	@PostMapping("/logout")
	public String logout() {
		return "Logout";
	}
	
}