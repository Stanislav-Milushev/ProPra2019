package propra.grpproj.quiz.SocketDataObjects;

public class Pub {
	private int ID;
	private String name;
	private String adresse;
	private boolean allowed;
	
	private int ownerID;

	public Pub(int iD, String name, String adresse, boolean allowed, int ownerID) {
		super();
		ID = iD;
		this.name = name;
		this.adresse = adresse;
		this.allowed = allowed;
		this.ownerID = ownerID;
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
	
	
}
