package propra.grpproj.quiz.SocketDataObjects;

public class RegisterPub {
	private String name;
	private String address;
	
	private int ownerID;
	
	/**
	 * 
	 * @param name Name of Kneipe
	 * @param userName Name of owner
	 * @param address Address of Kneipe
	 */
	public RegisterPub(String name, String address, int ownerID) {
		this.name = name;
		this.address = address;
		this.ownerID = ownerID;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public int getOwnerID() {
		return ownerID;
	}
	
}
