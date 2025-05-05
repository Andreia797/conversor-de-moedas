import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConversorMoedas {

    private static final String API_KEY = "efeb6b3e4df207f4a5c5f71f";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/%s/latest/%s";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            exibirMenuCriativo();
            System.out.print("🌍 Escolha a sua aventura cambial (digite o número): ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    converterMoeda("BRL", "USD", scanner);
                    break;
                case 2:
                    converterMoeda("USD", "BRL", scanner);
                    break;
                case 3:
                    converterMoeda("EUR", "BRL", scanner);
                    break;
                case 4:
                    converterMoeda("BRL", "EUR", scanner);
                    break;
                case 5:
                    converterMoeda("GBP", "BRL", scanner);
                    break;
                case 6:
                    converterMoeda("BRL", "GBP", scanner);
                    break;
                case 7:
                    converterMoeda("BRL", "CVE", scanner);
                    break;
                case 8:
                    converterMoeda("CVE", "BRL", scanner);
                    break;
                case 0:
                    System.out.println("\n🚪 Saindo da sua central de câmbio. Até a próxima jornada!");
                    break;
                default:
                    System.out.println("\n🤔 Opção inválida. A Terra das Moedas desconhece este caminho. Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenuCriativo() {
        System.out.println("\n✨ Bem-vindo ao Portal de Câmbio Global! ✨");
        System.out.println("------------------------------------------");
        System.out.println("  Moedas ao seu alcance:");
        System.out.println("  1. 🇧🇷 Real Brasileiro (BRL) <-> 🇺🇸 Dólar Americano (USD)");
        System.out.println("  2. 🇺🇸 Dólar Americano (USD) <-> 🇧🇷 Real Brasileiro (BRL)");
        System.out.println("  3. 🇪🇺 Euro (EUR) <-> 🇧🇷 Real Brasileiro (BRL)");
        System.out.println("  4. 🇧🇷 Real Brasileiro (BRL) <-> 🇪🇺 Euro (EUR)");
        System.out.println("  5. 🇬🇧 Libra Esterlina (GBP) <-> 🇧🇷 Real Brasileiro (BRL)");
        System.out.println("  6. 🇧🇷 Real Brasileiro (BRL) <-> 🇬🇧 Libra Esterlina (GBP)");
        System.out.println("  7. 🇧🇷 Real Brasileiro (BRL) <-> 🇨🇻 Escudo Cabo-verdiano (CVE)");
        System.out.println("  8. 🇨🇻 Escudo Cabo-verdiano (CVE) <-> 🇧🇷 Real Brasileiro (BRL)");
        System.out.println("------------------------------------------");
        System.out.println("  0. 🚪 Sair do Portal");
        System.out.println("------------------------------------------");
    }

    private static void converterMoeda(String moedaOrigem, String moedaDestino, Scanner scanner) {
        System.out.print("💰 Digite o valor em " + obterNomeMoeda(moedaOrigem) + " (" + moedaOrigem + "): ");
        double valorOrigem = scanner.nextDouble();

        try {
            double taxaCambio = buscarTaxaDeCambio(moedaOrigem, moedaDestino);
            if (taxaCambio != -1) {
                double valorConvertido = valorOrigem * taxaCambio;
                System.out.printf("📊 %.2f %s (%s) valem %.2f %s (%s)\n",
                        valorOrigem, obterNomeMoeda(moedaOrigem), moedaOrigem,
                        valorConvertido, obterNomeMoeda(moedaDestino), moedaDestino);
            } else {
                System.out.println("⚠️ Falha ao obter a taxa de câmbio neste momento.");
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("🚨 Erro na busca da taxa de câmbio: " + e.getMessage());
        }
    }

    private static String obterNomeMoeda(String codigoMoeda) {
        return switch (codigoMoeda.toUpperCase()) {
            case "BRL" -> "Real Brasileiro";
            case "USD" -> "Dólar Americano";
            case "EUR" -> "Euro";
            case "GBP" -> "Libra Esterlina";
            case "CVE" -> "Escudo Cabo-verdiano";
            default -> codigoMoeda.toUpperCase();
        };
    }

    private static double buscarTaxaDeCambio(String moedaBase, String moedaDestino) throws IOException, InterruptedException {
        String url = String.format(API_URL, API_KEY, moedaBase);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);

        if (jsonResponse.get("result").getAsString().equals("success")) {
            JsonObject rates = jsonResponse.getAsJsonObject("conversion_rates");
            if (rates.has(moedaDestino)) {
                return rates.get(moedaDestino).getAsDouble();
            } else {
                System.err.println("Moeda de destino desconhecida nos confins do nosso portal.");
                return -1;
            }
        } else {
            System.err.println("Erro nos servidores de câmbio: " + jsonResponse.get("error"));
            return -1;
        }
    }
}