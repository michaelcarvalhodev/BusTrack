package com.example.BusTrack.service;


import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.stereotype.Service;

@Service
public class BusLocationService {

    // Precisamos do serviço de autenticação para pegar o cliente logado
    private final SPTransAuthService authService;

    public BusLocationService(SPTransAuthService authService) {
        this.authService = authService;
    }


    public String buscar(String termo) {

        String url = "https://api.olhovivo.sptrans.com.br/v2.1/Linha/Buscar?termosBusca=" + termo;
        HttpGet get = new HttpGet(url);

        CloseableHttpClient cliente = authService.getHttpClient();

        try (CloseableHttpResponse resposta = cliente.execute(get)) {

            String response = EntityUtils.toString(resposta.getEntity());

            return response;

        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
            return "Erro";
        }


    }


}

