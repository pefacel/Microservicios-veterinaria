package cl.otelio.microservicios.commons.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cl.otelio.microservicios.commons.services.CommonService;




public class CommonController<E, S extends CommonService<E>> {

	@Autowired
	protected S service;
	
	
	
	@Autowired
	private Environment env;
	
	
	
	
	@GetMapping
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping("/pagina")
	public ResponseEntity<?> listar(Pageable pageable){
		return ResponseEntity.ok().body(service.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> ver(@PathVariable Long id){
		
		Optional<E> o = service.findById(id);
		
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		System.out.println(env.getProperty("local.server.port"));

		return ResponseEntity.ok().body(o.get());
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody E entity){
		
		E entityDb = service.save(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entityDb);
		
	}
	

	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
