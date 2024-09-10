package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.board.mapper.BoardMapper;
import com.yedam.app.board.service.BoardVO;

@SpringBootTest
class BootBoardApplicationTests {

	@Autowired
	private BoardMapper boardMapper;
	
	//@Test
	void contextLoads() {
	}

	//@Test
	void boardList() {
		List<BoardVO> list = boardMapper.selectBoardAll();
//		assertTrue(!list.isEmpty());
		
		list.forEach(board -> {
			System.out.println("board : " + board);
		});
	}
	
	//@Test
	void boarInfo() {
		BoardVO boardVO = new BoardVO();
		boardVO.setBno(1002);
		
		BoardVO findVO = boardMapper.selectBoardInfo(boardVO);
		assertEquals(findVO.getWriter(), "김작가");

	}
	
	
	@Test
	void boardInsert() throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date day = sdf.parse("2024-01-01");
		
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("제목1");
		boardVO.setContents("내용1");
		boardVO.setWriter("김작가");
		boardVO.setRegdate(day);
		boardVO.setImage("이미지1.jpg");
		
		System.err.println("before) " + boardVO.getBno());
		
		int result = boardMapper.insertBoardInfo(boardVO);
		
		System.err.println("after) " + boardVO.getBno());
		
		assertEquals(result, 1);
	}
	
	//@Test
	void boardIsertBuilder() {
		BoardVO boardVO = BoardVO
				.builder()
				.title("First board")
				.writer("Tester")
				.regdate(new Date())
				.build();
				
		int result = boardMapper.insertBoardInfo(boardVO);
		assertEquals(result, 1);		
	}
	
	//@Test
	void boardDelete() {
		int result = boardMapper.deleteBoardInfo(1003);
		assertEquals(result, 1);
	}
	
	//@Test
	void boardUpdate() {
		
		BoardVO boardVO = new BoardVO();
		boardVO.setBno(1002);
		
		BoardVO findVO = boardMapper.selectBoardInfo(boardVO);
		
		findVO.setTitle("제목 수정2");
		findVO.setContents("내용 수정2");
		findVO.setWriter("김작가 수정2");
		findVO.setImage("이미지 수정2.jpg");
		
		int result = boardMapper.updateBoardInfo(findVO);
		assertEquals(result, 1);		
	}
	
}
