package ubb;

import java.util.HashMap;
import java.util.Map;

class ObserveImpl {
	
	
	private Map<Object, SubjectOperationImpl> links;
	
	ObserveImpl() {
		links = new HashMap<>();
	}
	
	SubjectOperationImpl getSubjectOperationFor(Object object) {
		if (links.get(object) == null) {
			SubjectOperationImpl operationImpl = new SubjectOperationImpl();
			links.put(object, operationImpl);
		}
		return links.get(object);
	}
}
