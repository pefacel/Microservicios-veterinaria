package cl.otelio.microservicios.app.clientes.services;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.otelio.microservicios.app.clientes.models.entity.Cliente;
import cl.otelio.microservicios.app.clientes.models.repository.ClienteRepository;
import cl.otelio.microservicios.commons.services.CommonServiceImpl;

@Service
public class ClienteServiceImpl extends CommonServiceImpl<Cliente,ClienteRepository> implements ClienteService {

	@Override
	@Transactional(readOnly = true)
	public Cliente findClienteByMascotaId(Long id) {
		return repository.findClienteByMascotaId(id);
	}

	
}
