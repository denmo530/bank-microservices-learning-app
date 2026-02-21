package com.demor.gateway_server.filters;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Order(1)
@Component
public class RequestTraceFilter implements GlobalFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestTraceFilter.class);
    private final FilterUtility filterUtility;

    RequestTraceFilter(FilterUtility filterUtility) {
        this.filterUtility = filterUtility;
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, @NonNull GatewayFilterChain chain) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();

        if (isCorrelationIdPresent(requestHeaders)) {
            logger.debug("bank-correlation-id found in RequestTraceFilter: {}",
                    filterUtility.getCorrelationId(requestHeaders));
        }

        String correlationId = generateCorrelationId();
        exchange = filterUtility.setCorrelationId(exchange, correlationId);
        logger.debug("bank-correlation-id generated in RequestTraceFilter: {}", correlationId);

        return chain.filter(exchange);
    }

    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }

    private boolean isCorrelationIdPresent(HttpHeaders requestHeaders) {
        return filterUtility.getCorrelationId(requestHeaders) != null;
    }
}
