package com.study.springboot.controller;

import java.util.Optional;

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

@Controller
@SessionAttributes("loginUser")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/list")
	public String list(@RequestParam(value="nowPage", defaultValue="0") int nowPage, Model model) {
		Page<Board> pageList = boardService.list(PageRequest.of(nowPage, 10, Sort.by(Sort.Direction.DESC, "bno")));
		
		int pagePerBlock = 5;	// [1][2][3][4][5] 되어있음
		int endPage = Math.min(pageList.getTotalPages(), nowPage + pagePerBlock); // 둘 중 작은 값을 불러옴

		model.addAttribute("boardPage", pageList);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("endPage", endPage);
		return "board/list";
	}
	
	@GetMapping("/insertForm")
	public String insertForm() {
		return "board/insertForm";
	}
	
	@PostMapping("/insert")
	public String insert(Board board) {
		boardService.insert(board);
		return "redirect:list";	
	}
	
	@GetMapping("/detailForm")
	public String detailForm(Board board,  Model model) {
		Optional<Board> b = boardService.detailForm(board);
		model.addAttribute("board" , b.get()); // .get() 을 꼭 써줘야함 why Optional때문에 ~!!
		return "board/detailForm";
	}
	
	
	
	/*
	@PostMapping("/insert")
	public String insert(Board board, HttpSession session) {
		Member m = (Member)session.getAttribute("loginUser");
		board.setWriter(m.getName());
		boardService.insert(board);
		return "redirect:list";
	}
	*/
}