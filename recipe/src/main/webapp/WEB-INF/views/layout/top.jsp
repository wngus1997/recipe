<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="https://kit.fontawesome.com/487e3fe7a5.js" crossorigin="anonymous"></script>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/layout/top.css"/>">
		<script src="<c:url value='/js/layout/top.js'/>"></script>
	</head>
	<body>
		<div id="top_wrap">
			<div id="top_box">
				<div id="top_logo"><a href="<c:url value='/'/>"><i class="fa-solid fa-utensils"></i>자취요리</a></div>
				
				<div id="top_myIcon">
					<div id="top_myIcon_box">
						<c:if test="${empty sessionScope.sid}">
							<a onclick="loginAlert()" style="cursor : pointer"><i class="fa-solid fa-pen-to-square"></i></a>
						</c:if>
						<c:if test="${not empty sessionScope.sid}">
							<a href="<c:url value='/recipe/insertForm'/>"><i class="fa-solid fa-pen-to-square"></i></a>
						</c:if>
						<a><label for="top_myMenuBox" id="top_userIcon"><i class="fa-solid fa-circle-user"></i></label></a>
					</div>
					<input type="checkbox" id="top_myMenuBox">
					<div id="top_userMenuBox">
						<ul>
							<c:if test="${empty sessionScope.sid}">
								<li><a href="<c:url value='/member/loginForm'/>">로그인</a></li>
								<li><a href="<c:url value='/member/joinForm'/>">회원가입</a></li>
							</c:if>
							
							<!-- 로그인 성공 후 보여줄 메뉴 항목 -->
							<c:if test="${not empty sessionScope.sid}">
								<li><a href="<c:url value='/member/logout'/>">로그아웃</a></li>
								<hr>
								<li><a href="<c:url value='/board/noticeboardlist'/>">공지사항</a></li>
								<li><a href="<c:url value='/map'/>">지도</a></li>
								<li><a href="<c:url value='/mypage'/>">마이페이지</a></li>
								<li><a href="<c:url value='/rental/rentalreg/${sessionScope.sid}'/>">공간등록</a></li>
								<li><a href="<c:url value='/boxList'/>">박스구매</a></li>
								<li><a href="<c:url value='/board/inquiryboardlist'/>">문의사항</a></li>
							</c:if>
						</ul>
					</div>
					<label for="top_myMenuBox" id="top_myMenuBox_all"></label>
				</div>
			</div>
			<div id="top_box1">
				<ul>
					<li><a><span>레시피</span></a></li><!-- Url에 따라서 또는 스프링 컨텍스트 활용-->
					<li><a>공사중</a></li>
				</ul>
			</div>
			<div style="border-bottom : 3px solid var(--main-color);"></div>
		</div>
	</body>
</html>