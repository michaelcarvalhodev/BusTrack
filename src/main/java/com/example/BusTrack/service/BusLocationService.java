package com.example.BusTrack.service;

import com.example.BusTrack.dto.Linha;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
public class BusLocationService {

    private final SPTransAuthService authService;

    public BusLocationService(SPTransAuthService authService) {
        this.authService = authService;
    }

    public List<Linha> buscar(String termo) {

        String url = "https://api.olhovivo.sptrans.com.br/v2.1/Linha/Buscar?termosBusca=" + termo;
        HttpGet get = new HttpGet(url);

        CloseableHttpClient cliente = authService.getHttpClient();

        try (CloseableHttpResponse resposta = cliente.execute(get)) {

            String response = EntityUtils.toString(resposta.getEntity());

            return new Linha().;

        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
            return null;
        }

    }

}

