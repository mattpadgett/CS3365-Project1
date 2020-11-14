package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.User;
import util.DBUtil;

public class AdministrationViewController {
	private Main main;
	
	private ObservableList<User> users = FXCollections.observableArrayList();
	private User selectedUser = null;
	
	@FXML
	private TableView<User> userTable;
	
	@FXML
	private TableColumn<User, String> usernameColumn;
	
	@FXML
	private TableColumn<User, String> firstNameColumn;
	
	@FXML
	private TableColumn<User, String> lastNameColumn;
	
	@FXML
	private TableColumn<User, String> userTypeColumn;
	
	@FXML
	private void handleDetails() {
		if(this.selectedUser == null) {
			return;
		}
		
		System.out.println(this.selectedUser.getFirstName());
	}
	
	@FXML
	private void handleDelete() {
		
	}
	
	@FXML
	private void handleNew() {
		
	}
	
	@FXML
	private void handleReturn() {
		this.main.showHomeView();
	}
	
	public void initialize() {
		ResultSet rs = DBUtil.selectQuery("SELECT UserId FROM User;");
		
		try {
			while(rs.next()) {
				this.users.add(new User(rs.getInt(1)));				
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		userTypeColumn.setCellValueFactory(cellData -> cellData.getValue().userTypeProperty());
		
		userTable.setItems(this.users);
		
		userTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectUser(newValue));
	}
	
	private void selectUser(User user) {
		if(user != null) {
			this.selectedUser = user;
		} else {
			this.selectedUser = null;
		}
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
}
