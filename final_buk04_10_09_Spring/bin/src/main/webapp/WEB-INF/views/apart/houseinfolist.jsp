<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<table class="table table-hover text-center">
	<thead>
		<tr>
			<th>아파트 코드</th>
			<th>아파트이름</th>
			<th>건축년</th>
			<th>도로명</th>
			<th>동</th>
			<th>위도</th>
			<th>경도</th>
		</tr>
	</thead>
	<tbody id="aptlist">
		<c:forEach var="house" items="${houseList}">
			<tr class="text-center">
				<td scope="row"><a href="${root}/house?mode=search-apt&aptCode=${house.aptCode}&dealYear=${year}&dealMonth=${month}">${house.aptCode}</a></td>
				<td>${house.apartmentName}</td>
				<td>${house.buildYear}</td>
				<td>${house.roadName}</td>
				<td>${house.dong}</td>
				<td>${house.lng}</td>
				<td>${house.lat}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<!-- 아파트 매매 정보 검색 end -->
