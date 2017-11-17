package com.vaticahealth.vatica.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Common {
	
	// Read from properties file by providing a key and getting a value
		// returned.
		public String readTestData(String key) {

			String filepath = "/src/test/resources/testdata.properties";

			File file = new File(System.getProperty("user.dir") + filepath);
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			OrderedProperties prop = new OrderedProperties();
			try {
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}

			return prop.getProperty(key);

		}


}
