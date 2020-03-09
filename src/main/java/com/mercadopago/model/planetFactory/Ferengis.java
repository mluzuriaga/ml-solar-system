package com.mercadopago.model.planetFactory;

import com.mercadopago.model.SolarSystemStatus;

class Ferengis extends Planet {

    private static Ferengis single_instance;

    private Ferengis() {

        super("Ferengis", 1, 500);

    }

    static Planet getInstance() {

        if (single_instance == null)
            single_instance = new Ferengis();

        return single_instance;

    }

    @Override
    public void setSolarSystemStatusPosition(SolarSystemStatus solarSystemStatus) {

        solarSystemStatus.setFerengisPosition(this.getAngularPosition());

    }

}
