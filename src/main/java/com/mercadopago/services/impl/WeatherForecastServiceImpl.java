package com.mercadopago.services.impl;

import com.mercadopago.model.SolarSystemStatus;
import com.mercadopago.model.dto.WeatherForecastDTO;
import com.mercadopago.model.weatherForecast.WeatherForecast;
import com.mercadopago.repositories.WeatherForecastRepository;
import com.mercadopago.services.WeatherForecastService;
import com.mercadopago.utils.SolarSystemDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class WeatherForecastServiceImpl implements WeatherForecastService {

    @Autowired
    private WeatherForecastRepository weatherForecastRepository;

    @Override
    public WeatherForecastDTO getWeatherForecastDay(long days) {

        LocalDate date = SolarSystemDate.initialDate.plusDays(days);
        WeatherForecast weatherForecast = this.weatherForecastRepository.findAllByDate(date);
        return new WeatherForecastDTO(days, weatherForecast.getWeatherForecastType());

    }

}
