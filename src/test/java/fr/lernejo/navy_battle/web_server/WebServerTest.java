package fr.lernejo.navy_battle.web_server;

import fr.lernejo.navy_battle.client.NavyClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

class WebServerTest {

    PingHandler myTestPingHandler = new PingHandler();
    NavyWebServer myTestNavyWebServer;
    NavyClient myTestHttpClient;

    @BeforeEach
    void setup_web_server() throws IOException {
        myTestNavyWebServer = new NavyWebServer();
    }
    @BeforeEach
    void setup_client() {
        myTestHttpClient = new NavyClient();
    }

    @AfterEach
    void close_web_server() {
        myTestNavyWebServer.stop();
    }

    @Test
    void ping_assigned_path_should_be_ping() {
        Assertions.assertEquals("/ping", myTestPingHandler.getAssignedPath());
    }

    @Test
    void ping_should_return_status_code_200_and_body_OK() throws IOException, URISyntaxException, InterruptedException {
        myTestNavyWebServer.createContext( myTestPingHandler.getAssignedPath(), myTestPingHandler );
        HttpResponse<String> response = myTestHttpClient.ping( "localhost", NavyWebServer.DEFAULT_PORT );
        Assertions.assertEquals( 200, response.statusCode() );
        Assertions.assertEquals( "OK", response.body() );
    }

    @Test
    void setupContexts_should_setup_all_contexts() throws IOException, URISyntaxException, InterruptedException {
        myTestNavyWebServer.setupContexts();
        HttpResponse<String> response = myTestHttpClient.ping( "localhost", NavyWebServer.DEFAULT_PORT );
        Assertions.assertEquals( 200, response.statusCode() );
        Assertions.assertEquals( "OK", response.body() );
        //Add content to check that all contexts are set
    }

    @Test
    void unknown_context_should_return_status_code_404() throws IOException, URISyntaxException, InterruptedException {
        myTestNavyWebServer.setupContexts();
        HttpResponse<String> response = myTestHttpClient.sendGETRequest( "localhost", NavyWebServer.DEFAULT_PORT, "/unknown/path" );
        Assertions.assertEquals( 404, response.statusCode() );
        //Add content to check that all contexts are set
    }
}
