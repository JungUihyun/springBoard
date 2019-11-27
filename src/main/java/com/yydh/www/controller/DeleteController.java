package com.yydh.www.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yydh.www.model.BoardDAO;

public class DeleteController implements Controller1 {
	@Override
	public String service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {


			

		if (req.getMethod().equalsIgnoreCase("post")) {
			int id = Integer.parseInt(req.getParameter("id"));

			int res = BoardDAO.getIns().delete(id);

			if (res == 1) {
				req.getSession().setAttribute("msg", "글 삭제 완료");
				return "redirect::/board";
			} else {
				req.getSession().setAttribute("msg", "글 삭제중 오류");
				return "board/view?id=" + id;
			}
		}
		return "board";

	}
}
