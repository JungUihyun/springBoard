package com.yydh.www.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yydh.www.model.BoardDAO;
import com.yydh.www.vo.BoardVO;

public class BoardController1 implements Controller1 {
	@Override
	public String service(HttpServletRequest req, HttpServletResponse resp) {
		
		int page = 1;
		try {
			Integer.parseInt(req.getParameter("p"));
			if(page < 1) {
				page = 1;
			}
		} catch (Exception e) {
			page = 1;
		}
		
		List<BoardVO> list = BoardDAO.getIns().getList(page);
		req.setAttribute("list", list);
		
		return "board";
	}
}
