package com.bolsadeideas.springboot.backend.apirest.services;

import com.bolsadeideas.springboot.backend.apirest.entity.Usuario;

public interface IUsuarioService {
	
	public Usuario findByUsername(String username);
	

}
