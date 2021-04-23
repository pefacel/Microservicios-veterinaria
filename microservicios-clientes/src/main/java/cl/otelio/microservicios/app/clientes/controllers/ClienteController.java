package cl.otelio.microservicios.app.clientes.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RefreshScope
public class ClienteController extends CommonController<Cliente, ClienteService> {

	
	
	@Autowired
	private Environment env;
	
	@Value("${configuracion.texto}")
	private String texto;
	
	
	@DeleteMapping("/eliminar-mascota/{id}")
	public ResponseEntity<?> eliminarClienteMascoPorId(@PathVariable Long id){
		service.eliminarClienteMascotaPorId(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@Override
	@GetMapping
	public ResponseEntity<?> listar() {
		List<Cliente> clientes = ((List<Cliente>) service.findAll()).stream().map(c -> {
			c.getClienteMascotas().forEach(cm -> {
				Mascota mascota = new Mascota();
				mascota.setId(cm.getMascotaId());
				c.addMascota(mascota);
			});
			return c;
		}).collect(Collectors.toList());
		return ResponseEntity.ok().body(clientes);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> ver(@PathVariable Long id) {

		Optional<Cliente> o = service.findById(id);
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Cliente cliente = o.get();

		if (cliente.getClienteMascotas().isEmpty() == false) {
			List<Long> ids = cliente.getClienteMascotas().stream().map(cm -> 
			cm.getMascotaId())
					.collect(Collectors.toList());

			List<Mascota> mascotas = (List<Mascota>) service.obtenerMascotasPorCliente(ids);

			cliente.setMascotas(mascotas);
		}

		return ResponseEntity.ok().body(cliente);
	}

	@Override
	@GetMapping("/pagina")
	public ResponseEntity<?> listar(Pageable pageable) {

		Page<Cliente> clientes = service.findAll(pageable).map(c -> {
			c.getClienteMascotas().forEach(cm ->{
				Mascota mascota = new Mascota();
				mascota.setId(cm.getMascotaId());
			c.addMascota(mascota);
			});
			return c;
		});
		return ResponseEntity.ok().body(clientes);
	}

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
	
	@GetMapping("/obtener-config")
	public ResponseEntity<?> obtenerConfig() {
		
		Map<String,String> jsonf = new HashMap<>();
		jsonf.put("texto", texto);
		
		if(env.getActiveProfiles().length>0 && env.getActiveProfiles()[0].equals("dev")) {
			
			jsonf.put("autor.nombre", env.getProperty("configuracion.nombre"));
		}
		
		return new ResponseEntity<Map<String,String>>(jsonf, HttpStatus.OK);
	}

}
