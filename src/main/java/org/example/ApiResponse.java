package org.example;

class ConversionRate {
    private double USD;
    private double BRL;
    private double ARS;
    private double COP;

    public ConversionRate(){}

    public double getUSD() {
        return USD;
    }

    public double getBRL() {
        return BRL;
    }

    public double getARS() {
        return ARS;
    }

    public double getCOP() {
        return COP;
    }
}

public class ApiResponse {
    private String result;
    private ConversionRate conversion_rates;

    public ApiResponse(){}

    public String getResult() {
        return result;
    }

    public ConversionRate getRates() {
        return conversion_rates;
    }
}
