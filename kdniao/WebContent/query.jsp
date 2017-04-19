<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>快递鸟信息查询</title>
</head>
<body>
	<form action="<%=path%>/query">
		<input type="submit" value="测试" />	
	</form>	
	<form action="<%=path%>/instantQuery">
		<input type="submit" value="即时查询" />	
	</form>	
</body>
</html>



