package com.example.BusTrack.service;

import jakarta.annotation.PostConstruct;
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

    private final BasicCookieStore cookieStore = new BasicCookieStore();
    private final CloseableHttpClient httpClient;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SPTransAuthService.class);

    public SPTransAuthService() {
        this.httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
    }

    @PostConstruct
    public boolean conectar() {
        String url = "https://api.olhovivo.sptrans.com.br/v2.1/Login/Autenticar?token=" + this.token;

        try {
            HttpPost httpPost = new HttpPost(url);

            httpPost.setHeader("Accept", "*/*");

            CloseableHttpResponse Resposta = httpClient.execute(httpPost);

            String responseBody = EntityUtils.toString(Resposta.getEntity());

            Resposta.close();

            return "true".equalsIgnoreCase(responseBody.trim());

        } catch (Exception e) {
            log.error("erro na SPTrans: {}", e.getMessage(), e);
            return false;
        }
    }

    public CloseableHttpClient getHttpClient() {
        return this.httpClient;
    }
}