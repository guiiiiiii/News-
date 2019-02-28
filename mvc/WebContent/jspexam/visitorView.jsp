<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import='model.vo.visitorVO, java.util.ArrayList'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
td {
	border-bottom: 1px dotted green;
}

tr:hover {
	background-color: lime;
}
</style>

</head>
<body>
	<%
		ArrayList<visitorVO> list = (ArrayList<visitorVO>) request.getAttribute("list");

		if (list != null) {
	%>
	<table>
		<%
			for (visitorVO vo : list) {
		%>
		<tr>
			<td><%=vo.getId()%></td>
			<td><%=vo.getName()%></td>
			<td><%=vo.getWritedate()%></td>
			<td><%=vo.getMemo()%></td>
			<td><a href='/mvc/visitor?id=<%=vo.getId()%>'>
			 <img
					src='/edu/images/delete.png' width=30px></a></td>
		</tr>
		<%
			}
		%>

	</table>

	<%
		} else {
	%>
	<h2>${msg }</h2>
	<%
		}
	%>
	<a href='/mvc/htmlexam/visitorMain.html'>방명록 메인 화면으로</a>
</body>
</html>