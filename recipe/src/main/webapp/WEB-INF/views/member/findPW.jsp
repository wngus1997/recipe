<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입</title>
	</head>
	<body>
		<form method="post" action="<c:url value="/member/insert" />">
			<input type="text" name="memId">
			<input type="password" name="memPw">
			<input type="text" name="memName">
			<input type="text" name="memNick">
			<input type="text" name="memEmail">
			<input type="submit" value="회원가입">
		</form>
	</body>
</html>