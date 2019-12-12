package propra.grpproj.quiz.Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import propra.grpproj.logic.AdminHandling;
import propra.grpproj.logic.PubHandling;
import propra.grpproj.logic.QuestionHandling;
import propra.grpproj.logic.QuizHandling;
import propra.grpproj.logic.ScoreboardUpdate;
import propra.grpproj.logic.UserHandling;
import propra.grpproj.quiz.SocketDataObjects.*;

public class SocketServer implements Runnable{
	private static SocketServer instance = null;
	
    private static final Logger LOG = LoggerFactory.getLogger(SocketServer.class);
    
    private ExecutorService executor = null;
    private int port;
    
    private ServerSocket server = null;
    
    HashMap<String, User> nameToSocket = new HashMap<String, User>();
    
    public SocketServer(int port){
        this.port = port;
    }
    
    /**
     * Starts the server on its own thread
     * @param port
     * @author Yannick
     */
    public static void start(int port) {
    	if(instance == null) {
    		instance = new SocketServer(port);
    		Thread t = new Thread(instance);
    		t.start();
    	}
    }
    
    public static SocketServer getInstance() {
    	return instance;
    }
    
    /**
     * Sends a given object to a specified user
     * @param o SocketDataObject that is sent to the client
     * @param username Name of the client
     * @author Yannick
     */
    public void sendObject(Object o, String username) {
    	if(nameToSocket.containsKey(username)) {
    		try {
				nameToSocket.get(username).oos.writeObject(o);
				nameToSocket.get(username).oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}    		
    	}
    }

    @Override
    public void run(){
        if(executor == null){
            executor = Executors.newFixedThreadPool(2);
        }
        if(server == null) {
        	 try {
        		 LOG.info("Server started");
				server = new ServerSocket(port);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        try {      
            LOG.info("Waiting for a client ...");
            
            Socket socket = server.accept();

            LOG.info("Client accepted");

            ClientConnection s = new ClientConnection(socket); //Create thread for client
            executor.submit(s);
        } catch (IOException i) {
            System.out.println(i);
            LOG.error("Failed to connect with client");
        }
        run();
    }
    
    /**
     * Holds streams
     * @author Yannick
     *
     */
    class User{
    	Socket socket;
    	ObjectOutputStream oos;
    	ObjectInputStream ois;
    }

    /**
     * Represents a connection from the server to a client
     * Listens for data from the client
     * @author Yannick
     */
    class ClientConnection implements Runnable{
        private User user;
        private String username;
        
        public ClientConnection(Socket socket) {
			user = new User();
			user.socket = socket;
		}
        
        @Override
        public void run() {
            try {//Create streams
                user.oos = new ObjectOutputStream(user.socket.getOutputStream());
            	user.ois = new ObjectInputStream(user.socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            Object recieve = null; //Listen for client
            do {
            	try {
					recieve = user.ois.readObject();
					
		        	if(recieve instanceof CreateConnection) {
		        		CreateConnection c = (CreateConnection) recieve;
		        		username = c.getUserName();
		        		nameToSocket.put(username, user);
		        	} else {
		        		recieveObject(recieve);
		        	}
				
				} catch (ClassNotFoundException | IOException | SQLException e) {
					e.printStackTrace();
				} 
            } while(!(recieve instanceof TerminateConnection));
            
            LOG.info("Closing connection");
            
            // close connection
            try {
            	user.oos.close();
            	user.ois.close();
            	user.socket.close();
                LOG.info("Closing connection to client");
            } catch (IOException e) {
                e.printStackTrace();
                LOG.error("Failed to close connection to client");
            }
            
            nameToSocket.remove(username);
        }
        
        /**
         * 
         * @param o Object to be sent to the client
         * @author Yannick & Marius & Stan
         * @throws SQLException 
         */
        private void recieveObject(Object o) throws SQLException {
        	if(o instanceof AcceptPub) {
        		AcceptPub acp = (AcceptPub) o;
        		String name = acp.getName();
        		String owner = acp.getOwner();
        		AdminHandling admin = new AdminHandling();
        		admin.approvePub(name,owner);
        	}
        	if(o instanceof AddQuestionSet) {
        		AddQuestionSet ql = (AddQuestionSet)o;
        		QuestionHandling.questionImport(ql);
        	}
        	if(o instanceof ChangePub) {
        		ChangePub cp = (ChangePub)o;
        	}
        	if(o instanceof ChangeQuestion) {
        		ChangeQuestion cq = (ChangeQuestion)o;
        	}
        	if(o instanceof CreatePubevening) {
        		CreatePubevening cpe = (CreatePubevening)o;
        		QuizHandling.getInstance().createQuiz(cpe);
        	}
        	if(o instanceof DeleteQuestion) {
        		DeleteQuestion dq = (DeleteQuestion)o;
        	}
        	if(o instanceof DeleteUser) {
        		DeleteUser du = (DeleteUser) o;
        		String name = du.getUsername();
        		String passwd = "";
        		UserHandling delete = new UserHandling();
        		delete.deleteUser(name, passwd);        		
        	}
        	if(o instanceof Exception) {
        		Explanation exp = (Explanation)o;
        	}
        	if(o instanceof GetQuestionSet) {
        		GetQuestionSet qs = (GetQuestionSet)o;
        		QuestionHandling.getQuestionSet(qs, username);
        	}
        	if(o instanceof IntegerMap) {
        		IntegerMap intMap = (IntegerMap)o;
        		QuizHandling.getInstance().answer(username, intMap);
        	}
        	if(o instanceof JoinQuiz) {
        		JoinQuiz jq = (JoinQuiz)o;
        		QuizHandling.getInstance().joinQuiz(jq.getUsername(), jq.getQuizID());
        	}
        	if(o instanceof Login) {
        		Login lin = (Login)o;
        		UserHandling user = new UserHandling();
        		user.user_login(lin);
        	}
        	if(o instanceof Pub) {
        		Pub pub = (Pub)o;
        	}
        	if(o instanceof PubList) {
        		PubList publ = (PubList)o;
        		
        	}
        	if(o instanceof Question) {
        		Question q = (Question)o;
        	}
        	if(o instanceof RegisterPub) {
        		RegisterPub regPub = (RegisterPub)o;
        		String name = regPub.getName();
        		String address = regPub.getAddress();
        		int ownerid = regPub.getOwnerID();
        		PubHandling pb = new PubHandling();
        		pb.registerPub(name, address, ownerid);
        	}
        	if(o instanceof RegisterUser) {
        		RegisterUser regUser = (RegisterUser)o;
        		String passwd = regUser.getPassword();
        		String mail = regUser.getMail();
        		String name = regUser.getUsername();
        		String type = "DEFAULT";
        		UserType usertype = UserType.valueOf(type);
        		UserHandling register = new UserHandling();
        		register.user_register(name, mail, passwd, usertype);
        	}
        	if(o instanceof RepeatPubevening) {
        		RepeatPubevening rpEvening = (RepeatPubevening)o;
        	}
        	if(o instanceof Scoreboard) {
        		Scoreboard scbd = (Scoreboard)o;
        		String name = scbd.getUser();
        		float score = scbd.getScore();
        		ScoreboardUpdate sco = new ScoreboardUpdate();
        		sco.writeToDB(name, score);
        	}
        }
    }
}
