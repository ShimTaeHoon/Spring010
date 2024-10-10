package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// DTO: 컨트롤러에서 뷰단으로 데이터를 전달하는 용도
// 엔티티와 구조가 같을때도 있고 약간 다를 때도 있음

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class BoardDTO {
	
	int no; //게시물번호
	String title; //제목
	String content; //내용
	String writer; //작성자
	LocalDateTime regDate; //등록일
	LocalDateTime modDate; //수정일
	
}
