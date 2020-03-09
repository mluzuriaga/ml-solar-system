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

    @Override
    public WeatherForecastDTO getWeatherForecastDay(long days) throws WeatherForecastException {

        LocalDate date = this.solarSystemPeriod.getInitialDate().plusDays(days);

        if (date.isAfter(this.solarSystemPeriod.getDate()))
            throw new WeatherForecastException("La fecha solicitada es posterior al periodo pronosticado. Para consultar sobre periodos anteriores contacte al administrador.");

        WeatherForecast weatherForecast = this.weatherForecastRepository.findAllByDate(date);
        return new WeatherForecastDTO(days, weatherForecast.getWeatherForecastType());

    }

    @Override
    public WeatherForecastReport getWeatherForecastReport() {

        return this.weatherForecastReportRepository.findTopByOrderByIdDesc();

    }

    @Override
    public void runAnotherPeriod(long days) {

        this.solarSystemPeriod.setInitialDate(this.solarSystemPeriod.getDate());
        this.solarSystemPeriod.setPeriodDays(days);
        this.solarSystemPeriod.setEnabled(true);

    }

}
