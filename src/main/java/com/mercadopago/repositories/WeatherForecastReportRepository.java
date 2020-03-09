package com.mercadopago.repositories;

import com.mercadopago.model.weatherForecast.WeatherForecastReport;
import org.springframework.data.repository.CrudRepository;

public interface WeatherForecastReportRepository extends CrudRepository<WeatherForecastReport, Long> {

    WeatherForecastReport findTopByOrderByIdDesc();

}
