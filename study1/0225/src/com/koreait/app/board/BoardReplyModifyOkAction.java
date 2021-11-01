package com.koreait.app.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.Action;
import com.koreait.action.ActionForward;
import com.koreait.app.board.dao.ReplyDAO;

public class BoardReplyModifyOkAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		ReplyDAO rdao = new ReplyDAO();
		ActionForward forward = new ActionForward();
		
		int replynum = Integer.parseInt(request.getParameter("replynum"));
		int boardnum = Integer.parseInt(request.getParameter("boardnum"));
		String i = request.getParameter("num");
		String replycontents = request.getParameter("num");
		rdao.modifyReply(replynum,replycontents);
		forward.setRedirect(true);
		forward.setPath(request.getContextPath()+"/board/BoardView.bo?num="+boardnum);
		
		return forward;
	}

}
