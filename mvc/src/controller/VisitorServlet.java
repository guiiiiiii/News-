package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.VisitorDAO;
import model.vo.visitorVO;

@WebServlet("/visitor")
public class VisitorServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		VisitorDAO dao = new VisitorDAO();
		String id = request.getParameter("id");
		String keyword = request.getParameter("searchKeyword");
		if (id != null) {
			boolean result = dao.delete(Integer.parseInt(id));
			if (result) {
				request.setAttribute("list", dao.listAll());
			} else {
				request.setAttribute("msg", id + "의 글 삭제에 실패했어요...");
			}
		} else if (keyword != null) {
			ArrayList<visitorVO> list = dao.search(keyword);
			if (list.size() != 0) {
				request.setAttribute("list", list);
			} else {
				request.setAttribute("msg", keyword + "를 포한한 글이 없어요...");
			}
		} else {
			ArrayList<visitorVO> list = dao.listAll();
			if (list.size() != 0) {
				request.setAttribute("list", list);
			} else {
				request.setAttribute("msg", "글이 없어요...");
			}
		}

		request.getRequestDispatcher("/jspexam/visitorView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		visitorVO vo = new visitorVO();
		vo.setName(request.getParameter("name"));
		vo.setMemo(request.getParameter("memo"));
		VisitorDAO dao = new VisitorDAO();
		boolean result = dao.insert(vo);
		if (result) {
			request.setAttribute("msg", "글이 성공적으로 등록되었어요");
		} else
			request.setAttribute("msg", "오류가 발생했어요!");

		request.getRequestDispatcher("/jspexam/visitorView.jsp").forward(request, response);

	}

}
