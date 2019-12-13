package propra.grpproj.quiz.SocketDataObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PubList implements Serializable{
	private List<Pub> list = null;
	
	/**
	 * 
	 * @param list
	 */
	
	PubList(List<Pub> list){
		this.list= list;
	}
	
	public PubList() {
		// TODO Auto-generated constructor stub
	}

	public List<Pub> getList () {
		return list;
	}

	public void setList(List<Pub> pList) {
		this.list = pList;
	}
}
