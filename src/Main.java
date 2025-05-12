import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Obtendo taxas de câmbio em tempo real");
        String json;
        try {
            json = MoedaService.getTaxasDeCambio("USD");
        } catch (Exception e) {
            System.out.println("Erro ao buscar dados da API: " + e.getMessage());
            return;
        }

        Map<String, Double> taxas = ConversorMoedas.filtrarMoedas(json);

        int opcao = -1;
        String[] codigos = taxas.keySet().toArray(new String[0]);

        while (opcao != 0) {
            System.out.println("\n=== CONVERSOR DE MOEDAS ===");
            System.out.println("Escolha a moeda de destino:");
            for (int i = 0; i < codigos.length; i++) {
                System.out.println((i + 1) + " - " + codigos[i]);
            }
            System.out.println("0 - Sair");

            System.out.print("Opção: ");
            opcao = scanner.nextInt();

            // Se a opção for válida, realiza a conversão
            if (opcao > 0 && opcao <= codigos.length) {
                String moedaDestino = codigos[opcao - 1];
                double taxa = taxas.get(moedaDestino);

                // Aqui solicita o valor em USD para conversão
                System.out.print("Informe o valor em USD que deseja converter: ");
                double valorUSD = scanner.nextDouble();

                double convertido = ConversorMoedas.converter(valorUSD, taxa);
                System.out.printf("%.2f USD = %.2f %s\n", valorUSD, convertido, moedaDestino);
            } else if (opcao != 0) {
                // Opção inválida
                System.out.println("Opção inválida. Tente novamente.");
            }
        }

        System.out.println("Programa encerrado.");
    }
}