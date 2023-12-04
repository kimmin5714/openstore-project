<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <%@ include file="/WEB-INF/views/common/head.jsp" %>
    <title>Document</title>
  </head>
  <body>
    <%@ include file="/WEB-INF/views/common/nav.jsp" %> 
	<c:if test="${not empty userinfo && userinfo.userId == 'admin'}">
	<!-- 회원 관리 테이블 시작 -->
	<main class="container border p-5 d-flex flex-column">
      <div class="">
        <table class="table table-striped col-6" id="manage-table">
          <thead class="fs-5 fw-bold">
            <tr>
              <th class="col-1">아이디</th>
              <th class="col-1">이름</th>
              <th class="col-2">이메일</th>
              <th class="col-1"></th>
            </tr>
          </thead>
          <tbody id="manage-table-body">   
			<!-- 회원 관리 테이블 행 삽입 로직 시작 -->
			<c:forEach var="user" items="${users}">    
              <!-- <a class="user-record" data-id="${user.userId}" href="#" style="text-decoration: none"> -->
              <tr>
                <%-- <th scope="row">${user.userId}</th> --%>
                <td>
                    ${user.userId}
                </td>
                <td>${user.userName}</td>
                <td>${user.emailId} @ ${user.emailDomain}</td>
                <td> <button type="button" class="btn btn-info user-record" data-id="${user.userId}" >수정</button></td>
              </tr>
			</c:forEach>  
			<!-- 회원 관리 테이블 행 삽입 로직 종료 -->
            <!-- <tr>
              <td>John</td>
              <td>john@example.com</td>
              <td>
                <a href="./modify.html" class="text-decoration-none">
                  <button type="button" class="btn btn-info">수정</button>
                </a>
                <button
                  type="button"
                  class="btn btn-danger"
                  onclick="alert('삭제되었습니다.');">
                  삭제
                </button>
              </td>
            </tr> -->
          </tbody>
        </table>
      </div>
      <a href="${root}/user?action=mvjoin" class="text-decoration-none btn btn-primary col-1">
        <!-- <button type="button" class="btn btn-primary col-1">회원 등록</button> -->
        회원 등록
      </a>
    </main>
    <!-- 회원 관리 테이블 종료 -->
	</c:if>
	<%@ include file="/WEB-INF/views/common/login_script.jsp" %>
	<%@ include file="/WEB-INF/views/common/adminUserList_script.jsp" %>
  </body>
</html>
