package cl.otelio.microservicios.app.mascotas.models.repository;

import org.springframework.data.repository.CrudRepository;

import cl.otelio.microservicios.app.mascotas.models.entity.Mascota;

public interface MascotaRepository extends CrudRepository<Mascota, Long> {

}
