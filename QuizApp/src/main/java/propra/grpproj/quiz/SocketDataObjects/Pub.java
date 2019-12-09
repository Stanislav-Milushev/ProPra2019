package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;

public class Pub implements Serializable{
	private int ID;
	private String name;
	private String adresse;
	private boolean allowed;
	
	private int ownerID;
	private String ownerName;

	public Pub(int iD, String name, String adresse, boolean allowed, int ownerID, String ownerName) {
		super();
		ID = iD;
		this.name = name;
		this.adresse = adresse;
		this.allowed = allowed;
		this.ownerID = ownerID;
		this.ownerName = ownerName;
		
	}

	public int getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public String getAdresse() {
		return adresse;
	}

	public boolean isAllowed() {
		return allowed;
	}

	public int getOwnerID() {
		return ownerID;
	}
	
	public String getOwnerName() {
		return ownerName;
	}
	
}
