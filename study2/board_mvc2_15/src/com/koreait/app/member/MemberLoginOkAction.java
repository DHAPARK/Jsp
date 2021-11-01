package com.koreait.app.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.action.Action;
import com.koreait.action.ActionForward;
import com.koreait.app.member.dao.MemberBean;
import com.koreait.app.member.dao.MemberDAO;

public class MemberLoginOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		MemberDAO mdao = new MemberDAO();
		HttpSession session = request.getSession();
		String userid = request.getParameter("userid");
		String userpw = request.getParameter("userpw");
		MemberBean member = mdao.login(userid,userpw);
		if(member == null) {
			forward.setPath("/app/member/loginview.jsp?result=false");
		}else {
			session.setAttribute("session_id", member);
			forward.setPath("/board/BoardList.bo");
		}
		forward.setRedirect(true);
		return forward;
	}
}






