package ubb.example;

import ubb.Observe;

public class Main {
	public static void main(String[] args) {
		Observe.init();
		SubjectExample subject = new SubjectExample();
		ObserverExample o1 = new ObserverExample();
		NewThreadObserverExample o2 = new NewThreadObserverExample();
		
		Observe.on(subject).addObserver(o1);
		Observe.on(subject).addObserver(o2);
		subject.setState(2);
		subject.setState(5);
		subject.setState(7);
		Observe.destory();
	}
}
