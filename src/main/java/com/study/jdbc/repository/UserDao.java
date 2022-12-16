package main.java.com.study.jdbc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lombok.RequiredArgsConstructor;
import main.java.com.study.jdbc.entity.User;
import main.java.com.study.jdbc.util.DBConnectionMgr;

// @RequiredArgsConstructor
// 필수.! args가 필수
public class UserDao {
	// 데이터베이스에 접근하는 객체
//	private final DBConnectionMgr pool;
	// 상수는 무조건 초기화가 되어야 한다
	
//	public UserDao(DBConnectionMgr pool) {
//		this.pool = pool;
//	}
// 	위의 3줄을 requiredArgsConstructor  임!
	
	private DBConnectionMgr pool;
	
	public UserDao() {
		pool = DBConnectionMgr.getInstance();
	}
	
	public int insertUser(User user) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// select 경우엔 rs를 사용해준다
		String sql = null;
		int successCount = 0;
		
		try {
			con = pool.getConnection(); // try catch 해줘야하는 부분
			sql = "insert into user_mst values(0, '?')";
			// autoicrement 로 키값이 증가
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUsername()); 
			// 위의 insertUser를?
			successCount = pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys(); // 선언은 위에서 해줌
			if(rs.next()) {
				user.setUser_Id(rs.getInt(1));
				// rs.get키값이 int이기 때문에 user에 바로 넣어줌
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// 꼭 해줘야함 아래줄부터
		} finally {
			pool.freeConnection(con, pstmt, rs);
			// db에 들어오고 나갈 때 커넥션을 끊어줌
		}
		return successCount;
	}
	
}
