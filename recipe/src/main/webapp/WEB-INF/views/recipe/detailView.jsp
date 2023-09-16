<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>레시피 등록</title>
		<script src="https://kit.fontawesome.com/487e3fe7a5.js" crossorigin="anonymous"></script>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/recipe/detailView.css"/>">
		<script src="<c:url value='/js/jquery-3.6.1.min.js'/>"></script>
		<script src="<c:url value='/js/recipe/detailView.js'/>"></script>
	</head>
	<body>
		<c:import url="/WEB-INF/views/layout/top.jsp" />
		<div id="wrap">
			<div id="overallBox">
				<div>
					<div><a>${recipe.memNick}</a></div>
					<div>${recipe.foodName}</div>
					<div>${recipe.rcpDate}</div>
				</div>
				<div> <img src="<c:url value='/images/${recipe.repImg}' />"></div>
				<div>${recipe.rcpTitle}</div>
				<div>${recipe.rcpDcb}</div>
				<div></div>
				<div>
					<div>${ctg1}</div>
					<div>${ctg2}</div>
					<div>${ctg3}</div>
				</div>
			</div>
			<div id="idntBox">
				<c:forEach var="Title" items="${idntTitle}">
					<div class="idntOne">
						<div class="idntTitle">${Title}</div>
						<ul class="idntListBox">
							<c:forEach var="idnt" items="${idnt}">
								<c:if test="${Title eq idnt.idntTitle}">
									<li><div>${idnt.idntName}</div><div>${idnt.idntQty}</div></li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
				</c:forEach>
			</div>
			<div id="orderBox">
				<c:forEach var="order" items="${orderOne}">
					<div class="orderOne">
						<div class="orderContent">
							<div>${order.step}</div>
							<div>${order.ordDcb}</div>
						</div>
						<div><img src="<c:url value='/images/${order.ordImg}' />" onerror="this.style.display='none'" alt='' ></div>
					</div>
				</c:forEach>
			</div>
			<div id="comImgBox">
				<div class="comImgOne">
					<c:forTokens  var="comImg" items="${recipe.comImg}" delims=",">
						<div><img src="<c:url value='/images/${comImg}' />"></div>
					</c:forTokens>
				</div>
			</div>
			<div id="FavorBox">
				<button onClick="favorInOut()"><span class="TF"><c:if test="${favorCheck != 0}">★</c:if><c:if test="${favorCheck == 0}">☆</c:if></span>즐겨찾기</button>
			</div>
			<%-- <form action="<c:url value='/comment/insert' />" method="post" id="cmtForm"> --%>
			<input type="hidden" id="rcpNo" value="${recipe.rcpNo}" name="rcpNo">
			<div id="commentBox">
				<div id="commentWrite">
					<div>${memId}</div>
					<div><textarea placeholder="댓글을 남겨주세요" name="cmtDcb" id="cmtDcb"></textarea></div>
					<div><button type="button" id="insertBtn" onclick="cmtInsert()">등록</button></div>
				</div>
				<c:forEach var="cmt" items="${cmtList}">
				<div class="commentOne">
					<div></div>
					<div>
						<div class="cmtMemDate"><div class="cmtNick">${cmt.memNick}</div><span>${cmt.cmtDate}</span></div>
						<div class="cmtDcb">
							<div>${cmt.cmtDcb}</div>
							<c:if test="${memId eq cmt.memId}">
								<div class="memSame"><!-- <button type="button">수정</button> --><button type="button" onclick="cmtDelete(${cmt.cmtNo})">삭제</button></div>
							</c:if>
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
			<!-- </form> -->
			<div id="spaceBox"></div>
		</div>
		<c:import url="/WEB-INF/views/layout/bottom.jsp" />
	</body>
</html>