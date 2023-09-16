<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/chat/chatAllList.css"/>">
		<script src="<c:url value='/js/jquery-3.6.1.min.js'/>"></script>
		<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
		<script src="<c:url value='/js/chat/chatAllList.js'/>"></script>
	</head>
	<body>
		<c:import url="/WEB-INF/views/layout/top.jsp" />
		<c:import url="/WEB-INF/views/layout/sideMenu.jsp" />
		<div id="wrap">
			<div id="top">
			</div>
			<div style="width : 100%;">
				<form method="post" action="<c:url value='/chat/create'/>"
				style="margin-left : 200px; display : flex; align-items : center;">
					자신 아이디(실험용)<input type="text" name="memId" id="memId">
					상대 닉네임 : <input type="text" name="memNick" style="height : 30px; width : 200px; font-size : 20px;">
					<input type="submit" value="채팅방 만들기">
				</form>
			</div>
			<div id="chatBox">
				<div id="roomList">
					<c:forEach var="room" items="${roomAllList}">
						<input type="radio" name="roomSelect" class="hidden" id="${room.roomNo }">
						<label for="${room.roomNo}" class="roomName">
							<div>${room.op}</div>
						</label>
					</c:forEach>
				</div>
				<div id="chatOneBox">
					<div id="textBox">
					</div>
					<div id="inputBox">
						<div><textarea name="message" onkeydown="resize(this)" row="1" onkeyup="resize(this)" id="textarea"></textarea></div>
						<div><button type="button" id="submitBtn">전송</button></div>
					</div>
				</div>
				<button type="button" id="chatDel" style="height : 50px;">나가기</button>
			</div>
		</div>
		<c:import url="/WEB-INF/views/layout/bottom.jsp" />
	</body>
</html>