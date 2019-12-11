package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;

public class RegisterPub implements Serializable{
	private String name;
	private String address;
	private boolean success;

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
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
