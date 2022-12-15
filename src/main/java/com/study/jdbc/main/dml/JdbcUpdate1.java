package main.java.com.study.jdbc.main.dml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import main.java.com.study.jdbc.util.DBConnection;

public class JdbcUpdate1 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("수정할 계정의 id값을 입력하세요 : ");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.print("수정할 아이디 입력 : ");
		String username = sc.nextLine();
		
		Connection con = DBConnection.getInstance().getConnection();
		String sql = "update user_mst set username = ? where id = ?";
		// insert하는 코드에서 쿼리문만 변경되었다 ?의 위치로 해당 키값을 수정해줄 수 있다
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			// 1번 물음표 username => id값을 정한다 (해당값을 찾아감)
			pstmt.setInt(2, id);
			// 2번 물음표 where => id값을 수정하는 거
			int successCount = pstmt.executeUpdate();
			System.out.println(successCount + "건 수정완료!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

// delete는 해보자 where 쪽에 수정해가면서 해보자

