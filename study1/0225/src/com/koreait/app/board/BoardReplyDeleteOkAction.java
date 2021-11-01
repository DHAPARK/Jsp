package com.koreait.app.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.Action;
import com.koreait.action.ActionForward;
import com.koreait.app.board.dao.ReplyDAO;

public class BoardReplyDeleteOkAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		ReplyDAO rdao = new ReplyDAO();
		
		int replynum = Integer.parseInt(request.getParameter("replynum"));
		int boardnum = Integer.parseInt(request.getParameter("boardnum"));
		
		rdao.deleteReply(replynum);
		forward.setRedirect(true);
		forward.setPath(request.getContextPath()+"/board/BoardView.bo?num="+boardnum);
		
		return forward;
	}
	
}
