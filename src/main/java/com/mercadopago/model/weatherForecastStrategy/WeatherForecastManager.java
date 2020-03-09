package com.mercadopago.model.weatherForecastStrategy;

import com.mercadopago.config.SolarSystemPeriod;
import com.mercadopago.model.planetFactory.Planet;
import com.mercadopago.model.weatherForecast.WeatherForecast;
import com.mercadopago.model.weatherForecast.WeatherForecastType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WeatherForecastManager {

    @Autowired
    private SolarSystemPeriod solarSystemPeriod;

    @Autowired
    private List<WeatherForecastStrategy> strategyList;

    /**
     * Obtiene el periodo que se ajusta segun la posicion de los planetas
     *
     * @param planets -
     * @return -
     */
    public WeatherForecast getWeatherForcast(List<Planet> planets) {

        // Recorro las estrategias y tomo solo la que se ajusta segun la posicion de los planetas.
        // Luego a la seleccionada se le pide el pronostico.
        // Si ninguna se ajusta se retorna el pronostico con NOTHING
        return this.strategyList.stream()
                .filter(strategy -> strategy.fitsPrediction(planets))
                .findFirst()
                .map(strategy -> strategy.getWeatherForecast(planets))
                .orElse(new WeatherForecast(this.solarSystemPeriod.getDate(), WeatherForecastType.NOTHING));

    }

}
