package hello;

public class Chat {
	private String user;
	private String message;
	private long id;
	public Chat() {
		
	}
	public Chat(long id, String user, String message) {
		this.id = id;
		this.user = user;
		this.message = message;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.format("Customer[id=%s, user=%d, msg=%s]", this.id, this.user, this.message);
	}
}
