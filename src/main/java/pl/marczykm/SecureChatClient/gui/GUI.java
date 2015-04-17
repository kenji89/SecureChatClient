package pl.marczykm.SecureChatClient.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.marczykm.SecureChatClient.gui.view.ChatView;
import pl.marczykm.SecureChatClient.gui.view.LoginView;

@SuppressWarnings("restriction")
public class GUI extends Application {
	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage)  {
		this.primaryStage = primaryStage;
		LoginView loginView = new LoginView(this);

        this.primaryStage.setTitle("SecureChat - by mm");
        this.primaryStage.setScene(loginView);
        this.primaryStage.show();
	}
	
	public void showChatView(){
		ChatView chatView = new ChatView();
		
		primaryStage.setScene(chatView);
		primaryStage.show();
	}
	
	public static void main( String[] args )
    {
		launch(args);
    }
}
