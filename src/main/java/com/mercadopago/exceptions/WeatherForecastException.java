package com.mercadopago.exceptions;

/**
 * Excepcion arrojada si se solicita el clima de una fecha no calculada
 */
public class WeatherForecastException extends Exception {

    public WeatherForecastException(String message) {

        super(message);

    }

}
