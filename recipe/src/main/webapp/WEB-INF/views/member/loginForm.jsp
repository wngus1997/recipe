<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>로그인</title>
		<script src="https://kit.fontawesome.com/487e3fe7a5.js" crossorigin="anonymous"></script>
		<script src="<c:url value='/js/jquery-3.6.1.min.js'/>"></script>
		<script src="<c:url value='/js/member/login.js'/>"></script>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/member/loginForm.css"/>">
	</head>
	<body>
	
		<div id="memMesBox">아이디를 입력해주세요</div>
		<div style=" width :100px; height :100px;"></div>
		<form id="loginForm" method="post" action="<c:url value="/member/login" />">
		
		<div id="contentBox">
			<div id="logo">
				<a href="<c:url value='/'/>"><i class="fa-solid fa-utensils"></i>자취요리</a>
			</div>
			
			
			<div id="loginBox">
				<div id="loginBox_top"></div><!-- 위에 빈공간 -->
				<div id="loginBox_mid">
					<div>
		 				<input type="text" id="memId" placeholder="아이디">
						<input type="password" id="memPw" placeholder="비밀번호">
					</div>
					<div>
						<div id="loginBox_login">
							<button id="loginBtn">로그인</button>
						</div>
					</div>
				</div ><!-- 로그인 비밀번호 -->
			</div>
			<div id="loginBox_bot">
				<a href="<c:url value='/member/findIDFrom'/>">아이디찾기</a>
				|
				<a href="<c:url value='/member/findPWFrom'/>">비밀번호찾기</a>
				|
				<a href="<c:url value='/member/joinForm'/>">회원가입</a>
			</div>
		</div>
		
		</form>
	</body>
</html>