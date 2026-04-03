package com.example.BusTrack.service;

import com.example.BusTrack.dto.Coordenada;
import com.example.BusTrack.dto.Linha;
import com.example.BusTrack.dto.Parada;
import com.example.BusTrack.dto.PosicaoLinha;
import com.example.BusTrack.dto.TrajetoResposta;
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

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BusLocationService.class);

    public BusLocationService(SPTransAuthService authService) {
        this.authService = authService;
    }


    public List<Linha> buscar(String termo) {

        String url = "https://api.olhovivo.sptrans.com.br/v2.1/Linha/Buscar?termosBusca=" + termo;
        HttpGet get = new HttpGet(url);

        CloseableHttpClient cliente = authService.getHttpClient();

        try (CloseableHttpResponse resposta = cliente.execute(get)) {

            String response = EntityUtils.toString(resposta.getEntity());
            ObjectMapper mapper = new ObjectMapper();

            Linha[] arrayDeLinhas = mapper.readValue(response, Linha[].class);
            return List.of(arrayDeLinhas);

        } catch (Exception e) {
            log.error("erro SPTrans: {}", e.getMessage(), e);
            return null;
        }

    }

    public PosicaoLinha buscarPosicao(int codigoLinha) {

        String url = "https://api.olhovivo.sptrans.com.br/v2.1/Posicao/Linha?codigoLinha=" + codigoLinha;
        HttpGet get = new HttpGet(url);
        CloseableHttpClient cliente = authService.getHttpClient();

        try (CloseableHttpResponse resposta = cliente.execute(get)) {

            String response = EntityUtils.toString(resposta.getEntity());
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(response, PosicaoLinha.class);

        } catch (Exception e) {
            System.err.println("Erro ao buscar posicao: " + e.getMessage());
            return null;
        }
    }

    public List<Parada> buscarParadas(int codigoLinha) {

        String url = "https://api.olhovivo.sptrans.com.br/v2.1/Parada/BuscarParadasPorLinha?codigoLinha=" + codigoLinha;
        HttpGet get = new HttpGet(url);
        CloseableHttpClient cliente = authService.getHttpClient();

        try (CloseableHttpResponse resposta = cliente.execute(get)) {

            String response = EntityUtils.toString(resposta.getEntity());
            ObjectMapper mapper = new ObjectMapper();

            Parada[] arrayDeParadas = mapper.readValue(response, Parada[].class);
            return List.of(arrayDeParadas);

        } catch (Exception e) {
            log.error("Erro ao buscar paradas: {}", e.getMessage(), e);
            return null;
        }
    }

    public List<Coordenada> buscarTrajeto(int codigoLinha) {

        String url = "https://api.olhovivo.sptrans.com.br/v2.1/Posicao/Trajeto?codigoLinha=" + codigoLinha;
        HttpGet get = new HttpGet(url);
        CloseableHttpClient cliente = authService.getHttpClient();

        try (CloseableHttpResponse resposta = cliente.execute(get)) {

            String response = EntityUtils.toString(resposta.getEntity());
            log.info("Trajeto raw response: {}", response);
            ObjectMapper mapper = new ObjectMapper();

            TrajetoResposta trajetoResposta = mapper.readValue(response, TrajetoResposta.class);

            return trajetoResposta.getLs().stream()
                    .flatMap(segmento -> segmento.getC().stream())
                    .toList();

        } catch (Exception e) {
            log.error("Erro ao buscar trajeto: {}", e.getMessage(), e);
            return null;
        }
    }

}

