package com.mercadopago.model.weatherForecastStrategy;

import com.mercadopago.model.planetFactory.Planet;
import com.mercadopago.model.planetFactory.PlanetFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

@Test
public class OptimalWeatherStrategyTest {

    private OptimalWeatherStrategy optimalWeatherStrategy;

    @BeforeClass
    public void init() {
        this.optimalWeatherStrategy = new OptimalWeatherStrategy();
    }

    public void optimalTest() {

        List<Planet> planetList = new ArrayList<>();

        Planet betasoides = PlanetFactory.getBetasoides();
        betasoides.setAngularPosition(171);
        planetList.add(betasoides);

        Planet ferengis = PlanetFactory.getFerengis();
        ferengis.setAngularPosition(297);
        planetList.add(ferengis);

        Planet vulcanos = PlanetFactory.getVulcanos();
        vulcanos.setAngularPosition(-45);
        planetList.add(vulcanos);

        boolean optimal = this.optimalWeatherStrategy.fitsPrediction(planetList);
        assertTrue(optimal);

    }

}
