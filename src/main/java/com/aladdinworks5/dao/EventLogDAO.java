package com.aladdinworks5.dao;

import java.util.List;

import com.aladdinworks5.dao.GenericDAO;
import com.aladdinworks5.domain.EventLog;





public interface EventLogDAO extends GenericDAO<EventLog, Integer> {
  
	List<EventLog> findAll();
	






}


