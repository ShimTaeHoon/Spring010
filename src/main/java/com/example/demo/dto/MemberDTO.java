package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MemberDTO {

	String id;
	
	String password;
	
	String name;
	
	LocalDateTime regDate;
	
	LocalDateTime modDate; 
	
	String role; //사용자 등급 (예: ROLE_USER, ROLE_ADMIN)
	
}
