package com.bolsadeideas.springboot.backend.apirest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.backend.apirest.dto.ClienteDto;
import com.bolsadeideas.springboot.backend.apirest.entity.Cliente;
import com.bolsadeideas.springboot.backend.apirest.entity.Region;
@Repository
public interface IClienteDao extends JpaRepository<Cliente,Long> {
	
	@Query(value = "Select new com.bolsadeideas.springboot.backend.apirest.dto.ClienteDto("+
			" c.id," + 
			" c.nombre," + 
			" c.apellidos," + 
			" c.email," + 
			" c.crateAt," +
			" c.foto,"+
			" r.id,"+			 
			" r.nombre ) "+
			" from Cliente c left join Region r on c.region.id=r.id")
	public List<ClienteDto> findClientes();
	
	
	@Query(value = "Select new com.bolsadeideas.springboot.backend.apirest.dto.ClienteDto("+
			" c.id," + 
			" c.nombre," + 
			" c.apellidos," + 
			" c.email," + 
			" c.crateAt," +
			" c.foto,"+
			" r.id,"+			 
			" r.nombre ) "+
			" from Cliente c left join Region r on c.region.id=r.id"+
			" where c.id = :id")
	public ClienteDto findClienteById(@Param("id") Long id);
	
	
}
