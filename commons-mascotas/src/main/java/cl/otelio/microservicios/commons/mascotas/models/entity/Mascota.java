package cl.otelio.microservicios.commons.mascotas.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "mascotas")
public class Mascota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String raza;


	@Column(name = "fecha_nacimiento")
	private Date fechaNacimiento;

	@Column(name = "created_at")
	private Date createdAt;

	@PrePersist
	public void prePersist() {
		this.createdAt = new Date();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Mascota)) {
			return false;
		}
		
		Mascota m = (Mascota) obj;

		return this.id != null && this.id.equals(m.getId());
	}

}
