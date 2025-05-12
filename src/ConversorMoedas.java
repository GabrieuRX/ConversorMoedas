import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashMap;
import java.util.Map;

public class ConversorMoedas {

    private static final String[] MOEDAS_FILTRADAS = {"ARS", "BOB", "BRL", "CLP", "COP", "USD"};

    public static Map<String, Double> filtrarMoedas(String json) {
        Map<String, Double> resultados = new HashMap<>();

        JsonObject jsonObj = JsonParser.parseString(json).getAsJsonObject();
        JsonObject taxas = jsonObj.getAsJsonObject("conversion_rates");

        // Filtrando as moedas
        for (String codigo : MOEDAS_FILTRADAS) {
            if (taxas.has(codigo)) {
                double valor = taxas.get(codigo).getAsDouble();
                resultados.put(codigo, valor);
            }
        }

        return resultados;
    }

    public static double converter(double valorUSD, double taxaDestino) {
        return valorUSD * taxaDestino;
    }
}