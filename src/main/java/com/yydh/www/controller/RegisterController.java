package com.yydh.www.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yydh.www.model.MemberDAO;

public class RegisterController implements Controller1 {
	@Override
	public String service(HttpServletRequest req, HttpServletResponse resp) {
		
		if (req.getMethod().equalsIgnoreCase("POST")) {
			String name = req.getParameter("name");
			String id = req.getParameter("id");
			String password = req.getParameter("password");

			boolean rs = MemberDAO.instance.register(name, id, password);

			if (rs) {
				System.out.println("회원가입 성공");
				req.getSession().setAttribute("msg", "회원가입 성공");
				return "redirect::/";
			} else {
				System.out.println("회원가입 실패");
				req.getSession().setAttribute("msg", "회원가입  실패");
				return "register";
			}
		}
		return "register";
	}

}
