<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>레시피 등록</title>
		<script src="https://kit.fontawesome.com/487e3fe7a5.js" crossorigin="anonymous"></script>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/recipe/insertForm.css"/>">
		<link href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
		<script src="<c:url value='/js/jquery-3.6.1.min.js'/>"></script>
		<script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.js" ></script>
		<script src="<c:url value='/js/recipe/insertForm.js'/>"></script>
	</head>
	<body>
		<c:import url="/WEB-INF/views/layout/top.jsp" />
		<div id="wrap">
			<form id="contentBox" method="post" action="<c:url value='/recipe/insert'/>" enctype="multipart/form-data">
				<div id="content_1" class="content">
					<div class="content_title">
						<div>레시피 정보</div>
					</div>
					<div id="mainImgBox">
						<div>대표 이미지 추가하기*</div>
						<input type="file" id="repImg" class="hidden" name="mainImg" onChange="setThumbnail(event, this.id);">
						<label class="mainImg" for="repImg"></label>
					</div>
					<div id="recipeInf">
						<div class="smallBox">
							<div>레시피 제목*</div>
							<div><input type="text" name="rcpTitle"></div>
						</div>
						<div class="bigBox">
							<div>레시피 소개</div>
							<div><textarea name="rcpDcb"></textarea></div>
						</div>
						<div class="bigBox">
							<div>주의사항</div>
							<div><textarea name="caut"></textarea></div>
						</div>
						<div class="smallBox">
							<div>카테고리*</div>
							<div>
								<select name="ctg1">
									<option value="0">종류별</option>
									<c:forEach var="ctg" items="${ctgList}">
										<c:if test="${ctg.ctgCls eq 'ctg1'}">
											<option value="${ctg.ctgNo}">${ctg.ctgName}</option>
										</c:if>
									</c:forEach>
								</select>
								<select name="ctg2">
									<option value="0">상황별</option>
									<c:forEach var="ctg" items="${ctgList}">
										<c:if test="${ctg.ctgCls eq 'ctg2'}">
											<option value="${ctg.ctgNo}">${ctg.ctgName}</option>
										</c:if>
									</c:forEach>
								</select>
								<select name="ctg3">
									<option value="0">재료별</option>
									<c:forEach var="ctg" items="${ctgList}">
										<c:if test="${ctg.ctgCls eq 'ctg3'}">
											<option value="${ctg.ctgNo}">${ctg.ctgName}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="smallBox">
							<div>음식명*</div>
							<div><input type="text" name="foodName"></div>
						</div>
					</div>
				</div>
				<div id="content_2" class="content">
					<div class="content_title">
						<div>재료와 준비물</div>
					</div>
					<div class="padding_2080" id="idntDiv">
						<input name="idnt" type="hidden" id="idnt" value=''>
						<div class="idntBox">
							<div class="idntTitle">
								<div>
									<div><i class="fa-solid fa-circle ui-state-default"></i></div>
									<div><input type="text" value="재료"></div>
								</div>
								<div class="bdleIdnt_del">
									<button type="button" onclick="idntBoxDel(this)"><i class="fa-solid fa-xmark"></i>&nbsp;묶음 삭제</button>
								</div>
							</div>
							<div class="idntNameBox">
								<div class="indntOneSpace">
									<div class="idntOne">
										<div><i class="fa-solid fa-circle ui-state-default"></i></div>
										<div class="idntName">
											<input type="text">
										</div>
										<div class="idntQty">
											<input type="text">
										</div>
										<div onclick="idntOneDel(this)"><i class="fa-regular fa-circle-xmark"></i></div>
									</div>
									<div class="idntOne">
										<div><i class="fa-solid fa-circle ui-state-default"></i></div>
										<div class="idntName">
											<input type="text">
										</div>
										<div class="idntQty">
											<input type="text">
										</div>
										<div onclick="idntOneDel(this)"><i class="fa-regular fa-circle-xmark"></i></div>
									</div>
								</div>
								<div class="idntOne_add"><button type="button" onclick="idntOneAdd(this)"><i class="fa-solid fa-plus"></i>&nbsp;추가</button></div>
							</div>
						</div>
					</div>
					<div id="bdleIdnt_add"><button type="button" onclick="idntBoxAdd(this)"><i class="fa-solid fa-plus"></i>&nbsp;재료묶음 추가</button></div>
				</div>
				<div id="content_3" class="content">
					<div class="content_title">
						<div>요리순서*</div>
					</div>
					<div class="padding_2080" id="stepDiv">
						<div class="stepBox">
							<div class="stepNum"><div class="ui-state-default" draggable="true">Step1</div></div>
							<div><textarea name="ordDcb"></textarea></div>
							<div>
								<i class="fa-solid fa-plus"></i>
								<input type="file" id="stepImg1" class="hidden" name="ordImg" onChange="setThumbnail(event, this.id);">
								<label class="stepImg" for="stepImg1"></label>
							</div>
							<div onclick="stepDel(this)"><i class="fa-regular fa-circle-xmark"></i></div>
						</div>
						
					</div>
					<div id="stepAdd"><button type="button" onclick="stepBoxAdd(this)"><i class="fa-solid fa-plus"></i>&nbsp;순서추가</button></div>
				</div>
				<div id="content_4" class="content">
					<div class="content_title">
						<div>완성사진</div>
					</div>
					<div id="completeImg" class="padding_2080">
						<div>
							<div>
								<i class="fa-solid fa-plus"></i>
								<input type="file" id="comImg1" class="hidden" name="comImg1" onChange="setThumbnail(event, this.id);">
								<label class="stepImg" for="comImg1"></label>
							</div>
							<div>
								<i class="fa-solid fa-plus"></i>
								<input type="file" id="comImg2" class="hidden" name="comImg1" onChange="setThumbnail(event, this.id);">
								<label class="stepImg" for="comImg2"></label>
							</div>
							<div>
								<i class="fa-solid fa-plus"></i>
								<input type="file" id="comImg3" class="hidden" name="comImg1" onChange="setThumbnail(event, this.id);">
								<label class="stepImg" for="comImg3"></label>
							</div>
							<div>
								<i class="fa-solid fa-plus"></i>
								<input type="file" id="comImg4" class="hidden" name="comImg1" onChange="setThumbnail(event, this.id);">
								<label class="stepImg" for="comImg4"></label>
							</div>
						</div>
					</div>
				</div>
				<div id="content_5" class="content">
					<div id="finalSel" class="padding_2080">
						<div>
							<button type="button" id="save">저장</button>
							<button type="button" id="public">공개</button>
							<button type="button" id="cancel">취소</button>
						</div>
						<input type="hidden" value="" name="state" id="state">
					</div>
				</div>
			</form>
			<div Style="height : 100px; width : 100%;"></div>
		</div>
		<c:import url="/WEB-INF/views/layout/bottom.jsp" />
	</body>
	
</html>