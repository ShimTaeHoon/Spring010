package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.CommentDTO;

@SpringBootTest
public class CommentServiceTest {

	@Autowired
	CommentService service;
	
	@Test
	public void 댓글등록() {
		
		CommentDTO dto = CommentDTO.builder()
									.boardNo(6)
									.content("ㅎㅇ")
									.writer("baejoohwan")
									.build();
		
		CommentDTO dto2 = CommentDTO.builder()
									.boardNo(6)
									.content("하이루")
									.writer("kiminsu")
									.build();
		
		CommentDTO dto3 = CommentDTO.builder()
									.boardNo(6)
									.content("방가방가")
									.writer("bjh")
									.build();
		
		service.register(dto);
		service.register(dto2);
		service.register(dto3);
				
	}
	
}
