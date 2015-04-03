package pl.marczykm.SecureChatClient.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pl.marczykm.SecureChatClient.exception.TechnicalException;
import pl.marczykm.SecureChatClient.helper.ServerConnection;

@SuppressWarnings("restriction")
public class GUI extends Application {
	
	private ServerConnection serverConnection = new ServerConnection();

	@Override
	public void start(Stage primaryStage)  {
		Button connectBtn = new Button();
		connectBtn.setText("Connect");
		connectBtn.setOnAction(new EventHandler<ActionEvent>(){

			public void handle(ActionEvent arg0) {
				try {
					serverConnection.connect();
				} catch (TechnicalException e) {
					e.printStackTrace();
				}
			}
		});
		
		Button sendBtn = new Button();
		sendBtn.setText("Send");
		sendBtn.setOnAction(new EventHandler<ActionEvent>(){

			public void handle(ActionEvent arg0) {
				try {
					serverConnection.writeToServer("dupa");
				} catch (TechnicalException e) {
					e.printStackTrace();
				}
			}
		});
		
		GridPane  grid = new GridPane ();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		grid.add(connectBtn, 0, 1);
		grid.add(sendBtn, 0, 2);
		
		Scene scene = new Scene(grid, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	public static void main( String[] args )
    {
		launch(args);
    }
}
