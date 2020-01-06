package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;

/**
 * 
 *
 */

public class TerminateConnection implements Serializable{
	public TerminateConnection(TerminateConnectionReason reason) {
		this.reason = reason;
	}
	private TerminateConnectionReason reason = null;
	
	
	public TerminateConnectionReason getReason() {
		return reason;
	}
	
	
}
