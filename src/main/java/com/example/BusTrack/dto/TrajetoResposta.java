package com.example.BusTrack.dto;

import java.util.List;

public class TrajetoResposta {
    private List<Segmento> ls;

    public TrajetoResposta() {}

    public List<Segmento> getLs() { return ls; }
    public void setLs(List<Segmento> ls) { this.ls = ls; }
}