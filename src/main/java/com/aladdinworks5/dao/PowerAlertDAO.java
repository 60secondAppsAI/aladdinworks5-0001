package com.aladdinworks5.dao;

import java.util.List;

import com.aladdinworks5.dao.GenericDAO;
import com.aladdinworks5.domain.PowerAlert;





public interface PowerAlertDAO extends GenericDAO<PowerAlert, Integer> {
  
	List<PowerAlert> findAll();
	






}


