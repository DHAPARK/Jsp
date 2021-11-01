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
import com.koreait.app.board.dao.ReplyBean;
import com.koreait.app.board.dao.ReplyDAO;

public class BoardViewAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		BoardDAO bdao = new BoardDAO();
		FileDAO fdao = new FileDAO();
		
		ReplyDAO rdao = new ReplyDAO();
		
		int boardnum = Integer.parseInt(request.getParameter("num"));
		bdao.updateReadCount(boardnum);
		BoardBean board = bdao.getDetail(boardnum);
		List<FileBean> files = fdao.getDetail(boardnum);
		List<ReplyBean> replys = rdao.getDetail(boardnum);
		
		
		if(board != null) {
			request.setAttribute("board", board);
			if(files!=null) {
				request.setAttribute("files", files);
			}
			request.setAttribute("replyList", replys);
			ActionForward forward = new ActionForward();
			forward.setPath("/app/board/boardView.jsp");
			forward.setRedirect(false);
			return forward;
		}
		return null;
	}
}
