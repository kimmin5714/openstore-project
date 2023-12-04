<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
      let isUseId = false;
      let input = document.querySelector("#useridRegister");
      let resultDiv = document.querySelector("#result-view");
      input.addEventListener("keyup", function () {
        let checkid = input.value;
        let len = checkid.length;
        if (len < 4 || len > 16) {
          isUseId = false;
          resultDiv.setAttribute("class", "mb-3 fw-bold text-dark");
          resultDiv.innerHTML = "아이디는 4자이상 16자이하입니다.";
        } else {
          let url = "${root}/user?action=idcheck&checkid=" + checkid;
          // let url = `${root}/user?action=idcheck&checkid=${checkid}`;
          // CSV
          fetch(url)
            .then((response) => response.text())
            .then((data) => resultViewText(data));

          // JSON
          // fetch(url)
          //   .then((response) => response.json())
          //   .then((data) => resultViewJSON(data));
        }
      });

      function resultViewText(data) {
        let val = data.split(",");
        let id = val[0];
        let cnt = val[1];
        if (cnt == 0) {
          isUseId = true;
          resultDiv.setAttribute("class", "mb-3 text-success");
          resultDiv.innerHTML = "<span class='fw-bold'>" + id + "</span>은 사용할 수 있습니다.";
        } else {
          isUseId = false;
          resultDiv.setAttribute("class", "mb-3 text-danger");
          resultDiv.innerHTML = "<span class='fw-bold'>" + id + "</span>은 사용할 수 없습니다.";
        }
      }

      function resultViewJSON(data) {
        if (data.cnt == 0) {
          isUseId = true;
          resultDiv.setAttribute("class", "mb-3 text-primary");
          resultDiv.innerHTML =
            "<span class='fw-bold'>" + data.checkid + "</span>은 사용할 수 있습니다.";
        } else {
          isUseId = false;
          resultDiv.setAttribute("class", "mb-3 text-warning");
          resultDiv.innerHTML =
            "<span class='fw-bold'>" + data.checkid + "</span>은 사용할 수 없습니다.";
        }
      }

      document.querySelector("#btn-join").addEventListener("click", function () {
        if (!document.querySelector("#username").value) {
          alert("이름 입력!!");
          return;
        } else if (!document.querySelector("#useridRegister").value) {
          alert("아이디 입력!!");
          return;
        } /* else if (!isUseId) {
          alert("아이디 중복 확인!!");
          return;
        } */ else if (!document.querySelector("#userpwdRegister").value) {
          alert("비밀번호 입력!!");
          return;
        } else if (
          document.querySelector("#userpwdRegister").value != document.querySelector("#pwdcheck").value
        ) {
          alert("비밀번호 확인!!");
          return;
        } 
        else {
          let form = document.querySelector("#form-join");
          form.setAttribute("action", "${root}/user/join");
          form.submit();
        }
      });
    </script>