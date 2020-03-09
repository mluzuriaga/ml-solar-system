package com.mercadopago.model.planetFactory;

import com.mercadopago.model.SolarSystemStatus;

class Vulcanos extends Planet {

    private static Vulcanos single_instance;

    private Vulcanos() {

        super("Vulcanos", -5, 1000);

    }

    static Planet getInstance() {

        if (single_instance == null)
            single_instance = new Vulcanos();

        return single_instance;

    }

    @Override
    public void setSolarSystemStatusPosition(SolarSystemStatus solarSystemStatus) {

        solarSystemStatus.setVulcanosPosition(this.getAngularPosition());

    }

}
