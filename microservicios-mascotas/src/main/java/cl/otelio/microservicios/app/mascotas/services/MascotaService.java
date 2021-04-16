package cl.otelio.microservicios.app.mascotas.services;


import java.util.List;

import cl.otelio.microservicios.commons.mascotas.models.entity.Mascota;
import cl.otelio.microservicios.commons.services.CommonService;

public interface MascotaService extends CommonService<Mascota> {

	public List<Mascota> findByNombre(String term);

	public Iterable<Mascota> findAllByid(Iterable<Long> ids);
	
	public void eliminarClienteMascoPorId(Long id);

}
