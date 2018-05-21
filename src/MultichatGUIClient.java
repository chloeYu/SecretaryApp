import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class MultichatGUIClient /* implements ActionListener */ {
	Button sendButton;
	TextArea chatRoom;
	TextField chatMessage;
	// ===============================
	String name;
	ClientSender sender;
	// ===============================

	public MultichatGUIClient(String name, TextArea chatRoom, TextField chatMessage, Button sendButton) {
		System.out.println("Client Created: " + name);
		this.name = name;
		this.sendButton = sendButton;
		this.chatRoom = chatRoom;
		this.chatMessage = chatMessage;
	}

	public void launchClient() {
		sendButton.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				System.out.println("send meg: " + chatMessage.getText());
				// =========================
				sender.send(chatMessage.getText());
				// =========================
				chatMessage.clear();
				chatMessage.requestFocus();
			}
		});

		chatMessage.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				KeyCode keyCode = event.getCode();
				System.out.println("Key Pressed: " + keyCode);
				if (keyCode == KeyCode.ENTER) {
					actionPerformed(null);
				}

			}
		});

		// =================================================
		Socket socket = null;
		try {
			String serverIp = "192.168.50.11";
			socket = new Socket(serverIp, 7777); // 소켓을 생성하여 연결을 요청한다.
			System.out.println("서버에 연결되었습니다.");

			// 메시지 전송용 Thread 생성
			sender = new ClientSender(socket);

			// 메시지 수신용 Thread 생성
			Thread receiver = new Thread(new ClientReceiver(socket));

			receiver.start();
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
		}
		// =================================================
	}

	public void actionPerformed(KeyEvent e) {
		// =========================
		sender.send(chatMessage.getText());
		System.out.println("Send message: " + chatMessage.getText());
		// =========================
		chatMessage.setText("");
		chatMessage.requestFocus();
	}

	// =================================================
	public void setMessage(String message) {
		/*if(message.substring(0, 10))
		System.out.println("Received Message: " + message);
		chatRoom.appendText(message);
		*/chatRoom.appendText("\n");
	}
	// =================================================

	// ===============================================================================
	// 메시지 전송용 객체
	// Thread 아님!!! (GUI 버전이므로, Event를 받아서 처리함)
	class ClientSender {
		Socket socket;
		DataOutputStream out;

		ClientSender(Socket socket) {
			this.socket = socket;

			try {
				this.out = new DataOutputStream(socket.getOutputStream());

				// 시작하자 마자, 자신의 대화명을 서버로 전송
				if (out != null) {
					out.writeUTF(name);
				}

			} catch (Exception e) {
			}
		}

		public void send(String message) {
			if (out != null) {
				try {
					// 키보드로 입력받은 데이터를 서버로 전송
					out.writeUTF("[" + name + "] " + message);
				} catch (IOException e) {
				}
			}
		}
	}

	// 메시지 수신용 Thread
	class ClientReceiver implements Runnable {
		Socket socket;
		DataInputStream in;

		// 생성자
		ClientReceiver(Socket socket) {
			this.socket = socket;

			try {
				// 서버로 부터 데이터를 받을 수 있도록 DataInputStream 생성
				this.in = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
			}
		}

		public void run() {
			while (in != null) {
				try {
					// 서버로 부터 전송되는 데이터를 출력
					MultichatGUIClient.this.setMessage(in.readUTF());
				} catch (IOException e) {
				}
			}
		}
	}

	// ===============================================================================

	// 실행 방법
	// java MultichatGUIClient 대화명
	// public static void main(String[] args) {
	//
	// String name = null;
	// Scanner scanner = new Scanner(System.in);
	//
	// do {
	// System.out.println("대화명을 입력하세요.");
	// System.out.print(">>> ");
	// name = scanner.nextLine();
	// if (name.isEmpty()) {
	// System.out.println("대화명은 한글자 이상 입력해야 합니다.\n\n");
	// }
	// } while (name.isEmpty());
	//
	// new MultichatGUIClient(name).launchTest();
	// }
}
