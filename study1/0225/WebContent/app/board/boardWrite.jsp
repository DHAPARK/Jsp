<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/examples/media/expand_style.css">
</head>
<body>
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="900px">
		<tr align="right" valign="middle">
			<td>${session_id.getUserid()} 님 환영합니다.
			<a href="${pageContext.request.contextPath}/member/Memberlogout.me">로그아웃</a>
			</td>
		</tr>
	</table>
	<br>
	<hr>
	<form enctype="multipart/form-data" action="${pageContext.request.contextPath}/board/BoardWriteOk.bo" method="post" name="boardform">
		<table width="900px" border="0" cellspacing="0" cellpadding="0">
			<tr align="center" valign="middle">
				<td><h3>MVC 게시판</h3></td>
			</tr>
		</table>
		<table border="1" cellpadding="0" cellspacing="0" width="900px">
			<tr>
				<td>
					제목
				</td>												
				<td>
					<input type="text" name="boardtitle" size="50" maxlength="100">
				</td>												
			</tr>
			<tr>
				<td>
					글쓴이
				</td>
				<td>
					<input readonly name="userid" type="text"
					 size="10" value="${session_id.getUserid()}" maxlength="10">
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea name="boardcontents" style="width:700px;height:200px;resize:none"></textarea>
				</td>
			</tr>
			<tr>
				<td>파일첨부1</td>
				<td>
					<input type="file" name="file1">
					<input type="button" value="첨부삭제" onclick="cancleFile('file1')">
				</td>
			</tr>
			<tr>
				<td>파일첨부2</td>
				<td>
					<input type="file" name="file2">
					<input type="button" value="첨부삭제" onclick="cancleFile('file2')">
				</td>
			</tr>
		</table>
		<table>
			<tr align="center" valign="middle">
				<td>
					<a href="javascript:sendit()">[등록]</a>
					<a href="${pageContext.request.contextPath}/board/BoardList.bo">[목록]</a>
				</td>
			</tr>
		</table>
	</form>
</center>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://code.jquery.com/jquery-1.9.0.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.js"></script>
<script>
	function sendit(){
		//검사하기(제목 비어있는지,내용 비어있는지 등등)
		document.boardform.submit();
	}
	function cancleFile(filename){
		if($.browser.msie){
			//input[anme=file1]		input[name='file1']
			$('input[name="'+filename+'"]').replaceWith(('input[name="'+filename+'"]').clone(true));
		}else{
			$('input[name="'+filename+'"]').val("");
		}
	}
</script>
</body>
</html>