package cl.otelio.microservicios.app.clientes.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.otelio.microservicios.app.clientes.models.entity.Cliente;
import cl.otelio.microservicios.app.clientes.models.entity.ClienteMascota;
import cl.otelio.microservicios.app.clientes.services.ClienteService;
import cl.otelio.microservicios.commons.controllers.CommonController;
import cl.otelio.microservicios.commons.mascotas.models.entity.Mascota;

@RestController
public class ClienteController extends CommonController<Cliente, ClienteService> {



	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Cliente cliente, @PathVariable Long id) {
		Optional<Cliente> o = this.service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Cliente clienteDb = o.get();
		clienteDb.setNombre(cliente.getNombre());
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(clienteDb));
	}

	@PutMapping("/{id}/asignar-mascotas")
	public ResponseEntity<?> asignarMascotas(@RequestBody List<Mascota> mascotas, @PathVariable Long id) {
		Optional<Cliente> o = this.service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		

		
		Cliente clienteDb = o.get();
		mascotas.forEach(m -> {
			ClienteMascota clienteMascota = new ClienteMascota();
			clienteMascota.setMascotaId(m.getId());
			clienteMascota.setCliente(clienteDb);
			clienteDb.addClienteMascota(clienteMascota);
		});
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(clienteDb));

	}

	@PutMapping("/{id}/eliminar-mascota")
	public ResponseEntity<?> eliminarMascota(@RequestBody Mascota mascota, @PathVariable Long id) {
		Optional<Cliente> o = this.service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cliente clienteDb = o.get();
		ClienteMascota clienteMascota = new ClienteMascota();
		clienteMascota.setMascotaId(mascota.getId());
		clienteDb.removeClienteMascota(clienteMascota);

		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(clienteDb));

	}

	@GetMapping("/mascota/{id}")
	public ResponseEntity<?> buscarPorMascotaId(@PathVariable Long id) {
		Cliente cliente = service.findClienteByMascotaId(id);
		return ResponseEntity.ok(cliente);
	}

}
