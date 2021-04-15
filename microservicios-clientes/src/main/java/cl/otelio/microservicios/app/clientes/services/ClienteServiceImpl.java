package cl.otelio.microservicios.app.clientes.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.otelio.microservicios.app.clientes.feign.clients.MascotaFeignClient;
import cl.otelio.microservicios.app.clientes.models.entity.Cliente;
import cl.otelio.microservicios.app.clientes.models.repository.ClienteRepository;
import cl.otelio.microservicios.commons.mascotas.models.entity.Mascota;
import cl.otelio.microservicios.commons.services.CommonServiceImpl;

@Service
public class ClienteServiceImpl extends CommonServiceImpl<Cliente,ClienteRepository> implements ClienteService {

	@Autowired
	private MascotaFeignClient clientMascota;
	
	
	@Override
	@Transactional(readOnly = true)
	public Cliente findClienteByMascotaId(Long id) {
		return repository.findClienteByMascotaId(id);
	}

	@Override
	public Iterable<Mascota> obtenerMascotasPorCliente(Iterable<Long> ids) {
		return clientMascota.obtenerMascotasPorCliente(ids);
	}
	@Override
	@Transactional
	public void eliminarClienteMascotaPorId(Long id) {
		repository.eliminarClienteMascotaPorId(id);
	}
	
	
}
