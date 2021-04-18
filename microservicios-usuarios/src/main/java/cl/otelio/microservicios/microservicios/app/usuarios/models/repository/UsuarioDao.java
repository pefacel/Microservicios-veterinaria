package cl.otelio.microservicios.microservicios.app.usuarios.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import cl.otelio.microservicios.microservicios.commons.usuarios.models.entity.Usuario;



@RepositoryRestResource(path="usuarios")
public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long> {

	@RestResource(path="buscar-username")
	public Usuario findByUsername(@Param("username") String username);
	
	// Alternativa 
	@Query("select u from Usuario u where u.username=?1 or u.email=?2")
	public Usuario obtenerPorUsername(String username, String email);
	
	
	
}
