package com.yydh.www.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yydh.www.model.BoardDAO;
import com.yydh.www.model.BoardDTO;

@Controller
public class boardController {

	@RequestMapping("/board.do")
	public String getBoardList(BoardDTO dto, BoardDAO dao, Model model) {
		
		ArrayList<BoardDTO> userList = dao.selectPosting(1);
		model.addAttribute("list", userList);
		
		return "board";
	}
}
