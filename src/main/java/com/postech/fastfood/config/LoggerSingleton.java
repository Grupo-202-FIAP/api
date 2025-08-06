package com.postech.fastfood.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerSingleton {
    private static LoggerSingleton instance;

    private final Logger logger;

    private LoggerSingleton() {
        this.logger = LoggerFactory.getLogger(LoggerSingleton.class);
    }

    public static synchronized LoggerSingleton getInstance() {
        if (instance == null) {
            instance = new LoggerSingleton();
        }
        return instance;
    }

    public void info(String msg, Object... params) {
        logger.info(msg, params);
    }

    public void debug(String msg, Object... params) {
        logger.debug(msg, params);
    }

    public void error(String msg, Throwable t) {
        logger.error(msg, t);
    }

    public void warn(String msg, Object... params) {
        logger.warn(msg, params);
    }

}
