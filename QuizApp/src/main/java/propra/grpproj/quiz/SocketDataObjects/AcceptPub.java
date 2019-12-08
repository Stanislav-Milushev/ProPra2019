package propra.grpproj.quiz.SocketDataObjects;

public class AcceptPub {
	private String name;
	private String owner;

	public AcceptPub(String name, String Owner) {
		this.name = name;
		this.owner = owner;
	}

	public String getName() {
		return name;
	}
	
	public String getOwner() {
		return owner;
	}
	
}
