<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" http-equiv="Content-Type" content="text/html">
<title>Insert title here</title>
</head>
<body>
	
	<form action="/member/MemberJoinOk.me" method="post" name="joinform">
		<p>
			<span style="color:red;" id="checkText"></span><br>
			<label>아이디 <input onkeyup="checkId();" type="text" name="userid"></label>
		</p>
		<p>
			<label>비밀번호 <input type="password" name="userpw"></label>
		</p>
		<p>
			<label>이름 <input type="text" name="username"></label>
		</p>
		<p>
			성별<br>
			<label>남 <input name="usergender" type="radio" value="m" checked></label>
			<label>여 <input name="usergender" type="radio" value="w"></label>
		</p>
		<p>
			<label>핸드폰번호 <input type="text" name="userphone"></label>
		</p>
		<p>
			<input type="text" name="userpostcode" id="sample6_postcode" placeholder="우편번호">
			<input type="button" name="findaddr" onclick="sample6_execDaumPostcode();" value="우편번호 찾기"><br>
			<input type="text" name="useraddr" id="sample6_address" placeholder="주소"><br>
			<input type="text" name="useraddrdetail" id="sample6_detailAddress" placeholder="상세주소">
			<input type="text" name="useraddretc" id="sample6_extraAddress" placeholder="참고항목">
		</p>
		
		<input type="button" value="회원가입" onclick="sendit();">
	</form>
</body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="./user.js"></script>
</html>













