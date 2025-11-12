package co.edu.unbosque.parcial3.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

@Service
public class CensorshipService {

    public String censor(String text) {
        String url = "https://www.purgomalum.com/service/json?text=" + text.replace(" ", "%20");
        String json = ExternalHttpRequestHandler.doGetAndParse(url);
        JsonObject obj = JsonParser.parseString(json).getAsJsonObject();
        return obj.get("result").getAsString();
    }
}