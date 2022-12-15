package main.java.com.study.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class DBConnection {

	private static DBConnection instance = null;
	
	private DBConnection() {}
	
	public static DBConnection getInstance() {
		if(instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}
	
	public Connection getConnection() {
		Connection connection = null;
		
		String url = null;
		String username = null;
		String password = null;
		
		try {
			Class.forName(Driver.class.getName());
			System.out.println("데이터베이스 드라이브 로딩 성공!");
			// Class.forName("Driver.class.getName"); 이렇게 문자열로 작성 가능
			// forName 하는 순간 drive 내에 객체를 생성!
			// 생성이 되어야 실행이 가능하다!!
			url = "jdbc:mysql://localhost:3306/subquery_study";
			username = "root";
			password = "root";
			connection = DriverManager.getConnection(url, username, password);
			// 3가지가 필요함 url username password
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 연결 실패!");
		}
		return connection;
	}
	
}
