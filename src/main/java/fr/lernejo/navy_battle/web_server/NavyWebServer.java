package fr.lernejo.navy_battle.web_server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

public class NavyWebServer {

    private final HttpServer myHttpServer;
    private InetSocketAddress mySocket;
    private ExecutorCompletionService<HttpServer> myExecutorService;

    public NavyWebServer(int port) throws IOException {
        this.mySocket = new InetSocketAddress(port);
        this.myHttpServer = HttpServer.create(mySocket, 0);
        this.myHttpServer.setExecutor( Executors.newCachedThreadPool() );
        this.myExecutorService = new ExecutorCompletionService( this.myHttpServer.getExecutor() );
        this.myHttpServer.start();
    }

    public void createContext(String path) {
        this.myHttpServer.createContext(path, new PingHandler());
    }



}
