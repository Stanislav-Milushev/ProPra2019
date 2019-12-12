package propra.grpproj.quiz.Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import propra.grpproj.gui.GuiAdmin;
import propra.grpproj.gui.GuiUserLogin;
import propra.grpproj.logic.QuestionHandling;
import propra.grpproj.quiz.SocketDataObjects.AcceptPub;
import propra.grpproj.quiz.SocketDataObjects.AddQuestion;
import propra.grpproj.quiz.SocketDataObjects.AddQuestionSet;
import propra.grpproj.quiz.SocketDataObjects.ChangePub;
import propra.grpproj.quiz.SocketDataObjects.ChangeQuestion;
import propra.grpproj.quiz.SocketDataObjects.CreateConnection;
import propra.grpproj.quiz.SocketDataObjects.CreatePubevening;
import propra.grpproj.quiz.SocketDataObjects.DeleteQuestion;
import propra.grpproj.quiz.SocketDataObjects.DeleteUser;
import propra.grpproj.quiz.SocketDataObjects.Explanation;
import propra.grpproj.quiz.SocketDataObjects.Login;
import propra.grpproj.quiz.SocketDataObjects.Pub;
import propra.grpproj.quiz.SocketDataObjects.PubList;
import propra.grpproj.quiz.SocketDataObjects.Question;
import propra.grpproj.quiz.SocketDataObjects.GetQuestionSet;
import propra.grpproj.quiz.SocketDataObjects.IntegerMap;
import propra.grpproj.quiz.SocketDataObjects.JoinQuiz;
import propra.grpproj.quiz.SocketDataObjects.RegisterPub;
import propra.grpproj.quiz.SocketDataObjects.RegisterUser;
import propra.grpproj.quiz.SocketDataObjects.RepeatPubevening;
import propra.grpproj.quiz.SocketDataObjects.Scoreboard;
import propra.grpproj.quiz.SocketDataObjects.TerminateConnection;
import propra.grpproj.quiz.SocketDataObjects.UserType;

public class SocketClient implements Runnable{
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	private String username;
	
	private static SocketClient instance = null;
	
	private boolean terminateConnection = false;
	private static final Logger LOG = LoggerFactory.getLogger(SocketClient.class);
	 
	 /**
	  * Creates a SocketClient to connect to the server
	  * @param ip IP of the server
	  * @param port Port the server is listening
	  * @author Yannick
	  */
	 private SocketClient(String ip, int port, String username) {
		 this.username = username;
		 try {
			socket = new Socket(ip, port);
			LOG.info("Connected to " + ip + " with port " + port);
		} catch (IOException e) {
			e.printStackTrace();
			LOG.error("Failed to connect to " + ip + " with port " + port);
		}
	 }
	 
	 public static void connect(String ip, int port, String username) {
		 if(instance == null) {
			 SocketClient client = new SocketClient(ip, port, username);
			 instance = client;
			 Thread clientConnection = new Thread(client);
			 clientConnection.start();
		 }
	 }
	 
	 public static SocketClient getInstance() {
		 return instance;
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
		
		CreateConnection create = new CreateConnection(username);
		try {
			oos.writeObject(create);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
    		//Unbenutzt
    	}
		if(o instanceof AddQuestion) {
			//Sollte nicht passieren
    	}
		if(o instanceof AddQuestionSet) {
			//Sollte nicht passieren
		}
		if(o instanceof ChangePub) {
			//Sollte nicht passieren
		}
		if(o instanceof ChangeQuestion) {
			//Sollte nicht passieren
		}
    	if(o instanceof CreatePubevening) {
    		//Sollte nicht passieren
    	}
    	if(o instanceof DeleteQuestion) {
    		//Sollte nicht passieren
    	}
    	if(o instanceof DeleteUser) {
    		//Sollte nicht passieren
    	}
    	if(o instanceof Explanation) {
    		Explanation exp = (Explanation)o;
    		//Explanation anzeigen
    	}
    	if(o instanceof GetQuestionSet) {
    		GetQuestionSet ql = (GetQuestionSet)o;
    		GuiAdmin.getInstance().getQuestionListFromServer(ql);
    	}
    	if(o instanceof IntegerMap) {
    		//2
    	}
    	if(o instanceof JoinQuiz) {
    		//Vielleicht eine Rückantwort anzeigen
    	}
    	if(o instanceof Login) {
    		Login lin = (Login)o;
    		UserType usertype = lin.getType();
    		GuiUserLogin.getInstance().login_Return(usertype);
    	}
    	if(o instanceof Pub) {
    		Pub pub = (Pub)o;
    	}
    	if(o instanceof PubList) {
    		PubList publ = (PubList)o;
    		GuiAdmin.getInstance().getPubListFromServer(publ);
    	}
    	if(o instanceof Question) {
    		Question q = (Question)o; //Frage anzeigen
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
    		Scoreboard scbd = (Scoreboard)o; //Score anzeigen
    	}
	}

}
