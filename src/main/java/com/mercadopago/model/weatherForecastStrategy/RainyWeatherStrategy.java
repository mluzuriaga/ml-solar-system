package com.mercadopago.model.weatherForecastStrategy;

import com.mercadopago.config.SolarSystemPeriod;
import com.mercadopago.model.planetFactory.Planet;
import com.mercadopago.model.weatherForecast.WeatherForecast;
import com.mercadopago.model.weatherForecast.WeatherForecastType;
import com.mercadopago.utils.SolarSystemMaths;
import com.mercadopago.utils.TriangleMath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

/**
 * LLUVIA Y LLUVIA INTENSA
 */
@Component
public class RainyWeatherStrategy implements WeatherForecastStrategy {

    @Autowired
    private SolarSystemPeriod solarSystemPeriod;

    /**
     * Calcula si en base a la posicion de los planetas se ajusta a lluvia
     *
     * @param planets - planetas
     * @return - true si se ajusta
     */
    @Override
    public boolean fitsPrediction(List<Planet> planets) {

        List<Point2D> listOfPoints = new ArrayList<>();

        // Obtengo las posiciones X/Y de cada planeta en base a sus posiciones angulares
        for (Planet planet : planets)
            listOfPoints.add(planet.getXYPosition());

        // Posicion del sol
        Point2D sunPoint = new Point2D.Double(0, 0);

        // Verifica si el sol se encuentra dentro del triangulo formado por los planetas
        return SolarSystemMaths.isInside(listOfPoints, sunPoint);

    }

    /**
     * Obtiene el pronostico de tiempo lluvia
     *
     * @param planets -
     * @return -
     */
    @Override
    public WeatherForecast getWeatherForecast(List<Planet> planets) {

        // Calculo el perimetro del triangulo formado
        double perimeter = TriangleMath.trianglePerimeter(planets.get(0).getXYPosition(), planets.get(1).getXYPosition(), planets.get(2).getXYPosition());

        if (abs(perimeter - TriangleMath.MAXIMUM_PERIMETER) < 0.01) {
            // Si el perimetro calculado es igual al perimetro maximo (calculado), con un margen de error por los calculos hechos entonces es periodo de lluvia maximo
            this.solarSystemPeriod.addIntenseRainyDay();
            return new WeatherForecast(this.solarSystemPeriod.getDate(), WeatherForecastType.RAINY_INTENSE);
        } else {
            // Si el perimetro calculado es menor al perimetro maximo entonces es periodo de lluvia
            this.solarSystemPeriod.addRainyDay();
            return new WeatherForecast(this.solarSystemPeriod.getDate(), WeatherForecastType.RAINY);
        }

    }

}
