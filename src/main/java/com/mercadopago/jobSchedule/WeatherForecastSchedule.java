package com.mercadopago.jobSchedule;

import com.mercadopago.config.SolarSystemPeriod;
import com.mercadopago.model.SolarSystem;
import com.mercadopago.model.weatherForecast.WeatherForecast;
import com.mercadopago.model.weatherForecast.WeatherForecastReport;
import com.mercadopago.repositories.WeatherForecastReportRepository;
import com.mercadopago.repositories.WeatherForecastRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private WeatherForecastReportRepository weatherForecastReportRepository;

    @Autowired
    private SolarSystemPeriod solarSystemPeriod;

    //    (cron = "${solar.system.cronJob}")
    @Scheduled(fixedRate = 1)
    public void executeDay() {

        if (this.solarSystemPeriod.isEnabled()) {

            long daysPassed = DAYS.between(this.solarSystemPeriod.getInitialDate(), this.solarSystemPeriod.getDate());
            if (daysPassed < this.solarSystemPeriod.getPeriodDays()) {

                LOGGER.info("Ejecutando el Job de Pronostico del dia: " + this.solarSystemPeriod.getDate());

                WeatherForecast weatherForecastStrategy = this.solarSystem.runOneDayWeatherForecast();
                this.weatherForecastRepository.save(weatherForecastStrategy);

                LOGGER.info("------------------------------------------------------------");

            } else {

                WeatherForecastReport weatherForecastReport = new WeatherForecastReport(this.solarSystemPeriod.getInitialDate(), this.solarSystemPeriod.getDate(), this.solarSystemPeriod.getDroughtDays(), this.solarSystemPeriod.getRainyDays(), this.solarSystemPeriod.getIntenseRainyDays(), this.solarSystemPeriod.getOptimalDays());
                this.weatherForecastReportRepository.save(weatherForecastReport);

                this.solarSystemPeriod.cleanCounters();
                this.solarSystemPeriod.setEnabled(false);

            }

        }

    }

}
