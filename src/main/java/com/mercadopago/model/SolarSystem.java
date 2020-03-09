package com.mercadopago.model;

import com.mercadopago.config.SolarSystemPeriod;
import com.mercadopago.jobSchedule.WeatherForecastSchedule;
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

    @Autowired
    private WeatherForecastSchedule weatherForecastSchedule;

    private List<Planet> planets;

    @Value("${solar.system.daysPeriod}")
    private int daysPeriod;

    @PostConstruct
    public void initSolarSystem() {

        LocalDate initialDate = LocalDate.now();

        this.solarSystemPeriod.setDate(initialDate);
        this.solarSystemPeriod.setInitialDate(initialDate);
        this.solarSystemPeriod.setPeriodDays(this.daysPeriod);

        this.planets = getAllPlanets();

    }

    private List<Planet> getAllPlanets() {

        List<Planet> planetList = new ArrayList<>();
        planetList.add(PlanetFactory.getBetasoides());
        planetList.add(PlanetFactory.getVulcanos());
        planetList.add(PlanetFactory.getFerengis());

        return planetList;

    }

    public WeatherForecast runOneDayWeatherForecast() {

        WeatherForecast todayWeatherForecast = this.weatherForecastManager.getWeatherForcast(this.planets);
        SolarSystemStatus solarSystemStatus = new SolarSystemStatus();
        this.planets.forEach(planet -> planet.setSolarSystemStatusPosition(solarSystemStatus));
        todayWeatherForecast.setSolarSystemStatus(solarSystemStatus);

        double perimeter = TriangleMath.trianglePerimeter(this.planets.get(0).getXYPosition(), this.planets.get(1).getXYPosition(), this.planets.get(2).getXYPosition());
        solarSystemStatus.setPerimeter(perimeter);

        this.solarSystemPeriod.nextDay();
        this.planets.forEach(Planet::rotate);

        return todayWeatherForecast;

    }

}
