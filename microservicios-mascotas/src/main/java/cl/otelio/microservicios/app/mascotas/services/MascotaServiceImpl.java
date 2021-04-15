package cl.otelio.microservicios.app.mascotas.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.otelio.microservicios.app.mascotas.feign.clients.ClienteFeignClient;
import cl.otelio.microservicios.app.mascotas.models.repository.MascotaRepository;
import cl.otelio.microservicios.commons.mascotas.models.entity.Mascota;
import cl.otelio.microservicios.commons.services.CommonServiceImpl;

@Service
public class MascotaServiceImpl extends CommonServiceImpl<Mascota, MascotaRepository> implements MascotaService {

	@Autowired
	private ClienteFeignClient clientFeign;
	
	@Override
	@Transactional(readOnly = true)
	public List<Mascota> findByNombre(String term) {
		return repository.findByNombre(term);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Mascota> findAllByid(Iterable<Long> ids) {
		return repository.findAllById(ids);
	}

	@Override
	public void eliminarClienteMascoPorId(Long id) {
		clientFeign.eliminarClienteMascoPorId(id);		
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		super.deleteById(id);
		this.eliminarClienteMascoPorId(id);
	}


	
	
}
