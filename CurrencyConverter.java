import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Alexey Dolzhenko
 * 11/8/2021
 */

public class CurrencyConverter extends AccessKey {

    public static void main(String[] args) throws IOException {

        boolean working = true;

        while (working) {
            HashMap<Integer, String> currencyCodes = new HashMap<Integer, String>();

            currencyCodes.put(1, "RUB");
            currencyCodes.put(2, "USD");
            currencyCodes.put(3, "CHF");
            currencyCodes.put(4, "JPY");
            currencyCodes.put(5, "GBP");

            int to;
            String base = "EUR";
            String toCode;
            double amount;

            Scanner scan = new Scanner(System.in);

            System.out.println();
            System.out.println("This is a currency converter!");

            System.out.println("Choose a currency you want to convert to:");
            System.out.println("1. RUB\n2. USD\n3. CHF\n4. JPY\n5. GBP");
            to = scan.nextInt();
            while (to < 1 || to > 5) {
                System.out.println("Please, select an available currency option!");
                System.out.println("1. RUB\n2. USD\n3. CHF\n4. JPY\n5. GBP");
                to = scan.nextInt();
            }
            toCode = currencyCodes.get(to);

            System.out.println("What amount do you want to convert?");
            amount = scan.nextDouble();

            sendHttpGETRequest(base, toCode, amount);

            System.out.println("Would you like to convert again?");
            System.out.println("1. Yes \n2. No ");
            if (scan.nextInt() == 2) {
                working = false;
            }
        }
        System.out.println("Thanks for using this currency converter!");
    }

    public static void sendHttpGETRequest(String base, String toCode, double amount) throws IOException {

        DecimalFormat form = new DecimalFormat("00.00");

        String GET_URL = "http://data.fixer.io/api/latest?" + getAccessKey() + "base=" + base + "&symbols=" + toCode;
        URL url = new URL(GET_URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject jsonObject = new JSONObject(response.toString());
            double exchangeRate = jsonObject.getJSONObject("rates").getDouble(toCode);
            System.out.println();
            System.out.println(form.format(amount) + " " + base + " = " + form.format(amount * exchangeRate) + " " + toCode);
            System.out.println();
        } else {
            System.out.println("GET request failed!");
        }
    }
}

