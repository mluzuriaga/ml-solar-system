package com.mercadopago.model.planetFactory;

import com.mercadopago.model.SolarSystemStatus;

/**
 * Unica instancia del planeta Ferengis, no pueden existir mas de un planeta Ferengis en este sistema solar
 */
class Ferengis extends Planet {

    private static Ferengis single_instance;

    private Ferengis() {

        super("Ferengis", 1, 500);

    }

    /**
     * Obtiene la unica instancia de Ferengis
     *
     * @return -
     */
    static Planet getInstance() {

        if (single_instance == null)
            single_instance = new Ferengis();

        return single_instance;

    }

    /**
     * Ferengis setea su posicion al estado actual del sistema solar
     *
     * @param solarSystemStatus - estado actual del sistema solar
     */
    @Override
    public void setSolarSystemStatusPosition(SolarSystemStatus solarSystemStatus) {

        solarSystemStatus.setFerengisPosition(this.getAngularPosition());

    }

}
