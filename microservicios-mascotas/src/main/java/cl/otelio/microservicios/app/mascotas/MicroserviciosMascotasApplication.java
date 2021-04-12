package cl.otelio.microservicios.app.mascotas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroserviciosMascotasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosMascotasApplication.class, args);
	}

}
