package cl.otelio.microservicios.microservicios.app.oauth.setrvices;

import cl.otelio.microservicios.microservicios.commons.usuarios.models.entity.Usuario;

public interface IUsuarioService {

	
	public Usuario findByUsername(String username);
}
