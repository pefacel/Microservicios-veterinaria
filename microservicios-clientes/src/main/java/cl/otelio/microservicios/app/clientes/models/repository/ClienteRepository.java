package cl.otelio.microservicios.app.clientes.models.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import cl.otelio.microservicios.app.clientes.models.entity.Cliente;

public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long> {

	@Query("select c from Cliente c join fetch c.clienteMascotas m where m.mascotaId=?1")
	public Cliente findClienteByMascotaId(Long id);
	
}
