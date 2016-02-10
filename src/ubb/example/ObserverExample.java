package ubb.example;

import ubb.Observer;

@Observer
public class ObserverExample {

	public void onNotify() {
		System.out.println("Thread: " + Thread.currentThread().getName() + ", has been notified");
	}
	
}
