package com.bolsadeideas.springboot.backend.apirest.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;



public class ClienteDto {
	
	public Long id;
	@NotEmpty(message = "El atributo Nombre no puede estar vacio")
	@Size(min = 4,max = 12,message ="El atributo nombre debe contener entre 4 a 12 caracteres")
	public String nombre;
	@NotEmpty
	public String apellidos;
	@Column(name = "email")
	@Email(message = "El email no es valido")
	public String email;
	public String foto;
	@Temporal(TemporalType.DATE)
	public Date crateAt;
	//@NotEmpty(message = "El atributo region no puede ser nulo")
	public Long idRegion;	
	public String nombreRegion;


	
	public ClienteDto() {}
	
	
	



	/*public ClienteDto(Long id, String nombre, String apellidos, String email, Date createAt) {
	
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.createAt = createAt;
	}*/



	public ClienteDto(Long id,
			String nombre,
			String apellidos, 
			String email, 
			Date createAt,
			String foto,
			Long idRegion, 
			String nombreRegion) {
		
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.crateAt = createAt;
		this.foto=foto;
		this.idRegion = idRegion;
		this.nombreRegion = nombreRegion;
	}


	
	
	
	
	
	
}
