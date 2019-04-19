<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'reg.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script type="text/javascript"
	src="${pageContext.request.contextPath }/statics/js/jquery-1.12.4.js"></script>

</head>

<body>

	<h1>注册页面</h1>
	<p>${error}</p>
	<form action="${pageContext.request.contextPath }/doreg" mothod="post"
		id="frm">
		用户名<input type="text" name="userName" id="userName" value="${name}" />
		<span id="nameError">${nameError}</span><br /> 密码 <input
			type="password" name="password" id="password" value="${pwd}" /> <span
			id="pwdError">${pwdError}</span><br /> <input type="submit"
			value="注册">
	</form>
	<script>
		$(document).ready(function() {
			$("#userName").blur(function() {
				var name = $("#userName").val();
				if (name == null || name == "") {
					$("#nameError").html("用户名不能为空");
				} else {
					/* $.ajax({
						type:"GET",
						url:"fream?userName="+name, 
						dataType:"json",
						success:function(data){ 
							$("#nameError").html(data);
						}
					});   */

					$.get("fream?userName=" + name, "json",succ
					 );
				}
			});
			
			 function succ(data) {
						alert(data); 
						$("#nameError").html(data);
						al();
			}
			//在ajax执行完之后执行该方法
			function al(){
				alert("...");
			}
			$("#password").blur(function() {
				var pwd = $("#password").val();
				var pwdError = $("#pwdError");
				if (pwd == null || pwd == "") {
					pwdError.html("密码不能为空");
				} else {
					pwdError.html("");
				}
			}); 
			   $("#frm").submit(function() {
				var name = $("#userName").val();
				var pwd = $("#password").val(); 
				if(name == null || name ==""){
					$("#nameError").html("用户名不能为空");
					return false;
				}
				if(pwd == null || pwd == ""){
					$("#pwdError").html("密码不能为空"); 
					return false;
				}
				if(name.length < 3 || name.length > 10){
					$("#nameError").html("用户名长度为3-10");
					return false;
				}
				
				if(pwd.length < 3 || pwd.length > 10){
					$("#pwdError").html("用户名长度为3-10");
					return false;
				}
				
				return true;
			});  

		});
	</script>
</body>
</html>
