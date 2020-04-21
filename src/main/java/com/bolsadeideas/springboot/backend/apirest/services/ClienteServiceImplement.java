package com.bolsadeideas.springboot.backend.apirest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.backend.apirest.dao.IClienteDao;
import com.bolsadeideas.springboot.backend.apirest.dto.ClienteDto;
import com.bolsadeideas.springboot.backend.apirest.entity.Cliente;
import com.bolsadeideas.springboot.backend.apirest.entity.Region;

@Service
public class ClienteServiceImplement  implements IClienteService{

	@Autowired
	private IClienteDao clienteDao;	
	
	
	@Transactional(readOnly = true)
	@Override
	public List<ClienteDto> findAll() {	
		//return clienteDao.findAll();
		return clienteDao.findClientes();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
		
	}
	
	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}


	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {	
		return clienteDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public ClienteDto findClienteById(Long id) {	
		return clienteDao.findClienteById(id);
	}


	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);
		
	}

	

	
	

}
