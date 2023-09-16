<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="listRecipeBox">
	<c:if test="${not empty recipeList}">
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
	<c:if test="${empty recipeList}">
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