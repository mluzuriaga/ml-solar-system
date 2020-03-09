package com.mercadopago.controllers;

import com.mercadopago.exceptions.WeatherForecastException;
import com.mercadopago.model.dto.WeatherForecastDTO;
import com.mercadopago.model.weatherForecast.WeatherForecastReport;
import com.mercadopago.services.WeatherForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherForecastController {

    @Autowired
    private WeatherForecastService weatherForecastService;

    /**
     * Endpoint para obtener el pronostico de un dia en particular del ultimo intervalo pronosticado
     *
     * @param days - cantidad de dias desde el dia inicial del ultimo periodo calculado
     * @return - pronostico del clima
     */
    @GetMapping("/clima")
    public ResponseEntity getWeatherForecastDay(@RequestParam("dia") Long days) {

        try {

            WeatherForecastDTO weatherForecastDTO = this.weatherForecastService.getWeatherForecastDay(days);
            return new ResponseEntity<>(weatherForecastDTO, HttpStatus.OK);

        } catch (WeatherForecastException e) {

            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

    }

    /**
     * Endpoint para obtener el reporte de periodos del ultimo intervalo calculado
     *
     * @return - cantidad de periodos
     */
    @GetMapping("/reporte")
    public ResponseEntity<WeatherForecastReport> getWeatherForecastReport() {

        WeatherForecastReport weatherForecastReport = this.weatherForecastService.getWeatherForecastReport();
        return new ResponseEntity<>(weatherForecastReport, HttpStatus.OK);

    }

    /**
     * Endpoint para calcular nuevos periodos en un nuevo intervalo
     *
     * @param days - cantidad de dias del periodo que se desea calcular. La fecha inicial sera la ultima del periodo anterior.
     */
    @PostMapping("/nuevo")
    public void runAnotherPeriod(@RequestParam("dias") Long days) {

        this.weatherForecastService.runAnotherPeriod(days);

    }

}
