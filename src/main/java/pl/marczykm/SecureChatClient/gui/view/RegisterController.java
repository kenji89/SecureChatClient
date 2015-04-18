package pl.marczykm.SecureChatClient.gui.view;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import pl.marczykm.SecureChatClient.GUI;
import pl.marczykm.SecureChatClient.entity.User;
import pl.marczykm.SecureChatClient.gui.errormessage.LoginErrorMessage;
import pl.marczykm.SecureChatClient.helper.UserHelper;

@SuppressWarnings("restriction")
public class RegisterController extends AnchorPane implements Initializable {

	private GUI application;
	
	@FXML
	TextField userId;
	@FXML
	PasswordField password1;
	@FXML
	PasswordField password2;
	@FXML
	Label errorMessage;

//	private void prepareView() {
//		Text welcomeText = new Text("Welcome");
//		welcomeText.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
//		grid.add(welcomeText, 0, 0, 2, 1);
//
//		Label usernameLabel = new Label("Username");
//		grid.add(usernameLabel, 0, 1);
//		grid.add(usernameTextfield, 1, 1);
//
//		Label passwordLabel1 = new Label("Password");
//		grid.add(passwordLabel1, 0, 2);
//		grid.add(passwordField1, 1, 2);
//		
//		Label passwordLabel2 = new Label("Confirm password");
//		grid.add(passwordLabel2, 0, 3);
//		grid.add(passwordField2, 1, 3);
//
//		Button loginButton = new Button("Register");
//		HBox hbBtn = new HBox(10);
//		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
//		hbBtn.getChildren().add(loginButton);
//		grid.add(hbBtn, 1, 4);
//		loginButton.setOnAction(new EventHandler<ActionEvent>() {
//
//			public void handle(ActionEvent arg0) {
//				if (!validateFields()) {
//					RestTemplate restTemplate = new RestTemplate();
//
//					URI targetUrl = UriComponentsBuilder
//							.fromUriString("http://localhost:8080")
//							.path("/user/register").build().toUri();
//
//					Map<String,Object> parameters = new HashMap<String, Object>();
//					parameters.put("username", usernameTextfield.getText());
//					parameters.put("hashedPassword", UserHelper.hashPassword(passwordField1.getText()));
//					
//					User user = restTemplate
//							.postForObject(targetUrl, parameters, User.class);
//					if (user != null)
//						gui.gotoLogin();
//				}
//			}
//		});
//
//		errorText.setFill(Color.FIREBRICK);
//		errorText.setVisible(false);
//		grid.add(errorText, 1, 6);
//
//		grid.setAlignment(Pos.CENTER);
//		grid.setHgap(10);
//		grid.setVgap(10);
//		grid.setPadding(new Insets(25, 25, 25, 25));
//
//		// super.getStylesheets().add(Login.class.getResource("Login.css").toExternalForm());
//	}

	/**
	 * Validates fields.
	 * 
	 * @return true if error occured.
	 */
	protected boolean validateFields() {
		errorMessage.setText("");
		errorMessage.setVisible(false);
		if (userId.getText().equals("")) {
			errorMessage.setText(LoginErrorMessage.EMPTY_LOGIN_FIELD);
			errorMessage.setVisible(true);
			return true;
		}
		if (password1.getText().equals("")) {
			errorMessage.setText(LoginErrorMessage.EMPTY_PASSWORD_FIELD);
			errorMessage.setVisible(true);
			return true;
		}
		return false;
	}
	
	public void setApp(GUI application){
		this.application = application;
	}
	
	public void processRegister(ActionEvent event){
		if (!validateFields()) {
			RestTemplate restTemplate = new RestTemplate();

			URI targetUrl = UriComponentsBuilder
					.fromUriString("http://localhost:8080")
					.path("/user/register").build().toUri();

			Map<String,Object> parameters = new HashMap<String, Object>();
			parameters.put("username", userId.getText());
			parameters.put("hashedPassword", UserHelper.hashPassword(password1.getText()));
			
			User user = restTemplate
					.postForObject(targetUrl, parameters, User.class);
			if (user != null)
				application.gotoLogin();
		}
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		errorMessage.setText("");
		userId.setPromptText("demo");
		password1.setPromptText("demo");
	}

}
