<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<!-- NAV 임포트 해야함 -->
<!-- NAV 임포트 해야함 -->
<!DOCTYPE html>
<html lang="en">
  <head>
    <%@ include file="/WEB-INF/views/common/head.jsp" %>
    <title>Document</title>
  </head>
  <body>
    <%@ include file="/WEB-INF/views/common/nav.jsp" %>
	<div class="mask d-flex align-items-center h-100 gradient-custom-3 register-ch">
	  <div class="container h-100">
	    <div class="row d-flex justify-content-center align-items-center h-100">
	      <div class="col-12 col-md-9 col-lg-7 col-xl-6">
	        <div class="card" style="border-radius: 15px">
	          <div class="card-body p-5">
	            <h1 class="text-uppercase text-center mb-5 fs-2">회원 등록</h1>
	
	            <form id="form-join" method="post" action="" >
	              <input type="hidden" name="action" value="join">
	              <div class="form-outline mb-4">
	                <input
	                  type="text"
	                  id="username"
	                  name="username"
	                  class="form-control form-control-lg"
	                  placeholder="이름 입력" />
	              </div>
	              <div class="form-outline mb-4">
	                <input
	                  type="text"
	                  id="useridRegister"
	                  name="useridRegister"
	                  class="form-control form-control-lg"
	                  placeholder="아이디 입력" />
	              </div>
	
	              <div class="form-outline mb-4">	   
	                <input
	                  type="text"
	                  class="form-control"
	                  id="emailid"
	                  name="emailid"
	                  placeholder="이메일아이디" />
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
	                  id="userpwdRegister"
	                  name="userpwdRegister"
	                  class="form-control form-control-lg"
	                  placeholder="비밀번호" />
	              </div>
	
	              <div class="form-outline mb-4">
	                <input
	                  type="password"
	                  id="pwdcheck"
	                  class="form-control form-control-lg"
	                  placeholder="비밀번호 확인" />
	              </div>
	
	              <div class="d-flex justify-content-center">
	                <!-- <button
	                  type="button"
	                  class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">
	                  회원 등록
	                </button> -->
		                <button type="button" id="btn-join" class="btn btn-outline-primary mb-3">
	                회원가입
	              </button>
	              
	              <!-- <button type="button" class="btn btn-outline-success mb-3">초기화</button> -->
	              </div>
	            </form>
	          </div>
	        </div>
	      </div>
	    </div>
	  </div>
	</div>

    <%@ include file="/WEB-INF/views/common/login_script.jsp" %>
    <%@ include file="/WEB-INF/views/common/register_script.jsp" %>
  </body>
</html>
