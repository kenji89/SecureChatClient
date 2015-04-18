package pl.marczykm.SecureChatClient.gui.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pl.marczykm.SecureChatClient.GUI;
import pl.marczykm.SecureChatClient.gui.errormessage.LoginErrorMessage;

@SuppressWarnings("restriction")
public class LoginController extends AnchorPane implements Initializable {

	@FXML
	TextField userId;
	@FXML
	PasswordField password;
	@FXML
	Button login;
	@FXML
	Label errorMessage;

	private GUI application;

	public void setApp(GUI application) {
		this.application = application;
	}

	public void initialize(URL location, ResourceBundle resources) {
		errorMessage.setText("");
		userId.setPromptText("demo");
		password.setPromptText("demo");
	}

	public void processLogin(ActionEvent event) {
		if (!validateFields()) {
			if (application != null) {
				if (!application.userLogging(userId.getText(),
						password.getText())) {
					errorMessage.setText("Username/Password is incorrect");
				} else {
					errorMessage.setText("Hello " + userId.getText());
					application.gotoRegister();
				}
			}
		}
	}

	public void processRegister(ActionEvent event) {
		application.gotoRegister();
	}

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
		if (password.getText().equals("")) {
			errorMessage.setText(LoginErrorMessage.EMPTY_PASSWORD_FIELD);
			errorMessage.setVisible(true);
			return true;
		}
		return false;
	}
}