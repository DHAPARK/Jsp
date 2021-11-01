package com.koreait.app.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.Action;
import com.koreait.action.ActionForward;

public class FileDownloadAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		String realName = request.getParameter("realname");
		String dwName = request.getParameter("filename");
		String saveFolder = request.getServletContext().getRealPath("app/files");
		String filename = saveFolder+"\\"+dwName;
		String client = "";
		InputStream is = null;
		OutputStream os = null;
		File file = null;
		PrintWriter out = response.getWriter();
		
		
		file = new File(filename);
		is = new FileInputStream(file);
		//다운받기위한 통로 열어줘!
		
		client = request.getHeader("User-Agent");
		response.reset();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Description", "JSP Generated Data");
		//파일이름이 뒤에 식별자로 xxxx_1 이렇게 붙어 저장되었을때가 있을것이다.
		//그렇다면 사용자도 원래파일명인 xxxx가 아닌 xxxx_1로 다운이 받아지게 된다.
		//그럼으로 처리가 필요하다
		try {
			try {
				dwName = URLEncoder.encode(realName,"UTF-8").replaceAll("\\+","%20");
				//dwName과 realName이 다르다면 realName이 dwName에 들어가진다.
				//즉 실제로 다르다면 try가 제대로 실행이 된다.
				//서로 이름이 같으면 예외 발생
			} catch (Exception e) {
				e.printStackTrace();
				//즉 여기로 들어와야한다.
				dwName = URLEncoder.encode(file.getName(),"UTF-8").replaceAll("\\+", "%20");
			}
			
			dwName = new String(dwName.getBytes("UTF-8"),"ISO-8859-1");
			//해석후에 진짜 이름설정이 끝남 이라고 하시는데 잘 모르겠다. 이제 다운로드 준비가 된것
			
			if(client.indexOf("MSIE") != -1) {
				response.setHeader("Content-Disposition", "attachment;fileName="+dwName);
			}else {
				//그 외 경우
				response.setHeader("Content-Disposition", "attachment;fileName="+dwName+"\"");
				response.setHeader("Content-Type", "application/octet-stream;charset=utf-8");
			}
			response.setHeader("Content-length", file.length()+"");
			out.flush();
			os = response.getOutputStream();
			
			byte b[] = new byte[(int)file.length()];
			int leng = 0;
			while((leng=is.read(b,0,b.length))!=-1) {
				os.write(b,0,leng);
			}
		} catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}finally {
			if(is != null) {
				is.close();
			}
			if(os != null) {
				os.close();
			}
			if(out != null) {
				out.close();
			}
		}
		return null;
	}
}
