import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class Calculator {
	/** 결과값 변수 */
	private BigDecimal resultValue;

	/** 현재 입력된 값을 저장해 놓는 변수 */
	private BigDecimal memoryValue;

	/** 기존 표기된 내용을 지우고 새로 입력할지 여부 */
	private Boolean isNewValue;

	/** 현재 선택된 연산 버튼 */
	private Button selectedOperBtn;

	private static final String divideErrorText = "0으로 나눌 수 없습니다.";

	private Font buttonFont;
	private AnchorPane container;
	private TextField txt;
	private Label mLabel;
	private Button plusBtn;
	private Button minusBtn;
	private Button multBtn;
	private Button divBtn;
	private Button enterBtn;
	
	private Scene s;

//	private ActionListener btnClickListener;
	// =============================
	// Private Methods
	// =============================

	/** 초기화 */
	public void calInit() {
		this.buttonFont = new Font("Arial", 16);

		this.resultValue = new BigDecimal(0);
		this.memoryValue = new BigDecimal(0);
		this.selectedOperBtn = null;
		this.isNewValue = true;
	}

	/** UI 생성 */
	public void createUI() {
		container = new AnchorPane();
		System.out.println(container.getWidth());
		System.out.println(container.getHeight());
		//container.setPrefSize(600, 304);
		this.mLabel = new Label();
		this.mLabel.setText("M");
		this.mLabel.setLayoutX(5);
		this.mLabel.setLayoutY(12);
		this.mLabel.setPrefSize(20, 20);
		this.mLabel.setVisible(false);
		container.getChildren().add(this.mLabel);

		// result text field
		txt = new TextField();
		txt.setBorder(null);
		txt.setText("0");
		txt.setAlignment(Pos.CENTER_RIGHT);
		// txt.setBounds(25, 0, 215, 40);
		txt.setLayoutX(25);
		txt.setLayoutY(0);
		txt.setPrefSize(215, 40);
		txt.setFocusTraversable(false);
		txt.setEditable(false);
		container.getChildren().add(txt);

		// functions for memory
		int _scaleNum = 60;

		createButton("MC", 0, 40, _scaleNum, 40);
		createButton("MR", _scaleNum + 6, 40, _scaleNum, 40);
		createButton("M+", _scaleNum * 2 + 12, 40, _scaleNum, 40);
		createButton("M-", _scaleNum * 3 + 18, 40, _scaleNum, 40);

		// number
		ArrayList<Character> types = new ArrayList<Character>();
		types.add('7');
		types.add('8');
		types.add('9');
		types.add('4');
		types.add('5');
		types.add('6');
		types.add('1');
		types.add('2');
		types.add('3');
		types.add('0');
		types.add('.');
		types.add('C');

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				String _name = String.valueOf(types.remove(0));

				createButton(_name, 9 + j * _scaleNum, 130 + (i * _scaleNum), _scaleNum, _scaleNum);
			}
		}

		// operator
		divBtn = createButton("/", 0, 80, _scaleNum, 43);
		multBtn = createButton("*", _scaleNum + 6, 80, _scaleNum, 43);
		minusBtn = createButton("-", _scaleNum * 2 + 12, 80, _scaleNum, 43);
		plusBtn = createButton("+", _scaleNum * 3 + 18, 80, _scaleNum, 43);
		enterBtn = createButton("=", _scaleNum * 3 + 18, 246, _scaleNum, 124);
		createButton("←", _scaleNum * 3 + 18, 123 + 6, _scaleNum, 55);
		createButton("√", _scaleNum * 3 + 18, 178 + 10, _scaleNum, 55);

		// frame.pack();
		// frame.setSize(274, 408);
		// frame.setVisible(true);

		System.out.println("Calculator UI Created");
	}

	/** 버튼 생성 */
	private Button createButton(String name, int x, int y, int width, int height) {
		System.out.println("Create Buttons: " + name);
		Button btn = new Button(name);
		btn.setText(name);
		btn.setLayoutX(x);
		btn.setLayoutY(y);
		btn.setPrefSize(width, height);
		btn.setFocusTraversable(false);
		btn.setFont(this.buttonFont);
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String txtValue = txt.getText();
				Button b = (Button) event.getSource();
				String _name = b.getText();
				System.out.println(txtValue + ", " + _name);
				switch (_name) {
				case "M+":
					if (txtValue.equals(divideErrorText))
						return;

					mLabel.setVisible(true);
					memoryValue = memoryValue.add(new BigDecimal(Float.valueOf(txtValue)));
					isNewValue = true;
					break;

				case "M-":
					if (txtValue.equals(divideErrorText))
						return;

					mLabel.setVisible(true);
					memoryValue = memoryValue.subtract(new BigDecimal(Float.valueOf(txtValue)));
					isNewValue = true;
					break;

				case "MR":
					Float f = memoryValue.floatValue();
					Integer i = memoryValue.intValue();
					if (f.equals(Float.valueOf(i)))
						txt.setText(String.valueOf(i));
					else
						txt.setText(String.valueOf(f));
					isNewValue = true;
					break;

				case "MC":
					mLabel.setVisible(false);
					memoryValue = new BigDecimal(0);
					isNewValue = true;
					break;

				case "/":
				case "*":
				case "+":
				case "-":
					inputOperate(b);
					break;

				case "←":
					inputBackspace();
					break;

				case "√":
					calculateSquareRoot();
					break;

				case "=":
					inputEnter();
					break;

				case "C":
					inputEscape();
					break;

				default:
					pressNumberPad(_name);
					break;
				}
			}

		});
		this.container.getChildren().add(btn);

		return btn;
	}

	/** 이벤트 리스너 설정 */
	public void setEventListener(Scene s) {
		System.out.println("setEventListener()");
		s.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				KeyCode code = event.getCode();
				//System.out.println();
				System.out.println(code);
				if (code.isDigitKey())
					pressNumberPad(code);
				else if (code == KeyCode.PLUS || code == KeyCode.ADD)
					inputOperate(plusBtn);
				else if (code == KeyCode.MINUS || code == KeyCode.SUBTRACT)
					inputOperate(minusBtn);
				else if (code == KeyCode.MULTIPLY)
					inputOperate(multBtn);
				else if (code == KeyCode.DIVIDE)
					inputOperate(divBtn);
				else if (code == KeyCode.ENTER)
					inputEnter();// Enter(=Return)
				else if (code == KeyCode.ESCAPE)
					inputEscape();// Esc
				else if (code == KeyCode.BACK_SPACE)
					inputBackspace();// Backspace
				}
		});
	}

	/** 키패드 입력 */
	private void pressNumberPad(KeyCode numStr) {
		String numStrCopy;
		System.out.println(numStr);
		String txtValue = numStr.toString();
		txtValue = txtValue.substring(txtValue.length()-1); // Keycode의 마지막 Digit값을 읽음
		numStrCopy = txtValue;
		System.out.println(txtValue);
		System.out.println("Inteter : " + txtValue);
		if (isNewValue == true) {
			// if (selectedOperBtn != null)
			// selectedOperBtn.setForeground(Color.red);
			txt.setText("");
			isNewValue = false;
		}

		txtValue = txt.getText();
		if (txtValue.equals("0")) {
			txt.setText("");
			txtValue = "";
		}
		txt.setText(txtValue + numStrCopy);

		// 적용 후 새로운 value
		if (txt.getText().equals("."))
			txt.setText("0.");
	}
	
	// Method Overriding
	private void pressNumberPad(String numStr) {
		String txtValue = txt.getText();
		System.out.println("String");
		if (isNewValue == true) {
			// if (selectedOperBtn != null)
			// selectedOperBtn.setForeground(Color.red);
			txt.setText("");
			isNewValue = false;
		}

		txtValue = txt.getText();
		if (txtValue.equals("0")) {
			txt.setText("");
			txtValue = "";
		}
		txt.setText(txtValue + numStr);

		// 적용 후 새로운 value
		if (txt.getText().equals("."))
			txt.setText("0.");
	}
	

	/** 연산자 입력 */
	private void inputOperate(Button b) {
		String txtValue = txt.getText();
		System.out.println("inputOperate");
		if (txtValue.equals(divideErrorText))
			return;

		if (isNewValue == false)
			calculate();

		// if (selectedOperBtn != null)
		// selectedOperBtn.setForeground(Color.black);
		// b.setForeground(Color.red);
		selectedOperBtn = b;
	}

	/** Square Root 계산 */
	private void calculateSquareRoot() {
		Float fValue = Float.valueOf(txt.getText());

		BigDecimal tmp = new BigDecimal(Math.sqrt(fValue));

		Float f = tmp.floatValue();
		Integer i = tmp.intValue();
		if (f.equals(Float.valueOf(i)))
			txt.setText(String.valueOf(i));
		else
			txt.setText(String.valueOf(f));

		this.isNewValue = true;
	}

	/** 엔터 입력 (결과 계산) */
	private void inputEnter() {
		String txtValue = txt.getText();

		if (txtValue.equals(divideErrorText))
			return;

		calculate();
		// if (selectedOperBtn != null)
		// selectedOperBtn.setForeground(Color.black);
	}

	/** Esc 입력 (초기화) */
	private void inputEscape() {
		txt.setText("0");
		resultValue = new BigDecimal(0);

		// if (selectedOperBtn != null)
		// selectedOperBtn.setForeground(Color.black);
		selectedOperBtn = null;
	}

	/** 백스페이스 입력 (마지막 입력값 삭제) */
	private void inputBackspace() {
		String txtValue = txt.getText();
		txt.setText(txtValue.substring(0, txtValue.length() - 1));
		if (txt.getText().equals(""))
			txt.setText("0");
	}

	/** 계산하기 */
	private void calculate() {
		Float fValue = Float.valueOf(txt.getText());

		if (selectedOperBtn == null) {
			resultValue = new BigDecimal(fValue);
			isNewValue = true;
			return;
		}
		System.out.println(selectedOperBtn.getText());
		switch (selectedOperBtn.getText()) {
		case "/":
			if (txt.getText().equals("0") || txt.getText().equals("0.")) {
				this.txt.setText(this.divideErrorText);
				// if (this.selectedOperBtn != null)
				// this.selectedOperBtn.setForeground(Color.black);
				this.selectedOperBtn = null;
				this.isNewValue = true;
				this.resultValue = new BigDecimal(0);
				return;
			}
			resultValue = resultValue.divide(new BigDecimal(fValue));
			break;

		case "*":
			resultValue = resultValue.multiply(new BigDecimal(fValue));
			break;

		case "+":
			resultValue = resultValue.add(new BigDecimal(fValue));
			break;

		case "-":
			resultValue = resultValue.subtract(new BigDecimal(fValue));
			break;
		}

		Float f = resultValue.floatValue();
		Integer i = resultValue.intValue();
		if (f.equals(Float.valueOf(i)))
			txt.setText(String.valueOf(i));
		else
			txt.setText(String.valueOf(f));

		isNewValue = true;
	}

	/** 콘솔에 출력하기 */
	private void trace(String value) {
		System.out.println(value);
	}

	public AnchorPane getCalculator() {
		return this.container;
	}

	public Calculator() {
		calInit(); // 계산기 초기화
		createUI(); // 계산기 UI생성
	}
	/** main 함수 */
//	public static void main(String[] args) {
//		launch(args);
//	}
//
//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		calInit();
//		createUI();
//		Scene s = new Scene(container);
//		setEventListener(s);
//
//		primaryStage.setTitle("test Cal");
//		primaryStage.setScene(s);
//		primaryStage.show();
//
//	}
}