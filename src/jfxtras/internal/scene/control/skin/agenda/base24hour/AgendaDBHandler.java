package jfxtras.internal.scene.control.skin.agenda.base24hour;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.Agenda.Appointment;
import jfxtras.scene.control.agenda.Agenda.AppointmentGroup;

public class AgendaDBHandler {
	Connection con;
	static {
		// ClassLoader.getSystemClassLoader().loadClass("oracle.jdbc.driver.OracleDriver");
		try {
			// 1. 드라이버 연결: ojdbc5.jar가 추가되어 있어야 함
			// 드라이버를 통해 DB와 Java를 연결
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Returns the Class object associated with the class
			// or interface with the given string name.
			// Invoking this method is equivalent to:
			System.out.println("Driver Loading Succeed");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Loading Failed");
		}
	}

	public void connect() {
		try {
			// 주어진 User정보로 DB연결
			// con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "user1", "user1");
			// con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "only", "only");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "chloe", "chloe");
			System.out.println("DB connect Success!!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean updateAppointmentDB(Appointment appointment) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		connect();
		System.out.println(appointment.getAppointmentId());
		System.out.println(appointment.getStartLocalDateTime());
		System.out.println(appointment.getEndLocalDateTime());
		System.out.println(appointment.getSummary());
		System.out.println(appointment.getDescription());
		System.out.println(appointment.getAppointmentGroup().getDescription());
		System.out.println(appointment.getStatus());
		try {
			// New Appointment인지 Edit Appointment인지 체크하기 위해 DB Table을 ID로 검색
			stmt = con.prepareStatement("select * from Appointment where ID = ? ");
			stmt.setString(1, appointment.getAppointmentId());
			System.out.println("Select stmt: " + stmt);
			rs = stmt.executeQuery();
			if (rs.next()) {
				int status = 0;
				if (appointment.getStatus().equals("New"))
					status = 0;
				if (appointment.getStatus().equals("Confirm"))
					status = 1;
				if (appointment.getStatus().equals("Reject"))
					status = 2;

				System.out.println("Edit Appointment: " + appointment.getAppointmentId());
				// Appointment 값을 Update
				stmt = con.prepareStatement(
						"update Appointment set startdatetime = ?, enddatetime = ?, summary = ?, description = ?, appointgroup = ?, status = ? where ID = ?");

				System.out.println("Edit stmt: " + stmt);
				stmt.setString(1, appointment.getStartLocalDateTime().toString());
				stmt.setString(2, appointment.getEndLocalDateTime().toString());
				stmt.setString(3, appointment.getSummary());
				stmt.setString(4, appointment.getDescription());
				stmt.setString(5, appointment.getAppointmentGroup().getDescription());
				stmt.setInt(6, status);
				stmt.setString(7, appointment.getAppointmentId());
				try {
					stmt.executeQuery();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			} else {
				System.out.println("New Appointment: " + appointment.getAppointmentId());
				// Table에 새로 Appointment를 insert
				int status = 0;
				if (appointment.getStatus().equals("New"))
					status = 0;
				if (appointment.getStatus().equals("Confirm"))
					status = 1;
				if (appointment.getStatus().equals("Reject"))
					status = 2;

				stmt = con.prepareStatement("insert into Appointment values(?,?,?,?,?,?,?)");
				stmt.setString(1, appointment.getAppointmentId());
				stmt.setString(2, appointment.getStartLocalDateTime().toString());
				stmt.setString(3, appointment.getEndLocalDateTime().toString());
				stmt.setString(4, appointment.getSummary());
				stmt.setString(5, appointment.getDescription());
				stmt.setString(6, appointment.getAppointmentGroup().getDescription());
				stmt.setInt(7, status);
				System.out.println("insert stmt: " + stmt);
				try {
					stmt.executeQuery();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stmt.close();
				if (rs != null)
					rs.close();
				con.close();
			} catch (SQLException e) {

			}
		}

		return true;

	}

	public void disconnect() {

	}

	public ArrayList<Appointment> getAllAppointment(Agenda agenda) {
		System.out.println("getAllAppointment() aclled");
		ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();
		ResultSet rs = null;
		connect();
		Statement stmt = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from Appointment");
			while (rs.next()) {
				System.out.println("Add exising Appointments");
				String id = rs.getString("id"); // id값을 불러옴
				String startdatetime = rs.getString("startdatetime");
				String enddatetime = rs.getString("enddatetime");
				String summary = rs.getString("summary");
				String description = rs.getString("description");
				String appointgroup = rs.getString("appointgroup");
				int intStatus = rs.getInt("status");
				String status = null;
				if (intStatus == 0)
					status = "New";
				if (intStatus == 1)
					status = "Confirm";
				if (intStatus == 2)
					status = "Reject";

				// setup appointment groups
				final Map<String, Agenda.AppointmentGroup> lAppointmentGroupMap = new TreeMap<String, Agenda.AppointmentGroup>();
				for (Agenda.AppointmentGroup lAppointmentGroup : agenda.appointmentGroups()) {
					lAppointmentGroupMap.put(lAppointmentGroup.getDescription(), lAppointmentGroup);
				}

				appointmentList.add(new Agenda.AppointmentImplLocal().withAppointmentId(id)
						.withStartLocalDateTime(LocalDateTime.parse(startdatetime))
						.withEndLocalDateTime(LocalDateTime.parse(enddatetime)).withSummary(summary)
						.withDescription(description).withAppointmentGroup(lAppointmentGroupMap.get(appointgroup))
						.withStatus(status));
			} /*else {
				System.out.println("검색된 일정이 없습니다");
			}*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return appointmentList;
	}

	public void removeAppointment(Appointment appointment) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		connect();
		try {
			// New Appointment인지 Edit Appointment인지 체크하기 위해 DB Table을 ID로 검색
			stmt = con.prepareStatement("delete from Appointment where ID = ? ");
			stmt.setString(1, appointment.getAppointmentId());
			System.out.println("Select stmt: " + stmt);
			stmt.executeQuery();
			System.out.println("Appointment deleted from DB");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stmt.close();
				if (rs != null)
					rs.close();
				con.close();
			} catch (SQLException e) {

			}
		}
		
	}
}
