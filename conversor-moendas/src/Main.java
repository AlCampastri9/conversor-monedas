import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ApiClient apiClient = new ApiClient();

        System.out.print("Ingresa la moneda base (por ejemplo USD): ");
        String monedaBase = scanner.nextLine().toUpperCase();

        RespuestaMoneda datos = apiClient.obtenerDatosComoObjeto(monedaBase);

        if (datos == null) {
            System.out.println("No se pudo obtener la información de la API.");
            return;
        }

        Map<String, Double> tasas = datos.getConversion_rates();

        int opcion;
        do {
            System.out.println("\n=== Conversor de Monedas ===");
            System.out.println("1. Convertir moneda");
            System.out.println("2. Ver todas las tasas disponibles");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingresa la moneda a convertir (por ejemplo EUR): ");
                    String monedaDestino = scanner.nextLine().toUpperCase();

                    if (!tasas.containsKey(monedaDestino)) {
                        System.out.println("Moneda no válida o no disponible.");
                        break;
                    }

                    System.out.print("Ingresa el monto a convertir: ");
                    double monto = scanner.nextDouble();
                    scanner.nextLine(); // limpiar buffer

                    double tasa = tasas.get(monedaDestino);
                    double resultado = monto * tasa;

                    System.out.println(monto + " " + monedaBase + " = " + resultado + " " + monedaDestino);
                    break;

                case 2:
                    System.out.println("\nTasas de cambio disponibles para " + monedaBase + ":");
                    tasas.forEach((moneda, valor) -> System.out.println(moneda + ": " + valor));
                    break;

                case 3:
                    System.out.println("Gracias por usar el conversor de monedas.");
                    break;

                default:
                    System.out.println("Opción no válida. Intenta nuevamente.");
            }

        } while (opcion != 3);

        scanner.close();
    }
}