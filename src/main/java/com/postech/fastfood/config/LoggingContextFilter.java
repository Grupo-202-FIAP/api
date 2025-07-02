package com.postech.fastfood.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;
import java.util.UUID;
import jakarta.servlet.Filter;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Component
public class LoggingContextFilter implements Filter {

    private static final String TRACE_ID = "traceId";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        try {
            MDC.put(TRACE_ID, UUID.randomUUID().toString());
            chain.doFilter(request, response);
        } finally {
            MDC.remove(TRACE_ID);
        }
    }


}
