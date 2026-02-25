package com.example.BusTrack.dto;

public class Linha {

    private int cl; // Codigo Identificador da Linha
    private String lt; // Letreiro da linha


    public Linha(){

    }

    public Linha(int cl, String lt) {
        this.cl = cl;
        this.lt = lt;
    }

    public int getCl() {
        return cl;
    }

    public void setCl(int cl) {
        this.cl = cl;
    }

    public String getLt() {
        return lt;
    }

    public void setLt(String lt) {
        this.lt = lt;
    }
}
