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
import view.HomeViewController;
import view.LoginViewController;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private Stage loginStage;
	
	private Stage primaryStage;
	private Stage secondaryStage;
	
	private LoginViewController loginViewController;
	private HomeViewController homeViewController;
	
	public void showHomeView() {
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/HomeView.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();			
			
            this.loginStage.hide();
            
            this.primaryStage.setScene(new Scene(anchorPane));
            this.primaryStage.show();
            
            this.primaryStage.centerOnScreen();
            
            this.homeViewController = loader.getController();
            this.homeViewController.setMain(this);
        } catch(IOException e) {
            e.printStackTrace();
        }
	}
	
	public void showLoginView() {
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/LoginView.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();			
			
            this.primaryStage.hide();
            
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
		
		//User testing = new User(1, "Christian", "Luckow", "Andres", "ijasonluck@gmail.com", "christian.luckow");
//		testing.setUserTypeId(2);
//		testing.setFirstName("Chris");
//		testing.setLastName("Ramirez");
//		testing.setMiddleName("Jeffrey");
//		testing.setEmail("jason.luckow@outlook.com");
//		testing.setUsername("chris.ramirez");
		
		this.loginStage = new Stage();
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Hospital Management System");
		
		this.primaryStage.setResizable(false);
		
		this.loginStage.setResizable(false);
        this.loginStage.initStyle(StageStyle.UNDECORATED);        
		
		showLoginView();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
