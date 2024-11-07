package com.aladdinworks5.dao;

import java.util.List;

import com.aladdinworks5.dao.GenericDAO;
import com.aladdinworks5.domain.TemperatureSensor;





public interface TemperatureSensorDAO extends GenericDAO<TemperatureSensor, Integer> {
  
	List<TemperatureSensor> findAll();
	






}


