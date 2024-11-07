package com.aladdinworks5.dao;

import java.util.List;

import com.aladdinworks5.dao.GenericDAO;
import com.aladdinworks5.domain.Asset;





public interface AssetDAO extends GenericDAO<Asset, Integer> {
  
	List<Asset> findAll();
	






}


