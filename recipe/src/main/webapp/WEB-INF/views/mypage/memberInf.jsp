<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/mypage/memberInf.css"/>">
		<script src="https://kit.fontawesome.com/487e3fe7a5.js" crossorigin="anonymous"></script>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
	</head>
	<c:import url="/WEB-INF/views/layout/top.jsp" />
	<c:import url="/WEB-INF/views/layout/sideMenu.jsp" />
	<body>
		<div id="wrap">
			<div id="infoBox">
				<div id="title">회원정보수정</div>
				<div id="contentBox">
					<div id="profileBox">
						<div class="subtitle">프로필 변경</div>
						<div id="profile">
							<div id="profileImgBox">
								<div id="profileImg"><i class="fa-regular fa-user"></i></div>
								<input type="file" id="profileImgInf" class="hidden">
								<label for="profileImgInf" id="profileLabel">사진 변경</label>
							</div>
							<div id="nickNameBox">
								<div>닉네임</div>
								<input type="text"><button type="button" id="nickCheck">중복확인</button>
							</div>
						</div>
						<div id="profileBtnBox">
							<button type="button" id="profileBtn">적용</button>
						</div>
					</div>
					
					<div id="passwordBox">
						<div class="subtitle">비밀번호 변경</div>
						<div>
							<div class="pwBox">
								<div>현재 비밀번호</div>
								<input type="password" placeholder="현재 비밀번호">
							</div>
							<div class="pwBox">
								<div>새 비밀번호</div>
								<input type="password" placeholder="새 비밀번호">
							</div>
							<div class="pwBox">
								<div>새 비밀번호 확인</div>
								<input type="password" placeholder="새 비밀번호 확인">
							</div>
						</div>
						<div id="pwBtnBox">
							<button type="button" id="pwUpdateBtn">수정하기</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
	<%-- <c:import url="/WEB-INF/views/layout/bottom.jsp" /> --%>
</html>