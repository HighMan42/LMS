package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import dto.Books;
import util.DbConn;


public class BooksDao {
	
	public boolean insertBook(Books book) {
		boolean status = false;	
		Connection conn = DbConn.getConnection();
		String sql = "insert into books values(?,?,?, default)";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, book.getId());
			ps.setString(2, book.getName());
			ps.setString(3, book.getAuthor());
			int rows = ps.executeUpdate();
			if (rows>0) {
				status = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public ArrayList<Books> getBooks(){
		ArrayList<Books> empList = new ArrayList<>();
		try {
			Connection conn = DbConn.getConnection();
			String sql = "select * from books order by accession_no";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String author = rs.getString(3);
				String availability = rs.getString(4);
				Books b = new Books(id, name, author, availability);
				empList.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empList;
	}
	
	public boolean updateBooks(int id, String name, String author) {
		boolean status = false;
		Connection conn = DbConn.getConnection();
		String sql = "select * from books where accession_no=?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String sql1 = "update books set title=?, author=? where accession_no = ?";
				ps = conn.prepareStatement(sql1);
				ps.setString(1, name);
				ps.setString(2, author);
				ps.setInt(3, id);
				int rows = ps.executeUpdate();
				if(rows>0) {
					status = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean deleteBooks(int id) {
		boolean status = false;
		Connection conn = DbConn.getConnection();
		String sql = "delete from books where accession_no=?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int rows = ps.executeUpdate();
			if (rows>0) {
				status = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
}
