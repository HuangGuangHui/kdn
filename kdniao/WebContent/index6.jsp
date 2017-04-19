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
<title>快递鸟信息查询接口</title>
</head>
<body>
<script type="text/javascript">
</script>
		<div class="ftitle">Excel表导入</div>
		<hr>
		<form id="fimport" action="<%=path%>/send6" method="post" enctype="multipart/form-data">
		<div class="fitem">
			<label>Excel模板:</label>
			<a href="" class="easyui-linkbutton">模板下载</a>
		</div>
		<div class="fitem">
			<label>导入Excel:</label>
			<input id="filename" type="file" name="file" style="width:180px;"/>
		</div>
		<input type="submit"  value="导入"/>
		</form>
</body>
</html>



