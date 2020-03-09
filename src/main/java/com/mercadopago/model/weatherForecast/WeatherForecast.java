package com.mercadopago.model.weatherForecast;

import com.mercadopago.model.SolarSystemStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class WeatherForecast {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private WeatherForecastType weatherForecastType;

    @OneToOne(cascade = CascadeType.ALL)
    private SolarSystemStatus solarSystemStatus;

    public WeatherForecast() {

    }

    public WeatherForecast(LocalDate date, WeatherForecastType weatherForecastType) {

        this.date = date;
        this.weatherForecastType = weatherForecastType;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public WeatherForecastType getWeatherForecastType() {
        return weatherForecastType;
    }

    public void setWeatherForecastType(WeatherForecastType weatherForecastType) {
        this.weatherForecastType = weatherForecastType;
    }

    public SolarSystemStatus getSolarSystemStatus() {
        return solarSystemStatus;
    }

    public void setSolarSystemStatus(SolarSystemStatus solarSystemStatus) {
        this.solarSystemStatus = solarSystemStatus;
    }

}
