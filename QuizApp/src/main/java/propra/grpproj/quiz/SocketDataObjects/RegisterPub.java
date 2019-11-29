package propra.grpproj.quiz.SocketDataObjects;

public class RegisterPub {
	private String name;
	private String userName;
	private String address;
	
	/**
	 * 
	 * @param name Name of Kneipe
	 * @param userName Name of owner
	 * @param address Address of Kneipe
	 */
	public RegisterPub(String name, String userName, String address) {
		this.name = name;
		this.userName = userName;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getUserName() {
		return userName;
	}
	
}
