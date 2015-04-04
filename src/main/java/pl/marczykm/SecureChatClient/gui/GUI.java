package pl.marczykm.SecureChatClient.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.marczykm.SecureChatClient.gui.view.Login;

public class GUI extends Application {
	
//	private ServerConnection serverConnection = new ServerConnection();

	@Override
	public void start(Stage primaryStage)  {
		Login loginView = new Login();

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(loginView);
        primaryStage.show();
	}
	
	public static void main( String[] args )
    {
		launch(args);
    }
}
