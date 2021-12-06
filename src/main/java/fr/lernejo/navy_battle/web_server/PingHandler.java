package fr.lernejo.navy_battle.web_server;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class PingHandler implements CallHandler {

    @Override
    public String[] allowedRequestMethods() {
        return new String[]{"GET"};
    }

    @Override
    public boolean isMethodAllowed(String method) {
        return Arrays.stream(this.allowedRequestMethods()).toList().contains(method);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String body = "Not found";
        if ( ! this.isMethodAllowed(exchange.getRequestMethod()) ) {
            exchange.sendResponseHeaders(404, body.length());
        } else {
            body = "OK";
            exchange.sendResponseHeaders(200, body.length());
        }
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(body.getBytes());
        }
    }

    @Override
    public String getAssignedPath() {
        return "/ping";
    }
}
