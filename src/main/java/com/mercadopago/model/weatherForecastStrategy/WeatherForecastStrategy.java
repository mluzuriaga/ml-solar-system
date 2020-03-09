package com.mercadopago.model.weatherForecastStrategy;

import com.mercadopago.model.planetFactory.Planet;
import com.mercadopago.model.weatherForecast.WeatherForecast;

import java.util.List;

public interface WeatherForecastStrategy {

    // Chequea si un pronostico se ajusta segun la posicion de los planetas
    boolean fitsPrediction(List<Planet> planets);

    // Obtiene el pronostico
    WeatherForecast getWeatherForecast(List<Planet> planets);

}
