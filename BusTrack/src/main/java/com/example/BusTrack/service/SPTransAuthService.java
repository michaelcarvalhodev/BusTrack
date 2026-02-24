package com.example.BusTrack.service;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SPTransAuthService {

    @Value("${sptrans.token}")
    private String token;

    private BasicCookieStore cookieStore = new BasicCookieStore();
    private CloseableHttpClient httpClient;



    public SPTransAuthService() {
        this.httpClient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .build();
    }

    public boolean conectar() {
        String url = "https://api.olhovivo.sptrans.com.br/v2.1/Login/Autenticar?token=" + this.token;

        try {
            HttpPost httpPost = new HttpPost(url);

            // Adicionar apenas os headers necessários (sem Content-Length)
            httpPost.setHeader("Accept", "*/*");

            // Executar requisição
            CloseableHttpResponse Resposta = httpClient.execute(httpPost);

            String responseBody = EntityUtils.toString(Resposta.getEntity());

            Resposta.close();

            return "true".equalsIgnoreCase(responseBody.trim());

        } catch (Exception e) {
            System.err.println("Erro na autenticação: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public CloseableHttpClient getHttpClient() {
        return this.httpClient;
    }
}