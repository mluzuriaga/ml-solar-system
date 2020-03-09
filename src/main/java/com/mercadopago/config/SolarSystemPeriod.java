package com.mercadopago.config;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Mantiene las fechas de inicio y fin para el calculo de los periodos, y la cuenta de los periodos en dicho intervalo
 */
@Component
public class SolarSystemPeriod {

    // Indica si el Job esta activo o no
    private boolean enabled = true;

    // Fecha de inicio para el calculo de los periodos
    private LocalDate initialDate;
    // Fecha actual
    private LocalDate date;
    // Cantidad de dias del intervalo a calcular
    private long periodDays;

    // Cantidad de dias de sequia
    private long droughtDays = 0;
    // Cantidad de dias de lluvia
    private long rainyDays = 0;
    // Cantidad de dias de lluvia intensa
    private long intenseRainyDays = 0;
    // Cantidad de dias optimos de presion y temperatura
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

    /**
     * Suma un dia al dia actual - Usado por el cron Job para pasar de dia
     */
    public void nextDay() {
        date = date.plusDays(1);
    }

    /**
     * Limpia los contadores de los periodos del intervalo - Usado para poder calcular un nuevo periodo
     */
    public void cleanCounters() {

        droughtDays = 0;
        rainyDays = 0;
        intenseRainyDays = 0;
        optimalDays = 0;

    }

}
