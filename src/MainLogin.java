// 
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainLogin extends Application {


	public static void main(String args[])
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Parent root = FXMLLoader.load((getClass().getResource("layout/MainView2.fxml")));
		Scene s = new Scene(root);
		primaryStage.setTitle("Secretary System");
		primaryStage.setScene(s);
		primaryStage.show();
	}	
	
}
