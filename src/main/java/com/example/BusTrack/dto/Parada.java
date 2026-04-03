package com.example.BusTrack.dto;

public class Parada {
    private double py; // Latitude
    private double px; // Longitude
    private String np; // Nome da parada
    private String ed; // Endereco

    public Parada() {}

    public double getPy() { return py; }
    public void setPy(double py) { this.py = py; }

    public double getPx() { return px; }
    public void setPx(double px) { this.px = px; }

    public String getNp() { return np; }
    public void setNp(String np) { this.np = np; }

    public String getEd() { return ed; }
    public void setEd(String ed) { this.ed = ed; }
}