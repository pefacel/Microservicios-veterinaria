package cl.otelio.microservicios.app.mascotas.services;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.otelio.microservicios.app.mascotas.models.repository.MascotaRepository;
import cl.otelio.microservicios.commons.mascotas.models.entity.Mascota;
import cl.otelio.microservicios.commons.services.CommonServiceImpl;

@Service
public class MascotaServiceImpl extends CommonServiceImpl<Mascota, MascotaRepository> implements MascotaService {

	@Override
	@Transactional(readOnly = true)
	public List<Mascota> findByNombre(String term) {
		return repository.findByNombre(term);
	}
	
	
	
}
