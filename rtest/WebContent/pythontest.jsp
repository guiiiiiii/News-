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
request.setCharacterEncoding("utf-8");
String name= request.getParameter("name");
%>
<h1><%=name %>회원님 환영합니다.</h1>
<h3>게시판, 쇼핑 메뉴를 사용할 수 있습니다</h3>
<h1>감사합니다</h1>
<p>파이썬 테스트중입니다. <br>
<%=request.getMethod() %>
</body>
</html>