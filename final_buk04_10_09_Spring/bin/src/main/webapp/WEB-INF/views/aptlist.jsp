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
   
    <!-- 중앙 컨테이너 시작 -->
    <div class="container-fluid border col-10 p-5 d-flex">
      <div class="col-md-8">
        <div class="alert alert-success mt-3 text-center fw-bold" role="alert">
          아파트 정보
        </div>
        <!-- 아파트 매매 정보 검색 start -->
        <div class="row col-md-12 justify-content-center mb-2">
          <div class="form-group col-md-2">
            <select class="form-select bg-secondary text-light" id="sido">
              <option value="">시도선택</option>
            </select>
          </div>
          <div class="form-group col-md-2">
            <select class="form-select bg-secondary text-light" id="gugun">
              <option value="">구군선택</option>
            </select>
          </div>
          <div class="form-group col-md-2">
            <select class="form-select bg-secondary text-light" id="dong">
              <option value="">동선택</option>
            </select>
          </div>
          <div class="form-group col-md-2">
            <select class="form-select bg-dark text-light" id="year"></select>
          </div>
          <div class="form-group col-md-2">
            <select class="form-select bg-dark text-light" id="month">
              <option value="">매매월선택</option>
            </select>
          </div>
          <div class="form-group col-md-2">
            <button type="button" id="list-btn" class="btn btn-outline-primary">
              조회
            </button>
          </div>
        </div>
        <!-- kakao map start -->
        <div id="map" class="mt-3" style="width: 100%; height: 700px"></div>
        <!-- kakao map end -->
      </div>
      <div class="col-md-4 flex-column">
        <h2 id="none-message" style="display: none">
          거래내역이 존재하지 않습니다.
        </h2>
        <table class="table table-hover text-center" style="display: none">
          <tr>
            <th>아파트이름</th>
            <th>층</th>
            <th>면적</th>
            <th>법정동</th>
            <th>거래금액</th>
          </tr>
          <tbody id="aptlist"></tbody>
        </table>
        <!-- 아파트 매매 정보 검색 end -->
      </div>
    </div>
    <!-- 중앙 컨테이너 종료 -->

    <%@ include file="/common/login_script.jsp" %>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    <script
      type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=62e31f3c9a0f48ed0f5fec1c88b656e4&libraries=services,clusterer,drawing"></script>
    <script src="./assets/js/key.js"></script>

    <script src="./assets/js/aptlist.js"></script>
  </body>
</html>
