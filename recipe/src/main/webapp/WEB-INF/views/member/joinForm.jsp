<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입</title>
		<script src="https://kit.fontawesome.com/487e3fe7a5.js" crossorigin="anonymous"></script>
		<script src="<c:url value='/js/jquery-3.6.1.min.js'/>"></script>
		<script src="<c:url value='/js/member/joinForm.js'/>"></script>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/member/joinForm.css"/>">
	</head>
	<body>
		<div style=" width :100px; height :100px;"></div>
		<form method="post" action="<c:url value="/member/insert" />" id="joinForm">
		<div id="contentBox">
			<div id="logo">
				<a href="<c:url value='/'/>"><i class="fa-solid fa-utensils"></i>자취요리</a>
			</div>
			<table rules="none">
				<tr style="height : 10px"></tr>
				<tr>
					<td>아이디</td>
					<td class="inputInf">
						<input type="text" id="memId" name="memId">
						<button type="button"  id="memIdBtn">중복 확인</button>
					</td>
				</tr>
				<tr class="infMes">
					<td></td>
					<td id="idMes"></td>
				</tr>
				
				<tr>
					<td>비밀번호<br>
						비밀번호 확인
					</td>
					<td class="inputInf">
						<input type="password" id="memPw" name="memPw"><br>
						<input type="password" id="memPwChk">
					</td>
				</tr>
				<tr class="infMes">
					<td></td>
					<td id="pwMes"></td>
				</tr>
				
				<tr>
					<td>이름</td>
					<td class="inputInf">
						<input type="text" id="memName" name="memName">
					</td>
				</tr>
				
				<tr>
					<td>별명</td>
					<td class="inputInf">
						<input type="text" id="memNick" name="memNick">
						<button type="button" id="memNickBtn">중복 확인</button>
					</td>
				</tr>
				<tr class="infMes">
					<td></td>
					<td id="nickMes"></td>
				</tr>
				
				<tr>
					<td>이메일</td>
					<td class="inputInf">
						<input type="hidden" name="memEmail" id="memEmail">
						<input type="text" id="memEmailF">
						@
						<input type="text" id="memEmailB">
						<button type="button" id="emailBtn">이메일 인증</button>
					</td>
				</tr>
				<tr class="infMes">
					<td></td>
					<td id="emailMes"></td>
				</tr>
			</table>
		</div>
		<div id="joinBox"><input type="submit" value="회원가입" id="joinBtn"></div>
		</form>
	</body>
</html>