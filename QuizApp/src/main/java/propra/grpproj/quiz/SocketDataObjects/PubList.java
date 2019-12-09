package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;
import java.util.ArrayList;

public class PubList implements Serializable{
	public ArrayList<Pub> list = new ArrayList<Pub>();
	
	public ArrayList<Pub> getList () {
		
		ArrayList<Pub> plist = this.list;
		return list;
	}
}
