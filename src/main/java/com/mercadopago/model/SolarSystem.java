package com.mercadopago.model;

import com.mercadopago.config.SolarSystemPeriod;
import com.mercadopago.model.planetFactory.Planet;
import com.mercadopago.model.planetFactory.PlanetFactory;
import com.mercadopago.model.weatherForecast.WeatherForecast;
import com.mercadopago.model.weatherForecastStrategy.WeatherForecastManager;
import com.mercadopago.utils.TriangleMath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class SolarSystem {

    @Autowired
    private SolarSystemPeriod solarSystemPeriod;

    @Autowired
    private WeatherForecastManager weatherForecastManager;

    private List<Planet> planets;

    @Value("${solar.system.daysPeriod}")
    private int daysPeriod;

    @PostConstruct
    public void initSolarSystem() {

        // Obtengo la hora inicial para el job que comienza a correr desde que se levanta el sistema
        LocalDate initialDate = LocalDate.now();

        // Configuracion inicial
        this.solarSystemPeriod.setDate(initialDate);
        this.solarSystemPeriod.setInitialDate(initialDate);
        this.solarSystemPeriod.setPeriodDays(this.daysPeriod);

        // Mantengo los planetas en una lista
        this.planets = getAllPlanets();

    }

    /**
     * Lista de todos los planetas del sistema solar
     *
     * @return -
     */
    private List<Planet> getAllPlanets() {

        List<Planet> planetList = new ArrayList<>();
        planetList.add(PlanetFactory.getBetasoides());
        planetList.add(PlanetFactory.getVulcanos());
        planetList.add(PlanetFactory.getFerengis());

        return planetList;

    }

    /**
     * Ejecuta un dia de pronostico
     *
     * @return -
     */
    public WeatherForecast runOneDayWeatherForecast() {

        // Obtengo el pronostico del dia
        WeatherForecast todayWeatherForecast = this.weatherForecastManager.getWeatherForcast(this.planets);

        // Guardo el estado en el que se encontraba el sistema solar al obtener dicho pronostico
        SolarSystemStatus solarSystemStatus = new SolarSystemStatus();
        this.planets.forEach(planet -> planet.setSolarSystemStatusPosition(solarSystemStatus));
        todayWeatherForecast.setSolarSystemStatus(solarSystemStatus);

        // Calculo el perimetro al momento de obtener el pronostico
        double perimeter = TriangleMath.trianglePerimeter(this.planets.get(0).getXYPosition(), this.planets.get(1).getXYPosition(), this.planets.get(2).getXYPosition());
        solarSystemStatus.setPerimeter(perimeter);

        // Muevo al dia siguiente y traslado a los planetas un dia
        this.solarSystemPeriod.nextDay();
        this.planets.forEach(Planet::rotate);

        // Retorno el pronostico
        return todayWeatherForecast;

    }

}
