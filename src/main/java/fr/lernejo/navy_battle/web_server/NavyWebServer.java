package fr.lernejo.navy_battle.web_server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class NavyWebServer {

    public static final int DEFAULT_PORT = 9876;

    private final HttpServer myHttpServer;
    private final List<CallHandler> contexts = new ArrayList<>();

    public NavyWebServer(int port) throws IOException {
        this.myHttpServer = HttpServer.create( new InetSocketAddress( port ), 0);
        this.myHttpServer.setExecutor( Executors.newFixedThreadPool(1) );
        this.myHttpServer.start();
        this.contexts.add(new PingHandler());
    }

    public void createContext(String path, CallHandler handler) {
        this.myHttpServer.createContext(path, handler);
    }

    public void setupContexts() {
        contexts.forEach(
            context -> this.createContext(context.getAssignedPath(), context)
        );
    }

    public void stop() {
        this.myHttpServer.stop(0);
    }

}
