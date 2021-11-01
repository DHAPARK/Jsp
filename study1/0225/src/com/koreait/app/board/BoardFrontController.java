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
       
    public BoardFrontController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
							//이게 우리가 요청보낸 URI
		String contextPath = request.getContextPath();
							//앞의 경로?
		//uri는 우리가 보낸 요청 자체를 뜻하고, contextPath는 localhost8085같은 앞부분인듯
		String command = requestURI.substring(contextPath.length());
		ActionForward forward = null;
		
		if(command.equals("/board/BoardList.bo")) {
			try {
				forward = new BoardListAction().execute(request, response);
			} catch (Exception e) {
				System.out.println("/board/BoardList 오류");
			}
		}else  if(command.equals("/board/BoardWrite.bo")){
			forward = new ActionForward();
			forward.setPath("/app/board/boardWrite.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/board/BoardWriteOk.bo")) {
			try {
				forward = new BoardWriteOkAction().execute(request, response);
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("/board/BoardWriteOk.bo에서 오류");
			}
			
		}else if(command.equals("/board/BoardView.bo")){
			//new BoardViewAction()
			try {
				forward = new BoardViewAction().execute(request, response);
			} catch (Exception e) {
				System.out.println("board/boardView 오류");
				e.printStackTrace();
			}
		}else if(command.equals("/board/FileDownload.bo")){
			try {
				forward = new FileDownloadAction().execute(request, response);
			} catch (Exception e) {
				System.out.println("/board/FileDownload.bo 오류");
				e.printStackTrace();
			}
		}else if(command.equals("/board/BoardModify.bo")) {
			try {
				forward = new BoardModifyAction().execute(request, response);
			} catch (Exception e) {
				System.out.println("/board/BoardModify.bo에서 오류");
				e.printStackTrace();
			}
		}else if(command.equals("/board/BoardModifyOk.bo")) {
			try {
				forward = new BoardModifyOkAction().execute(request, response);
			} catch (Exception e) {
				System.out.println("board/BoardModifyOk.bo에서 오류");
				e.printStackTrace();
			}
		}else if(command.equals("/board/BoardDelete.bo")) {
			try {
				forward = new BoardDeleteAction().execute(request, response);
			} catch (Exception e) {
				System.out.println("/board/BoardReplyModifyOk 오류");
				System.out.println(e);
				e.printStackTrace();
			}
		}else if(command.equals("/board/BoardReplyOk.bo")) {
			try {
				forward = new BoardReplyOkAction().execute(request, response);
			} catch (Exception e) {
				System.out.println("/board/BoardReplyOk 오류");
				e.printStackTrace();
			}
		}else if(command.equals("/board/BoardReplyModify.bo")) {
			try {
				forward = new BoardReplyModifyOkAction().execute(request, response);
			} catch (Exception e) {
				System.out.println("/board/BoardReplyModifyOk 오류");
				e.printStackTrace();
			}
		}else if(command.equals("/board/BoardReplyDeleteOk.bo")){
			try {
				forward = new BoardReplyDeleteOkAction().execute(request, response);
			} catch (Exception e) {
				System.out.println("/board/BoardReplyDeleteOk.bo에서 오류발생");
				e.printStackTrace();
			}
		}else if(command.equals("/board/BoardDelete.bo")){
			try {
				forward = new BoardDeleteAction().execute(request, response);
			} catch (Exception e) {
				System.out.println("/board/BoardDelete.bo에서 오류");
				e.printStackTrace();
			}
		}else {
			forward = new ActionForward();
			forward.setPath("/app/error/404.jsp");
			forward.setRedirect(false);
		}
		//execute()에서 리턴받은 forward로 응답 설정 후 응답하기
		if(forward != null) {
			if(forward.isRedirect()) {
				//redirect 방식
				response.sendRedirect(forward.getPath());
			}else {
				//forward 
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
}
