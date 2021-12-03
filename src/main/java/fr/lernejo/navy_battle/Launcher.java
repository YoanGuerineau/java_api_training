package fr.lernejo.navy_battle;

import fr.lernejo.navy_battle.web_server.NavyWebServer;

import java.io.IOException;

public class Launcher {

    public static void main(String[] args) throws IOException {
        NavyWebServer myNavyWebServer;
        if ( args.length > 0 ) {
            int givenPort = Integer.parseInt(args[0]);
            myNavyWebServer = new NavyWebServer(givenPort);
        } else {
            System.out.println("You launched the server without providing a port.\nDefaulting port to: " + NavyWebServer.DEFAULT_PORT );
            myNavyWebServer = new NavyWebServer();
        }
        myNavyWebServer.setupContexts();
    }

}
