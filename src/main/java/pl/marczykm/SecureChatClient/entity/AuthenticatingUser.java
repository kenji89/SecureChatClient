package pl.marczykm.SecureChatClient.entity;

public class AuthenticatingUser {
	private String username;
	private String hashedPassword;

	public AuthenticatingUser() {
	}

	public AuthenticatingUser(String username, String hashedPassword) {
		super();
		this.username = username;
		this.hashedPassword = hashedPassword;
	}

	public String getUsername() {
		return username;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}
}
