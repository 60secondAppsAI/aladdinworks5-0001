package com.aladdinworks5.dao;

import java.util.List;

import com.aladdinworks5.dao.GenericDAO;
import com.aladdinworks5.domain.Equipment;





public interface EquipmentDAO extends GenericDAO<Equipment, Integer> {
  
	List<Equipment> findAll();
	






}


