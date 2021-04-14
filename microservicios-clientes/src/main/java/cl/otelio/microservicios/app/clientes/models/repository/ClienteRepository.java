package cl.otelio.microservicios.app.clientes.models.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cl.otelio.microservicios.app.clientes.models.entity.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

	@Query("select c from Cliente c join fetch c.mascotas m where m.id=?1")
	public Cliente findClienteByMascotaId(Long id);
	
}
