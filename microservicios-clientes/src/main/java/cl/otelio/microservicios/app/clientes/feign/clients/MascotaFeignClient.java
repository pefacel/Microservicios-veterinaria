package cl.otelio.microservicios.app.clientes.feign.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.otelio.microservicios.commons.mascotas.models.entity.Mascota;


@FeignClient(name = "microservicio-mascotas")
public interface MascotaFeignClient {

	@GetMapping("/mascotas-por-cliente")
	public Iterable<Mascota> obtenerMascotasPorCliente(@RequestParam Iterable<Long> ids);
}
