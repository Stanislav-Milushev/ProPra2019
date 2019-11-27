package propra.grpproj.quiz.Socket.SocketDataObjects;

public class RegisterKneipe {
	private String name;
	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	private String address;
	
	public RegisterKneipe(String name, String addresse) {
		this.name = name;
		this.address = addresse;
	}
}
