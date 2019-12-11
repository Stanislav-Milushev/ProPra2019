package propra.grpproj.quiz.Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import propra.grpproj.gui.GuiAdmin;
import propra.grpproj.gui.GuiUserLogin;
import propra.grpproj.quiz.SocketDataObjects.AcceptPub;
import propra.grpproj.quiz.SocketDataObjects.CreatePubevening;
import propra.grpproj.quiz.SocketDataObjects.DeleteUser;
import propra.grpproj.quiz.SocketDataObjects.Login;
import propra.grpproj.quiz.SocketDataObjects.Pub;
import propra.grpproj.quiz.SocketDataObjects.PubList;
import propra.grpproj.quiz.SocketDataObjects.Question;
import propra.grpproj.quiz.SocketDataObjects.QuestionList;
import propra.grpproj.quiz.SocketDataObjects.RegisterPub;
import propra.grpproj.quiz.SocketDataObjects.RegisterUser;
import propra.grpproj.quiz.SocketDataObjects.RepeatPubevening;
import propra.grpproj.quiz.SocketDataObjects.Scoreboard;
import propra.grpproj.quiz.SocketDataObjects.TerminateConnection;

public class SocketClient implements Runnable{
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	private boolean terminateConnection = false;
	private static final Logger LOG = LoggerFactory.getLogger(SocketClient.class);
	 
	 /**
	  * Creates a SocketClient to connect to the server
	  * @param ip IP of the server
	  * @param port Port the server is listening
	  * @author Yannick
	  */
	 public SocketClient(String ip, int port) {
		 try {
			socket = new Socket(ip, port);
			LOG.info("Connected to " + ip + " with port " + port);
		} catch (IOException e) {
			e.printStackTrace();
			LOG.error("Failed to connect to " + ip + " with port " + port);
		}
	 }
	
	@Override
	public void run() {
		try { //Create streams
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			LOG.error("failed to open streams to server");
		}		
		
        Object recieve = null;
        //Read from the server until terminateconnection = true
        do {
        	try {
				recieve = ois.readObject();
				recieveObject(recieve);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
        } while(!(recieve instanceof TerminateConnection) && !terminateConnection);

        try {//Close the connection
            oos.close();
            ois.close();
            socket.close();
			LOG.info("Closed connection");
        } catch (IOException i) {
            System.out.println(i);
            LOG.error("Failed to close connection");
        }
	}
	
	/**
	 * Sends a given object to the server
	 * @param o Object to be sent to the server
	 * @author Yannick
	 */
	public void sendObject(Object o) {
		try {
			oos.writeObject(o);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
			LOG.error("Failed to send object to server");
		}
		
		//Close the connection
		if(o instanceof TerminateConnection) {
			terminateConnection = true;
		}
	}
	
	/**
	 * Compares object from the server to known objects
	 * and calls the responding functions in the gui
	 * @param o object recieved from the server
	 * @author Yannick
	 */
	private void recieveObject(Object o) {
		if(o instanceof AcceptPub) {
    		AcceptPub pub = (AcceptPub)o;
    	}
    	if(o instanceof CreatePubevening) {
    		CreatePubevening cpe = (CreatePubevening)o;
    	}
    	if(o instanceof DeleteUser) {
    		DeleteUser delUser = (DeleteUser)o;
    	}
    	if(o instanceof Login) {
    		Login lin = (Login)o;
    		GuiUserLogin.getInstance().Login_Return(lin);
    	}
    	if(o instanceof Pub) {
    		Pub pub = (Pub)o;
    	}
    	if(o instanceof PubList) {
    		PubList publ = (PubList)o;
    		GuiAdmin.getInstance().getPubListFromServer(publ);
    	}
    	if(o instanceof Question) { 
    		Question q = (Question)o;
    	}
    	if(o instanceof QuestionList) {
    		QuestionList ql = (QuestionList)o;
    		GuiAdmin.getInstance().getQuestionListFromServer(ql);
    	}
    	if(o instanceof RegisterPub) {
    		RegisterPub regPub = (RegisterPub)o;
    	}
    	if(o instanceof RegisterUser) {
    		RegisterUser regUser = (RegisterUser)o;
    	}
    	if(o instanceof RepeatPubevening) {
    		RepeatPubevening rpEvening = (RepeatPubevening)o;
    	}
    	if(o instanceof Scoreboard) {
    		Scoreboard scbd = (Scoreboard)o;
    	}
	}

}
