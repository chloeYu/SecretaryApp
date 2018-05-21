import java.awt.Font;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.TreeMap;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.util.Callback;
import javafx.util.Duration;
import jfxtras.animation.Timer;
import jfxtras.internal.scene.control.skin.agenda.base24hour.AgendaDBHandler;
import jfxtras.scene.control.CalendarPicker;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.Agenda.Appointment;
import jfxtras.scene.control.agenda.Agenda.LocalDateTimeRange;
import jfxtras.scene.layout.GridPane;
import jfxtras.util.NodeUtil;


public class MainController implements Initializable {
	@FXML
	private Pane root;
	@FXML
	private TextField id;
	@FXML
	private PasswordField pw; // 사용자 아이디와 패스워드 읽어옴
	@FXML
	private Button login; // 사용자 로그인 버튼
	@FXML
	private Button sendButton; // 채팅 메시지 보내기 버튼
	@FXML
	private TabPane tabPane;
	@FXML
	private TextArea chatRoom;
	@FXML
	private TextField chatMessage;
	@FXML
	private Tab calendar;
	@FXML
	private Tab calTab;
	@FXML
	static GridPane lGridPane;
	@FXML
	private Agenda agenda;
	@FXML
	private CalendarPicker secretaryCalendar;
	@FXML
	private AnchorPane calContainer;
	@FXML
	private MenuItem logout;
	@FXML
	private MenuItem exit;
	@FXML
	private MenuItem about;
	private int userType = 0; // 비서인지 CEO인지를 구분짓는 flag int
	
	private String clientId; // 채팅에서 사용할 ID
	
