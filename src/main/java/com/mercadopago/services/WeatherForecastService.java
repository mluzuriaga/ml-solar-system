package com.mercadopago.services;

import com.mercadopago.model.dto.WeatherForecastDTO;

public interface WeatherForecastService {

    WeatherForecastDTO getWeatherForecastDay(long days);

}
