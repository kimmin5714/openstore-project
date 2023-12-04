<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%-- 부트스트랩 사용을 위한 코드 --%>
    <!-- JavaScript Bundle with Popper -->
    <footer class="navbar navbar-expand-lg navbar-light bg-light container justify-content-end">
      <div class="row">
        <ul class="navbar-nav">
          <li><a href="#" class="nav-link text-secondary">카페소개</a></li>
          <li>
            <a href="#" class="nav-link text-secondary">개인정보처리방침</a>
          </li>
          <li><a href="#" class="nav-link text-secondary">이용약관</a></li>
          <li><a href="#" class="nav-link text-secondary">오시는길</a></li>
          <li>
            <label class="nav-link text-secondary">&copy; SSAFY Corp.</label>
          </li>
        </ul>
      </div>
    </footer>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
      crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
      crossorigin="anonymous"></script>
    <!-- <script src="./assets/js/main.js"></script> -->
    <script type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8d97146c98de467b31caf7278bbb3ff8&libraries=services,clusterer,drawing"></script>
    <!-- 아파트, 카카오 맵 관련 js -->
    <script src="./assets/js/apt_map.js"></script>
    <script>
      document.querySelector("#list-btn").addEventListener("click", function () {
        let form = document.querySelector("#form-search");
        form.setAttribute("action", "${root}/house/search-dong");
        form.submit();
      })
    </script>

    </body>

    </html>