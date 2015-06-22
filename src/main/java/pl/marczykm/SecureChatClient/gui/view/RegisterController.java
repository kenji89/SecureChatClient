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
			parameters.put("hashedPassword", password1.getText());
			
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
