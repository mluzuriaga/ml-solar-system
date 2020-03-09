package com.mercadopago.model.dto;

import com.mercadopago.model.weatherForecast.WeatherForecastType;

/**
 * DTO para retornar el clima solicitado con la estructura de JSON pedida.
 */
public class WeatherForecastDTO {

    private long dia;
    private WeatherForecastType clima;

    public WeatherForecastDTO(long dia, WeatherForecastType clima) {

        this.dia = dia;
        this.clima = clima;

    }

    public long getDia() {
        return dia;
    }

    public void setDia(long dia) {
        this.dia = dia;
    }

    public WeatherForecastType getClima() {
        return clima;
    }

    public void setClima(WeatherForecastType clima) {
        this.clima = clima;
    }

}
