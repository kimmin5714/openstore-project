<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<script>
	<c:if test="${empty userinfo || userinfo.userId != 'admin'}">
		location.replace("${root}/error/error.jsp");
	</c:if>

    let users = document.querySelectorAll(".user-record");
    users.forEach(function (user) {
    	user.addEventListener("click", function () {
        console.log(this.getAttribute("data-id"));
        location.href = "${root}/user?action=mvModifyUserInfo&userId=" + this.getAttribute("data-id");
      });
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
