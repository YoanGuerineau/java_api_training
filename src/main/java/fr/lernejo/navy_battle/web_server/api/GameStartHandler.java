package fr.lernejo.navy_battle.web_server.api;

import com.sun.net.httpserver.HttpExchange;
import fr.lernejo.navy_battle.client.NavyClient;
import fr.lernejo.navy_battle.transactions.JSONGameStart;
import fr.lernejo.navy_battle.web_server.CallHandler;
import fr.lernejo.navy_battle.web_server.NavyWebServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class GameStartHandler implements CallHandler {

    @Override
    public String getAssignedPath() { return "/api/game/start"; }

    @Override
    public String[] allowedRequestMethods() { return new String[]{"POST"}; }

    @Override
    public boolean isMethodAllowed( String method ) { return Arrays.stream( this.allowedRequestMethods() ).toList().contains( method ); }

    @Override
    public void handle( HttpExchange exchange ) throws IOException {
        int responseCode = 404;
        String body = "Not found";
        if ( this.isMethodAllowed( exchange.getRequestMethod() ) ) {
            responseCode = 202;
            byte[] buffIn = exchange.getRequestBody().readAllBytes();
            System.out.println( new String( buffIn, StandardCharsets.UTF_8 ) );
            body = new JSONGameStart( new URL( "http://"+exchange.getRequestHeaders().getFirst( "Host" ) ), "May the best win!" ).getJSONString();
        }
        exchange.sendResponseHeaders( responseCode, body.length() );
        try ( OutputStream os = exchange.getResponseBody() ) { os.write( body.getBytes() ); }
    }
}
