package com.mercadopago.model.planetFactory;

import com.mercadopago.model.SolarSystemStatus;

/**
 * Unica instancia del planeta Betasoides, no pueden existir mas de un planeta Betasoide en este sistema solar
 */
public class Betasoides extends Planet {

    private static Betasoides single_instance;

    private Betasoides() {

        super("Betasoides", 3, 2000);

    }

    /**
     * Obtiene la unica instancia de Betasoides
     *
     * @return -
     */
    static Planet getInstance() {

        if (single_instance == null)
            single_instance = new Betasoides();

        return single_instance;

    }

    /**
     * Betasoides setea su posicion al estado actual del sistema solar
     *
     * @param solarSystemStatus - estado actual del sistema solar
     */
    @Override
    public void setSolarSystemStatusPosition(SolarSystemStatus solarSystemStatus) {

        solarSystemStatus.setBetasoidesPosition(this.getAngularPosition());

    }

}
