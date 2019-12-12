package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PubList implements Serializable{
	public List<Pub> list = new ArrayList<Pub>();
	
	PubList(List<Pub> list){
		this.list= list;
	}
	public List<Pub> getList () {
		return list;
	}

	public void setList(List<Pub> pList) {
		this.list = pList;
	}
}
