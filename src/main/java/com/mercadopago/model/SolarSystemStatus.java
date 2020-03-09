package com.mercadopago.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Mantiene el estado del sistema solar al momento de solicitar un pronostico
 */
@Entity
public class SolarSystemStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // Posicion angular del planeta Betasoides
    private int betasoidesPosition;
    // Posicion angular del planeta Ferengis
    private int ferengisPosition;
    // Posicion angular del planeta Vulcanos
    private int vulcanosPosition;

    // Perimetro del triangulo formado por los planetas
    private double perimeter;

    SolarSystemStatus() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBetasoidesPosition() {
        return betasoidesPosition;
    }

    public void setBetasoidesPosition(int betasoidesPosition) {
        this.betasoidesPosition = betasoidesPosition;
    }

    public int getFerengisPosition() {
        return ferengisPosition;
    }

    public void setFerengisPosition(int ferengisPosition) {
        this.ferengisPosition = ferengisPosition;
    }

    public int getVulcanosPosition() {
        return vulcanosPosition;
    }

    public void setVulcanosPosition(int vulcanosPosition) {
        this.vulcanosPosition = vulcanosPosition;
    }

    public double getPerimeter() {
        return perimeter;
    }

    void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

}
