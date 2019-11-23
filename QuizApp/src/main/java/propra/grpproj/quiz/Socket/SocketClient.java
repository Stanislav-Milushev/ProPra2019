package propra.grpproj.quiz.Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import propra.grpproj.quiz.Socket.SocketDataObjects.TerminateConnection;

public class SocketClient implements Runnable{
	private Socket socket;
	private boolean terminateConnection = false;
	private static final Logger LOG = LoggerFactory.getLogger(SocketClient.class);
	 
	 /**
	  * 
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
		ObjectInputStream input = null;
		ObjectOutputStream output = null;
		try {
			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e1) {
			e1.printStackTrace();
			LOG.error("failed to open streams to server");
		}		
		
        Object recieve = null;
        
        do {
        	try {
				recieve = input.readObject();
				recieveObject(recieve);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
        } while(!(recieve instanceof TerminateConnection) && !terminateConnection);
        // close the connection

        try {
            input.close();
            output.close();
            socket.close();
			LOG.info("Closed connection");
        } catch (IOException i) {
            System.out.println(i);
            LOG.error("Failed to close connection");
        }
	}
	
	/**
	 * 
	 * @param o Object to be sent to the server
	 * @author Yannick
	 */
	public void sendObject(Object o) {
		ObjectOutputStream output = null;;
		try {
			output = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			LOG.error("Failed to open outputstream");
		}
		try {
			output.writeObject(o);
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
	 * 
	 * @param o object recieved from the server
	 * @author Yannick
	 */
	private void recieveObject(Object o) {
		
	}

}
