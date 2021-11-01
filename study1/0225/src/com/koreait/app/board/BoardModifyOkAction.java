package com.koreait.app.board;

import java.io.File;
import java.util.List;

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

public class BoardModifyOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		BoardBean board = new BoardBean();
		BoardDAO bdao = new BoardDAO(); 
		FileDAO fdao = new FileDAO();
		
		ActionForward forward = new ActionForward();
		String saveFolder = request.getServletContext().getRealPath("app/files");
		int size = 1024*1024*5;//5MB
		MultipartRequest multi = new MultipartRequest(request,saveFolder,size,"UTF-8",new DefaultFileRenamePolicy());

		int boardnum = Integer.parseInt(multi.getParameter("boardnum"));
		board.setBoardnum(Integer.parseInt(multi.getParameter("boardnum")));
		board.setBoardtitle(multi.getParameter("boardtitle"));
		board.setBoardcontents(multi.getParameter("boardcontents"));
		board.setUserid(multi.getParameter("userid"));
		boolean bcheck = bdao.modifyBoard(board);
		
		
		String[] filename = {
			multi.getFilesystemName("file1"),
			multi.getFilesystemName("file2")
		};

		
		String[] realname = {
			multi.getOriginalFileName("file1"),
			multi.getOriginalFileName("file2")
		};
		
		List<FileBean> files = fdao.getDetail(boardnum);
		String[] filenames = multi.getParameterValues("filename");
		
		int cnt=filenames.length;

		for(int i=0;i<cnt;i++) {
			if(filename[i]==null) {
				continue;
			}
			
			if(files.size()!=0) {
				File f = new File(saveFolder,files.get(i).getFilename());
				if(f.exists()) {
					f.delete();
				}
			}
			fdao.deleteFileByName(files.get(i).getFilename());
			FileBean newfile = new FileBean();
			newfile.setBoardnum(boardnum);
			newfile.setFilename(filename[i]);
			newfile.setRealname(realname[i]);
			fdao.insertFiles(newfile);
		}
		
		forward.setRedirect(true);
		forward.setPath(request.getContextPath()+"/board/BoardView.bo?num="+boardnum);
		return forward;
	}
}
