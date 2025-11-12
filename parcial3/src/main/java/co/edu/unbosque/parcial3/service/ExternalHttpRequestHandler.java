package co.edu.unbosque.parcial3.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


/**
 * Clase encargada de realizar solicitudes HTTP externas, manejando las operaciones 
 * de tipo GET, POST, PUT y DELETE para interactuar con un servidor y procesar las respuestas.
 * Utiliza la arquitectura cliente-servidor con un patrón de solicitud-respuesta, 
 * y maneja los datos a través de objetos DTO (Data Transfer Object).
 * 
 * @author Daniel Garay
 * @version 1.0
 * @since 2025
 */
public class ExternalHttpRequestHandler {
    
    // Cliente HTTP con configuración básica.
    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)
        .connectTimeout(Duration.ofSeconds(10))
        .build();

    /**
     * Realiza una solicitud HTTP GET a una URL proporcionada y devuelve la respuesta 
     * formateada en formato JSON bonito (pretty).
     * 
     * @param url La URL a la que se realiza la solicitud.
     * @return La respuesta del servidor en formato JSON bonito.
     */
    public static String doGetAndParse(String url) {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
            .header("Content-type", "application/json").build();
        HttpResponse<String> response = null;
        try {
            response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("status code ->" + response.statusCode());
        String uglyJson = response.body();
        return prettyPrintUsingGson(uglyJson);
    }

    /**
     * Formatea una cadena JSON en un formato legible utilizando Gson.
     * 
     * @param uglyJson El JSON sin formato.
     * @return El JSON formateado en un formato bonito.
     */
    public static String prettyPrintUsingGson(String uglyJson) {
        Gson gson = new GsonBuilder().setLenient().setPrettyPrinting().create();
        JsonElement jsonElemnt = JsonParser.parseString(uglyJson);
        String prettyJsonString = gson.toJson(jsonElemnt);
        return prettyJsonString;
    }

    /**
     * Realiza una solicitud HTTP PUT a una URL proporcionada con los datos JSON enviados en el cuerpo.
     * 
     * @param url La URL a la que se realiza la solicitud.
     * @param json El contenido JSON que se enviará en el cuerpo de la solicitud.
     * @return La respuesta del servidor con el código de estado y el cuerpo.
     */
    public static String doPut(String url, String json) {
        HttpRequest solicitud = HttpRequest.newBuilder()
            .PUT(HttpRequest.BodyPublishers.ofString(json))
            .uri(URI.create(url))
            .header("Content-type", "application/json")
            .build();
        HttpResponse<String> respuesta = null;
        try {
            respuesta = HTTP_CLIENT.send(solicitud, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return respuesta.statusCode() + "\n" + respuesta.body();
    }

    /**
     * Realiza una solicitud HTTP DELETE a una URL proporcionada.
     * 
     * @param url La URL a la que se realiza la solicitud.
     * @return La respuesta del servidor con el código de estado y el cuerpo.
     */
    public static String doDelete(String url) {
        HttpRequest solicitud = HttpRequest.newBuilder().DELETE()
            .uri(URI.create(url))
            .header("Content-type", "application/json")
            .build();
        HttpResponse<String> respuesta = null;
        try {
            respuesta = HTTP_CLIENT.send(solicitud, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return respuesta.statusCode() + "\n" + respuesta.body();
    }

   

  

    /**
     * Realiza una solicitud HTTP POST a una URL proporcionada con los datos JSON enviados en el cuerpo.
     * 
     * @param url La URL a la que se realiza la solicitud.
     * @param json El contenido JSON que se enviará en el cuerpo de la solicitud.
     * @return La respuesta del servidor con el código de estado y el cuerpo.
     */
    public static String doPost(String url, String json) {
        HttpRequest solicitud = HttpRequest.newBuilder()
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .uri(URI.create(url))
            .header("Content-type", "application/json")
            .build();
        HttpResponse<String> respuesta = null;
        try {
            respuesta = HTTP_CLIENT.send(solicitud, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return respuesta.statusCode() + "\n" + respuesta.body();
    }

    /**
     * Realiza una solicitud HTTP GET para obtener una lista de películas desde el servidor.
     * 
     * @param url La URL a la que se realiza la solicitud.
     * @return Una lista de objetos {@link PeliculaDTO} obtenidos del servidor.
     */
   
}
