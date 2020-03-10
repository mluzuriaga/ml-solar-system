package com.mercadopago.repositories;

import com.mercadopago.model.weatherForecast.WeatherForecast;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface WeatherForecastRepository extends CrudRepository<WeatherForecast, Long> {

    WeatherForecast findAllByDate(LocalDate localDate);

}
