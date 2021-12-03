package fr.lernejo.navy_battle;

import fr.lernejo.navy_battle.web_server.NavyWebServer;

import java.io.IOException;

public class Launcher {

    public static void main(String[] args) throws IOException {
        NavyWebServer myNavyWebServer = new NavyWebServer(9876);
        myNavyWebServer.createContext("/ping");
    }

}
