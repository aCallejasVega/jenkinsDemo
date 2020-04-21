package com.bolsadeideas.springboot.backend.apirest.services;

import java.util.List;

import com.bolsadeideas.springboot.backend.apirest.entity.Region;

public interface IRegionService {

	public List<Region> findAllRegiones();
	public Region findById(Long id);
	
}
