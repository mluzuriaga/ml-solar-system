package com.mercadopago.model.weatherForecastStrategy;

import com.mercadopago.model.planetFactory.Planet;
import com.mercadopago.model.weatherForecast.WeatherForecast;
import com.mercadopago.model.weatherForecast.WeatherForecastType;
import com.mercadopago.utils.SolarSystemDate;
import com.mercadopago.utils.SolarSystemMaths;
import com.mercadopago.utils.TriangleMath;
import javafx.geometry.Point2D;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

@Component
public class RainyWeatherStrategy implements WeatherForecastStrategy {

    @Override
    public boolean fitsPrediction(List<Planet> planets) {

        List<Point2D> listOfPoints = new ArrayList<>();
        for (Planet planet : planets)
            listOfPoints.add(planet.getXYPosition());

        Point2D sunPoint = new Point2D(0, 0);

        return SolarSystemMaths.isInside(listOfPoints, sunPoint);

    }

    @Override
    public WeatherForecast getWeatherForecast(List<Planet> planets) {

        double perimeter = TriangleMath.trianglePerimeter(planets.get(0).getXYPosition(), planets.get(1).getXYPosition(), planets.get(2).getXYPosition());
        if (abs(perimeter - TriangleMath.MAXIMUM_PERIMETER) < 0.01)
            return new WeatherForecast(SolarSystemDate.date, WeatherForecastType.RAINY_INTENSE);
        else
            return new WeatherForecast(SolarSystemDate.date, WeatherForecastType.RAINY);

    }

}
