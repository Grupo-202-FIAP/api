package com.postech.fastfood.adapter.driven.logging;

import com.postech.fastfood.core.ports.LoggerPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerAdapter implements LoggerPort {

    private final Logger logger = LoggerFactory.getLogger(LoggerAdapter.class);

    @Override
    public void info(String msg, Object... args) {
        logger.info(msg, args);
    }

    @Override
    public void debug(String msg, Object... args) {
        logger.debug(msg, args);
    }

    @Override
    public void warn(String msg, Object... args) {
        logger.warn(msg, args);
    }

    @Override
    public void error(String msg, Throwable t, Object... args) {
        logger.error(String.format(msg, args), t);
    }

    @Override
    public void error(String msg, Object... args) {
        logger.error(msg, args);
    }
}
