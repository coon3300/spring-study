package com.yedam.app.board.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;

@Controller
public class BoardController {
	private BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	// 전체조회 : URI - boardList / RETURN - board/boardList
	@GetMapping("boardList") 
	public String boardList(Model model) {
		List<BoardVO> list = boardService.boardList();
		
		model.addAttribute("boards", list);
		
		return "board/boardList";
		// prefix + return + suffix
		// classpath:/templates/ + board/boardList + .html
	}
	
	// 단건조회 : URI - boardInfo / PARAMETER - BoardVO(QueryString)
	//          RETURN - board/boardInfo
	// QueryString
	// 1) 객체 :  커맨드 객체
	// 2) 단일 값 : @RequestParam
	@GetMapping("boardInfo")
	public String boardInfo(BoardVO boardVO, Model model) {
		BoardVO findVO = boardService.boardInfo(boardVO);
		model.addAttribute("board", findVO);
		
		return "board/boardInfo";
		
	}
	// 등록 - 페이지 : URI - boardInsert / RETURN - board/boardInsert
	// 현재는 단순한 입력이라 모델이 없음. 외래키 관련해서 라디오버튼 셀렉트 등 필요할 경우 모델 필요함.
	@GetMapping("boardInsert")
	public String boardInsertForm() {
		return "board/boardInsert";
	}
	
	
	// 등록 - 처리 : URI - boardInsert / PARAMETER - BoardVO(QueryString)
	//             RETURN - 단건조회 다시 호출
	// <form/> 활용한 submit
	@PostMapping("boardInsert")
	public String boardInsertProcess(BoardVO baordVO) {
		int bno = boardService.insertBoard(baordVO);
		
		return "redirect:boardInfo?bno=" + bno;
	}
	
	// 수정 - 페이지 : URI - boardUpdate / PARAMETER - BoardVO(QueryString)
	//               RETURN - board/boardUpdate
	// => 단건조회
	@GetMapping("boardUpdate")
	public String boardUpdateForm(BoardVO boardVO, Model model) {
		BoardVO findVO = boardService.boardInfo(boardVO);
		model.addAttribute("board", findVO);
		return "board/boardUpdate";
	}
	
	// 수정 - 처리 : URI - boardUpdate / PARAMETER - BoardVO(JSON)
	//             RETURN - 수정결과 데이터(Map)
	// => 등록(내부에서 수행하는 쿼리문 - UPDATE문)
	@PostMapping("boardUpdate")
	@ResponseBody // ajax // @RequestBody // json
	public  Map<String, Object> boardUpdate(@RequestBody BoardVO boardVO) {
		return boardService.updateBoard(boardVO);
	}
	
	// 삭제 - 처리 : URI - boardDelete / PARAMETER - Integer
	//             RETURN - 전체조회 다시 호출
	@GetMapping("boardDelete") // QueryString : @RequestParam 지정하면 필수. 생략 가능.
	public String boardDelete(@RequestParam Integer bno) {
		boardService.deleteBoard(bno);
		return "redirect:boardList";
	}
	
	
}
