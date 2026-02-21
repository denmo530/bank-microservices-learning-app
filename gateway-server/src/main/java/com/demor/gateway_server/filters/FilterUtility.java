package com.demor.gateway_server.filters;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;
import java.util.Optional;

@Component
public class FilterUtility {
    public static final String CORRELATION_ID = "bank-correlation-id";

    public String getCorrelationId(HttpHeaders requestHeaders) {
        if (requestHeaders.get(CORRELATION_ID) != null) {
            List<String> requestHeaderList = requestHeaders.get(CORRELATION_ID);

            if (requestHeaderList == null)
                return null;

            Optional<String> header = requestHeaderList.stream().findFirst();

            if (header.isPresent())
                return requestHeaderList.stream().findFirst().get();

            return null;
        }

        return null;
    }
    public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value) {
        return exchange.mutate().request(exchange.getRequest().mutate().header(name, value).build()).build();
    }
    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        return this.setRequestHeader(exchange, CORRELATION_ID, correlationId);
    }
}
