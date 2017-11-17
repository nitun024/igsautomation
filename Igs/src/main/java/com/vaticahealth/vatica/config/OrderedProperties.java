package com.vaticahealth.vatica.config;

import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Vector;

public class OrderedProperties extends Properties {

	// To maintain the insertion order and sort the property file after writing.
	@SuppressWarnings("unchecked")
	public synchronized Enumeration keys() {
		Enumeration keysEnum = super.keys();
		Vector keyList = new Vector();
		while (keysEnum.hasMoreElements()) {
			keyList.add(keysEnum.nextElement());
		}
		Collections.sort(keyList);
		return keyList.elements();
	}

}