package com.mercadopago.services;

import com.mercadopago.exceptions.WeatherForecastException;
import com.mercadopago.model.dto.WeatherForecastDTO;
import com.mercadopago.model.weatherForecast.WeatherForecastReport;

public interface WeatherForecastService {

    // Obtiene el pronostico del clima en el dia indicado
    WeatherForecastDTO getWeatherForecastDay(long days) throws WeatherForecastException;

    // Obtiene el reporte del ultimo intervalo calculado
    WeatherForecastReport getWeatherForecastReport();

    // Corre un nuevo intervalo de pronosticos
    boolean runAnotherPeriod(long days);

}
