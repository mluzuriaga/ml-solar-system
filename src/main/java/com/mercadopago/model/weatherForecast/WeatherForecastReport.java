package com.mercadopago.model.weatherForecast;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Entity
public class WeatherForecastReport {

    @Id
    @GeneratedValue
    private long id;

    private LocalDate initDay;
    private LocalDate lastDay;
    private long period;

    private long droughtDays;
    private long rainyDays;
    private long intenseRainyDays;
    private long optimalDays;

    public WeatherForecastReport() {

    }

    public WeatherForecastReport(LocalDate initDay, LocalDate lastDay, long droughtDays, long rainyDays, long intenseRainyDays, long optimalDays) {

        this.initDay = initDay;
        this.lastDay = lastDay;
        this.period = DAYS.between(this.initDay, this.lastDay);

        this.droughtDays = droughtDays;
        this.rainyDays = rainyDays;
        this.intenseRainyDays = intenseRainyDays;
        this.optimalDays = optimalDays;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getInitDay() {
        return initDay;
    }

    public void setInitDay(LocalDate initDay) {
        this.initDay = initDay;
    }

    public LocalDate getLastDay() {
        return lastDay;
    }

    public void setLastDay(LocalDate lastDay) {
        this.lastDay = lastDay;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public long getDroughtDays() {
        return droughtDays;
    }

    public void setDroughtDays(long droughtDays) {
        this.droughtDays = droughtDays;
    }

    public long getRainyDays() {
        return rainyDays;
    }

    public void setRainyDays(long rainyDays) {
        this.rainyDays = rainyDays;
    }

    public long getIntenseRainyDays() {
        return intenseRainyDays;
    }

    public void setIntenseRainyDays(long intenseRainyDays) {
        this.intenseRainyDays = intenseRainyDays;
    }

    public long getOptimalDays() {
        return optimalDays;
    }

    public void setOptimalDays(long optimalDays) {
        this.optimalDays = optimalDays;
    }

}
