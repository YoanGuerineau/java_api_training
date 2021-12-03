package fr.lernejo.navy_battle.web_server;

import fr.lernejo.navy_battle.client.NavyClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

class PingHandlerTest {

    NavyWebServer myTestNavyWebServer;
    NavyClient myTestHttpClient;

    @Test
    void ping_should_return_status_code_200_and_body_OK() throws IOException, URISyntaxException, InterruptedException {
        myTestNavyWebServer = new NavyWebServer();
        myTestNavyWebServer.createContext( "/ping" );
        myTestHttpClient = new NavyClient();
        HttpResponse<String> response = myTestHttpClient.ping( "localhost", NavyWebServer.DEFAULT_PORT );
        Assertions.assertEquals( 200, response.statusCode() );
        Assertions.assertEquals( "OK", response.body() );
    }
}
