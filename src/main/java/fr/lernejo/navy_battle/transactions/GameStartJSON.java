package fr.lernejo.navy_battle.transactions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameStartJSON {

    public final String DEFAULT_PATTERN = "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx";

    private final String id;
    private final URL url;
    private final String message;
    private final Gson gson;

    public GameStartJSON(URL url, String message ) {
        this.id = getIdFromPattern( DEFAULT_PATTERN );
        this.url = url;
        this.message = message;
        gson = new GsonBuilder().create();
    }

    public String getIdFromPattern(String pattern) {
        char[] chars = pattern.toCharArray();
        String generatedId = "";
        Random r = new Random();
        for (char aChar : chars) {
            char nextChar = aChar;
            if (aChar == 'x') {
                nextChar = getRandomChar();
            }
            generatedId += nextChar;
        }
        return generatedId;
    }

    private char getRandomChar() {
        Random r = new Random();
        int randomValue = r.nextInt(16);
        if ( randomValue < 10) {
            return (char)(randomValue + '0');
        } else {
            return (char)((randomValue % 10) + 'a');
        }
    }

    public String getJSON() {
        Map<String,String> returnObject = new HashMap<>();
        returnObject.put("id",this.id);
        returnObject.put("url",String.valueOf(this.url));
        returnObject.put("message",this.message);
        return gson.toJson(returnObject);
    }
}
