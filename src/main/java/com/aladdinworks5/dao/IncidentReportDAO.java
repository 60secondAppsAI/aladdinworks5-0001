package com.aladdinworks5.dao;

import java.util.List;

import com.aladdinworks5.dao.GenericDAO;
import com.aladdinworks5.domain.IncidentReport;





public interface IncidentReportDAO extends GenericDAO<IncidentReport, Integer> {
  
	List<IncidentReport> findAll();
	






}


