 package model.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.vo.NewsVO;

public class NewsDAO {

   private Connection connectDB() throws SQLException {
      Connection conn = null;// 연결객체
      DataSource ds = null;
      try {
    	  InitialContext ic= new InitialContext();
    	  ds = (DataSource) ic.lookup("java:comp/env/jdbc/myoracle");
    	  conn = ds.getConnection();
    	} catch (Exception e) {
    	  e.printStackTrace();
    	}

      return conn;
   }

   private void close(Connection conn, Statement stmt, ResultSet rs) {
      try {
         conn.close();
         stmt.close();
         rs.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }

   }

   public ArrayList<NewsVO> listAll() {

      ArrayList<NewsVO> list = new ArrayList<>();

      try (Statement stmt = connectDB().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, writer, title, content, " + "to_char(writedate,"
                  + "'yyyy\"년\" mm\"월\" dd\"일\"') " + "as writedate, cnt FROM news");

      ) {

         if (rs.next()) {
            do {
               NewsVO vo = new NewsVO();
               vo.setId(rs.getInt("id"));
               vo.setWriter(rs.getString("writer"));
               vo.setTitle(rs.getString("title"));
               vo.setContent(rs.getString("content"));
               vo.setWritedate(rs.getString("writedate"));
               vo.setCnt(rs.getInt("cnt"));

               list.add(vo);

            } while (rs.next());

         } else {
            System.out.println("추출된 행이 없슈!!");
         }

         close(connectDB(), stmt, rs);

      } catch (SQLException sqle) {
         sqle.printStackTrace();
         System.out.println("SQL 오류");
      }

      return list;
   }

   public NewsVO listOne(int id) {

      NewsVO vo = new NewsVO();
      try (Statement stmt = connectDB().createStatement();
            ResultSet rs1 = stmt.executeQuery("UPDATE news set cnt=cnt+1 where id="+id);
            
          ResultSet rs = stmt.executeQuery("SELECT id, writer, title, content, " + "to_char(writedate,"
                      + "'yyyy\"년\" mm\"월\" dd\"일\"') " + "as writedate, cnt FROM news where id=" + id);
      ) {

         if (rs.next()) {
            do {

               vo.setId(rs.getInt("id"));
               vo.setWriter(rs.getString("writer"));
               vo.setTitle(rs.getString("title"));
               vo.setContent(rs.getString("content"));
               vo.setWritedate(rs.getString("writedate"));
               vo.setCnt(rs.getInt("cnt"));

            } while (rs.next());

         } else {
            System.out.println("추출된 행이 없슈!!");
         }

         close(connectDB(), stmt, rs);

      } catch (SQLException sqle) {
         sqle.printStackTrace();
         System.out.println("SQL 오류");
      }

      return vo;
   }

   public boolean update(NewsVO vo) {

      boolean result = true;

      try (PreparedStatement pstmt = connectDB()
            .prepareStatement("UPDATE news set writer=?,title=?,content=? where id = ?");) {
         pstmt.setString(1, vo.getWriter());
         pstmt.setString(2, vo.getTitle());
         pstmt.setString(3, vo.getContent());
         pstmt.setInt(4, vo.getId());

         pstmt.executeUpdate();

         System.out.println(vo);
      } catch (SQLException sqle) {
         result = false;
         sqle.printStackTrace();
         System.out.println("SQL 오류");
      }

      return result;
   }
   
   public boolean insert(NewsVO vo) {

      boolean result = true;

      try (PreparedStatement pstmt = connectDB()
            .prepareStatement("INSERT into news values(news_seq.nextval," + "?,?,?,sysdate,0)");) {

         pstmt.setString(1, vo.getWriter());
         pstmt.setString(2, vo.getTitle());
         pstmt.setString(3, vo.getContent());

         pstmt.executeUpdate();
         System.out.println(vo);
      } catch (SQLException sqle) {
         result = false;
         sqle.printStackTrace();
         System.out.println("SQL 오류");
      }

      return result;
   }

   public boolean delete(int id) {

      boolean result = true;
      try (PreparedStatement pstmt = connectDB().prepareStatement("DELETE from news where id=?");) {

         pstmt.setInt(1, id);
         pstmt.executeUpdate();

      } catch (SQLException sqle) {
         result = false;
         sqle.printStackTrace();
         System.out.println("SQL 오류");
      }

      return result;
   }

}