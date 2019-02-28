package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calcs")
public class CalcServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		int num1 = Integer.parseInt(request.getParameter("num1"));
		int num2 = Integer.parseInt(request.getParameter("num2"));
		String op = request.getParameter("op");
		int result = 0;
		boolean check = true;
		if (op.equals("*"))
			result = num1 * num2;
		if (op.equals("/")) {
			if (num2 == 0)
				check = false;
			else {
				result = num1 / num2;
			}
		}
		if (op.equals("+"))
			result = num1 + num2;
		if (op.equals("-"))
			result = num1 - num2;

		if (check) {
			request.setAttribute("result", result);
			request.getRequestDispatcher("/jspexam/calcResult.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "나눗셈 연산시 두 번째 숫자는 0 일 수 없지");
			request.getRequestDispatcher("/jspexam/errorResult.jsp").forward(request, response);

		}

	}

}
