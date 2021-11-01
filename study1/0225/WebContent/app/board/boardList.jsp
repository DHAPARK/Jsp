<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${session_id eq null }">
		<script>
			alert("로그인 후 이용하세요!");
			location.replace("${pageContext.request.contextPath}/member/MemberLogin.me");
		</script>
	</c:if>
	<c:if test="${param.result == false}">
		<script>
			alert("게시글 등록 실패");
		</script>
	</c:if>
	<c:set var="list" value="${requestScope.boardList}"/>
	<c:set var="nowPage" value="${requestScope.nowPage}"/>
	<c:set var="startPage" value="${requestScope.startPage}"/>
	<c:set var="endPage" value="${requestScope.endPage }"/>
	<c:set var="totalCnt" value="${requestScope.totalCnt }"/>
	<c:set var="totalPage" value="${requestScope.totalPage }"/>
	<!-- 게시판 리스트 -->
	<table border="0" cellpadding="0" cellspacing="0" width="900px">
		<tr align="right" valign="middle">
			<td>${session_id.getUserid()} 님 환영합니다.
			<a href="${pageContext.request.contextPath}/member/Memberlogout.me">로그아웃</a>
			</td>
		</tr>
	</table>
	<!-- 게시판 리스트 -->
	<table border="0" cellpadding="0" cellspacing="0" width="900px">
		<tr align="center" valign="middle">
			<td><h3>MVC 게시판</h3></td>
		</tr>
		<tr align="right">
			<td>전체 글 갯수 : ${totalCnt}</td>
		</tr>		
	</table>
	
	<table border="1" cellspacing="0" cellpadding="0" width="900px">
		<tr align="center">
			<td width="5%">번호</td>
			<td width="50%">제목</td>
			<td width="17%">작성자</td>
			<td width="19">날짜</td>
			<td width="7%">조회수</td>
		</tr>
		<c:choose>
			<c:when test="${list != null and fn:length(list) > 0}">
				<c:forEach var="board" items="${list}">
					<tr align="center" valign="middle" onmouseover="this.style.backgroundColor='#e0f7fa'" onmouseout="this.style.backgroundColor=''">
						<td height="24px">${board.getBoardnum()}</td>
						<td><a href="${pageContext.request.contextPath}/board/BoardView.bo?num=${board.getBoardnum()}">
						${board.getBoardtitle()}
						</a></td>
						<td>${board.getUserid()}</td>
						<td>${board.getBoarddate()}</td>
						<td>${board.getBoardreadcount()}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr height="50px" align="center">
					<td colspan="5">등록된 게시물이 없습니다.</td>				
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<br>
	<table border="0" cellpadding="0" cellspacing="0" width="900px">
		<tr align="center" valign="middle">
			<td>
				<c:choose>
					<c:when test="${nowPage>1}">
						<a href="${pageContext.request.contextPath}/board/BoardList.bo?page=${nowPage-1}">[이전]</a>
					</c:when>
				</c:choose>
				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					<c:choose>
						<c:when test="${i==nowPage}">
							[${i}]
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath}/board/BoardList.bo?page=${i}">[${i}]</a>
						</c:otherwise>
					</c:choose>
					
				</c:forEach>
				<c:if test="${nowPage<totalPage }">
					<a href="${pageContext.request.contextPath}/board/BoardList.bo?page=${nowPage+1}">[다음]</a>
				</c:if>
			</td>
		</tr>
	
	</table>
	
	<table border="0"cellpadding="0" cellspacing="0" width="900px">
		<tr align="right" valign="middle">
			<td>
				<a href="${pageContext.request.contextPath}/board/BoardWrite.bo">[글쓰기]</a>
			</td>
		</tr>
	</table>
	
	
</body>
</html>