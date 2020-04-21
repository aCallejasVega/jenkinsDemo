package com.bolsadeideas.springboot.backend.apirest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bolsadeideas.springboot.backend.apirest.entity.Cliente;
import com.bolsadeideas.springboot.backend.apirest.entity.Region;

public interface IRegionDao extends JpaRepository<Region,Long>{

}
