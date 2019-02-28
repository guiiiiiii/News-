<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.vo.MemberVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원 정보(스크립트 방식)</h1>
	<hr>
	<ul>
		<%
			MemberVO vo = (MemberVO) request.getAttribute("obj");
			if (vo != null) {
		%>
		<li>회원 이름 : <%=vo.getName()%>
		<li>회원 계정 : <%=vo.getAccount()%>
		<li>회원 암호 :<%=vo.getPassword()%>
		<li>회원 전화번호 :<%=vo.getTelNumber()%> <%
 	}
 %>
	</ul>
	<hr>
	<h1>회원 정보(액션태그방식)</h1>
	<hr>
	<ul>

		<jsp:useBean id='obj' class='model.vo.MemberVO' scope='request' />
		<li>회원 이름 : <jsp:getProperty name='obj' property='name' />
		<li>회원 계정 : <jsp:getProperty name='obj' property='account' />
		<li>회원 암호 :<jsp:getProperty name='obj' property='password' />
		<li>회원 전화번호 :<jsp:getProperty name='obj' property='telNumber' />
	</ul>
	<hr>
	<h1>회원 정보(EL(Expression Language)방식)</h1>
	<hr>
	<ul>

		<li>회원 이름 : ${obj.name}
		<li>회원 계정 : ${obj.account}
		<li>회원 암호 :${obj.password}
		<li>회원 전화번호 :${obj.telNumber}
	</ul>
	<hr>
	<a href='/mvc/htmlexam/memberForm.html'>입력 화면으로</a>
</body>
</html>