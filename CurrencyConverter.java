import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/6fb56ea06eff127264ce72ae/latest/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter base currency (e.g., USD): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter target currency (e.g., EUR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        double exchangeRate = fetchExchangeRate(baseCurrency, targetCurrency);
        if (exchangeRate == -1) {
            System.out.println("Error fetching exchange rate.");
        } else {
            double convertedAmount = amount * exchangeRate;
            System.out.printf("Converted amount: %.2f %s\n", convertedAmount, targetCurrency);
        }

        scanner.close();
    }

    private static double fetchExchangeRate(String baseCurrency, String targetCurrency) {
        try {
            String urlStr = API_URL + baseCurrency;
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                System.out.println("Error: Unable to fetch exchange rate.");
                return -1;
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String responseStr = response.toString();
            String searchStr = "\"" + targetCurrency + "\":";
            int targetIndex = responseStr.indexOf(searchStr);
            if (targetIndex == -1) {
                System.out.println("Error: Target currency not found in response.");
                return -1;
            }

            int start = targetIndex + searchStr.length();
            int end = responseStr.indexOf(",", start);
            if (end == -1) {
                end = responseStr.indexOf("}", start);
            }

            String rateStr = responseStr.substring(start, end);
            double exchangeRate = Double.parseDouble(rateStr.trim());
            return exchangeRate;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
