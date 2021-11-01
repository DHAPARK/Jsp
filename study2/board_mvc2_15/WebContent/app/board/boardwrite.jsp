<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
	<table border="0" cellpadding="0" cellspacing="0" width="900px">
		<tr align="right" valign="middle">
			<td>${session_id.getUserid()}님 환영합니다.<a href="${pageContext.request.contextPath}/member/Memberlogout.me">로그아웃</a></td>
		</tr>
	</table>
	<br>
	<hr>
	<form enctype="multipart/form-data" action="${pageContext.request.contextPath}/board/BoardWriteOk.bo" method="post" name="boardform">
		<table width="900px" border="0" cellspacing="0" cellpadding="0">
			<tr align="center" valign="middle">
				<td><h3>MVC게시판</h3></td>
			</tr>
		</table>
		
		<table border="1" cellspacing="0" cellpadding="0" width="900px">
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
					<input readonly name="userid" type="text" size="10" value="${session_id.getUserid()}" maxlength="10">
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea name="boardcontents" style="width:700px;height:200px;"></textarea>
				</td>
			</tr>
			<tr>
				<td>파일 첨부1</td>
				<td>
					<input type="file" name="file1">
					<input type="button" value="첨부삭제" style="display:hidden">
				</td>
			</tr>
			<tr>
				<td>파일 첨부2</td>
				<td>
					<input type="file" name="file2">
					<input type="button" value="첨부삭제" style="display:hidden">
				</td>
			</tr>
		</table>
	</form>
</center>
</body>
</html>