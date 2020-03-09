package com.mercadopago.model.planetFactory;

import com.mercadopago.model.SolarSystemStatus;
import com.mercadopago.utils.SolarSystemMaths;

import java.awt.geom.Point2D;

import static java.lang.Math.abs;

public abstract class Planet {

    // Nombre del planeta
    private String name;
    // Posicion actual del planeta
    private int angularPosition;
    // Velocidad a la que se traslada el planeta
    private int angularVelocity;
    // Distancia al sol
    private long sunDistance;

    Planet(String name, int angularVelocity, long sunDistance) {

        this.name = name;
        this.angularVelocity = angularVelocity;
        this.sunDistance = sunDistance;

        this.angularPosition = 0;

    }

    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    int getAngularVelocity() {
        return angularVelocity;
    }

    public void setAngularVelocity(int angularVelocity) {
        this.angularVelocity = angularVelocity;
    }

    long getSunDistance() {
        return sunDistance;
    }

    public void setSunDistance(long sunDistance) {
        this.sunDistance = sunDistance;
    }

    int getAngularPosition() {
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

    /**
     * Traslada al planeta un dia segun su velocidad
     */
    public void rotate() {

        this.angularPosition = this.angularPosition + this.angularVelocity;

        // Para mantener las posiciones angulares de 0-360
        if (abs(this.angularPosition) >= 360)

            if (this.angularPosition > 0)
                this.angularPosition -= 360;
            else
                this.angularPosition += 360;

    }

    /**
     * Cada planeta setea su posicion al estado actual del sistema solar
     *
     * @param solarSystemStatus -
     */
    public abstract void setSolarSystemStatusPosition(SolarSystemStatus solarSystemStatus);

}
