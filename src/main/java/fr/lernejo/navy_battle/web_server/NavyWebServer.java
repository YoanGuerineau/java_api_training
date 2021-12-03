package fr.lernejo.navy_battle.web_server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

public class NavyWebServer {

    public static final int DEFAULT_PORT = 9876;
    private final HttpServer myHttpServer;
    private ExecutorCompletionService<HttpServer> myExecutorService;

    public NavyWebServer() throws IOException {
        this(DEFAULT_PORT);
    }

    public NavyWebServer(int port) throws IOException {
        this.myHttpServer = HttpServer.create( new InetSocketAddress( port ), 0);
        this.myHttpServer.setExecutor( Executors.newCachedThreadPool() );
        this.myExecutorService = new ExecutorCompletionService( this.myHttpServer.getExecutor() );
        this.myHttpServer.start();
    }

    public void createContext(String path) {
        this.myHttpServer.createContext(path, new PingHandler());
    }



}
