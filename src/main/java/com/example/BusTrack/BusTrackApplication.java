package com.example.BusTrack;

import com.example.BusTrack.service.BusLocationService;
import com.example.BusTrack.service.SPTransAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BusTrackApplication implements CommandLineRunner {

	@Autowired
	private SPTransAuthService authService;


	@Autowired
	private BusLocationService busLocationService;

	public static void main(String[] args) {
		SpringApplication.run(BusTrackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("teste");

		boolean autenticado = authService.conectar();

		System.out.println("Autenticado: " + autenticado);

		if (autenticado) {
			System.out.println("Sucesso! Agora você pode fazer outras chamadas à API");

			String resposta = busLocationService.buscar("Lapa");
			System.out.println(resposta);

		} else {
			System.out.println("Falha na autenticação");
		}
	}

}