package com.mercadopago.model.weatherForecastStrategy;

import com.mercadopago.model.planetFactory.Planet;
import com.mercadopago.model.planetFactory.PlanetFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

@Test
public class RainyWeatherStrategyTest {

    private RainyWeatherStrategy rainyWeatherStrategy;

    @BeforeClass
    public void init() {
        this.rainyWeatherStrategy = new RainyWeatherStrategy();
    }

    public void rainyTest() {

        List<Planet> planetList = new ArrayList<>();

        Planet betasoides = PlanetFactory.getBetasoides();
        betasoides.setAngularPosition(78);
        planetList.add(betasoides);

        Planet ferengis = PlanetFactory.getFerengis();
        ferengis.setAngularPosition(26);
        planetList.add(ferengis);

        Planet vulcanos = PlanetFactory.getVulcanos();
        vulcanos.setAngularPosition(-130);
        planetList.add(vulcanos);

        boolean rainy = this.rainyWeatherStrategy.fitsPrediction(planetList);
        assertTrue(rainy);

    }

}
