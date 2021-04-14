package cl.otelio.microservicios.app.clientes.services;

import cl.otelio.microservicios.app.clientes.models.entity.Cliente;
import cl.otelio.microservicios.commons.services.CommonService;

public interface ClienteService extends CommonService<Cliente> {
	public Cliente findClienteByMascotaId(Long id);

}
