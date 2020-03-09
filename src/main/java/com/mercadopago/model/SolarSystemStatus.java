package com.mercadopago.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SolarSystemStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int betasoidesPosition;
    private int ferengisPosition;
    private int vulcanosPosition;

    private double perimeter;

    public SolarSystemStatus() {

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

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

}
