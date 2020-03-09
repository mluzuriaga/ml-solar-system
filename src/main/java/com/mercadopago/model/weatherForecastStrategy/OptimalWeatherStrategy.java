package com.mercadopago.model.weatherForecastStrategy;

import com.mercadopago.config.SolarSystemPeriod;
import com.mercadopago.model.planetFactory.Planet;
import com.mercadopago.model.weatherForecast.WeatherForecast;
import com.mercadopago.model.weatherForecast.WeatherForecastType;
import com.mercadopago.utils.SolarSystemMaths;
import java.awt.geom.Point2D;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * OPTIMO DE PRESION Y TEMPERATURA
 */
@Component
public class OptimalWeatherStrategy implements WeatherForecastStrategy {

    @Autowired
    private SolarSystemPeriod solarSystemPeriod;

    /**
     * Calcula si en base a la posicion de los planetas se ajusta a tiempo optimo de presion y temperatura
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

        // Verifica si la posicion de los planetas forma una lina
        return SolarSystemMaths.isLine(listOfPoints);

    }

    /**
     * Obtiene el pronostico de tiempo optimo de presion y temperatura
     *
     * @param planets -
     * @return -
     */
    @Override
    public WeatherForecast getWeatherForecast(List<Planet> planets) {

        // Sumo al contador de tiempo optimo
        this.solarSystemPeriod.addOptimalDay();
        // Genero y retorno un pronostico de tiempo optimo
        return new WeatherForecast(this.solarSystemPeriod.getDate(), WeatherForecastType.OPTIMAL);

    }

}
