package com.mercadopago.jobSchedule;

import com.google.gson.Gson;
import com.mercadopago.model.SolarSystem;
import com.mercadopago.model.weatherForecast.WeatherForecast;
import com.mercadopago.repositories.WeatherForecastRepository;
import com.mercadopago.utils.SolarSystemDate;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.slf4j.LoggerFactory.getLogger;

@Component
public class WeatherForecastSchedule {

    private static final Logger LOGGER = getLogger(WeatherForecastSchedule.class);

    @Autowired
    private SolarSystem solarSystem;

    @Autowired
    private WeatherForecastRepository weatherForecastRepository;

    private boolean enabled = true;

    @Value("${solar.system.daysPeriod}")
    private int daysPeriod;

    private Gson gson = new Gson();

    //    (cron = "${solar.system.cronJob}")
    @Scheduled(fixedRate = 1)
    public void executeDay() {

        if (this.enabled) {

            long daysPassed = DAYS.between(SolarSystemDate.initialDate, SolarSystemDate.date);
            if (daysPassed < this.daysPeriod) {

                LOGGER.info("Ejecutando el Job de Pronostico del dia: " + SolarSystemDate.date);

                WeatherForecast weatherForecastStrategy = this.solarSystem.runOneDayWeatherForecast();
                this.weatherForecastRepository.save(weatherForecastStrategy);

                LOGGER.info("------------------------------------------------------------");

            } else {


            }

        }

    }

}
