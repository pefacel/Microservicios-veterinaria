package cl.otelio.microservicios.app.clientes.services;


import cl.otelio.microservicios.app.clientes.models.entity.Cliente;
import cl.otelio.microservicios.commons.mascotas.models.entity.Mascota;
import cl.otelio.microservicios.commons.services.CommonService;

public interface ClienteService extends CommonService<Cliente> {
	public Cliente findClienteByMascotaId(Long id);
	public Iterable<Mascota> obtenerMascotasPorCliente(Iterable<Long> ids);
	public void eliminarClienteMascotaPorId(Long id);

}
