package pl.marczykm.SecureChatClient.gui.view;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

@SuppressWarnings("restriction")
public class ChatController extends Scene {
	private static GridPane grid = new GridPane();

	public ChatController() {
		super(grid, 350, 350);
		prepareView();
	}
	
	private void prepareView() {
		ScrollPane messagesPane = new ScrollPane();
		messagesPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		messagesPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		messagesPane.setPrefHeight(300);
		
		Label label = new Label("Marcin (12:04): test\nAnia (12:05): test 2\n");
		messagesPane.setContent(label);
		
		grid.add(messagesPane, 0, 0);
		
		final TextArea messageInput = new TextArea();
		messageInput.setPrefWidth(300);
		messageInput.setPrefHeight(50);
		Button sendMessage = new Button("Send");
		sendMessage.setPrefWidth(50);
		sendMessage.setPrefHeight(50);
		
		sendMessage.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				
			}
		});
		
		HBox hb = new HBox();
		hb.getChildren().addAll(messageInput, sendMessage);
		
		grid.add(hb, 0, 1);
	}
}
