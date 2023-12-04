<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<script>
  /* document.querySelector("#btn-mv-join").addEventListener("click", function () {
    location.replace("${root}/user?action=mvjoin");
  }); */

	<c:if test="${empty userinfo}">
	  document.querySelector("#btn-login").addEventListener("click", function () {
	    if (!document.querySelector("#userid").value) {
	      alert("아이디 입력!!");
	      return;
	    } else if (!document.querySelector("#userpwd").value) {
	      alert("비밀번호 입력!!");
	      return;
	    } else {
	      let form = document.querySelector("#form-login");
	      form.setAttribute("action", "${root}/user/login");
	      form.submit();
	    }
	  });
	</c:if>
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
