<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>test-jsp</title>
</head>
<body>

<form action="./requestServerCode" method="post">
		
		请输入你的测试用用例：
		客户端ID：<input type="text" required="required" name="id" value="" placeholder="请输入"><br>
 
		
		客户端secret：<input type="password" required="required" name="password" value="" placeholder="请输入"><br>
		<input type="submit" name="" value="提交"  placeholder=""><br>
 
		<input type="reset" name="重置" value="重置" placeholder=""><br>
 
 
		
	</form>

</body>
</html>