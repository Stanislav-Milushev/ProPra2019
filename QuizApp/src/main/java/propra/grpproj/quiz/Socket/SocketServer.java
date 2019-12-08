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
import propra.grpproj.logic.ScoreboardUpdate;
import propra.grpproj.logic.UserHandling;
import propra.grpproj.quiz.SocketDataObjects.*;

public class SocketServer implements Runnable{
    private static final Logger LOG = LoggerFactory.getLogger(SocketServer.class);
    private ExecutorService executor = null;
    private int port;
    private ServerSocket server = null;
    
    HashMap<String, Socket> nameToSocket = new HashMap<String, Socket>();
    HashMap<Socket, String> socketToName = new HashMap<Socket, String>();
    
    public SocketServer(int port){
        this.port = port;
    }
    
    /**
     * 
     * @param o SocketDataObject that is sent to the client
     * @param username Name of the client
     * @author Yannick
     */
    public void sendObject(Object o, String username) {
    	if(nameToSocket.containsKey(username)) {
    		try {
				ObjectOutputStream oos = new ObjectOutputStream(nameToSocket.get(username).getOutputStream());
				oos.writeObject(o);
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        try {      
            LOG.info("Waiting for a client ...");
            
            Socket socket = server.accept();

            LOG.info("Client accepted");

            ClientConnection s = new ClientConnection(socket);
            executor.submit(s);
        } catch (IOException i) {
            System.out.println(i);
            LOG.error("Failed to connect with client");
        }
        run();
    }

    /**
     * 
     * @param s socket to be removed from the maps
     * @author Yannick
     */
    private void removeSocket(Socket s){
    	nameToSocket.remove(socketToName.get(s));
        socketToName.remove(s);        
    }

    /**
     * 
     * @author Yannick
     * Represents a connection from the server to a client
     * Listens for data from the client
     */
    class ClientConnection implements Runnable{
        private Socket s;
        
        public ClientConnection(Socket socket) {
			s = socket;
		}
        @Override
        public void run() {
        	ObjectInputStream data = null;
        	ObjectOutputStream dataout = null;

            try {
                dataout = new ObjectOutputStream(s.getOutputStream());
            	data = new ObjectInputStream(s.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            Object recieve = null;
            do {
            	try {
					recieve = data.readObject();
					recieveObject(recieve);
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } while(!(recieve instanceof TerminateConnection));
            
            LOG.info("Closing connection to" + socketToName.get(s));
            
            // close connection
            try {
                s.close();
                data.close();
                dataout.close();
                LOG.info("Closing connection to client");
            } catch (IOException e) {
                e.printStackTrace();
                LOG.error("Failed to close connection to client");
            }
            removeSocket(s);
        }
        
        /**
         * 
         * @param o Object to be sent to the client
         * @author Yannick & Marius
         * @throws SQLException 
         */
        private void recieveObject(Object o) throws SQLException {
        	if(o instanceof CreateConnection) {
        		CreateConnection c = (CreateConnection) o;
        		String name = c.getUserName();
        		nameToSocket.put(name, s);
        		socketToName.put(s, name);
        	}
        	if(o instanceof AcceptPub) {
        		AcceptPub acp = (AcceptPub) o;
        		String name = acp.getName();
        		String owner = acp.getOwner();
        		AdminHandling admin = new AdminHandling();
        		admin.approvePub(name,owner);
        	}
        	if(o instanceof CreatePubevening) {
        		CreatePubevening cpe = (CreatePubevening)o;
        	}
        	if(o instanceof DeleteUser) {
        		DeleteUser du = (DeleteUser) o;
        		String name = du.getUsername();
        		String passwd = "";
        		UserHandling delete = new UserHandling();
        		delete.deleteUser(name, passwd);
        		
        	}
        	if(o instanceof Login) {
        		Login lin = (Login)o;
        		String email = lin.getUserName();
        		String passwd = lin.getPassword();
        		UserType type = lin.getType();
        		UserHandling login = new UserHandling();
        		login.user_login(email, passwd);
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
        	if(o instanceof QuestionList) {
        		QuestionList ql = (QuestionList)o;
        	}
        	if(o instanceof RegisterPub) {
        		RegisterPub regPub = (RegisterPub)o;
        	}
        	if(o instanceof RegisterUser) {
        		RegisterUser regUser = (RegisterUser)o;
        		String passwd = regUser.getPassword();
        		String mail = regUser.getMail();
        		String name = regUser.getUsername();
        		UserHandling register = new UserHandling();
        		register.user_register(name, mail, passwd);
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
