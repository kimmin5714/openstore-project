<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="col-md-9">
	<div class="alert alert-success mt-3 text-center fw-bold" role="alert">
		아파트 정보</div>
	<!-- 아파트 매매 정보 검색 start -->
	<form id = "form-search" method="GET" action="">
		<div class="row col-md-12 justify-content-center mb-2">
			<div class="form-group col-md-2">
				<select class="form-select bg-secondary text-light" id="sido" name="sido">
					<option value="">시도선택</option>
				</select>
			</div>
			<div class="form-group col-md-2">
				<select class="form-select bg-secondary text-light" id="gugun" name="gugun">
					<option value="">구군선택</option>
				</select>
			</div>
			<div class="form-group col-md-2">
				<select class="form-select bg-secondary text-light" id="dong" name="dong">
					<option value="">동선택</option>
				</select>
			</div>
			<div class="form-group col-md-2">
				<select class="form-select bg-dark text-light" id="year" name="year"></select>
			</div>
			<div class="form-group col-md-2">
				<select class="form-select bg-dark text-light" id="month" name="month">
					<option value="">매매월선택</option>
				</select>
			</div>
			<div class="form-group col-md-2">
				<button type="button" id="list-btn" class="btn btn-outline-primary">
					아파트매매정보</button>
			</div>
		</div>
	</form>
	<!-- kakao map start -->
	<div id="map" class="mt-3" style="width: 100%; height: 400px"></div>
	<!-- kakao map end -->
	<c:choose>
	<c:when test="${empty houseDealList}">
	<%@ include file="/WEB-INF/views/apart/houseinfolist.jsp" %>
	</c:when>
	<c:when test="${empty houseList}">
	<%@ include file="/WEB-INF/views/apart/housedeallist.jsp" %>
	</c:when>
	<c:otherwise>
	<div></div>
	</c:otherwise>
	</c:choose>
</div>
<!-- 중앙 content end -->