package com.svi.config;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

public class ConfigInitiator {
	public static void initialize(String configPath)
			throws InvalidFileFormatException, IOException, FileNotFoundException {
		File file = new File(configPath);
		if (file.exists()) {
			Ini ini = new Ini(file);
			Config.initialize(ini, ConfigHeader.CONFIG);

		} else {
			throw new FileNotFoundException("MISSING CONFIGURATION FILE AT " + configPath);
		}
	}
}
