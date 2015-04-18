package pl.marczykm.SecureChatClient;

import java.io.InputStream;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import pl.marczykm.SecureChatClient.entity.User;
import pl.marczykm.SecureChatClient.gui.view.ChatController;
import pl.marczykm.SecureChatClient.gui.view.LoginController;
import pl.marczykm.SecureChatClient.gui.view.RegisterController;

@SuppressWarnings("restriction")
public class GUI extends Application {
	private Stage stage;
	private User loggedUser;
	private final double WINDOW_WIDTH = 800.0;
    private final double WINDOW_HEIGHT = 635.0;

	private static GUI instance;

	public static GUI getInstance() {
		if (instance == null)
			instance = new GUI();
		return instance;
	}

	public static void main(String[] args) {
		Application.launch(GUI.class, args);
	}

	@Override
	public void start(Stage primaryStage) {
		this.stage = primaryStage;
		this.stage.setTitle("SecureChat - by mm");
		stage.setMinWidth(WINDOW_WIDTH);
        stage.setMinHeight(WINDOW_HEIGHT);
        stage.setMaxWidth(WINDOW_WIDTH);
        stage.setMaxHeight(WINDOW_HEIGHT);
		gotoLogin();
		primaryStage.show();
	}

//	public void showChatView() {
//		ChatController chatView = new ChatController();
//
//		stage.setScene(chatView);
//		stage.show();
//	}

	void userLogout() {
		loggedUser = null;
		gotoLogin();
	}

	public boolean userLogging(String userId, String password) {

		RestTemplate restTemplate = new RestTemplate();

		URI targetUrl = UriComponentsBuilder
				.fromUriString("http://localhost:8080")
				.path("/user/login")
				.queryParam("username", userId)
				.queryParam("hashedPassword", password)
				.build().toUri();
		User user = null;
		try {
			user = restTemplate.getForObject(targetUrl, User.class);	
		} catch (Exception e) {
			return false;
		}
		
		if (user != null) {
			loggedUser = user;
			return true;
		}
		return false;
	}

	public void gotoLogin() {
		try {
			LoginController login = (LoginController) replaceSceneContent("Login.fxml");
			login.setApp(this);
		} catch (Exception e) {
			Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public void gotoRegister() {
        try {
            RegisterController register = (RegisterController) replaceSceneContent("Register.fxml");
            register.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	public void gotoChat(){
		try {
            ChatController chat = (ChatController) replaceSceneContent("Chat.fxml");
            chat.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

	private Initializable replaceSceneContent(String fxml) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		InputStream in = GUI.class.getResourceAsStream(fxml);
		loader.setBuilderFactory(new JavaFXBuilderFactory());
		loader.setLocation(GUI.class.getResource(fxml));
		AnchorPane page;
		try {
			page = (AnchorPane) loader.load(in);
		} finally {
			in.close();
		}
		Scene scene = new Scene(page, 800, 600);
		stage.setScene(scene);
		stage.sizeToScene();
		return (Initializable) loader.getController();
	}

}
