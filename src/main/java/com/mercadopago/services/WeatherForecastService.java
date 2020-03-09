package com.mercadopago.services;

import com.mercadopago.exceptions.WeatherForecastException;
import com.mercadopago.model.dto.WeatherForecastDTO;
import com.mercadopago.model.weatherForecast.WeatherForecastReport;

public interface WeatherForecastService {

    WeatherForecastDTO getWeatherForecastDay(long days) throws WeatherForecastException;

    WeatherForecastReport getWeatherForecastReport();

    void runAnotherPeriod(long days);

}
