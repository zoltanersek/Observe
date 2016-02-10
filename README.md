# Observe
The observer pattern is a widely used pattern that involves two entities: subjects or observables and observers. It is mostly the first design pattern a student learns, and therefore its implementation is very important for those first encounters with design patterns and generic software design. However, after that its implementation is just boilerplate code, and every time we want to implement this pattern we have to write the code for setting up notification, adding and removing of observers and other repetitive tasks.


The Observe library is a library that takes the boilerplate part of the Observer pattern out, by implementing the functionality in the background. Using this library can save the developer some time. It is based on annotations and it is easy to set up.
As I have said the Observer pattern involves communication between two entities: the Subject and the Observer. An observer can register to be notified when the subject changes its state.


## Usage example

You have to start by initializing the Observe library, calling Observe.init()
This operation has to be done once, before any usage of the library other functionalities, and it is often best performed at the initialization of the application that is using the library.


When you are done with the application you can call Observe.destroy() for a cleanup. However, this step is not mandatory.        

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

Here we see an example of a Subject. You have to annotate the Subject class with the @Subject annotation. When you want to notify your observers about a state change you will call the Observe.on(this).notifyObservers();

    @Observer
    public class ObserverExample {

	   public void onNotify() {
		    System.out.println("Thread: " + Thread.currentThread().getName() + ", has been notified");
	   }
	
    }
    
    @Observer
    public class NewThreadObserverExample {

	    public void onNotifyNewThread() {
		    System.out.println("Thread: " + Thread.currentThread().getName() + ", has been notified");
	    }
    }

Above we can see two examples of Observers. The observers need to be annotated with the @Observer annotation. You have two options here: implement onNotify(), which will be called when a subject changes its state or onNotifyNewThread() which is also called when a subject changes its state with the difference that it is called on a new thread. Make sure that the name of the methods are the ones presented above, or else they won’t be called.

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

And finally, here we can see how it all ties together. When you want to work wi
