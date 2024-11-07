package com.aladdinworks5.dao;

import java.util.List;

import com.aladdinworks5.dao.GenericDAO;
import com.aladdinworks5.domain.MaintenanceLog;





public interface MaintenanceLogDAO extends GenericDAO<MaintenanceLog, Integer> {
  
	List<MaintenanceLog> findAll();
	






}


