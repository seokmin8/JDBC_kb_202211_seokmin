package main.java.com.study.jdbc.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.com.study.jdbc.util.DBConnection;

public class JdbcTest2 {

   public static void main(String[] args) {
      Connection connection = DBConnection.getInstance().getConnection();
      
      String sql = "select * from board_mst";
      
      PreparedStatement pstmt;
      try {
         pstmt = connection.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery();
         
         System.out.println("id\ttitle\t\tcontent\t\t\tread_count\twriter_id");
         
         while(rs.next()) { 
            System.out.println("id : " + rs.getInt(1) 
            + "\t title : " + rs.getString(2) 
            + "\t content : " + rs.getString(3)
            + "\t read_count " + rs.getInt(4)
            + "\t writer_id " + rs.getInt(5));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } 
      
   }
   

}