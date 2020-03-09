package com.mercadopago.model.weatherForecastStrategy;

import com.mercadopago.config.SolarSystemPeriod;
import com.mercadopago.model.planetFactory.Planet;
import com.mercadopago.model.weatherForecast.WeatherForecast;
import com.mercadopago.model.weatherForecast.WeatherForecastType;
import com.mercadopago.utils.SolarSystemMaths;
import javafx.geometry.Point2D;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OptimalWeatherStrategy implements WeatherForecastStrategy {

    @Autowired
    private SolarSystemPeriod solarSystemPeriod;

    @Override
    public boolean fitsPrediction(List<Planet> planets) {

        List<Point2D> listOfPoints = new ArrayList<>();

        for (Planet planet : planets)
            listOfPoints.add(planet.getXYPosition());

        return SolarSystemMaths.isLine(listOfPoints);

    }

    @Override
    public WeatherForecast getWeatherForecast(List<Planet> planets) {
        this.solarSystemPeriod.addOptimalDay();
        return new WeatherForecast(this.solarSystemPeriod.getDate(), WeatherForecastType.OPTIMAL);
    }

}
