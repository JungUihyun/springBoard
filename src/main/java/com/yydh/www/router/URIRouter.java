package com.yydh.www.router;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yydh.www.controller.BoardController1;
import com.yydh.www.controller.Controller1;
import com.yydh.www.controller.DeleteController;
import com.yydh.www.controller.LoginController;
import com.yydh.www.controller.LogoutController;
import com.yydh.www.controller.ModifyController;
import com.yydh.www.controller.RegisterController;
import com.yydh.www.controller.ViewController;
import com.yydh.www.controller.WriteController;

public class URIRouter extends HttpServlet {
	
	private Map<String, Controller1> urlMap = new HashMap<>();
	
	@Override
	public void init() throws ServletException {
		urlMap.put("/", new LoginController());
		urlMap.put("/register", new RegisterController());
		urlMap.put("/board", new BoardController1());
		urlMap.put("/logout", new LogoutController());
		urlMap.put("/board/write", new WriteController());
		urlMap.put("/board/view", new ViewController());
		urlMap.put("/board/delete", new DeleteController());
		urlMap.put("/board/modify", new ModifyController());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		
		String uri = req.getRequestURI(); // Request uri
		String context = req.getContextPath();
		
		uri = uri.substring(context.length());
		
		Controller1 c = urlMap.get(uri);
		
		String view = null;
		try {
			view = c.service(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			view = "notfound";
		}
		
		// Redirect setting
		if(view.startsWith("redirect::")) {
			String target = view.substring("redirect::".length());
			resp.sendRedirect(target);
		}else {
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/" + view + ".jsp");
			rd.forward(req, resp);
		}
		
	}
	
	
}
