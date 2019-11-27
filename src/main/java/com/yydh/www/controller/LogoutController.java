package com.yydh.www.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;

public class LogoutController implements Controller1 {
	@Override
	public String service(HttpServletRequest req, HttpServletResponse resp) {
		
		if (req.getMethod().equalsIgnoreCase("get")) {
			HttpSession session = req.getSession();
			session.invalidate();
			req.getSession().setAttribute("msg", "로그아웃 성공");
			return "index";
		}
		return "index";
	}
}
