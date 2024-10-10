package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.CommentRepository;

// 자식 클래스들 중 서비스로 지정
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardRepository repository; //리파지토리를 빈으로 주입
	
	@Autowired
	CommentRepository commentRepository; // 댓글 리파지토리
	
	@Override
	public int register(BoardDTO dto) {

		// 전달받은 DTO를 엔티티로 변환
		Board entity = dtoToEntity(dto);
		
		// 엔티티를 테이블에 저장
		repository.save(entity);
		
		// 저장 후 게시물의 번호를 반환
		int newNo = entity.getNo();
		
		return newNo;
		
	}

	@Override
//		public List<BoardDTO> getList() {
		public Page<BoardDTO> getList(int pageNumber) {
		
//		// 데이터베이스에서 게시물 목록 가져오기
//		List<Board> result = repository.findAll();
//		
//		// 엔티티 리스트를 DTO 리스트로 변환하기
//		List<BoardDTO> list = new ArrayList<>();
//		
//		// 리스트에서 스트림 생성하기
//		// 스트림을 사용하여 모든 엔티티를 DTO로 변환
//		// 함수형 인터페이스는 람다식 함수로 구현한다
//		// collect: 스트림을 다른 자료구조로 변환하는 함수
//		list = result.stream()
//				.map(entity -> entityToDto(entity))
//				.collect(Collectors.toList()); // 리스트로 변환..
//		
//		return list; // 변환한 DTO 리스트 반환
		
		// 페이지번호 인덱스로 변경
		// 페이지번호에서 1만큼 빼야함
		int pageNum = (pageNumber == 0) ? 0 : pageNumber - 1;
		
		// 정렬 조건
		Sort sort = Sort.by("no").descending();
		
		// 페이지 조건
		Pageable pageable = PageRequest.of(pageNum, 10, sort);
		
		Page<Board> page = repository.findAll(pageable);
		
		// 엔티티 리스트 -> DTO 리스트로 변환
		
		Page<BoardDTO> dtoPage = page
									.map(entity->entityToDto(entity));
		
		// 변환한 DTO타입의 page 객체 반환
		return dtoPage;
		
	}

	@Override
	public BoardDTO read(int no) {
		
		// 게시물 번호로 글 조회
		Optional<Board> optional = repository.findById(no);
		
		// 값이 있는지 확인
		if(optional.isPresent()) {
			Board board = optional.get();
			BoardDTO dto = entityToDto(board);
			return dto;
		}
		
		return null;
	}

	@Override
	public void modify(BoardDTO dto) {
		
		// 전달받은 DTO에서 게시물 번호를 꺼내고, DB에 존재하는지 확인
		int no = dto.getNo();
		
		Optional<Board> optional = repository.findById(no);
		
		if(optional.isPresent()) {
			
			//dto가아닌 기존 데이터 Board에서 수정, dto는 위험
			Board board = optional.get();
			
			// 기존 엔티티에서 제목, 내용, 작성자 변경
			board.setTitle(dto.getTitle());
			board.setContent(dto.getContent());
//			board.setWriter(dto.getWriter());
			
			// 데이터베이스에 업데이트
			repository.save(board);
			
		}
		
	}

	@Override
	public void remove(int no) {

		// 게시물이 존재하는지 확인하고 삭제
		Optional<Board> optional = repository.findById(no);
		
//		if(optional.isPresent() == true) {
		if(optional.isPresent()) {
			
			// 게시물에 달린 댓글 먼저 삭제
			Board board = Board.builder().no(no).build();
		
			commentRepository.deleteByBoard(board);
			
			// 게시물댓글 삭제 후 부모 데이터 삭제
			repository.deleteById(no);
			
		}	
		
	}
	
}

//R apply(T Entity) {
//	return entityToDto(entity);
//}

// 람다식
// entity -> entityToDto(entity)

