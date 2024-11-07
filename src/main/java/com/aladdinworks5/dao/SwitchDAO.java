package com.aladdinworks5.dao;

import java.util.List;

import com.aladdinworks5.dao.GenericDAO;
import com.aladdinworks5.domain.Switch;





public interface SwitchDAO extends GenericDAO<Switch, Integer> {
  
	List<Switch> findAll();
	






}


