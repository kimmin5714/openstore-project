<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<table class="table table-hover text-center">
	<thead>
		<tr>
			<th>거래 금액</th>
			<th>거래 년도</th>
			<th>거래 월</th>
			<th>거래 일</th>
			<th>면적</th>
			<th>층</th>
		</tr>
	</thead>
	<tbody id="aptlist">
		<c:forEach var="houseDeal" items="${houseDealList}">
			<tr class="text-center">
				<td scope="row">${houseDeal.dealAmount}</td>
				<td>${houseDeal.dealYear}</td>
				<td>${houseDeal.dealMonth}</td>
				<td>${houseDeal.dealDay}</td>
				<td>${houseDeal.area}</td>
				<td>${houseDeal.floor}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 아파트 매매 정보 검색 end -->