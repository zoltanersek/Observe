package ubb.example;

import ubb.Observer;

@Observer
public class NewThreadObserverExample {

	public void onNotifyNewThread() {
		System.out.println("Thread: " + Thread.currentThread().getName() + ", has been notified");
	}
	
}
