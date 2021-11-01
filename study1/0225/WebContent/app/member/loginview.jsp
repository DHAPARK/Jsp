<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String check = request.getHeader("Cookie");
		String joinid = "";
		if(check != null){
			Cookie[] cookies = request.getCookies();
			for(Cookie c : cookies){
				if(c.getName().equals("joinid")){
					joinid = c.getValue();
				}				
			}
		}
		String result = request.getParameter("result");
		if(result != null && result.equals("false")){
			%>
			<script>alert("로그인 실패!")</script>
			<% 	
		}
	%>

	<form action="/member/MemberLoginOk.me" method="post" name="loginform">
	<label>아이디<input type="text" value="<%=joinid%>" name="userid" id="userid"></label>
	<label>비밀번호<input type="password" name="userpw" id="userpw"></label>
	<input type="button" value="로그인"onclick="sendit()">
	</form>
</body>
<script>
	function sendit(){
		const loginform = document.loginform;
		const userid = document.loginform.userid;
		const userpw = document.loginform.userpw;
		
		if(userid.value=='' || userid.value==null){
			alert("아이디를 입력하세요.");
			userid.focus();
			return false;
		}
		if(userpw.value=='' || userpw.value==null){
			alert("비밀번호를 입력하세요.");
			userpw.focus();
			return false;
		}
		loginform.submit();
	}
</script>