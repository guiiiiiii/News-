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
<%
		request.setCharacterEncoding("utf-8");
		String input=request.getParameter("input");
		
		String[] result=RTest2.test3(input);
	
%>
<%=request.getParameter("input") %>
<%=result[0]%>
<%=result[1]%>
<%=result[2]%>
<%=result[3]%>
<%=result[4]%>
<%=result[5]%>
</body>
</html>