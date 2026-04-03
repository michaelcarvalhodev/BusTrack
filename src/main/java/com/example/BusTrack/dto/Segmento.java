package com.example.BusTrack.dto;

import java.util.List;

public class Segmento {
    private List<Coordenada> c;

    public Segmento() {}

    public List<Coordenada> getC() { return c; }
    public void setC(List<Coordenada> c) { this.c = c; }
}