package com.mercadopago.utils;

import java.time.LocalDate;

public class SolarSystemDate {

    public static LocalDate initialDate;
    public static LocalDate date;

    public static void nextDay() {
        date = date.plusDays(1);
    }

}
