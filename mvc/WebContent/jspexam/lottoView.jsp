<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String result = (String) session.getAttribute("result");

		if (result.equals("success")) {
	%>
	<img src="/edu/images/success.jpg">
	<br>
	<a href="/mvc/htmlexam/lottoForm.html">로또 응모</a>
	<%
		} else if (result.equals("fail")) {
	%>
	<img src="/edu/images/fail.jpg">
	<br>
	<a href="/mvc/htmlexam/lottoForm.html">로또 응모</a>
	<%
		} else
	%><h2>브라우저를 재기동하여 요청하세요!!</h2>
</body>
</html>