package ubb;

/**
 * This library helps development by simplifying Observer pattern implementation.
 * Annotate your subject classes with @Subject and observer classes with @Observer
 * @author Zoltan
 *
 */
public class Observe {
	
	private static ObserveImpl observeImpl;
	
	/**
	 * Initialize the observer object in memory graph
	 */
	public static void init() {
		observeImpl = new ObserveImpl();
	}
	/**
	 * Destroy the observer object in memory graph
	 */
	public static void destory() {
		SubjectOperationImpl.executor.shutdown();
		observeImpl = null;
	}
	
	/**
	 * Start working with the subject
	 * @param object subject for working on
	 */
	public static SubjectOperation on(Object object) {
		if (observeImpl == null) {
			throw new RuntimeException("Error: Call init first");
		}
		if (object == null) {
			throw new RuntimeException("Error: Passed object is null");
		}
		if (!ReflectionUtils.hasAnnotation(object, Subject.class)) {
			throw new RuntimeException("Error: " + object.getClass() + " must be declared with annotation type: @Subject");
		}
		return observeImpl.getSubjectOperationFor(object);
	}
}
