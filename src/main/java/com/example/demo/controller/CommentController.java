package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CommentDTO;
import com.example.demo.service.CommentService;

@RequestMapping("/comment")
@RestController   // @ResponseBody + @Controller
public class CommentController {

	@Autowired
	CommentService service;
	
	// Class List -> JSON 문자열 List로 변환
	// @ResponseBody
	// 게시물에 달린 댓글 목록을 반환하는 메소드
	@GetMapping("/list") // /comment/list?boardNo=1
	public List<CommentDTO> list(@RequestParam(name = "boardNo") int boardNo) {
		
		// 댓글 목록 조회
		List<CommentDTO> list = service.getListByBoardNo(boardNo);
		
		return list; // 댓글 목록 전송
		
	}
	
	// 댓글 등록 처리 메소드
	@PostMapping("/register")
	// 매개변수로 Principal을 선언하면, 스프링컨테이너가 인증객체를 주입함 // 로그인처리 끝나고 principal 선언해야함
	public int register(CommentDTO dto, Principal principal) {
		
		// 로그인한 사용자의 아이디를 꺼내서 DTO에 업데이트
		String id = principal.getName();
		
		// 스프링 시큐리티를 처리하기 전까지는 임시 아이디 사용 // "aa" -> id
		dto.setWriter(id);
		
		// 테이블에 댓글 저장
		int newNo = service.register(dto);
		
		// 처리 결과 반환
		return newNo;
		
	}
	
	// 댓글 삭제 메소드
	@DeleteMapping("/remove")
	public boolean remove(@RequestParam(name = "commentNo") int commentNo) {
		
		// 댓글 삭제
		boolean result = service.remove(commentNo);
		
		// 처리 결과 반환
		return result;
		
	}
	
}
