package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;

@Controller
//@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService service;
	
	// 목록 화면을 반환하는 메소드
	// /member/list
	// /member/list?page=1
	// /member/list?page=2
	@GetMapping("/member/list")
	public void list(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		
		Page<MemberDTO> list = service.getList(page);
		
		model.addAttribute("list", list);
		
	}
	
	// 등록화면을 반환하는 메소드
	// void는 파일의 경로가 url경로가 됨
	@GetMapping("/register")
	public String register() {
		return "/member/register";
	}
	
	// 회원을 등록하는 메소드
	@PostMapping("/register")
	public String registerPost(MemberDTO dto, RedirectAttributes redirectAttributes) {
	
		boolean result= service.register(dto);
		
		if(result) {
			
			// 회원가입에 성공했으면 목록화면으로 이동
//			return "redirect:/member/list";
			return "redirect:/"; // 로그인 로직 생성 후 수정
			
		} else {
			
			// 등록에 실패했으면 다시 회원가입폼으로 이동하면서
			// 에러메세지 표시
			redirectAttributes.addFlashAttribute("msg", "아이디가 중복되어 등록에 실패하였습니다");
				
//			return "redirect:/member/register";
			return "redirect:/register"; // 수정
			
		}
		
	}
	
	// 상세화면을 반환하는 메소드
	@GetMapping("/member/read")
	// /member/read?id=user1&page=1	
	// /member/read?id=user1 << 두번째 파라미터에 defaultValue가 있어서 생략 가능, 0값 반환
	public void read(@RequestParam(name = "id") String id, @RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		
		// 전달받은 파라미터로 회원 조회
		MemberDTO dto = service.read(id);
		// 조회한 회원정보를 화면에 전달
		model.addAttribute("dto", dto);
		// 페이지 번호를 화면에 전달
		model.addAttribute("page", page);
		
	}
	
}
