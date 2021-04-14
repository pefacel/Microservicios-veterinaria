package cl.otelio.microservicios.app.clientes.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "clientes_mascotas")
public class ClienteMascota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="mascota_id", unique=true)
	private Long mascotaId;
	
	@JsonIgnoreProperties(value={"clienteMascotas"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ClienteMascota)) {
			return false;
		}
		
		ClienteMascota m = (ClienteMascota) obj;

		return this.mascotaId != null && this.mascotaId.equals(m.getMascotaId());
	
	}
	
	
}
