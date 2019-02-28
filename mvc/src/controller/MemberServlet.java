package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.vo.MemberVO;

@WebServlet("/ms")
public class MemberServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String name = request.getParameter("name");
		String telNumber = request.getParameter("telNum");
		String account = request.getParameter("account");
		String password = request.getParameter("password");

		MemberVO vo = new MemberVO();

		if ((name.trim()).equals("")) {
			vo.setName("없음");
		} else {
			vo.setName(name);
		}
		if ((telNumber.trim()).equals("")) {
			vo.setTelNumber("없음");
		} else {
			vo.setTelNumber(telNumber);
		}
		if ((account.trim()).equals("")) {
			vo.setAccount("없음");
		} else {
			vo.setAccount(account);
		}
		if ((password.trim()).equals("")) {
			vo.setPassword("없음");
		} else {
			vo.setPassword(password);
		}
		request.setAttribute("obj", vo);
		request.getRequestDispatcher("/jspexam/memberView.jsp").forward(request, response);

	}

}
