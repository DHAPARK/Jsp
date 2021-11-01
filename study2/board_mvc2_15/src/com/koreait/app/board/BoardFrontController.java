package com.koreait.app.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.ActionForward;
import com.koreait.app.member.CheckIdAction;
import com.koreait.app.member.MemberJoinOkAction;
import com.koreait.app.member.MemberLoginOkAction;

@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		ActionForward forward = null;
		
		if(command.equals("/board/BoardList.bo")) {
			try {
				forward = new BoardListAction().execute(request, response);
			} catch (Exception e) {
				System.out.println("/board/BoardList 오류");
			}
		}else {
			forward = new ActionForward();
			forward.setPath("/app/error/404.jsp");
			forward.setRedirect(false);
		}
		
		//execute()에서 리턴받은 forward로 응답 설정 후 응답하기
		if(forward!=null) {
			if(forward.isRedirect()) {
				//redirect 방식
				response.sendRedirect(forward.getPath());
			}else {
				//forward 방식
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
	}
}
