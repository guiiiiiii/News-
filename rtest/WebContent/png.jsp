<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="edu.R.RTest2" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% RTest2 r = new RTest2();
	r.test2();
	Thread.sleep(1000);

%>
<img src='test.png'>
</body>
</html>