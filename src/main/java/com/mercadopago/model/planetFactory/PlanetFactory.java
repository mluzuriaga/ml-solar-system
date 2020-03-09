package com.mercadopago.model.planetFactory;

/**
 * Expone los planetas disponibles del sistema solar
 */
public class PlanetFactory {

    /**
     * Planeta VULCANOS
     * Velocidad angular: 5 grados/dia en sentido anti horario
     * Distancia al sol: 1000km
     *
     * @return -
     */
    public static Planet getVulcanos() {

        return Vulcanos.getInstance();

    }

    /**
     * Planeta BETASOIDES
     * Velocidad angular: 3 grados/dia en sentido horario
     * Distancia al sol: 2000km
     *
     * @return -
     */
    public static Planet getBetasoides() {

        return Betasoides.getInstance();

    }

    /**
     * Planeta FERENGIS
     * Velocidad angular: 1 grados/dia en sentido horario
     * Distancia al sol: 500km
     *
     * @return -
     */
    public static Planet getFerengis() {

        return Ferengis.getInstance();

    }

}
