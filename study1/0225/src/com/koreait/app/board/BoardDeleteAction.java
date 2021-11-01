package com.koreait.app.board;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.Action;
import com.koreait.action.ActionForward;
import com.koreait.app.board.dao.BoardDAO;
import com.koreait.app.board.dao.FileBean;
import com.koreait.app.board.dao.FileDAO;
import com.koreait.app.board.dao.ReplyDAO;

public class BoardDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		BoardDAO bdao = new BoardDAO();
		FileDAO fdao = new FileDAO();
		ReplyDAO rdao = new ReplyDAO();
		ActionForward forward = new ActionForward();
		
		String saveFolder = request.getServletContext().getRealPath("app/files");
		int boardnum = Integer.parseInt(request.getParameter("num"));
		
		List<FileBean> files = fdao.getDetail(boardnum);
		for(FileBean file: files) {
			File f= new File(saveFolder,file.getFilename());
			if(f.exists()) {
				f.delete();
			}
		}
		fdao.deleteFiles(boardnum);
		rdao.deleteAll(boardnum);
		bdao.delete(boardnum);
		
		forward.setRedirect(true);
		forward.setPath(request.getContextPath()+"/board/BoardList.bo");
		return forward;
	}
}
