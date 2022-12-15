package main.java.com.study.jdbc.main;

import java.sql.Connection;

import main.java.com.study.jdbc.util.DBConnection;

public class JdbcTest1 {

	public static void main(String[] args) {
		// System.out.println(Driver.class.getName());
		Connection connection = DBConnection.getInstance().getConnection();
		
		System.out.println(connection);
		// 주소가 잡히면 생성! null이면 안된것

	}

}
