package com.mercadopago.services.impl;

import com.mercadopago.config.SolarSystemPeriod;
import com.mercadopago.exceptions.WeatherForecastException;
import com.mercadopago.model.dto.WeatherForecastDTO;
import com.mercadopago.model.weatherForecast.WeatherForecast;
import com.mercadopago.model.weatherForecast.WeatherForecastReport;
import com.mercadopago.repositories.WeatherForecastReportRepository;
import com.mercadopago.repositories.WeatherForecastRepository;
import com.mercadopago.services.WeatherForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class WeatherForecastServiceImpl implements WeatherForecastService {

    @Autowired
    private SolarSystemPeriod solarSystemPeriod;

    @Autowired
    private WeatherForecastRepository weatherForecastRepository;

    @Autowired
    private WeatherForecastReportRepository weatherForecastReportRepository;

    /**
     * Obtengo el pronostico del dia indicado
     *
     * @param days - dias desde el dia inicial del intervalo calculado
     * @return -
     * @throws WeatherForecastException -
     */
    @Override
    public WeatherForecastDTO getWeatherForecastDay(long days) throws WeatherForecastException {

        // Obtengo la fecha deseada sumando los dias a la fecha inicial de intervalo
        LocalDate date = this.solarSystemPeriod.getInitialDate().plusDays(days);

        // Si la fecha solicitada a un no se calculo se retorna una excepcion
        if (date.isAfter(this.solarSystemPeriod.getDate()))
            throw new WeatherForecastException("La fecha solicitada es posterior al periodo pronosticado. Para consultar sobre periodos anteriores contacte al administrador.");

        // Se busca por fecha usando el repositorio
        WeatherForecast weatherForecast = this.weatherForecastRepository.findAllByDate(date);
        return new WeatherForecastDTO(days, weatherForecast.getWeatherForecastType());

    }

    /**
     * Obtengo el reporte del ultimo intervalo calculado
     *
     * @return -
     */
    @Override
    public WeatherForecastReport getWeatherForecastReport() {

        // Obtengo usando el repositorio el ultimo reporte
        return this.weatherForecastReportRepository.findTopByOrderByIdDesc();

    }

    /**
     * Corre un nuevo intervalo de pronosticos
     *
     * @param days - cantidad de dias desde la ultima fecha que se quiere pronosticar
     * @return -
     */
    @Override
    public boolean runAnotherPeriod(long days) {

        if (!this.solarSystemPeriod.isEnabled()) {

            // Seteo la fecha inicial
            this.solarSystemPeriod.setInitialDate(this.solarSystemPeriod.getDate());
            // Seteo la cantidad de dias que se va a pronosticar
            this.solarSystemPeriod.setPeriodDays(days);
            // Habilito el cron
            this.solarSystemPeriod.setEnabled(true);

            return true;

        } else
            return false;

    }

    @Override
    public boolean stopJob() {

        if (this.solarSystemPeriod.isEnabled()) {

            // Deshabilito el cron
            this.solarSystemPeriod.setEnabled(false);

            // Genero el reporte del periodo
            WeatherForecastReport weatherForecastReport = new WeatherForecastReport(this.solarSystemPeriod.getInitialDate(), this.solarSystemPeriod.getDate(), this.solarSystemPeriod.getDroughtDays(), this.solarSystemPeriod.getRainyDays(), this.solarSystemPeriod.getIntenseRainyDays(), this.solarSystemPeriod.getOptimalDays(), this.solarSystemPeriod.getIntenseRainyDates());
            // Persisto el reporte
            this.weatherForecastReportRepository.save(weatherForecastReport);

            // Limpio los contadores de periodos del intervalo
            this.solarSystemPeriod.cleanCounters();

            return true;

        } else
            return false;

    }

}
