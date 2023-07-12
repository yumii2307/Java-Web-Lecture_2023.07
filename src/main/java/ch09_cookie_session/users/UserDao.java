package ch09_cookie_session.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.time.LocalDate;

public class UserDao {
	// DB 접속
	public Connection getConnection() {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/project");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public User getUser(String uid) {
		Connection conn = getConnection();
		String sql = "select * from users where uid=?";
		User user = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				uid = rs.getString(1);
				String password = rs.getString(2);
				String uname = rs.getString(3);
				String email = rs.getString(4);
				LocalDate regDate = LocalDate.parse(rs.getString(5));
				int isDeleted = rs.getInt(6);
				user = new User(uid, password, uname, email, regDate, isDeleted);
			}
			rs.close(); pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public List<User> getUserList() {
		List<User> list = new ArrayList<>();
		Connection conn = getConnection();
		String sql = "select * from users where isDeleted=0 order by regDate desc";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String uid = rs.getString(1);
				String password = rs.getString(2);
				String uname = rs.getString(3);
				String email = rs.getString(4);
				LocalDate regDate = LocalDate.parse(rs.getString(5));
				int isDeleted = rs.getInt(6);
				User user = new User(uid, password, uname, email, regDate, isDeleted);
				
				list.add(user);
			}
			rs.close(); stmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void insertUser(User u) {
		Connection conn = getConnection();
		String sql = "insert into users values (?, ?, ?, ?, default, default)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u.getUid());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, u.getUname());
			pstmt.setString(4, u.getEmail());
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateUser(User u) {
		Connection conn = getConnection();
		String sql = "update users set password=?, uname=?, email=?, regDate=? where uid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u.getPassword());
			pstmt.setString(2, u.getUname());
			pstmt.setString(3, u.getEmail());
			pstmt.setString(4, u.getRegDate().toString());
			pstmt.setString(5, u.getUid());
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateUserWithoutPassword(User u) {
		Connection conn = getConnection();
		String sql = "update users set uname=?, email=? where uid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u.getUname());
			pstmt.setString(2, u.getEmail());
			pstmt.setString(3, u.getUid());
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser(String uid) {
		Connection conn = getConnection();
		String sql = "update users set isDeleted=1 where uid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 }
