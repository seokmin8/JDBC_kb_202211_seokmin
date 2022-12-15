package main.java.com.study.jdbc.main.dml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.java.com.study.jdbc.util.DBConnection;

public class JdbcInsert2 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		List<String> usernameList = new ArrayList<>();
		
		while(true) {
			System.out.print("등록할 아이디 입력: ");
			usernameList.add(scanner.nextLine());
			// 입력받는값을 바로 add 시킨다
			System.out.print("아이디를 추가로 등록 하시겠습니까? ( Y/y, 취소하려면 아무키나 입력하세요.)");
			String selected = scanner.nextLine();
			// 추가
			if(!"yY".contains(selected.isBlank() ? "n" : selected)) {
				// .isBlank 비었는지 확인 -> 비었으면 selected(입력받은값으로 contains문 진행, 안비었으면 n이 포함되었는지 확인
				
				// y가 아닌 다른값을 입력했을 때! .contains <= 포함하고 있냐 묻는거 
				// if(!"yY".contains(scanner.nextLine().substring(0, 1)))
				// .subString(0에서 1까지 자름, y가 포함되어 있는지 )
				break;
			}
		}
		
		Connection con = DBConnection.getInstance().getConnection();
		String prefixSql = "insert into user_mst values";
		// 머리말
		String valuesBody = "";
		// 이어서 몸통에 추가될 데이터 값
		String suffixSql = ";";
		// 마지막은 ; 주고 마무리!
		// 위의 값들이 완성이 안되면 pstmt 안됨.
		
		for(int i = 0; i < usernameList.size(); i++) {
			valuesBody += "(0, ?)";
			// autoincrement 이면 첫 값은 0으로 시작
			// (0, ? <- 1번 ),(0, ? <- 2번),(0, ? <- 3번물음표)
			if(i < usernameList.size() - 1) {
				// usernameList 보다 작을 때 쉼표
				valuesBody += ", ";
			}
		}
		System.out.println(valuesBody);
		
		
		try {
			PreparedStatement pstmt = con.prepareStatement(prefixSql + valuesBody + suffixSql);
			for(int i = 0; i < usernameList.size(); i++) {
				// 첫번째 물음표
				pstmt.setString(i + 1, usernameList.get(i));
				
			}
			int successCount = pstmt.executeUpdate();
			System.out.println(successCount + "건 등록완료!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 이 코드에서 query문만 바뀐다면 update, delete
		// where 에 ?로 채워주면 된다
		// select만 excuteUpdate 해준다 
		
		//System.out.println(usernameList);

	}

}
