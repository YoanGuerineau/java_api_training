package fr.lernejo.navy_battle.client;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class NavyClient {

    private final HttpClient myHttpClient;

    public NavyClient( ) {
        myHttpClient = HttpClient.newBuilder()
            .version( HttpClient.Version.HTTP_1_1 )
            .followRedirects( HttpClient.Redirect.NORMAL )
            .connectTimeout( Duration.ofSeconds( 5 ) )
            .build();
    }

    public HttpResponse<String> sendGETRequest(String domainName, int port, String path) {
        HttpResponse<String> response = null;
        try {
            URL targetURL = new URL( "http", domainName, port, path );
            HttpRequest request = HttpRequest.newBuilder()
                .uri( targetURL.toURI() )
                .timeout( Duration.ofSeconds( 5 ) )
                .GET()
                .build();
            response = myHttpClient.send( request, HttpResponse.BodyHandlers.ofString() );
        } catch ( IOException | InterruptedException | URISyntaxException e) {
            System.err.println("Error when sending GET request to: " + "http://"+domainName+":"+port+"/ping");
            e.printStackTrace();
        }
        return response;
    }

    public HttpResponse<String> ping( String domainName, int port ) {
        return this.sendGETRequest( domainName, port, "/ping" );
    }
}
