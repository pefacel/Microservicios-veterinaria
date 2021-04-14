package cl.otelio.microservicios.app.mascotas.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cl.otelio.microservicios.commons.mascotas.models.entity.Mascota;

public interface MascotaRepository extends CrudRepository<Mascota, Long> {

	@Query("select m from Mascota m where m.nombre like %?1%")
	public List<Mascota> findByNombre(String term);
 	
	
}


