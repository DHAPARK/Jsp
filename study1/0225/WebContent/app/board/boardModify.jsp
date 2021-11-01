<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	.flabel{
		cursor:pointer;
		border:1px solid block;
	}
	.finput{
		position: absolute;
		width:1px;
		height:1px;
		padding:0;
		margin:-1px;
	}
</style>

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
	<c:set var="board" value="${requestScope.board}"></c:set>
	<c:set var="files" value="${requestScope.files }"/> 
	
	<form enctype="multipart/form-data" action="${pageContext.request.contextPath}/board/BoardModifyOk.bo" method="post" name="boardform">
		<input type="hidden" name="boardnum" value="${board.getBoardnum()}">
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
					<input value="${board.getBoardtitle()}"type="text" name="boardtitle" size="50" maxlength="100">
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
					<textarea name="boardcontents" style="width:700px;height:200px;resize:none">${board.getBoardcontents()}</textarea>
				</td>
			</tr>
			<c:choose>
				<c:when test="${files != null }">
					<c:forEach var="i" begin="0" end="1">
						<tr>
							<td>첨부파일${i+1}</td>
							
							<td>
							<c:choose>
								<c:when test="${i<files.size()}">
									<p class="filename" name="filename${i+1}" id="file${i+1}">${files.get(i).getRealname()}</p>
								</c:when>
								<c:otherwise>
									<p class="filename" name="filename${i+1}" id="file${i+1}"></p>
								</c:otherwise>
							</c:choose>
							<label class="flabel" for="fileInput${i+1}">첨부하기</label>
							<input class="finput" name="file${i+1}" id="fileInput${i+1}" type="file">
							<!-- <input type="file"/> -->
							<input type="hidden" name="filename">
							</td>
						
						
						</tr>
					</c:forEach>				
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="2">첨부파일이 없습니다.</td>
					</tr>
				</c:otherwise>
			
			</c:choose>
		</table>
		<table>
			<tr align="center" valign="middle">
				<td>
					<a href="javascript:sendit()">[수정완료]</a>
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
	$(document).ready(function(){
		$('.finput').change(function(e){
			$(this).prev().prev().text(e.target.files[0].name);
			$(this).next().val(e.target.files[0].name);
		});
	});
	
	
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