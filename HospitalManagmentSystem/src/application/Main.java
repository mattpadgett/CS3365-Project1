package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import view.LoginViewController;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private Stage primaryStage;
	private Stage secondaryStage;
	
	private LoginViewController loginViewController;
	
	public void showLoginView() {
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/LoginView.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();			
			
            this.primaryStage.setScene(new Scene(anchorPane));
            this.primaryStage.show();			
            
            this.primaryStage.centerOnScreen();
            
            this.loginViewController = loader.getController();
            this.loginViewController.setMain(this);  
        } catch(IOException e) {
            e.printStackTrace();
        }
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Hospital Management System");
		
		showLoginView();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
