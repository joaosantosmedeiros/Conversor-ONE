package org.example;

import com.google.gson.Gson;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int opc;
        HttpClient httpClient = HttpClient.newHttpClient();
        Gson gson = new Gson();
        String uri = "https://v6.exchangerate-api.com/v6/d1660b2f4b0c21456cd54238/latest/USD";

        do {
            System.out.println("***************************************");
            System.out.println("Seja bem vindo ao conversor de moedas\n");
            System.out.println("1) Dolar -> Peso Argentino");
            System.out.println("2) Peso Argentino -> Dolar");
            System.out.println("3) Dolar -> Real Brasileiro");
            System.out.println("4) Real Brasileiro -> Dolar");
            System.out.println("5) Dolar -> Peso Colombiano");
            System.out.println("6) Peso Colombiano -> Dolar");
            System.out.println("7) Sair");
            System.out.println("Escolha uma opção válida: ");
            System.out.println("***************************************\n");

            double valor = 0d;
            opc = scanner.nextInt();
            if(opc >= 1 && opc < 7){
                System.out.print("Digite o valor que deseja converter: ");
                valor = scanner.nextDouble();

                String primeiraMoeda = opc == 2 ? "ARS" : opc == 4 ? "BRL" : opc == 6 ? "COB" : "USD";
                String segundaMoeda = opc == 1 ? "ARS" : opc == 3 ? "BRL" : opc == 5 ? "COB" : "USD";

                if(opc == 2){
                    uri = "https://v6.exchangerate-api.com/v6/d1660b2f4b0c21456cd54238/latest/ARS";
                }else if(opc == 4){
                    uri = "https://v6.exchangerate-api.com/v6/d1660b2f4b0c21456cd54238/latest/BRL";
                }else if(opc == 6){
                    uri = "https://v6.exchangerate-api.com/v6/d1660b2f4b0c21456cd54238/latest/COP";
                }

                HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(uri)).build();
                HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                ConversionRate rate = gson.fromJson(httpResponse.body(), ApiResponse.class).getRates();
                double valorFinal = opc == 1 ? rate.getARS() : opc == 3 ? rate.getBRL() : opc == 5 ? rate.getCOP() : rate.getUSD();
                System.out.printf("Valor %.2f [%s] corresponde ao valor final de %.2f [%s]\n\n", valor, primeiraMoeda, valorFinal * valor, segundaMoeda);
            }else if(opc == 7){
                System.out.println("Finalizando....");
            }else{
                System.out.println("Opção inválida!8");
            }

        }while(opc != 7);
    }
}