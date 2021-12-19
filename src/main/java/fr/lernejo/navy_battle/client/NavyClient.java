package fr.lernejo.navy_battle.client;
import fr.lernejo.navy_battle.transactions.JSONGameStart;
import fr.lernejo.navy_battle.web_server.NavyWebServer;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Random;

public class NavyClient extends NavyWebServer {

    private final HttpClient myHttpClient;
    private final URL targetURL;
    private final int givenPort;

    public NavyClient( int givenPort, String targetURL ) throws IOException {
        super( givenPort );
        URL foundURL = null;
        try { foundURL = new URL( targetURL ); }
        catch ( MalformedURLException e ) { e.printStackTrace(); }
        this.givenPort = givenPort;
        this.myHttpClient = HttpClient.newHttpClient();
        this.targetURL = foundURL;
    }

    public HttpResponse<String> sendGETRequest( String path ) {
        HttpResponse<String> response = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri( URI.create( this.targetURL + path ) )
                .timeout( Duration.ofSeconds( 5 ) )
                .GET()
                .build();
            response = myHttpClient.send( request, HttpResponse.BodyHandlers.ofString() );
        } catch ( IOException | InterruptedException e ) {
            System.err.println( "Error when sending GET request to: " + "http://"+this.targetURL.getHost()+":"+this.targetURL.getPort()+path );
            e.printStackTrace();
        }
        return response;
    }

    public HttpResponse<String> sendPOSTRequest( String path, String toPost ) {
        HttpResponse<String> response = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri( URI.create( this.targetURL + path ) )
                .setHeader( "Accept", "application/json" )
                .setHeader( "Content-Type", "application/json" )
                .POST( HttpRequest.BodyPublishers.ofString( toPost ) )
                .build();
           response = myHttpClient.send( request, HttpResponse.BodyHandlers.ofString() );
        } catch ( IOException | InterruptedException e ) {
            System.err.println( "Error when sending POST request to: " + "http://"+this.targetURL.getHost()+":"+this.targetURL.getPort()+path );
            e.printStackTrace();
        }
        return response;
    }

    public HttpResponse<String> ping() { return this.sendGETRequest( "/ping" ); }

    public HttpResponse<String> gameStart() throws MalformedURLException {
        JSONGameStart myMessage = new JSONGameStart( new URL("http://localhost:"+this.givenPort ), "I will beat you!");
        return this.sendPOSTRequest( "/api/game/start", myMessage.getJSONString() );
    }

    public HttpResponse<String> fire( String targetCell ) {
        System.out.println( "Firing: " + targetCell );
        return this.sendGETRequest( "/api/game/fire?cell="+targetCell );
    }

    public void engage() throws MalformedURLException {
        HttpResponse<String> myGameStartResponse = this.gameStart();
        System.out.println( myGameStartResponse.statusCode() );
        System.out.println( myGameStartResponse.body() );
        this.nextMove();
    }

    public void nextMove() {
        String targetCell = String.valueOf( (char)(new Random().nextInt(this.getEnemyOcean().getBoard()[0].length ) + (int)'A') ) + new Random().nextInt( this.getEnemyOcean().getBoard().length );
        HttpResponse<String> myGameFireResponse = this.fire( targetCell );
        System.out.println( myGameFireResponse.statusCode() );
        System.out.println( myGameFireResponse.body() );
    }
}
