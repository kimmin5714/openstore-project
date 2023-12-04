<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light shadow">
	<div class="container">
		<a class="navbar-brand text-primary fw-bold "
			href="${ root }/index.jsp"> <img src="./assets/img/home_logo.png"
			alt="" width="60" /> FIND MY HOUSE
		</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<div class="ms-auto"></div>
			<!-- 로그인 전 -->
			<c:choose>
				<c:when test="${empty member}">
					<ul class="navbar-nav mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link" aria-current="page"
							href="${ root }/member/join.jsp">회원가입</a></li>
						<li class="nav-item"><a class="nav-link" aria-current="page"
							href="${ root }/auth?cmd=goLoginPage">로그인</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<!-- 로그인 후 -->
					<ul class="navbar-nav mb-2 me-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link">${ member.userName }님
								반갑습니다.</a></li>
						<li class="nav-item"><a class="nav-link" aria-current="page"
							href="${ root }/auth?cmd=logout">로그아웃</a></li>
						<li class="nav-item"><a class="nav-link" aria-current="page"
							href="${ root }/auth?cmd=goModifyPage">내정보수정</a></li>
						<li class="nav-item"><a class="nav-link" aria-current="page"
							href="${ root }/admin?cmd=goManagePage">관리자 </a></li>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</nav>