package com.svi.config;

import org.ini4j.Ini;
import org.ini4j.Profile.Section;

public enum Config {
	
	DB_URL,
	USER,
	PASS,
	DRIVER
	;
	private static Section section;

	public String getValue() {
		if (section != null) {
			return section.getOrDefault(this.toString(), "");
		}
		return "";
	}

	public static void initialize(Ini ini, ConfigHeader configHeader) throws NullPointerException {
		if (ini != null) {
			String key = configHeader.getValue();
			if (ini.containsKey(key)) {
				section = ini.get(key);
			} else {
				throw new NullPointerException("MISSING CONFIG HEADER: " + key);
			}
		}
	}
}

