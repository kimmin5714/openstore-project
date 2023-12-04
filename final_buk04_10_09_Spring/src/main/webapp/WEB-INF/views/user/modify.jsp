<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <%@ include file="/common/head.jsp" %>
    <title>Document</title>
  </head>
  <body>
    <%@ include file="/common/nav.jsp" %>
	<div class="mask d-flex align-items-center h-100 gradient-custom-3 register-ch">
	  <div class="container h-100">
	    <div class="row d-flex justify-content-center align-items-center h-100">
	      <div class="col-12 col-md-9 col-lg-7 col-xl-6">
	        <div class="card" style="border-radius: 15px">
	          <div class="card-body p-5">
	            <h1 class="text-uppercase text-center mb-5 fs-2">개인 정보 수정</h1>
	
	            <form id="form-join" method="post" action="" >
	              <input type="hidden" name="action" value="modify">
	              <div class="form-outline mb-4">
	                <input
	                  type="text"
	                  id="username"
	                  name="username"
	                  class="form-control form-control-lg"
	                 	value="${userInfo.userName}" />
	              </div>
	              <div class="form-outline mb-4">
	                <input
	                  type="text"
	                  id="useridR"
	                  name="userid"
	                  class="form-control form-control-lg"
	                  value="${userInfo.userId}" />
	              </div>
	
	              <div class="form-outline mb-4">
	                <!-- <input
	                  type="email"
	                  id="userEmail"
	                  class="form-control form-control-lg"
	                  placeholder="이메일 입력" /> -->
	                <input
	                  type="text"
	                  class="form-control"
	                  id="emailid"
	                  name="emailid"
	                  value="${userInfo.emailId}" />
	                <span class="input-group-text">@</span>
	                <select
	                  class="form-select"
	                  id="emaildomain"
	                  name="emaildomain"
	                  aria-label="이메일 도메인 선택">
	                  <option selected>선택</option>
	                  <option value="ssafy.com">싸피</option>
	                  <option value="google.com">구글</option>
	                  <option value="naver.com">네이버</option>
	                  <option value="kakao.com">카카오</option>
	                </select>
	              </div>
	
	              <div class="form-outline mb-4">
	                <input
	                  type="password"
	                  id="userpwdR"
	                  name="userpwd"
	                  class="form-control form-control-lg"
	                  value="${userInfo.userPwd}"
	                  placeholder="비밀번호" />
	              </div>
	
	              <div class="form-outline mb-4">
	                <input
	                  type="password"
	                  id="pwdcheck"
	                  class="form-control form-control-lg"
	                  value="${userInfo.userPwd}"
	                  placeholder="비밀번호 확인" />
	              </div>
	
	              <div class="d-flex justify-content-center">
	                <!-- <button
	                  type="button"
	                  class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">
	                  회원 등록
	                </button> -->
                <button type="button" id="btn-modify" class="btn btn-outline-primary mb-3 me-3">
	                수정하기
	              </button>
	              <!-- <button type="button" id="btn-delete" class="btn btn-outline-primary mb-3">
	                탈퇴하기
	              </button> -->
	            <a href="${root}/user?action=deleteUser&userId=${userInfo.userId}" id="btn-delete" class="btn btn-outline-danger mb-3">
	                탈퇴하기
	              </a>
	              
	              <!-- <button type="button" class="btn btn-outline-success mb-3">초기화</button> -->
	              </div>
	            </form>
	          </div>
	        </div>
	      </div>
	    </div>
	  </div>
	</div>

    <%@ include file="/common/login_script.jsp" %>
     <script>
      document.querySelector("#btn-modify").addEventListener("click", function () {
          if (!document.querySelector("#username").value) {
            alert("이름 입력!!");
            return;
          } else if (!document.querySelector("#useridR").value) {
            alert("아이디 입력!!");
            return;
          } /* else if (!isUseId) {
            alert("아이디 중복 확인!!");
            return;
          } */ else if (!document.querySelector("#userpwdR").value) {
            alert("비밀번호 입력!!");
            return;
          } else if (
            document.querySelector("#userpwdR").value != document.querySelector("#pwdcheck").value
          ) {
            alert("비밀번호 확인!!");
            return;
          } else {
            let form = document.querySelector("#form-join");
            form.setAttribute("action", "${root}/user");
            form.submit();
          }
        });
      </script>
    <%-- <%@ include file="/common/register_script.jsp" %> --%>
  </body>
</html>
