package ubb;

/**
 * Operations to be performed on a subject
 * @author Zoltan
 *
 */
public interface SubjectOperation {
	/**
	 * Add a new observer
	 * @param observer
	 */
	public void addObserver(Object observer);
	/**
	 * Remove an observer
	 * @param observer
	 */
	public void removeObserver(Object observer);
	/**
	 * Notify all observers
	 */
	public void notifyObservers();
}
