package model.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.vo.visitorVO;

public class VisitorDAO {
	public ArrayList<visitorVO> listAll() {

		ArrayList<visitorVO> list = new ArrayList<>();
		try {
			Class.forName("oracle.jdbc.OracleDriver");

		} catch (ClassNotFoundException cnfe) {
			System.out.println("드라이버 로딩 오류");
		}
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "java", "java");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT id, name, to_char(writedate," + "'yyyy\"년\" mm\"월\" dd\"알\"')"
						+ "as writedate, memo FROM visitor");

		) {

			if (rs.next()) {
				do {
					visitorVO vo = new visitorVO();
					vo.setId(rs.getInt("id"));
					vo.setName(rs.getString("name"));
					vo.setWritedate(rs.getString("writedate"));
					vo.setMemo(rs.getString("memo"));

					list.add(vo);

				} while (rs.next());

			} else {
				System.out.println("추출된 행이 없슈!!");
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("SQL 오류");
		}

		return list;
	}

	public ArrayList<visitorVO> search(String keyword) {

		ArrayList<visitorVO> list = new ArrayList<>();
		try {
			Class.forName("oracle.jdbc.OracleDriver");

		} catch (ClassNotFoundException cnfe) {
			System.out.println("드라이버 로딩 오류");
		}
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "java", "java");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT id, name, to_char(writedate," + "'yyyy\"년\" mm\"월\" dd\"알\"')"
						+ "as writedate, memo FROM visitor " + "where memo like '%"+keyword+"%'");

		) {

			if (rs.next()) {
				do {
					visitorVO vo = new visitorVO();
					vo.setId(rs.getInt("id"));
					vo.setName(rs.getString("name"));
					vo.setWritedate(rs.getString("writedate"));
					vo.setMemo(rs.getString("memo"));

					list.add(vo);

				} while (rs.next());

			} else {
				System.out.println("추출된 행이 없슈!!");
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("SQL 오류");
		}

		return list;
	}

	public boolean insert(visitorVO vo) {

		boolean result = true;
		try {
			Class.forName("oracle.jdbc.OracleDriver");

		} catch (ClassNotFoundException cnfe) {
			System.out.println("드라이버 로딩 오류");
		}

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "java", "java");
				PreparedStatement pstmt = conn
						.prepareStatement("INSERT into visitor values(visitor_seq.nextval," + "?,sysdate,?)");) {

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getMemo());
			pstmt.executeUpdate();

		} catch (SQLException sqle) {
			result = false;
			sqle.printStackTrace();
			System.out.println("SQL 오류");
		}

		return result;
	}

	public boolean delete(int id) {

		boolean result = true;
		try {
			Class.forName("oracle.jdbc.OracleDriver");

		} catch (ClassNotFoundException cnfe) {
			System.out.println("드라이버 로딩 오류");
		}

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "java", "java");
				PreparedStatement pstmt = conn.prepareStatement("DELETE from visitor where id=?");) {

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
