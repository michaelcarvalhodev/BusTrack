package com.example.BusTrack.dto;

import java.util.List;

public class PosicaoLinha {

    private String hr;
    private List<Veiculo> vs;

    public PosicaoLinha() {

    }

    public List<Veiculo> getVs() {
        return vs;
    }

    public void setVs(List<Veiculo> vs) {
        this.vs = vs;
    }

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }
}
