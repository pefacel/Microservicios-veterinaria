package cl.otelio.microservicios.app.clientes.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cl.otelio.microservicios.commons.mascotas.models.entity.Mascota;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;

	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;

	@JsonIgnoreProperties(value={"cliente"}, allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade= CascadeType.ALL, orphanRemoval = true)
	private List<ClienteMascota> clienteMascotas;
	
	@Transient
	private List<Mascota> mascotas;

	@PrePersist
	public void PrePersist() {
		this.createAt = new Date();
	}

	public Cliente() {
		this.mascotas = new ArrayList<>();
		this.clienteMascotas = new ArrayList<>();

	}
	
	public void addMascota(Mascota mascota) {
		this.mascotas.add(mascota);
	}
	
	public void removeMascota(Mascota mascota) {
		this.mascotas.remove(mascota);
	}

	public void addClienteMascota(ClienteMascota clienteMascota) {
		this.clienteMascotas.add(clienteMascota);
	}
	
	public void removeClienteMascota(ClienteMascota clienteMascota) {
		this.clienteMascotas.remove(clienteMascota);
	}
	
	
	
}
