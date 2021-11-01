package com.koreait.app.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.koreait.action.Action;
import com.koreait.action.ActionForward;
import com.koreait.app.board.dao.ReplyBean;
import com.koreait.app.board.dao.ReplyDAO;
import com.koreait.app.member.dao.MemberBean;

public class BoardReplyOkAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		
		ReplyDAO rdao = new ReplyDAO();
		
		int boardnum = Integer.parseInt(request.getParameter("num"));
		String replycontents = request.getParameter("replycontents");
		String userid = ((MemberBean)request.getSession().getAttribute("session_id")).getUserid();
		
		ReplyBean reply = new ReplyBean();
		reply.setBoardnum(boardnum);
		reply.setReplycontents(replycontents);
		reply.setUserid(userid);
		if(rdao.insertReply(reply)) {
			forward.setRedirect(true);
			forward.setPath(request.getContextPath()+"/board/BoardView.bo?num="+boardnum);
			return forward;
		}
		
		return null;
	}
	
}
