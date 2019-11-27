package com.yydh.www.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yydh.www.model.BoardDAO;
import com.yydh.www.vo.BoardVO;

public class ViewController implements Controller1{

	@Override
	public String service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		int id = Integer.parseInt(req.getParameter("id"));
		BoardVO data = BoardDAO.getIns().view(id);
		req.setAttribute("data", data);
		
		return "view";
	}

}
