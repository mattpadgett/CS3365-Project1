package application;
	
import java.io.IOException;
import java.sql.PreparedStatement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.PatientChart;
import model.TreatmentRecord;
import model.User;
import model.TransactionRecord;
import model.BillingRecord;
import util.Authentication;
import util.DBUtil;
import view.AdminDetailsPopController;
import view.AdministrationViewController;
import view.BillingRecordViewController;
import view.BillingViewController;
import view.HomeViewController;
import view.LoginViewController;
import view.NewPaymentPopController;
import view.PatientsDetailsPopController;
import view.PatientsViewController;
import view.ProfileViewController;
import view.TreatmentDetailsPopController;
import view.TreatmentRecordViewController;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private Stage loginStage;
	
	private Stage primaryStage;
	private Stage secondaryStage;
	private Stage popStage;
	
	private LoginViewController loginViewController;
	private HomeViewController homeViewController;
	private AdministrationViewController adminViewController;
	
	private AdminDetailsPopController adminDetailsPopController;
	private ProfileViewController profileViewController;
	
	private PatientsViewController patientsViewController;
	private PatientsDetailsPopController patientsDetailsPopController;
	private TreatmentRecordViewController treatmentRecordViewController;
	private TreatmentDetailsPopController treatmentDetailsPopController;
	
	private BillingViewController billingViewController;
	private NewPaymentPopController newPaymentPopController;
	private BillingRecordViewController billingRecordViewController;
	
	private User loggedUser;
	
	public void showProfileView() {
		try {	
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/ProfileView.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();
            
            this.popStage.setScene(new Scene(anchorPane));
            
            this.profileViewController = loader.getController();
            this.profileViewController.setMain(this);
            this.profileViewController.setup();
            
            this.popStage.centerOnScreen();
            this.popStage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
	}
	
	public void showAdminDetailsPop(User user, boolean newUserMode) {
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/AdminDetailsPop.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();
            
            this.popStage.setScene(new Scene(anchorPane));
            
            this.adminDetailsPopController = loader.getController();
            this.adminDetailsPopController.setMain(this);
            this.adminDetailsPopController.loadData(user);
            this.adminDetailsPopController.setNewUserMode(newUserMode);
            
            this.popStage.centerOnScreen();
            this.popStage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
	}
	
	public void showAdminView() {
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/AdministrationView.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();
            
            this.primaryStage.setScene(new Scene(anchorPane));
            this.primaryStage.show();
            
            this.primaryStage.centerOnScreen();
            
            this.adminViewController = loader.getController();
            this.adminViewController.setMain(this);
        } catch(IOException e) {
            e.printStackTrace();
        }
	}
	
	public void showBillingView() {
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/BillingView.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();
            
            this.primaryStage.setScene(new Scene(anchorPane));
            this.primaryStage.show();
            
            this.primaryStage.centerOnScreen();
            
            this.billingViewController = loader.getController();
            this.billingViewController.setMain(this);
        } catch(IOException e) {
            e.printStackTrace();
        }
	}
	
	public void showBillingRecordView(PatientChart patient) {
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/BillingRecordView.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();
            
            this.primaryStage.setScene(new Scene(anchorPane));
            this.primaryStage.show();
            
            this.primaryStage.centerOnScreen();
            
            this.billingRecordViewController = loader.getController();
            this.billingRecordViewController.setMain(this);
            this.billingRecordViewController.setPatient(patient);
        } catch(IOException e) {
            e.printStackTrace();
        }
	}
	
	public void showNewPaymentPop(PatientChart patient, TransactionRecord transaction, boolean newTransactionMode) {
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/NewPaymentPop.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();
            
            this.popStage.setScene(new Scene(anchorPane));
            
            this.newPaymentPopController = loader.getController();
            this.newPaymentPopController.setMain(this);
            this.newPaymentPopController.loadData(transaction, patient);
            this.newPaymentPopController.setNewTransactionMode(newTransactionMode);
            
            this.popStage.centerOnScreen();
            this.popStage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
	}
	
	public void showPatientsView() {
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/PatientsView.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();
            
            this.primaryStage.setScene(new Scene(anchorPane));
            this.primaryStage.show();
            
            this.primaryStage.centerOnScreen();
            
            this.patientsViewController = loader.getController();
            this.patientsViewController.setMain(this);
        } catch(IOException e) {
            e.printStackTrace();
        }
	}
	
	public void showTreatmentRecordView(PatientChart patient) {
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/TreatmentRecordView.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();
            
            this.primaryStage.setScene(new Scene(anchorPane));
            this.primaryStage.show();
            
            this.primaryStage.centerOnScreen();
            
            this.treatmentRecordViewController = loader.getController();
            this.treatmentRecordViewController.setMain(this);
            this.treatmentRecordViewController.setPatient(patient);
        } catch(IOException e) {
            e.printStackTrace();
        }
	}
	
	public void showTreatmentDetailsPop(PatientChart patient, TreatmentRecord record, boolean newRecordMode) {
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/TreatmentDetailsPop.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();
            
            this.popStage.setScene(new Scene(anchorPane));
            
            this.treatmentDetailsPopController = loader.getController();
            this.treatmentDetailsPopController.setMain(this);
            this.treatmentDetailsPopController.loadPatientData(patient);
            this.treatmentDetailsPopController.loadRecordData(record);
            this.treatmentDetailsPopController.setNewRecordMode(newRecordMode);
            
            this.popStage.centerOnScreen();
            this.popStage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
	}
	
	public void showPatientsDetailsPop(PatientChart patient, boolean newPatientMode) {
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/PatientsDetailsPop.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();
            
            this.popStage.setScene(new Scene(anchorPane));
            
            this.patientsDetailsPopController = loader.getController();
            this.patientsDetailsPopController.setMain(this);
            this.patientsDetailsPopController.loadData(patient);
            this.patientsDetailsPopController.setNewPatientMode(newPatientMode);
            
            this.popStage.centerOnScreen();
            this.popStage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
	}
	
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
            this.homeViewController.setupAccess();
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
            
            //this.setLoggedUser(new User(1));
            //this.showHomeView();
        } catch(IOException e) {
            e.printStackTrace();
        }
	}
	
	public User getLoggedUser() {
		return this.loggedUser;
	}
	
	public void setLoggedUser(User user) {
		this.loggedUser = user;
	}
	
	@Override
	public void start(Stage primaryStage) {
		
//		User testingUser = new User(1, "Christian", "Luckow", "Andres", "ijasonluck@gmail.com", "christian.luckow");
//		testingUser.setUserTypeId(2);
//		testingUser.setFirstName("Chris");
//		testingUser.setLastName("Ramirez");
//		testingUser.setMiddleName("Jeffrey");
//		testingUser.setEmail("jason.luckow@outlook.com");
//		testingUser.setUsername("chris.ramirez");
		
//		PatientChart testingChart = new PatientChart("Json", "Luckow", "Jeffrey", "707 Ave T", "3478 CatClaw DR #212", "Lubbock", "TX", "79401", "000-00-0000", "The Best Insurance");
//		testingChart.setFirstName("json");
//		testingChart.setLastName("Ramirez");
//		testingChart.setMiddleName("Andres");
//		testingChart.setAddress1("3478 CatClaw Dr #212");
//		testingChart.setAddress2("707 Ave T");
//		testingChart.setCity("Abilene");
//		testingChart.setState("Texas");
//		testingChart.setZipCode("79606");
//		testingChart.setSocialSecurityNumber("000-00-0001");
//		testingChart.setInsuranceProvider("The Worst Insurance");
//		PatientChart testingChart2 = new PatientChart("Jason", "Luckow", "Jeffrey", "707 Ave T", "3478 CatClaw DR #212", "Lubbock", "TX", "79401", "000-00-0000", "The Best Insurance");
//		System.out.println(testingChart.getPatientChartID());
		
		
//		TreatmentRecord testingTreatRecord = new TreatmentRecord(testingChart, "11/02/2020", 165.00f, 71.00f, "140/90mmHg", "Corona", "Does not actually have corona", 0);
//		System.out.println("here1");
		//testingTreatRecord.setDate("11/05/2020");
//		testingTreatRecord.setWeight(120.00f);
//		testingTreatRecord.setHeight(84.00f);
//		testingTreatRecord.setBloodPressure("180/100mmHg");
//		testingTreatRecord.setVisitReason("Heart feels like they are about to explode");
//		testingTreatRecord.setTreatmentNote("Kicking them out bc they don't have health insurance");
//		testingTreatRecord.setPrescriptionId(1);
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Hospital Management System");
		this.primaryStage.setResizable(false);
		
		this.loginStage = new Stage();
		this.loginStage.setResizable(false);
        this.loginStage.initStyle(StageStyle.UNDECORATED);
        
        this.popStage = new Stage();
        this.popStage.setResizable(false);
        this.popStage.initStyle(StageStyle.UNDECORATED);
		
		showLoginView();
	}
	
	public Stage getPopStage() {
		return this.popStage;
	}
	
	public LoginViewController getLoginViewController() {
		return loginViewController;
	}

	public HomeViewController getHomeViewController() {
		return homeViewController;
	}

	public AdministrationViewController getAdminViewController() {
		return adminViewController;
	}

	public AdminDetailsPopController getAdminDetailsPopController() {
		return adminDetailsPopController;
	}
	
	public PatientsViewController getPatientsViewController() {
		return patientsViewController;
	}

	public PatientsDetailsPopController getPatientsDetailsPopController() {
		return patientsDetailsPopController;
	}
	
	public TreatmentRecordViewController getTreatmentRecordViewController() {
		return treatmentRecordViewController;
	}

	public TreatmentDetailsPopController getTreatmentDetailsPopController() {
		return treatmentDetailsPopController;
	}

	public BillingRecordViewController getBillingRecordViewController() {
		return billingRecordViewController;
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
