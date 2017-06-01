package ubb.example;

import ubb.Observe;
import ubb.Subject;

@Subject
public class SubjectExample {
	
	private int state;
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
		Observe.on(this).notifyObservers();
	}
}
