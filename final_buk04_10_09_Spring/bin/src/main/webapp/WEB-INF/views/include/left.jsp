<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="col-md-3">
	<!-- 프로필 사진 start -->
	<div class="text-center mt-3">
		<img src="./assets/img/profile.png" class="img-thumbnail" alt="프로필" />
	</div>
	<!-- 프로필 사진 end -->
	<!-- 메뉴 start -->
	<div class="accordion mt-3" search-area>
		<div class="accordion-item">
			<h2 class="accordion-header" id="aptSearch">
				<button class="accordion-button" type="button"
					data-bs-toggle="collapse" data-bs-target="#area-aptSearch"
					aria-expanded="true" aria-controls="area-aptSearch">아파트 정보
					조회</button>
			</h2>
			<div id="area-aptSearch" class="accordion-collapse collapse show"
				aria-labelledby="aptSearch" data-bs-parent="#areas">
				<div class="accordion-body">
					<a class="nav-link" href="aptmap.html">아파트 실거래가 조회</a>
				</div>
			</div>
		</div>
		<div class="accordion-item">
			<h2 class="accordion-header" id="community">
				<button class="accordion-button" type="button"
					data-bs-toggle="collapse" data-bs-target="#area-community"
					aria-expanded="true" aria-controls="area-community">커뮤니티</button>
			</h2>
			<div id="area-community" class="accordion-collapse collapse show"
				aria-labelledby="community" data-bs-parent="#areas">
				<div class="accordion-body">
					<a class="nav-link" href="#">공지사항</a>
				</div>
				<div class="accordion-body">
					<a class="nav-link" href="#">자유글</a>
				</div>
				<div class="accordion-body">
					<a class="nav-link" href="#">주변탐방</a>
				</div>
				<div class="accordion-body">
					<a class="nav-link" href="#">부동산 뉴스</a>
				</div>
			</div>
		</div>
	</div>
	<!-- 메뉴 end -->
</div>