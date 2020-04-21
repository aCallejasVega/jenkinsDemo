package com.bolsadeideas.springboot.backend.apirest.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bolsadeideas.springboot.backend.apirest.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long>{

	
	public Usuario findByUsername(String username);
	
	@Query("select u from Usuario u where u.username= :username")
	public Usuario findByUsername2(@Param("username") Long username);
	
	
}
