package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	private Connection conn;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/bbs";
			String dbID = "root";
			String dbPW = "1234";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {        
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "1234");
            System.out.println("MySQL Connection Success");
        } 

        catch (SQLException e) {
            System.out.println("Database와 연결이 되지 않았습니다");
            e.printStackTrace();
        }

        return conn;
    }
	
	public int login(String userID, String userPW) {
		String SQL = "SELECT userPW FROM user WHERE userID = ?";
		
		try {
			pstat = conn.prepareStatement(SQL);
			pstat.setString(1, userID);

			rs = pstat.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(userPW)) {
					return 1;
				}
				else {
					return 0;
				}
			}
			return -1;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -2;
	}
	
	public String backname(String userID) {
		String SQL = "SELECT name FROM user WHERE userID = ?";
		try {
			pstat = conn.prepareStatement(SQL);
			pstat.setString(1, userID);

			rs = pstat.executeQuery();
			if(rs.next()) {
					return rs.getString(1);
				}
			return "잘못된 데이터베이스 입니다.";
		}catch(Exception e) {
			e.printStackTrace();
}
		return "잘못된 입력입니다.";
}
	
	public int joining(String userID, String userPW, String name, String gender, String email) {
		String SQL = "INSERT INTO user (userID,userPW,name,gender,email,admin) values(?,?,?,?,?,?)";
		PreparedStatement psta;
		try {
			psta = conn.prepareStatement(SQL);
			psta.setString(1, userID);
			psta.setString(2, userPW);
			psta.setString(3, name);
			psta.setString(4, gender);
			psta.setString(5, email);
			psta.setBoolean(5, false);
			
			psta.executeUpdate();
			return 1;
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("error!!! error!!!");
		}
		return 0;
	}
	
	public int checkID(String userID) {
		String SQL = "SELECT * From user WHERE userID = ?";
		try {
			pstat = conn.prepareStatement(SQL);
			pstat.setString(1, userID);

			rs = pstat.executeQuery();
			if(rs.next()) {
					return 1;
				}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}





