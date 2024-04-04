package com.svi.config;

public enum ConfigHeader {
	CONFIG;

	private String value;

	private ConfigHeader(String value) {
		this.value = value;
	}

	private ConfigHeader() {
		this.value = this.toString();
	}

	public String getValue() {
		return value;
	}
}
