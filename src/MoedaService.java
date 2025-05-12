import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MoedaService {

    private static final String API_URL = "https://v6.exchangerate-api.com/v6/07f97a714d175cd54ab37b9b/latest/USD";

    public static String getTaxasDeCambio(String moedaBase) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Falha ao obter dados da API");
        }

        return response.body();
    }
}