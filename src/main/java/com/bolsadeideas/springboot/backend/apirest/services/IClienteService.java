package com.bolsadeideas.springboot.backend.apirest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.bolsadeideas.springboot.backend.apirest.dto.ClienteDto;
import com.bolsadeideas.springboot.backend.apirest.entity.Cliente;
import com.bolsadeideas.springboot.backend.apirest.entity.Region;

public interface IClienteService {

	public List<ClienteDto> findAll();
	public Page<Cliente> findAll(Pageable pageable);
	public ClienteDto findClienteById(Long id);
	public Cliente findById(Long id);
	public Cliente save(Cliente cliente);
	public void delete(Long id);
	
}
