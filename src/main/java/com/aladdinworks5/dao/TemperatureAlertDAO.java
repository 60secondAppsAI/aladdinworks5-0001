package com.aladdinworks5.dao;

import java.util.List;

import com.aladdinworks5.dao.GenericDAO;
import com.aladdinworks5.domain.TemperatureAlert;





public interface TemperatureAlertDAO extends GenericDAO<TemperatureAlert, Integer> {
  
	List<TemperatureAlert> findAll();
	






}


