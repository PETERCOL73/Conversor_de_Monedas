import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// Es responsable de realizar las consultas a una API para obtener las tasas de cambio entre diferentes monedas.
public class ConsultaConversion {
    public String buscaConversion(String monedaBase, String monedaObjetivo, double cantidad){
        try{
            URI direccion = URI.create("https://v6.exchangerate-api.com/v6/8f3d8ac0eff2f592e501ceca/pair/" + monedaBase + "/" +
                    monedaObjetivo+ "/" + cantidad);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(direccion)
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            var json = response.body();
            JsonElement jsonElement = JsonParser.parseString(json);
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            if (jsonObject.has("conversion_rate")) {
                double tasaConversion = jsonObject.get("conversion_rate").getAsDouble();
                double resultadoConversion = cantidad * tasaConversion;
                return String.valueOf(resultadoConversion);
            } else {
                System.out.println("El resultado de la conversión no está disponible. Verifica la respuesta de la API.");
                return "Error: conversión no disponible";
            }

        }   catch (IOException | InterruptedException e){
            System.out.println("Ocurrió un error: " + e.getMessage());
            throw new RuntimeException("Error" + e.getMessage());
        }
    }
}
