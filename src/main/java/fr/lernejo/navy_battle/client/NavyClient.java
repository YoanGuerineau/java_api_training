package fr.lernejo.navy_battle.client;

import fr.lernejo.navy_battle.web_server.NavyWebServer;

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

    public HttpResponse<String> ping( String domainName, int port ) throws IOException, URISyntaxException, InterruptedException {
        URL targetURL = new URL( "http", domainName, port, "/ping" );
        HttpRequest pingRequest = HttpRequest.newBuilder()
            .uri( targetURL.toURI() )
            .timeout( Duration.ofSeconds( 5 ) )
            .GET()
            .build();
        return myHttpClient.send( pingRequest, HttpResponse.BodyHandlers.ofString() );
    }
}
