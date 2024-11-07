package com.aladdinworks5.dao;

import java.util.List;

import com.aladdinworks5.dao.GenericDAO;
import com.aladdinworks5.domain.DataCenter;





public interface DataCenterDAO extends GenericDAO<DataCenter, Integer> {
  
	List<DataCenter> findAll();
	






}


