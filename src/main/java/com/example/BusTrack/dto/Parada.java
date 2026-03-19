package com.example.BusTrack.dto;

public class Parada {
    private double py; // Latitude
    private double px; // Longitude

    public Parada() {}

    public double getPy() { return py; }
    public void setPy(double py) { this.py = py; }

    public double getPx() { return px; }
    public void setPx(double px) { this.px = px; }
}