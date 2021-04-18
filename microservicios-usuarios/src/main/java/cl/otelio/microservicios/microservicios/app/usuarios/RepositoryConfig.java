package cl.otelio.microservicios.microservicios.app.usuarios;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import cl.otelio.microservicios.microservicios.commons.usuarios.models.entity.Role;
import cl.otelio.microservicios.microservicios.commons.usuarios.models.entity.Usuario;




@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer{

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Usuario.class, Role.class);
	}

}
