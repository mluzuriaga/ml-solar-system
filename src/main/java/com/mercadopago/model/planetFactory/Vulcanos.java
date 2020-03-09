package com.mercadopago.model.planetFactory;

import com.mercadopago.model.SolarSystemStatus;

/**
 * Unica instancia del planeta Vulcanos, no pueden existir mas de un planeta Vulcanos en este sistema solar
 */
class Vulcanos extends Planet {

    private static Vulcanos single_instance;

    private Vulcanos() {

        super("Vulcanos", -5, 1000);

    }

    /**
     * Obtiene la unica instancia de Vulcanos
     *
     * @return -
     */
    static Planet getInstance() {

        if (single_instance == null)
            single_instance = new Vulcanos();

        return single_instance;

    }

    /**
     * Vulcanos setea su posicion al estado actual del sistema solar
     *
     * @param solarSystemStatus - estado actual del sistema solar
     */
    @Override
    public void setSolarSystemStatusPosition(SolarSystemStatus solarSystemStatus) {

        solarSystemStatus.setVulcanosPosition(this.getAngularPosition());

    }

}
