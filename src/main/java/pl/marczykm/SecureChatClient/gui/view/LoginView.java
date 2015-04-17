package pl.marczykm.SecureChatClient.gui.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pl.marczykm.SecureChatClient.gui.GUI;
import pl.marczykm.SecureChatClient.gui.errormessage.LoginErrorMessage;

@SuppressWarnings("restriction")
public class LoginView extends Scene {
	private static GridPane grid = new GridPane();
	private Text errorText = new Text();
	
	private final GUI gui;

	private TextField usernameTextfield = new TextField();
	private PasswordField passwordField = new PasswordField();

	public LoginView(GUI gui) {
		super(grid, 300, 250);
		prepareView();
		this.gui = gui;
	}

	private void prepareView() {
		Text welcomeText = new Text("Welcome");
		welcomeText.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
		grid.add(welcomeText, 0, 0, 2, 1);

		Label usernameLabel = new Label("Username");
		grid.add(usernameLabel, 0, 1);

		grid.add(usernameTextfield, 1, 1);

		Label passwordLabel = new Label("Password");
		grid.add(passwordLabel, 0, 2);

		grid.add(passwordField, 1, 2);

		Button loginButton = new Button("Log in");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(loginButton);
		grid.add(hbBtn, 1, 4);
		loginButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				if (!validateFields()) {
//					User user = new User(usernameTextfield.getText(),
//							passwordField.getText());
//					gui.showChatView();
				}
			}
		});

		errorText.setFill(Color.FIREBRICK);
		errorText.setVisible(false);
		grid.add(errorText, 1, 6);

		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		// super.getStylesheets().add(Login.class.getResource("Login.css").toExternalForm());
	}

	/**
	 * Validates fields.
	 * 
	 * @return true if error occured.
	 */
	protected boolean validateFields() {
		errorText.setText("");
		errorText.setVisible(false);
		if (usernameTextfield.getText().equals("")) {
			errorText.setText(LoginErrorMessage.EMPTY_LOGIN_FIELD);
			errorText.setVisible(true);
			return true;
		}
		if (passwordField.getText().equals("")) {
			errorText.setText(LoginErrorMessage.EMPTY_PASSWORD_FIELD);
			errorText.setVisible(true);
			return true;
		}
		return false;
	}

}