	private MultichatGUIClient client;
	

	
	@Override 
	public void initialize(URL location, ResourceBundle resources) {
		
		about.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {                                                   
				Alert alert = new Alert(AlertType.INFORMATION);
				System.out.println("About");
				alert.setTitle("About");
				alert.setHeaderText(null);
				alert.setContentText("Secretary Application\n\nby Team3");
				alert.showAndWait();
			}
		});
		

		exit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});

		// Agenda 초기화
		System.out.println("Agenda Initializing..");
		initAgenda();
		System.out.println("Agenda Initialized..");
		
		// Method1: bind picker to agenda
		CalendarPicker lCalendarPicker = new CalendarPicker();
		secretaryCalendar.setCalendar(Calendar.getInstance());
		agenda.displayedCalendar().bind(secretaryCalendar.calendarProperty());

		// 로그인 버튼 핸들러 등록
		login.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				clientId = id.getText();
				if (login(clientId, pw.getText())) {
					if (userType == 0) { // 사용자가 비서일 경우
						System.out.println("Secretary logged in");
						tabPane.setVisible(true);
						chatRoom.setVisible(true);
						chatMessage.setVisible(true);
						sendButton.setVisible(true);
						agenda.setVisible(true);
					} else if (userType == 1) { // 사용자가 CEO일 경우
						System.out.println("CEO logged in");
						tabPane.setVisible(true);
						chatRoom.setVisible(true);
						//chatRoom.setText("사장님이 입장하셨습니다");
						chatMessage.setVisible(true);
						sendButton.setVisible(true);
						agenda.setVisible(true);
					}
					// 채팅클라이언트 호출
					// createChattingClient();
					login.setVisible(false);
					id.setVisible(false);;
					pw.setVisible(false);;
				} else {
					Alert alert = new Alert(AlertType.WARNING);
					System.out.println("로그인 실패");
					alert.setTitle("로그인 실패");
					alert.setHeaderText(null);
					alert.setContentText("잘못된 아이다/비밀번호 입니다\n\n다시 로그인하세요");
					alert.showAndWait();
//					pw.clear();
//					id.clear();
					id.requestFocus();
				}
			}
		});
		
		logout.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (userType == 0) { // 사용자가 비서일 경우
					System.out.println("Secretary logged in");
					tabPane.setVisible(false);
					chatRoom.setVisible(false);
					//chatRoom.setText("비서님이 입장하셨습니다");
					chatMessage.setVisible(false);
					sendButton.setVisible(false);
					agenda.setVisible(false);
				} else if (userType == 1) { // 사용자가 CEO일 경우
					System.out.println("CEO logged in");
					tabPane.setVisible(false);
					chatRoom.setVisible(false);
					chatRoom.setText("사장님이 입장하셨습니다");
					chatMessage.setVisible(false);
					sendButton.setVisible(false);
					agenda.setVisible(false);
				}
				//id.clear(); 
				id.setVisible(true);
				//pw.clear(); 
				pw.setVisible(true);
				login.setVisible(true);
			}
		});
		// CalendarPicker 초기화
		calendar.setOnSelectionChanged(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				if (calendar.isSelected()) {
					System.out.println("Calendar Tab selected");
				}

			}
		});

		// Calculator탭 이벤트 핸들러
		calTab.setOnSelectionChanged(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				if (calTab.isSelected()) {
					Calculator calc = new Calculator();
					calc.setEventListener(tabPane.getScene());
					calContainer.getChildren().add(calc.getCalculator());
				}

			}
		});
	}

	private void createChattingClient() {
		if(clientId == null){
			clientId = "손님";
		}
		System.out.println(clientId);
        client = new MultichatGUIClient(clientId, chatRoom, chatMessage, sendButton);
        System.out.println("ChatRoom Logged in by : " + clientId);
        client.launchClient();
		
	}

	private void initAgenda() {
		System.out.println("initAgenda()");
		AgendaDBHandler dbHandler = new AgendaDBHandler();
		;
		// setup appointment groups
		final Map<String, Agenda.AppointmentGroup> lAppointmentGroupMap = new TreeMap<String, Agenda.AppointmentGroup>();
		for (Agenda.AppointmentGroup lAppointmentGroup : agenda.appointmentGroups()) {
			lAppointmentGroupMap.put(lAppointmentGroup.getDescription(), lAppointmentGroup);
		}
		ArrayList<Appointment> tempList = dbHandler.getAllAppointment(agenda);
		Appointment[] appointmentList = tempList.toArray(new Appointment[tempList.size()]);
		agenda.appointments().addAll(appointmentList);

		// accept new appointments
		agenda.newAppointmentCallbackProperty().set(new Callback<Agenda.LocalDateTimeRange, Agenda.Appointment>() {
			@Override
			public Agenda.Appointment call(LocalDateTimeRange dateTimeRange) {
				System.out.println("New Appointment added");
				Appointment newAppointment = new Agenda.AppointmentImplLocal()
						.withAppointmentId(Long.toString(System.currentTimeMillis()))
						.withStartLocalDateTime(dateTimeRange.getStartLocalDateTime())
						.withEndLocalDateTime(dateTimeRange.getEndLocalDateTime()).withSummary("new")
						.withDescription("new").withAppointmentGroup(lAppointmentGroupMap.get("group01"))
						.withStatus("New");

				// DB에 새로운 Appointment를 저장
				dbHandler.updateAppointmentDB(newAppointment);
				// 새로운 Appointment가 생기면 접속해 있는 Client들에게 알림
				client.sender.send("#new#"+clientId+"#"+newAppointment.getStatus());
				return newAppointment;

			}
		});

		// action
		agenda.setActionCallback((appointment) -> {
			showPopup(agenda, "Action on " + appointment);
			System.out.println("show popup");
			return null;
		});

	}

	// 로그인 - DB에서 매치되는 User가 있는지 Search해서 있으면 true리턴, 없으면 false리턴
	boolean login(String id, String pw) {
		System.out.println("login by " + id + ", " + pw);
		
		LoginCall lc = new LoginCall();
		User user = lc.login(id, pw);
		if (user == null) {
			return false;
		} else { // 해당 유저를 찾으면 break
			System.out.println("ID: " + user.getId());
			System.out.println("Name: " + user.getName());
			System.out.println("Email: " + user.getEmail());
			System.out.println("Address: " + user.getAddress());
			return true;
		}
	}

	// if we ever find a better way of doing this...
	protected void showPopup(Node owner, String text) {
		Popup popup = new Popup();
		popup.setAutoFix(true);
		popup.setAutoHide(true);
		popup.setHideOnEscape(true);

		// popup contents
		Label label = new Label(text);
		label.setStyle("-fx-background-color: #FFFFFF;");
		popup.getContent().add(new TextField(text));
		popup.show(owner, NodeUtil.screenX(owner), NodeUtil.screenY(owner));

		new Timer(() -> {
			popup.hide();
		}).withDelay(new Duration(1500)).start();
	}
}
