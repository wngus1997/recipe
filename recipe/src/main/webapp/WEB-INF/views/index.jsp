<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>자취요리</title>
		<script src="https://kit.fontawesome.com/487e3fe7a5.js" crossorigin="anonymous"></script>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/recipe/index.css"/>">
		<script src="<c:url value='/js/jquery-3.6.1.min.js'/>"></script>
		<script src="<c:url value='/js/recipe/index.js'/>"></script>
		
	</head>
	<body>
		<c:import url="/WEB-INF/views/layout/top.jsp" />
		<div id="wrap">
			<div id="ctgSearchBox">
				<div class="content_title">
					<label for="ctgOCBox">
						<div><span>카테고리 검색</span>
							<span>
								<i class="fa-solid fa-angles-up" id="upIcon"></i>
								<i class="fa-solid fa-angles-down" id="downIcon"></i>
							</span>
						</div>
					</label>
				</div>
				<input type="checkbox" class="hidden" id="ctgOCBox">
				<div id="ctgSearchContent">
					<div id="ctgBox">
						<hr style="width : 90%;">
						<div id="ctg1" class="ctgOneBox">
							<div class="ctgName">
								종류별
							</div>
							<ul class="ctgElmtBox">
							<c:forEach var="ctg" items="${ctgList}">
								<c:if test="${ctg.ctgCls eq 'ctg1'}">
									<li>
										<input type="checkBox" class="hidden" id="${ctg.ctgNo}" name="ctg1" value="${ctg.ctgName}">
										<label for="${ctg.ctgNo}">
											<i class="fa-solid fa-check"></i>
											<i class="fa-regular fa-square"></i>
											${ctg.ctgName}
										</label>
									</li>
								</c:if>
							</c:forEach>
							</ul>
						</div>
						<hr style="width : 90%;">
						<div id="ctg2" class="ctgOneBox">
							<div class="ctgName">상황별</div>
							<ul class="ctgElmtBox">
								<c:forEach var="ctg" items="${ctgList}">
								<c:if test="${ctg.ctgCls eq 'ctg2'}">
									<li>
										<input type="checkBox" class="hidden" id="${ctg.ctgNo}" name="ctg2" value="${ctg.ctgName}">
										<label for="${ctg.ctgNo}">
											<i class="fa-solid fa-check"></i>
											<i class="fa-regular fa-square"></i>
											${ctg.ctgName}
										</label>
									</li>
								</c:if>
							</c:forEach>
							</ul>
						</div>
						<hr style="width : 90%;">
						<div id="ctg3" class="ctgOneBox">
							<div class="ctgName">재료별</div>
							<ul class="ctgElmtBox">
								<c:forEach var="ctg" items="${ctgList}">
									<c:if test="${ctg.ctgCls eq 'ctg3'}">
										<li>
											<input type="checkBox" class="hidden" id="${ctg.ctgNo}" name="ctg3" value="${ctg.ctgName}">
											<label for="${ctg.ctgNo}">
												<i class="fa-solid fa-check"></i>
												<i class="fa-regular fa-square"></i>
												${ctg.ctgName}
											</label>
										</li>
									</c:if>
								</c:forEach>
							</ul>
						</div>
						<hr style="width : 90%;">
						<div id="searchBox">
							<div>
								<select id="foodCF" name="search">
									<option selected value="foodName">음식명</option>
									<option value="rcpTitle">제목</option>
								</select>
								<input type="text" id="searchInp" value="">
							</div>
							<div class="ctgBtnBox">
								<div><button type="button" onclick="searchAjax()">검색</button></div>
								<div><button type="button" onclick="reset()">초기화</button></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="orderByBox">
				<div id="selectedBox">
				</div>
				<div id="orderBtnBox">
					<input type="radio" id="rcpDate" name="orderBy" class="hidden" value="rcpDate" checked>
					<label for="rcpDate"><div onClick="orderBtn('rcpDate')">날짜순</div></label>
					<input type="radio" id="foodName" name="orderBy" class="hidden" value="foodName">
					<label for="foodName"><div onClick="orderBtn('foodName')">이름순</div></label>
					<input type="radio" id="view" name="orderBy" class="hidden" value="view">
					<label for="view"><div onClick="orderBtn('view')">조회순</div></label>
				</div>
			</div>
			<div id="listPageBox">
				<div id="listRecipeBox">
					<c:if test="${not empty ctgList}">
						<c:forEach var="rcp" items="${recipeList}">
							<div class="recipeOne">
								<div class="imgBox">
									<a href="<c:url value='/recipe/detailView/${rcp.rcpNo}'/>" ><img src="<c:url value='/images/${rcp.repImg}' />"></a>
								</div>
								<div class="textInfBox">
									<div><a href="<c:url value='/recipe/detailView/${rcp.rcpNo}'/>" >${rcp.rcpTitle}</a></div>
									<div><a>${rcp.memNick}</a> / ${rcp.foodName}</div>
									<div>
										<!-- <div class="gradeStar">
											<div style="width : 20%;">★★★★★</div>
										</div>
										<div>☆☆☆☆☆</div> -->
										<div style="color : gray;">즐겨찾기 ${rcp.grade }</div>
										<div style="color : gray;">조회수 ${rcp.view }</div>
									</div>
								</div>
							</div>
			         	</c:forEach>
			         	<c:forEach var="i" begin="1" end="${index}">
			         		<div class="recipeOne"></div>
			         	</c:forEach>
					</c:if>
					<c:if test="${empty ctgList}">
						<div class="emptyList">검색결과가 없습니다.</div>
					</c:if>
					
				</div>
				<div id="pageBox">
					<div id="paging">
						<c:if test="${page.currentJ != 1}"><a onClick="curMethod(${(page.currentJ-2)*page.pageCnt+1})"><i class="fa-solid fa-chevron-left"></i></a></c:if>
						<c:forEach var="num" begin="${page.startP}" end="${page.endP}" step="1">
							<c:if test="${page.currentP eq num}"><span>${num}</span></c:if>
							<c:if test="${page.currentP != num}"><a onClick="curMethod(${num})">${num}</a></c:if>
						</c:forEach>
						<c:if test="${page.currentJ != page.endJ}"><a onClick="curMethod(${page.currentJ*page.pageCnt+1})"><i class="fa-solid fa-chevron-right"></i></a></c:if>
					</div>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/layout/bottom.jsp" />
	</body>
</html>