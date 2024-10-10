package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Board;
import com.example.demo.entity.Comment;

import jakarta.transaction.Transactional;

@Transactional  // 삭제후 TRANSACTIONAL 있어야 COMMIT됨
public interface CommentRepository extends JpaRepository<Comment, Integer>{

	// 게시물을 기준으로 댓글 목록 조회
	List<Comment> findByBoard(Board board);
	
	// 게시물을 기준으로 댓글을 모두 삭제
	void deleteByBoard(Board board);
	
}
