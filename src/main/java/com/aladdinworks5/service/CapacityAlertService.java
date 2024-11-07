package com.aladdinworks5.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks5.domain.CapacityAlert;
import com.aladdinworks5.dto.CapacityAlertDTO;
import com.aladdinworks5.dto.CapacityAlertSearchDTO;
import com.aladdinworks5.dto.CapacityAlertPageDTO;
import com.aladdinworks5.dto.CapacityAlertConvertCriteriaDTO;
import com.aladdinworks5.service.GenericService;
import com.aladdinworks5.dto.common.RequestDTO;
import com.aladdinworks5.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CapacityAlertService extends GenericService<CapacityAlert, Integer> {

	List<CapacityAlert> findAll();

	ResultDTO addCapacityAlert(CapacityAlertDTO capacityAlertDTO, RequestDTO requestDTO);

	ResultDTO updateCapacityAlert(CapacityAlertDTO capacityAlertDTO, RequestDTO requestDTO);

    Page<CapacityAlert> getAllCapacityAlerts(Pageable pageable);

    Page<CapacityAlert> getAllCapacityAlerts(Specification<CapacityAlert> spec, Pageable pageable);

	ResponseEntity<CapacityAlertPageDTO> getCapacityAlerts(CapacityAlertSearchDTO capacityAlertSearchDTO);
	
	List<CapacityAlertDTO> convertCapacityAlertsToCapacityAlertDTOs(List<CapacityAlert> capacityAlerts, CapacityAlertConvertCriteriaDTO convertCriteria);

	CapacityAlertDTO getCapacityAlertDTOById(Integer capacityAlertId);







}





