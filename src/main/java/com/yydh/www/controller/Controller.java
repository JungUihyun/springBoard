package com.yydh.www.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yydh.www.model.BoardDAO;
import com.yydh.www.model.MemberDAO;
import com.yydh.www.vo.BoardVO;
import com.yydh.www.vo.UserVO;

@Controller
public class Controller {
	
	@RequestMapping("/login")
	public String login(MemberDAO memberDAO, Model model, HttpServletRequest req, HttpServletResponse resp) {
		String id = req.getParameter("id");
		String password = req.getParameter("password");

		UserVO user = MemberDAO.instance.login(id, password);

		if (user != null) {
			System.out.println("로그인 성공");
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			req.getSession().setAttribute("msg", "로그인 성공");
			return "redirect::/board";
		} else {
			System.out.println("로그인 실패");
			HttpSession session = req.getSession();
			req.getSession().setAttribute("msg", "로그인 실패");
			return "redirect::/";
		}
	}
	
	@RequestMapping(value="board/delete", method=RequestMethod.GET)
	public String delete(MemberDAO memberDAO, Model model, HttpServletRequest req, HttpServletResponse resp) {
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
	
	@RequestMapping("/logout")
	public String logout(MemberDAO memberDAO, Model model, HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		session.invalidate();
		req.getSession().setAttribute("msg", "로그아웃 성공");
		return "index";
	}
	
	@RequestMapping("/register")
	public String register(MemberDAO memberDAO, Model model, HttpServletRequest req, HttpServletResponse resp) {
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
	
	@RequestMapping("/view")
	public String view(MemberDAO memberDAO, Model model, HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		BoardVO data = BoardDAO.getIns().view(id);
		req.setAttribute("data", data);
		
		return "view";
	}
	
	@RequestMapping("/write")
	public String write(MemberDAO memberDAO, Model model, HttpServletRequest req, HttpServletResponse resp) {
		String title = "";
		String content = "";
		
		UserVO user = (UserVO) req.getSession().getAttribute("user");

		BoardVO data = new BoardVO();
		data.setTitle(title);
		data.setContent(content);
		data.setWriter(user.getId());

		int res = BoardDAO.getIns().write(data);

		if (res != 1) {
			req.getSession().setAttribute("msg", "글쓰기중 오류 발생");
			return "write";
		} else {
			req.getSession().setAttribute("msg", "성공적으로 글 작성");
			return "redirect::/board";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
