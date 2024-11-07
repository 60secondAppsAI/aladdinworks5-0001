package com.aladdinworks5.dao;

import java.util.List;

import com.aladdinworks5.dao.GenericDAO;
import com.aladdinworks5.domain.CapacityAlert;





public interface CapacityAlertDAO extends GenericDAO<CapacityAlert, Integer> {
  
	List<CapacityAlert> findAll();
	






}


