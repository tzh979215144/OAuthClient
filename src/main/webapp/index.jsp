<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%-- <%
String path1 = request.getContextPath();
response.sendRedirect(path1 + "/login/toLogin.do");  
%> --%>
<html>

	<head>
	
		<script src="${pageContext.request.contextPath }/js/jquery.min63b9.js" type="text/javascript"></script>
	   	<meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	    <title>登录</title>
	    <script>
	    	if(window.top !== window.self){ window.top.location = window.location;}
	    </script>
	    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">  
		<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
		<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head>

	<body style="text-align:center;">
	
		<div class="container">
			<h2>欢迎</h2>
			<p>到服务端请求资源(用户名)</p>            
			<button type="button" onclick="gotoserver()" class="btn btn-default btn-lg">请求</button>
			<p>申请客户端ID</p>  
			<button type="button" onclick="gotoserver2()" class="btn btn-info btn-lg">申请</button>      
		</div>
		<br><br>
		
		<script type="text/javascript">
		
		function gotoserver(){
			alert("服务端正在认证");
			var url = "${pageContext.request.contextPath}";
			window.location.href = url+"/server/requestServerCode";
		}
		</script>
		                                   
		<script type="text/javascript">
		
		function gotoserver2(){
			alert("正在申请ID，等待人工审核");
			var url = "${pageContext.request.contextPath}";
			window.location.href = "http://10.50.130.239:8080/oauthserver/user/register";
		}
		</script>
	</body>
</html>