package cl.otelio.microservicios.app.mascotas.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservicio-clientes")
public interface ClienteFeignClient {

	@DeleteMapping("/eliminar-mascota/{id}")
	public void eliminarClienteMascoPorId(@PathVariable Long id);
	
	
}
