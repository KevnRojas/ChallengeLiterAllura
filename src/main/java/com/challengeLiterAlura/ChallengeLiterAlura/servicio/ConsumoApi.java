package com.challengeLiterAlura.ChallengeLiterAlura.servicio;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ConsumoApi {
    public String obtenerDatos(String url){
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(20))
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(20))
                .build();
        HttpResponse<String> response;

        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();


        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Ocurrio un error " + e);
        }

        String json = response.body();

        if (json == null || json.isEmpty()) {
            throw new RuntimeException("La respuesta JSON está vacía");
        }

        return json;
    }
}
