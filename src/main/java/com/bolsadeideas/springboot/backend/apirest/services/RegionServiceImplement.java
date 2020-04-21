package com.bolsadeideas.springboot.backend.apirest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.backend.apirest.dao.IRegionDao;
import com.bolsadeideas.springboot.backend.apirest.entity.Region;

@Service
public class RegionServiceImplement implements IRegionService {
	
	@Autowired
	private IRegionDao regionDao;

	@Override
	public List<Region> findAllRegiones() {
		return regionDao.findAll();
	}

	@Override
	public Region findById(Long id) {	
		if(id!=null) {
			return regionDao.findById(id).orElse(null);
		}else {
			return null;
		}
	}
	
	

}
