package cl.otelio.microservicios.app.mascotas;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EntityScan({"cl.otelio.microservicios.commons.mascotas.models.entity"})
public class MicroserviciosMascotasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosMascotasApplication.class, args);
	}

}
