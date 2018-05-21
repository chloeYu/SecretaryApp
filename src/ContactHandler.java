

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ContactHandler  {

	Connection con;
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {
		}
	}
	
	public void connect() {
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		try {
			con = DriverManager.getConnection(url, "account1", "account1");
		} catch (SQLException e) {
		}

	}
	
	public void InsertContact(Contact c) {
		connect();
		PreparedStatement preparedStatement = null;

		try {
			String sql = "insert into member values(?,?,?,?,?,?,?)";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, c.getCompany());
			preparedStatement.setString(2, c.getName());
			preparedStatement.setString(3, c.getPhone());
			preparedStatement.setString(4, c.getEmail());
			preparedStatement.setString(5, c.getAddr());
			preparedStatement.setString(6, c.getLocation());
			preparedStatement.setString(7, c.getExtra());
			preparedStatement.executeQuery();

		} catch (SQLException e) {
		} finally {
			try {
				preparedStatement.close();
				con.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public boolean deleteContact(String company){
		connect();
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from member where company = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, company);
			pstmt.executeQuery();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return  true;
	}
	
	public void updateContact(Contact c, String company){
		connect();
		PreparedStatement preparedStatement = null;

		try {
			String sql = "update member set values(?,?,?,?,?,?,?) where company = ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, c.getName());
			preparedStatement.setString(2, c.getPhone());
			preparedStatement.setString(3, c.getEmail());
			preparedStatement.setString(4, c.getAddr());
			preparedStatement.setString(5, c.getLocation());
			preparedStatement.setString(6, c.getExtra());
			preparedStatement.setString(7, c.getCompany());
			preparedStatement.executeQuery();

		} catch (SQLException e) {
		} finally {
			try {
				preparedStatement.close();
				con.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
