package com.aladdinworks5.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks5.domain.TemperatureAlert;
import com.aladdinworks5.dto.TemperatureAlertDTO;
import com.aladdinworks5.dto.TemperatureAlertSearchDTO;
import com.aladdinworks5.dto.TemperatureAlertPageDTO;
import com.aladdinworks5.dto.TemperatureAlertConvertCriteriaDTO;
import com.aladdinworks5.service.GenericService;
import com.aladdinworks5.dto.common.RequestDTO;
import com.aladdinworks5.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface TemperatureAlertService extends GenericService<TemperatureAlert, Integer> {

	List<TemperatureAlert> findAll();

	ResultDTO addTemperatureAlert(TemperatureAlertDTO temperatureAlertDTO, RequestDTO requestDTO);

	ResultDTO updateTemperatureAlert(TemperatureAlertDTO temperatureAlertDTO, RequestDTO requestDTO);

    Page<TemperatureAlert> getAllTemperatureAlerts(Pageable pageable);

    Page<TemperatureAlert> getAllTemperatureAlerts(Specification<TemperatureAlert> spec, Pageable pageable);

	ResponseEntity<TemperatureAlertPageDTO> getTemperatureAlerts(TemperatureAlertSearchDTO temperatureAlertSearchDTO);
	
	List<TemperatureAlertDTO> convertTemperatureAlertsToTemperatureAlertDTOs(List<TemperatureAlert> temperatureAlerts, TemperatureAlertConvertCriteriaDTO convertCriteria);

	TemperatureAlertDTO getTemperatureAlertDTOById(Integer temperatureAlertId);







}





