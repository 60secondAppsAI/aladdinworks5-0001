package com.aladdinworks5.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks5.domain.PowerAlert;
import com.aladdinworks5.dto.PowerAlertDTO;
import com.aladdinworks5.dto.PowerAlertSearchDTO;
import com.aladdinworks5.dto.PowerAlertPageDTO;
import com.aladdinworks5.dto.PowerAlertConvertCriteriaDTO;
import com.aladdinworks5.service.GenericService;
import com.aladdinworks5.dto.common.RequestDTO;
import com.aladdinworks5.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PowerAlertService extends GenericService<PowerAlert, Integer> {

	List<PowerAlert> findAll();

	ResultDTO addPowerAlert(PowerAlertDTO powerAlertDTO, RequestDTO requestDTO);

	ResultDTO updatePowerAlert(PowerAlertDTO powerAlertDTO, RequestDTO requestDTO);

    Page<PowerAlert> getAllPowerAlerts(Pageable pageable);

    Page<PowerAlert> getAllPowerAlerts(Specification<PowerAlert> spec, Pageable pageable);

	ResponseEntity<PowerAlertPageDTO> getPowerAlerts(PowerAlertSearchDTO powerAlertSearchDTO);
	
	List<PowerAlertDTO> convertPowerAlertsToPowerAlertDTOs(List<PowerAlert> powerAlerts, PowerAlertConvertCriteriaDTO convertCriteria);

	PowerAlertDTO getPowerAlertDTOById(Integer powerAlertId);







}





