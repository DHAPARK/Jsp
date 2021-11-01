package com.koreait.app.member;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.ActionForward;
import com.koreait.app.member.dao.MemberBean;
import com.koreait.app.member.dao.MemberDAO;

public class MemberJoinOkAction {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward(); 
		//가입시키는 코드 구현
		MemberDAO mdao = new MemberDAO();
		MemberBean member = new MemberBean();
		member.setUserid(request.getParameter("userid"));
		member.setUserpw(request.getParameter("userpw"));
		member.setUsergender(request.getParameter("usergender"));
		member.setUserphone(request.getParameter("userphone"));
		member.setUsername(request.getParameter("username"));
		member.setUseraddr(request.getParameter("useraddr"));
		member.setUserpostcode(request.getParameter("userpostcode"));
		member.setUseraddrdetail(request.getParameter("useraddrdetail"));
		member.setUseraddretc(request.getParameter("useraddretc"));
		if(mdao.join(member)) {
			Cookie cookie = new Cookie("joinid",member.getUserid());
			cookie.setPath("/");
			response.addCookie(cookie);
			//성공시 응답 생성
			forward.setRedirect(true);
			//Redirect : setPath 를 사용할 경우 이전 root 경로도 없어지기 때문에
			//반드시 request.getContextPath()를 적어준다.
			//Forward : setPath 를 사용하며 "/"앞에 root 경로가 그대로 전달되기 때문에
			//Request.getContextPath()를 적어주면 안된다.
			forward.setPath(request.getContextPath()+"/app/member/loginview.jsp");
		}
		
		return forward;
	}
}
