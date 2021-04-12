package cl.otelio.microservicios.app.mascotas.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.otelio.microservicios.app.mascotas.models.entity.Mascota;
import cl.otelio.microservicios.app.mascotas.services.MascotaService;
import cl.otelio.microservicios.commons.controllers.CommonController;

@RestController
public class MascotaController extends CommonController<Mascota, MascotaService> {


	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Mascota mascota, @PathVariable Long id) {

		Optional<Mascota> o = service.findById(id);
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Mascota mascotaDb = o.get();
		mascotaDb.setNombre(mascota.getNombre());
		mascotaDb.setRaza(mascota.getRaza());
		mascotaDb.setFechaNacimiento(mascota.getFechaNacimiento());

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(mascotaDb));

	}

}
