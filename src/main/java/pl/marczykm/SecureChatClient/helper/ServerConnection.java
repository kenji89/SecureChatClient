package pl.marczykm.SecureChatClient.helper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import pl.marczykm.SecureChatClient.exception.MessageKey;
import pl.marczykm.SecureChatClient.exception.TechnicalException;

public class ServerConnection {
	private String serverName;
	private int port;
	private Socket client;
	private DataInputStream in;
	private DataOutputStream out;

	public ServerConnection() {
		serverName = "localhost";
		port = 1099;
	}

	public ServerConnection(String serverName, int port) {
		super();
		this.serverName = serverName;
		this.port = port;
	}

	public void connect() throws TechnicalException {
		System.out.println("Connecting to " + serverName + " on port " + port
				+ "...");
		try {
			client = new Socket(serverName, port);

			System.out.println("Just connected to "
					+ client.getLocalSocketAddress());
			OutputStream outToServer = client.getOutputStream();
			out = new DataOutputStream(outToServer);
			InputStream inFromServer = client.getInputStream();
			in = new DataInputStream(inFromServer);
		} catch (UnknownHostException e) {
			throw new TechnicalException(MessageKey.SERVER_UNREACHABLE);
		} catch (IOException e) {
			throw new TechnicalException(MessageKey.SERVER_UNREACHABLE);
		}
	}

	public String writeToServer(String content) throws TechnicalException {
		if (client == null)
			connect();
		if (!client.isConnected())
			connect();

		try {
			out.writeUTF(content);
			String result = in.readUTF();
			client.close();
			client = null;
			return result;
		} catch (IOException e) {
			throw new TechnicalException(MessageKey.SERVER_UNREACHABLE);
		}
	}
}
