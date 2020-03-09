package com.mercadopago.model.planetFactory;

import com.mercadopago.model.SolarSystemStatus;

public class Betasoides extends Planet {

    private static Betasoides single_instance;

    private Betasoides() {

        super("Betasoides", 3, 2000);

    }

    static Planet getInstance() {

        if (single_instance == null)
            single_instance = new Betasoides();

        return single_instance;

    }

    @Override
    public void setSolarSystemStatusPosition(SolarSystemStatus solarSystemStatus) {

        solarSystemStatus.setBetasoidesPosition(this.getAngularPosition());

    }

}
