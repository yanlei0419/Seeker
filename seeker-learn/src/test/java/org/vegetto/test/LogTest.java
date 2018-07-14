package org.vegetto.test;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LogTest {
	private static Logger logger = LogManager.getLogger(LogTest.class.getName());
	public static void main(String[] args) {
		logger.info("info");
		logger.error("error");
		logger.warn("warn");
		logger.debug("debug");
		logger.trace("trace");
	}
}
