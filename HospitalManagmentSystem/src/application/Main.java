package application;
	
import java.io.IOException;
import java.sql.PreparedStatement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.User;
import util.Authentication;
import util.DBUtil;
import view.LoginViewController;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private Stage loginStage;
	
	private Stage primaryStage;
	private Stage secondaryStage;
	
	private LoginViewController loginViewController;
	
	public void showLoginView() {
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/LoginView.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();			
			
            this.primaryStage.hide();
            
            this.loginStage.setResizable(false);
            this.loginStage.initStyle(StageStyle.UNDECORATED);
            this.loginStage.setScene(new Scene(anchorPane));
            this.loginStage.show();			
            
            this.loginStage.centerOnScreen();
            
            this.loginViewController = loader.getController();
            this.loginViewController.setMain(this);
        } catch(IOException e) {
            e.printStackTrace();
        }
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		User testing = new User(1, "Christian", "Luckow", "Andres", "ijasonluck@gmail.com", "christian.luckow");
//		testing.setUserTypeId(2);
//		testing.setFirstName("Chris");
//		testing.setLastName("Ramirez");
//		testing.setMiddleName("Jeffrey");
//		testing.setEmail("jason.luckow@outlook.com");
//		testing.setUsername("chris.ramirez");
		
		this.loginStage = new Stage();
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Hospital Management System");
		
		showLoginView();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
