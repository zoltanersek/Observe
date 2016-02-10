package ubb;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents an observer
 * @author Zoltan
 * implement the following methods for something to happen on notify:
 * void onNotify() or void onNotifyNewThread() for executing on new thread
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface Observer {

}
