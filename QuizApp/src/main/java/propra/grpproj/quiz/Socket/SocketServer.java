package propra.grpproj.quiz.Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import propra.grpproj.quiz.Socket.SocketDataObjects.CreateConnection;
import propra.grpproj.quiz.Socket.SocketDataObjects.TerminateConnection;

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
        try {
            server = new ServerSocket(port);
            LOG.info("Server started");
            LOG.info("Waiting for a client ...");
            
            Socket socket = server.accept();

            LOG.info("Client accepted");

            ClientConnection s = new ClientConnection();
            s.s = socket;
            executor.submit(s);
        } catch (IOException i) {
            System.out.println(i);
        }
        run();
    }

    private void removeSocket(Socket s){
    	nameToSocket.remove(socketToName.get(s));
        socketToName.remove(s);        
    }

    class ClientConnection implements Runnable{
        Socket s;
        @Override
        public void run() {
        	ObjectInputStream data = null;
        	ObjectOutputStream dataout = null;

            try {
            	data = new ObjectInputStream(s.getInputStream());
                dataout = new ObjectOutputStream(s.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Object recieve = null;
            do {
            	try {
					recieve = data.readObject();
					recieveObject(recieve);
				} catch (ClassNotFoundException | IOException e) {
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
            } catch (IOException e) {
                e.printStackTrace();
            }
            removeSocket(s);
        }
        
        private void recieveObject(Object o) {
        	if(o instanceof CreateConnection) {
        		CreateConnection c = (CreateConnection) o;
        		String name = c.getUserName();
        		
        		nameToSocket.put(name, s);
        		socketToName.put(s, name);
        	}
        }
    }
}
