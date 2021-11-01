package com.koreait.app.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.Action;
import com.koreait.action.ActionForward;
import com.koreait.app.board.dao.BoardBean;
import com.koreait.app.board.dao.BoardDAO;
import com.koreait.app.board.dao.FileBean;
import com.koreait.app.board.dao.FileDAO;

public class BoardModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		BoardDAO bdao = new BoardDAO();
		FileDAO fdao= new FileDAO(); 
		
		int boardnum = Integer.parseInt(request.getParameter("num"));
		BoardBean board = bdao.getDetail(boardnum);
		List<FileBean> files = fdao.getDetail(boardnum);
		
		request.setAttribute("board",board);
		request.setAttribute("files", files);
		forward.setRedirect(false);
		forward.setPath("/app/board/boardModify.jsp");
		
		return forward;
	}

}
