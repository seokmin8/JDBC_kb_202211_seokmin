package main.java.com.study.jdbc.main.dml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import main.java.com.study.jdbc.util.DBConnection;

public class JdbcSelect2 {

   public static void main(String[] args) {
	   Scanner sc = new Scanner(System.in);
	   System.out.println("작성자 id: ");
	   int writerId = sc.nextInt();
	   // String writerId1 = sc.nextLine();
	   // 입력을 받는다
	   
	   Connection con = DBConnection.getInstance().getConnection();
	   // 가장먼저 파생되어지는 con 그다음 그안에서 pstmt
      
      // String sql = "select * from board_mst";
       String sql = " select * from board_mst where writer_id = ?";
       
      PreparedStatement pstmt;
      // 1. 쿼리문 영역
      try {
         pstmt = con.prepareStatement(sql);
         // pstmt를 생성할 메소드를 가지고있다.
         // 2. sql 문자열 값을 가지고 있다
         pstmt.setInt(1, writerId);
         //pstmt.setString(1, writerId) << String으로 값을 넣을 수 있다
         
         // sql의 쿼리문을 왼쪽에서 오른쪽으로 읽어가다가 첫번재 ? 를 만났을때
         // 첫번째는 1번 물음표! 2번째는 2번 => 1번 물음표에서 writerId를 대체
         // writerId는 스캐너로 입력받는 값
         ResultSet rs = pstmt.executeQuery();
         // select 처럼 보여지는 결과에서만 resultSet을 사용! 
         // update, del, insert 등의 결과출력 없이 데이터반영만 되는것에는 필요없음
         // 3. 쿼리문 영역에 작성한다
         // 쿼리 실행결과를  rs로 대입
         System.out.println("id\ttitle\t\tcontent\t\t\tread_count\twriter_id");
         
         while(rs.next()) { 
            System.out.println("id : " + rs.getInt(1) 
            + "\t title : " + rs.getString(2) 
            + "\t content : " + rs.getString(3)
            + "\t read_count " + rs.getInt(4)
            + "\t writer_id " + rs.getInt(5));
         }
         /*	while(rs.next())
          * 	System.out.println(rs.getInt(1) + " | "
          * + rs.getString(2) + " | "
          * + rs.getString(3) + " | "
          * +rs.getInt(4) + " | "
          * +rs.getInt(5);
          * }
          * */
         
      } catch (SQLException e) {
         e.printStackTrace();
      } 
      
   }
   

}