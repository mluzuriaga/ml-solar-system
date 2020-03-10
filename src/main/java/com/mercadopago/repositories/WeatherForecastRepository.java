package com.mercadopago.repositories;

import com.mercadopago.model.weatherForecast.WeatherForecast;
import com.mercadopago.model.weatherForecast.WeatherForecastType;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface WeatherForecastRepository extends CrudRepository<WeatherForecast, Long> {

    WeatherForecast findAllByDate(LocalDate localDate);

    List<WeatherForecast> findTopByOrderByWeatherForecastType(WeatherForecastType weatherForecastType);

}
