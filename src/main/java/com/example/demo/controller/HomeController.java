package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	// 메인화면을 반환하는 메소드     // localhost:8080/ << 메인화면 반환 됨.
	@GetMapping("/") // /board/main( /board/main ) >> BoardController << 에서 잘라낸 것.
	public String main() {
		// 반환할 뷰의 경로를 직접 작성
		return "/home/main";
	}
	
	@GetMapping("/customlogin")
	public String login() {
		
		return "/home/login";
		
	}
	
}
