package com.mercadopago.controllers;

import com.mercadopago.model.dto.WeatherForecastDTO;
import com.mercadopago.services.WeatherForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherForecastController {

    @Autowired
    private WeatherForecastService weatherForecastService;

    @GetMapping("/clima")
    public ResponseEntity<WeatherForecastDTO> getWeatherForecastDay(@RequestParam("dia") Long days) {

        WeatherForecastDTO weatherForecastDTO = this.weatherForecastService.getWeatherForecastDay(days);
        return new ResponseEntity(weatherForecastDTO, HttpStatus.OK);

    }

}
