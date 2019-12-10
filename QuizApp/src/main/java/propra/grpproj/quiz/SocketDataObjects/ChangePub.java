package propra.grpproj.quiz.SocketDataObjects;

public class ChangePub {

	private int pubID;
	private String pubName;
	private boolean allowed;
	private int ownderID;
	private String ownerName;
	private String address;
	
	public ChangePub(int pubID, String pubName, boolean allowed, int ownderID, String ownerName, String address) {
		super();
		this.pubID = pubID;
		this.pubName = pubName;
		this.allowed = allowed;
		this.ownderID = ownderID;
		this.ownerName = ownerName;
		this.address = address;
	}

	public int getPubID() {
		return pubID;
	}

	public String getPubName() {
		return pubName;
	}

	public boolean isAllowed() {
		return allowed;
	}

	public int getOwnderID() {
		return ownderID;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public String getAddress() {
		return address;
	}
	
	
	
}
