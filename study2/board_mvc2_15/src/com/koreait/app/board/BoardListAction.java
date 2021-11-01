package com.koreait.app.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.action.Action;
import com.koreait.action.ActionForward;
import com.koreait.app.board.dao.BoardDAO;

public class BoardListAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		BoardDAO bdao = new BoardDAO();
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		
		String temp = request.getParameter("page");
		int page = temp == null ? 1:Integer.parseInt(temp);
		int pagesize = 10;
		int totalCnt = bdao.getBoardCnt();
		
		//10 :한페이지에 띄워줄 게시굴 갯수
		int endRow = page*10;
		int startRow = endRow-9;
		
		int startPage = ((page-1)/pagesize)*pagesize + 1;
		int endPage = startPage+(pagesize-1);
		int totalPage = (totalCnt-1)/pagesize+1;
		
		endPage = endPage>totalPage?totalPage:endPage;
		request.setAttribute("totalPage",totalPage);
		request.setAttribute("totalCnt", totalCnt);
		request.setAttribute("nowPage",page);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("boardList",bdao.getBoardList(startRow,endRow));
		
		forward.setRedirect(false);
		forward.setPath("/app/board/boardList.jsp");
		return forward;
	}
	
}
