package com.demo.eurekademo.application_gateway.filter;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class LoggingGatewayFilterFactory extends AbstractGatewayFilterFactory<LoggingGatewayFilterFactory.Config> {
    private static final Logger log = LoggerFactory.getLogger(LoggingGatewayFilterFactory.class);

    public LoggingGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("logLevel");
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest httpRequest = exchange.getRequest();

            String logMessage = String.format("[%s] Request received from Ppath: %s", config.getLogLevel(),
                    httpRequest.getPath());
            if ("INFO".equalsIgnoreCase(config.getLogLevel())) {
                log.info(logMessage);
            } else if ("DEBUG".equalsIgnoreCase(config.getLogLevel())) {
                log.debug(logMessage);
            } else {
                log.trace(logMessage);
            }

            return chain.filter(exchange.mutate().request(httpRequest).build())
                    .then(Mono.fromRunnable(() -> {
                        log.trace("[{}] Response received for path: {}", config.getLogLevel(),
                                exchange.getRequest().getPath());
                    }));
        };
    }

    public static class Config {
        private String logLevel;

        public String getLogLevel() {
            return logLevel;
        }

        public void setLogLevel(String logLevel) {
            this.logLevel = logLevel;
        }
    }
}
