package jutils.jee.test;

import java.util.HashMap;
import java.util.Map;

public class XaTestData {

	private Map<String, Boolean> data = new HashMap<String, Boolean>();

	public void add(String propertyName, boolean value) {
		data.put(propertyName, value);
	}

	public boolean contains(String propertyName) {
		return data.containsKey(propertyName);
	}

	public boolean getValue(String propertyName) {
		return contains(propertyName) ? data.get(propertyName) : false;
	}
}
