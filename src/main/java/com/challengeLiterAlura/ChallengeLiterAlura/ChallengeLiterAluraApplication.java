package com.challengeLiterAlura.ChallengeLiterAlura;

import com.challengeLiterAlura.ChallengeLiterAlura.principal.Principal;
import com.challengeLiterAlura.ChallengeLiterAlura.servicio.ServicioLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class ChallengeLiterAluraApplication implements CommandLineRunner {

	@Autowired
	private ServicioLibro servicio;
	public static void main(String[] args) {

		SpringApplication.run(ChallengeLiterAluraApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal(servicio);

		principal.muestraElMenu();

	}
}

