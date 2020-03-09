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

/**
 * Job que calcula inicialmente los 10 años solicitados. Luego queda en stand by hasta ser solicitado un nuevo periodo
 */
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

    @Scheduled(cron = "${solar.system.cronJob}")
    public void executeDay() {

        if (this.solarSystemPeriod.isEnabled()) {

            // Obtengo los dias ya calculados
            long daysPassed = DAYS.between(this.solarSystemPeriod.getInitialDate(), this.solarSystemPeriod.getDate());
            if (daysPassed < this.solarSystemPeriod.getPeriodDays()) {

                // Si aun quedan dias por pronosticar
                LOGGER.info("* Ejecutando el Job de Pronostico del dia: " + this.solarSystemPeriod.getDate());

                // Solicito correr un dia de pronostico de clima
                WeatherForecast weatherForecastStrategy = this.solarSystem.runOneDayWeatherForecast();
                // Persisto el pronostico del dia
                this.weatherForecastRepository.save(weatherForecastStrategy);

            } else {

                // Genero el reporte del periodo
                WeatherForecastReport weatherForecastReport = new WeatherForecastReport(this.solarSystemPeriod.getInitialDate(), this.solarSystemPeriod.getDate(), this.solarSystemPeriod.getDroughtDays(), this.solarSystemPeriod.getRainyDays(), this.solarSystemPeriod.getIntenseRainyDays(), this.solarSystemPeriod.getOptimalDays());
                // Persisto el reporte
                this.weatherForecastReportRepository.save(weatherForecastReport);

                // Logueo los resultado por consola
                this.logReportResults();

                // Limpio los contadores de periodos del intervalo
                this.solarSystemPeriod.cleanCounters();
                // Deshabilito la ejecucion del cron
                this.solarSystemPeriod.setEnabled(false);

            }

        }

    }

    private void logReportResults() {

        LOGGER.info("------------------------------------------------------------------------------");

        LOGGER.info("* Generando reporte del periodo: " + this.solarSystemPeriod.getInitialDate() + " - " + this.solarSystemPeriod.getDate());
        LOGGER.info("  - Cantidad de dias de sequia: " + this.solarSystemPeriod.getDroughtDays());
        LOGGER.info("  - Cantidad de dias de lluvia: " + this.solarSystemPeriod.getRainyDays());
        LOGGER.info("  - Cantidad de dias de lluvia intensa: " + this.solarSystemPeriod.getIntenseRainyDays());
        LOGGER.info("  - Cantidad de dias de optimos de presion y temperatura : " + this.solarSystemPeriod.getOptimalDays());

        LOGGER.info("------------------------------------------------------------------------------");


    }

}
