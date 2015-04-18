package pl.marczykm.SecureChatClient.gui.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import pl.marczykm.SecureChatClient.GUI;

@SuppressWarnings("restriction")
public class ChatController extends AnchorPane implements Initializable {

	private GUI application;
	
	@FXML
	ScrollPane messagesPane;
	@FXML
	Label messages;

	public void setApp(GUI application) {
		this.application = application;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		messages.setText("Marcin (12:04): test\nAnia (12:05): test 2\n");
	}
	
	public void sendMessage(ActionEvent event){
		
	}
}
