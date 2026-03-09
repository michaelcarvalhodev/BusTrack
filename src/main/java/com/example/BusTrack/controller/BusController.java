package com.example.BusTrack.controller;

import com.example.BusTrack.dto.Linha;
import com.example.BusTrack.dto.PosicaoLinha;
import com.example.BusTrack.service.BusLocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/linhas")
//@CrossOrigin(origins = "https://bustrackfront.onrender.com/")
@CrossOrigin(origins = "http://localhost:4200")
public class BusController {


    private final BusLocationService busLocationService;

    public BusController (BusLocationService busLocationService){
        this.busLocationService = busLocationService;
    }

    @GetMapping("/buscar")
    public List<Linha> buscarLinhas (@RequestParam String termo){
        return busLocationService.buscar(termo);

    }

    @GetMapping("/posicao")
    public PosicaoLinha buscarPosicaoDaLinha(@RequestParam int codigoLinha) {
        return busLocationService.buscarPosicao(codigoLinha);
    }


}
