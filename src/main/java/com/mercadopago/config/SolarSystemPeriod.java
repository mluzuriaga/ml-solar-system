package com.mercadopago.config;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SolarSystemPeriod {

    private boolean enabled = true;

    private LocalDate initialDate;
    private LocalDate date;
    private long periodDays;

    private long droughtDays = 0;
    private long rainyDays = 0;
    private long intenseRainyDays = 0;
    private long optimalDays = 0;

    public SolarSystemPeriod() {

    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getPeriodDays() {
        return periodDays;
    }

    public void setPeriodDays(long periodDays) {
        this.periodDays = periodDays;
    }

    public long getDroughtDays() {
        return droughtDays;
    }

    public void addDroughtDay() {
        this.droughtDays++;
    }

    public long getRainyDays() {
        return rainyDays;
    }

    public void addRainyDay() {
        this.rainyDays++;
    }

    public long getIntenseRainyDays() {
        return intenseRainyDays;
    }

    public void addIntenseRainyDay() {
        this.intenseRainyDays++;
    }

    public long getOptimalDays() {
        return optimalDays;
    }

    public void addOptimalDay() {
        this.optimalDays++;
    }

    public void nextDay() {
        date = date.plusDays(1);
    }

    public void cleanCounters() {

        droughtDays = 0;
        rainyDays = 0;
        intenseRainyDays = 0;
        optimalDays = 0;

    }

}
