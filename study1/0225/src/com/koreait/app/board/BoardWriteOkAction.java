package com.koreait.app.board;

import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.Action;
import com.koreait.action.ActionForward;
import com.koreait.app.board.dao.BoardBean;
import com.koreait.app.board.dao.BoardDAO;
import com.koreait.app.board.dao.FileBean;
import com.koreait.app.board.dao.FileDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardWriteOkAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		//files폴더의 실제 경로를 찾아주는 메서드들
		String saveFolder = request.getServletContext().getRealPath("app/files");
		
		int size = 1024 * 1024 * 5; //5MB
		BoardDAO bdao = new BoardDAO();
		FileDAO fdao = new FileDAO();
		boolean bcheck = false;
		boolean fcheck1 = false;
		boolean fcheck2 = false;
		//게시글 내용들 DB에 넣기
		BoardBean board = new BoardBean();
		MultipartRequest multi = new MultipartRequest(request,saveFolder,size,"UTF-8",new DefaultFileRenamePolicy());
		board.setBoardtitle(multi.getParameter("boardtitle"));														//저장자리  //파일크기 //인코딩 //네이밍처리?
		board.setBoardcontents(multi.getParameter("boardcontents"));
		board.setUserid(multi.getParameter("userid"));
		//그니까 aron이란 이름의 파일을
		//2개 3개 10개 저장하면 그 aron파일을 구분하지를 못한다 근니까 그 뒤에 숫자를 붙여주는식의 처리 방식이 필요하다 그래서 0308 메모장에 적힌 라이브러리가 필요하다
		//파일 내용 저장하기
		
		//bdao.insertBoard(board);
		bcheck = bdao.insertBoard(board);
		
		//Enumeration<String> files =  multi.getFileNames();
		//while(files.hasMoreElements()) {
			//String filename = files.nextElement();
		//}
		String filename1 = multi.getFilesystemName("file1");
		String orgname1 = multi.getOriginalFileName("file1");
		String filename2 = multi.getFilesystemName("file2");
		String orgname2 = multi.getOriginalFileName("file2");
		if(filename1 == null) {fcheck1 = true;}
		if(filename2 == null) {fcheck2 = true;}
		
		int boardnum = bdao.getBoardSeq();
		//if(filename1 != null && filename1 !="") {
			//FileBean file = new FileBean();
			//file.setBoardnum(boardnum);
			//file.setFilename(filename1);
			//file.setRealname(orgname1);
			//fdao.insertFiles(file);
			//fcheck1 = fdao.insertFiles(file);
		//}
		if((filename1 != null && filename1 !="") || (filename2 != null && filename2 !="")) {
			FileBean file = new FileBean();
			if(filename1 != null && filename1 != "") {
				file.setBoardnum(boardnum);
				file.setFilename(filename1);
				file.setRealname(orgname1);
				fcheck1 = fdao.insertFiles(file);
			}
			if(filename2 != null && filename2 != "") {
				file.setBoardnum(boardnum);
				file.setFilename(filename2);
				file.setRealname(orgname2);
				fcheck2 = fdao.insertFiles(file);
			}
		}
		
		if(!bcheck || !fcheck1 || !fcheck2) {
			forward.setRedirect(true);
			forward.setPath(request.getContextPath()+"/board/BoardList.bo?result=false");
			return forward;
		}
		forward.setRedirect(true);
		forward.setPath(request.getContextPath()+"/board/BoardList.bo");
		return forward;
	}
}
