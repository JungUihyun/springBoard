package com.yydh.www.controller;

import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yydh.www.model.BoardDAO;
import com.yydh.www.vo.BoardVO;

public class ModifyController implements Controller1 {
	@Override
	public String service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if(req.getMethod().equalsIgnoreCase("get")) {
			int id = Integer.parseInt(req.getParameter("id"));
			BoardVO data = BoardDAO.getIns().view(id);
			req.setAttribute("data", data);
			
			return "modify";
		}
		
		if(req.getMethod().equalsIgnoreCase("post")) {
			int id = Integer.parseInt(req.getParameter("id"));
			String title = req.getParameter("title");
			String content = req.getParameter("content");
//			Part filePart = req.getPart("file");
			String filename = null;

//			if (!Paths.get(filePart.getSubmittedFileName()).getFileName().toString().equals("")) {
//				filename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//				filePart.write(req.getServletContext().getRealPath("/WEB-INF/upload" + filename));
//			} else {
//				filename = "";
//			}
			
			BoardVO board  = new BoardVO();
			board.setId(id);
			board.setTitle(title);
            board.setContent(content);
            board.setFiles(filename);
            
            int rowCount = BoardDAO.getIns().modify(board);
            
            if(rowCount > 0) {
            	return "redirect::/board/view?id=" + id;
            } else {
            	return "redirect::/board";
            }
            
		}
		return "board";
	}
}
