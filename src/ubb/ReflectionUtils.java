package ubb;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

class ReflectionUtils {
	static boolean hasAnnotation(Object object, Class<? extends Annotation> annotation) {
		return object.getClass().isAnnotationPresent(annotation);
	}
	
	static Method hasMethodWithName(Object object, String methodName) {
		try {
			return object.getClass().getMethod(methodName);
		} catch (Exception exception) {
			return null;
		}
	}
}
