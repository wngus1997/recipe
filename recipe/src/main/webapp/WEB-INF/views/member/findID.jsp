<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>아이디 찾기</title>
		<script src="<c:url value='/js/jquery-3.6.1.min.js'/>"></script>
		<script src="<c:url value='/js/member/findID.js'/>"></script>
		<style>
			:root{
				--main-color : #ffa477;
			}
			body, html{
				margin : 0;
				padding : 0;
			}
			#wrap{
				width : 100%;
				background : #F1F1F2;
				padding-top : 10px;
			}
			form{
				width : 1280px;
				margin : auto;
			}
			#bottomBox a{
				margin : auto;
				font-size : 20px;
				text-decoration : none;
				color : var(--main-color);
			}
			.inputOne{
				display : flex;
				padding-left : 300px;
			}
			.inputBox{
				display : flex;
				flex-direction: column;
			}
			input{
				height : 30px;
				font-size : 20px;
			}
			button{
				width : 100px;
				margin-left : 10px;
			}
		</style>
	</head>
	<body>
		<c:import url="/WEB-INF/views/layout/top.jsp" />
		<div id="wrap">
			<form method="post" action="<c:url value="/findID" />">
				<div>
					<h3>아이디 찾기</h3>
				</div>
				<div class="inputOne">
					<div class="inputBox">
						<div>이메일 : <input type="text" name="memEmail" id="memEmail"></div>
					</div>
					<button type="button" id="findIDBtn">아이디찾기</button>
				</div>
			</form>
			<hr style="margin-top : 100px;">
			<form method="post" action="<c:url value="/member/insert" />">
				<div>
					<h3>비밀번호 찾기</h3>
				</div>
				<div class="inputOne">
					<div class="inputBox">
						<div>아이디 : <input type="text" name="memId" id="memId"></div>
						<div style="margin-top : 5px;">이메일 : <input type="text" name="memEmail2" id="memEmail2"></div>
					</div>
					<button type="button" id="findPWBtn">비밀번호찾기</button>
				</div>
			</form>
			<hr style="margin-top : 10px;">
			<div style="width : 100%; text-align : center; padding-bottom : 300px;" id="bottomBox">
				<div style="width : 300px; height : 50px; background : white; margin : auto; display : flex; align-items : center;
						border-radius : 5px; border : 1px solid #ffa477; margin-top : 30px;">
					<a href="<c:url value='/member/loginForm' />">로그인 바로가기</a>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/layout/bottom.jsp" />
	</body>
</html>