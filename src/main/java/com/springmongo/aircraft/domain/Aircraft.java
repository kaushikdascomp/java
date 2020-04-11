package com.springmongo.aircraft.domain;

public class Aircraft {

    private String model;
    private int nbSeats;
    private Engine engine;

    public Aircraft(String model, int nbSeats){
        this.model = model;
        this.nbSeats = nbSeats;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getNbSeats() {
        return nbSeats;
    }

    public void setNbSeats(int nbSeats) {
        this.nbSeats = nbSeats;
    }
}
