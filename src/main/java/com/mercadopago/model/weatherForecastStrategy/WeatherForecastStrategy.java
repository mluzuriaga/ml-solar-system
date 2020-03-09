package com.mercadopago.model.weatherForecastStrategy;

import com.mercadopago.model.planetFactory.Planet;
import com.mercadopago.model.weatherForecast.WeatherForecast;

import java.util.List;

public interface WeatherForecastStrategy {

    boolean fitsPrediction(List<Planet> planets);

    WeatherForecast getWeatherForecast(List<Planet> planets);

}
