package com.mercadopago.model.weatherForecastStrategy;

import com.mercadopago.model.planetFactory.Planet;
import com.mercadopago.model.weatherForecast.WeatherForecast;
import com.mercadopago.model.weatherForecast.WeatherForecastType;
import com.mercadopago.utils.SolarSystemDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WeatherForecastManager {

    @Autowired
    private List<WeatherForecastStrategy> strategyList;

    public WeatherForecast getWeatherForcast(List<Planet> planets) {

        return this.strategyList.stream()
                .filter(strategy -> strategy.fitsPrediction(planets))
                .findFirst()
                .map(strategy -> strategy.getWeatherForecast(planets))
                .orElse(new WeatherForecast(SolarSystemDate.date, WeatherForecastType.NOTHING));

    }

}
