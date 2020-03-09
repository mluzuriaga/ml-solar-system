package com.mercadopago.model.weatherForecastStrategy;

import com.mercadopago.model.planetFactory.Planet;
import com.mercadopago.model.weatherForecast.WeatherForecast;
import com.mercadopago.model.weatherForecast.WeatherForecastType;
import com.mercadopago.utils.SolarSystemDate;
import com.mercadopago.utils.SolarSystemMaths;
import javafx.geometry.Point2D;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DroughtWeatherStrategy implements WeatherForecastStrategy {

    private long counter;

    @Override
    public boolean fitsPrediction(List<Planet> planets) {

        List<Point2D> listOfPoints = new ArrayList<>();

        for (Planet planet : planets)
            listOfPoints.add(planet.getXYPosition());

        Point2D sunPoint = new Point2D(0, 0);
        listOfPoints.add(sunPoint);

        if (SolarSystemMaths.isLine(listOfPoints)) {
            counter++;
            return true;
        } else
            return false;

    }

    @Override
    public WeatherForecast getWeatherForecast(List<Planet> planets) {
        return new WeatherForecast(SolarSystemDate.date, WeatherForecastType.DROUGHT);
    }

}
