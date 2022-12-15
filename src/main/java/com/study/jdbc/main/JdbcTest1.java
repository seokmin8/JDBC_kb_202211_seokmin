package main.java.com.study.jdbc.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.com.study.jdbc.util.DBConnection;

public class JdbcTest1 {

	// 2개의 프로그램이 합쳐지는 코드
	
	public static void main(String[] args) {
		// System.out.println(Driver.class.getName());
		Connection connection = DBConnection.getInstance().getConnection();
		// get에서 connection이 이루어짐
		// 싱글톤 패턴! DBconnection은 직접생성.getinstance ~ 부터는 싱글톤패턴
		// DB에 연결함.
		
		//System.out.println(connection);
		// 주소가 출력되면 생성! null이면 안된것
		
		String sql = "select * from score_mst";
		// 쿼리문 지정!
		
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			// 쿼리문을 입력하는 공간 = 연결된객체.공간을 생성해달라(공간에 sql이 들어간다)
			// 이상태로 가지고만 있고 실행되는것은 아님
			ResultSet rs = pstmt.executeQuery();
			// 여기서 쿼리문이 실행된다 -> execute => resultset (결과를 가지고있는 set)
			System.out.println("id\tname\t\tscore");
			// 컬럼명 만들어주는 1행
			
			// result -> java.sql
			while(rs.next()) {
				// rs.next의 값이 false가 될때까지 반복
				// 하나씩 꺼내지다 score의 데이터가 다 꺼내질때까지 (rs는 한번 사용하면 다시 사용안됨)
				System.out.println("id: " + rs.getInt(1)
				+ "\t name: " + rs.getString(2)
				+ "\t score: " + rs.getInt(3)); 
			}	// rs.getint => rs.next 현재의 rs는 첫번째 행의 값(첫 컬럼 값=id(컬럼은 1번부터 시작한다))
				// rs.getString => rs.next가 1번째 컬럼에서 int string int 데이터를 다 가져온다 그다음 2번째 컬럼으로 넘어가서 똑같은 반복
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 커넥션 객체를 통해 preparedStatement 영역이 sql 쿼리문
	}
}
