package com.mercadopago.model.weatherForecastStrategy;

import com.mercadopago.model.planetFactory.Planet;
import com.mercadopago.model.planetFactory.PlanetFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

@Test
public class DroughtWeatherStrategyTest {

    private DroughtWeatherStrategy droughtWeatherStrategy;

    @BeforeClass
    public void init() {
        this.droughtWeatherStrategy = new DroughtWeatherStrategy();
    }

    public void droughtTest() {

        List<Planet> planetList = new ArrayList<>();

        Planet betasoides = PlanetFactory.getBetasoides();
        betasoides.setAngularPosition(180);
        planetList.add(betasoides);

        Planet ferengis = PlanetFactory.getFerengis();
        ferengis.setAngularPosition(180);
        planetList.add(ferengis);

        Planet vulcanos = PlanetFactory.getVulcanos();
        vulcanos.setAngularPosition(-180);
        planetList.add(vulcanos);

        boolean drought = this.droughtWeatherStrategy.fitsPrediction(planetList);
        assertTrue(drought);

    }

}
