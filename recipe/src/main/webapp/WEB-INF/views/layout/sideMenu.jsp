<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/layout/sideMenu.css"/>">
		<script src="https://kit.fontawesome.com/487e3fe7a5.js" crossorigin="anonymous"></script>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
	</head>
	<body>
		<div id="side_wrap">
			<div id="side_sideBox">
				<div>마이페이지</div>
				<a href="<c:url value='/mypage/meminf'/>"><i class="fa-regular fa-user"></i>&nbsp;회원정보수정</a>
				<a href="<c:url value='/mypage/meminf'/>"><i class="fa-regular fa-note-sticky"></i>&nbsp;레시피관리</a>
				<a href="<c:url value='/mypage/meminf'/>"><i class="fa-regular fa-star"></i>&nbsp;즐겨찾기</a>
				<a href="<c:url value='/chat'/>"><i class="fa-regular fa-comment"></i>&nbsp;채팅하기</a>
			</div>
		</div>
	</body>
</html>