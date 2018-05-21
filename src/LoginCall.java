

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginCall {
	Connection con;
	static {
		//ClassLoader.getSystemClassLoader().loadClass("oracle.jdbc.driver.OracleDriver");
		try {
			// 1. 드라이버 연결: ojdbc5.jar가 추가되어 있어야 함
			// 드라이버를 통해 DB와 Java를 연결
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Returns the Class object associated with the class
			// or interface with the given string name.
			// Invoking this method is equivalent to:
		} catch (ClassNotFoundException e) {
			System.out.println(">>>");
		}
	}

	public void connect() {
		try {			
			// 주어진 User정보로 DB연결
//			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","user1","user1");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","chloe","chloe");
			System.out.println("DB connect Success!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 해당 ID와 PASSWORD를 가진 유저를 DB에서 검색해
	// 해당 유저가 있으면 User 객체를 생성해서 return
	public User login(String id, String password) {
		Statement stmt = null;
		ResultSet rs = null;
		User user = null;
		connect();
		try {
			stmt = con.createStatement();
			
			// Creates a Statement object for sending SQL statements to the
			// database.
			// SQL statements without parameters are normally executed using
			// Statement objects.
		
			String sql = "select * from member where id='" + id + "' and passwd='" + password + "'";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			// ResultSet: A table of data representing a database result set,
			// which is usually generated by executing a statement that queries
			// the database.
			if (rs.next()) {

				id = rs.getString("id"); // id값을 불러옴
				password = rs.getString("passwd");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				String address = rs.getString("address");
				user = new User(id, password, name, age, address, email);
			} else{
				System.out.println("User not found");
			}
			//sql = "insert into member values('bbb','1234','유보경',25,'Cheongju','chloe@gmail.com')";
			//stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				if(rs!=null)
					rs.close();
				con.close();
			} catch (SQLException e) {

			}
		}
//		return user;
		return new User("bbb","1234","유보경",25,"Cheongju","chloe@gmail.com");
	}

	public void disconnect() {

	}
}