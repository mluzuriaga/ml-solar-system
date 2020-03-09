package com.mercadopago.model.planetFactory;

import com.mercadopago.model.SolarSystemStatus;
import com.mercadopago.utils.SolarSystemMaths;
import javafx.geometry.Point2D;

import static java.lang.Math.abs;

public abstract class Planet {

    private String name;
    private int angularPosition;
    private int angularVelocity;
    private long sunDistance;

    Planet(String name, int angularVelocity, long sunDistance) {

        this.name = name;
        this.angularVelocity = angularVelocity;
        this.sunDistance = sunDistance;

        this.angularPosition = 0;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAngularVelocity() {
        return angularVelocity;
    }

    public void setAngularVelocity(int angularVelocity) {
        this.angularVelocity = angularVelocity;
    }

    public long getSunDistance() {
        return sunDistance;
    }

    public void setSunDistance(long sunDistance) {
        this.sunDistance = sunDistance;
    }

    public int getAngularPosition() {
        return angularPosition;
    }

    public void setAngularPosition(int angularPosition) {
        this.angularPosition = angularPosition;
    }

    /**
     * Obtiene la posicion X/Y del planeta segun la posicion angular
     *
     * @return retorna un punto X/Y
     */
    public Point2D getXYPosition() {

        return SolarSystemMaths.getCircumferencePoint(this.angularPosition, this.sunDistance);

    }

    public void rotate() {

        this.angularPosition = this.angularPosition + this.angularVelocity;

        if (abs(this.angularPosition) >= 360)

            if (this.angularPosition > 0)
                this.angularPosition -= 360;
            else
                this.angularPosition += 360;


    }

    public abstract void setSolarSystemStatusPosition(SolarSystemStatus solarSystemStatus);

}
