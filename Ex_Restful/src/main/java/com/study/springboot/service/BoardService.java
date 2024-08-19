package com.study.springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.study.springboot.domain.Board;
import com.study.springboot.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	BoardRepository boardRepository;

	public Board insert(Board board) {
		return boardRepository.save(board);
	}

	public Page<Board> list(PageRequest page) {
		return boardRepository.findAll(page);
	}

	public Optional<Board> detailForm(Board board) {
		return boardRepository.findById(board.getBno());		
	}
	
}
