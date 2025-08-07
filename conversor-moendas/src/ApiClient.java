import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;

public class ApiClient {
    private static final String API_KEY = "eff67a0fa697c653e2be93a4";
    private static final String URL_BASE = "https://v6.exchangerate-api.com/v6/";

    public String obtenerDatos(String monedaBase) {
        String url = URL_BASE + API_KEY + "/latest/" + monedaBase;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body(); // el JSON en forma de String
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Este método transforma el JSON a objeto Java con Gson
    public RespuestaMoneda obtenerDatosComoObjeto(String monedaBase) {
        String json = obtenerDatos(monedaBase);

        if (json != null) {
            Gson gson = new Gson();
            return gson.fromJson(json, RespuestaMoneda.class);
        } else {
            return null;
        }
    }
}