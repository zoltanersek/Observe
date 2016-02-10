package ubb;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SubjectOperationImpl implements SubjectOperation {

	static final String EXECUTOR_METHOD_NAME = "onNotifyNewThread";
	static final String NON_EXECUTOR_METHOD_NAME = "onNotify";
	static final int EXECUTOR_THREAD_POOL_SIZE = 5;
	
	List<Object> observers;
	static ExecutorService executor;
	
	static {
		executor = Executors.newFixedThreadPool(EXECUTOR_THREAD_POOL_SIZE);
	}
	
	SubjectOperationImpl() {
		observers = new ArrayList<>();
	}
	
	@Override
	public void addObserver(Object observer) {
		if (observer == null) {
			throw new RuntimeException("Error: Passed observer is null");
		}
		if (!observer.getClass().isAnnotationPresent(Observer.class)) {
			throw new RuntimeException("Error: " + observer.getClass() + " must be declared with annotation type: @Observer");
		}
		observers.add(observer);
	}

	@Override
	public void removeObserver(Object observer) {
		if (observer == null) {
			throw new RuntimeException("Error: Passed observer is null");
		}
		if (!ReflectionUtils.hasAnnotation(observer, Observer.class)) {
			throw new RuntimeException("Error: " + observer.getClass() + " must be declared with annotation type: @Observer");
		}
		boolean result = observers.remove(observer);
		if (result == false) {
			System.err.println("Warning: No object was present when trying to remove observer");
		}
	}

	@Override
	public void notifyObservers() {
		for (final Object object : observers) {
			boolean onExecutor = true;
			Method method = ReflectionUtils.hasMethodWithName(object, EXECUTOR_METHOD_NAME);
			if (method == null) {
				method = ReflectionUtils.hasMethodWithName(object, NON_EXECUTOR_METHOD_NAME);
				onExecutor = false;
			}
			if (method == null) {
				System.err.println("Warning: no onNotify* methods found on object with type: " + object.getClass());
				continue;
			}
			final Method methodFinalCopy = method;
			if (onExecutor) {
				executor.submit(new Runnable() {
					
					@Override
					public void run() {
						try {
							methodFinalCopy.invoke(object);
						} catch (Exception e) {
							System.err.println("Warning: could not invoke onNotify* methods found on object with type: " + object.getClass());
						}
					}
				});
			} else {
				try {
					methodFinalCopy.invoke(object);
				} catch (Exception e) {
					System.err.println("Warning: could not invoke onNotify* methods found on object with type: " + object.getClass());
				}
			}
		}
	}

}
