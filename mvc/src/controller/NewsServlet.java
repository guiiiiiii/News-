package controller;

import java.lang.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.NewsDAO;
import model.vo.NewsVO;

@WebServlet("/news")
public class NewsServlet extends HttpServlet {
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      NewsDAO dao = new NewsDAO();
      NewsVO vo = new NewsVO();
      String id = request.getParameter("id");

      if (id != null) {
         if (request.getParameter("action").equals("delete")) { // delete
            vo.setId(Integer.parseInt(id));
            boolean result = dao.delete(Integer.parseInt(id));
            if (result) {
               request.setAttribute("list", dao.listAll());
            } else {
               request.setAttribute("msg", "ㅠㅠ");
            }
         } // delete 끝
         if(request.getParameter("action").equals("read")) {// listOne
            NewsVO list = dao.listOne(Integer.parseInt(id));
            if (list != null) { // list가 null이 아니면 글 하나를 가져옴
               request.setAttribute("list", dao.listAll());
               request.setAttribute("list1", list);
            } else {
               request.setAttribute("msg", "키워드를 포함한 글이 없어요..");
            }
         }
      } else {// 전달된게 없으면 글 모두 출력하기

         ArrayList<NewsVO> list = dao.listAll();
         if (list.size() != 0) {
            request.setAttribute("list", list);
         } else {
            request.setAttribute("msg", "글을 등록해주세욤..");
         }
      }
      request.getRequestDispatcher("/jspexam/news.jsp").forward(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
      NewsVO vo = new NewsVO();
      NewsDAO dao = new NewsDAO();
      String id = request.getParameter("id");

      // 글작성
      if (request.getParameter("action").equals("insert")) { 
         vo.setTitle(request.getParameter("title"));
         vo.setWriter(request.getParameter("writer"));
         vo.setContent(request.getParameter("content"));

         boolean result = dao.insert(vo);
         if (result) {
            request.setAttribute("list", dao.listAll());
         } else
            request.setAttribute("msg", "오류 발생 ㅠㅠ");
      }
      else if(request.getParameter("action").equals("update")) { //글 수정
         vo.setTitle(request.getParameter("title"));
         vo.setWriter(request.getParameter("writer"));
         vo.setContent(request.getParameter("content"));
         vo.setId(Integer.parseInt(id));
         boolean result = dao.update(vo);
         if (result) {
            request.setAttribute("list", dao.listAll());
         } else {
            request.setAttribute("msg", "ㅠㅠ");
         }
      }
      
      request.getRequestDispatcher("/jspexam/news.jsp").forward(request, response);
   }

}