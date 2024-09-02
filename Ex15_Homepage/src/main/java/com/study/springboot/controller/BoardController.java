package com.study.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.study.springboot.domain.Board;
import com.study.springboot.service.BoardService;
import com.study.springboot.service.ReplyService;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("loginUser")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	// BoardService에 가는 변수명을 boardService 선언
	
	@Autowired
	ReplyService replyService;
	// ReplyService에 가는 변수명을 replyService 선언
	
	@GetMapping("/insertForm")
	public String insertForm() {
		return "board/insertForm";
	}
	/*
	   1. insertForm이 글쓰기 폼이기 때문에 list에서 글쓰기 누르는곳을 먼저 본다
	      <h4><a href="insertForm">[글쓰기]</a></h4> 이 부분이 있기 때문에 inserForm으로 감
	      여긴 이동만 도와주는 return이당
	*/
	
	
	@PostMapping("/insert")
	public String insert(Board board) {
		boardService.insert(board);
		return "redirect:list";
	}
	
	
	@GetMapping("/detailForm")
	public String detailForm(@RequestParam(value="bno") Long bno, Model model, HttpSession session) {
		model.addAttribute("board", boardService.selectDetail(bno).get());
		// boardService.selectDetail(bno)는 Optional<Board>를 반환하는 메서드
		// 게시글 번호(bno)를 이용해 게시글의 상세 정보를 조회
		// 게시글이 존재하지 않을 수도 있기 때문에 결과를 Optional로 래핑하여 반환
		
		model.addAttribute("reply", replyService.selectAll(bno));
		//model에 있는 reply에 replyService.selectAll(bno) 값을 넣는다는 소리
		
		session.setAttribute("boardDet0ailUrl", "/detailForm?bno=" + bno);
		
		return "board/detailForm";
	}
	
	@GetMapping("/list")
	public String list(@RequestParam(value="nowPage", defaultValue="0") int nowPage, Model model) {
		Page<Board> pageList = boardService.list(PageRequest.of(nowPage, 10, Sort.by(Sort.Direction.DESC, "bno")));
		//게시판에서 게시글 목록을 페이징 처리하여 가져오는 방법을 보여주는 코드
		/*
			PageRequest.of(nowPage, 10, Sort.by(Sort.Direction.DESC, "bno"))를 호출하여 페이지 요청을 생성
			nowPage: 요청하는 페이지 번호
			10: 페이지당 항목 수
			Sort.by(Sort.Direction.DESC, "bno"): 게시글 번호(bno)를 기준으로 내림차순 정렬
		*/
		int pagePerBlock = 5;	// [1][2][3][4][5]
		int endPage = Math.min(pageList.getTotalPages(), nowPage + pagePerBlock);

		model.addAttribute("boardPage", pageList);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("endPage", endPage);
		return "board/list";
	}	
	
	//

	
	@PostMapping("/update")
	public String update(Board board) {
		boardService.update(board);
		return "redirect:list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam(value="bno") Long bno) {
		boardService.delete(bno);
		return "redirect:list";
	}
}
