package com.aladdinworks5.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks5.domain.MaintenanceLog;
import com.aladdinworks5.dto.MaintenanceLogDTO;
import com.aladdinworks5.dto.MaintenanceLogSearchDTO;
import com.aladdinworks5.dto.MaintenanceLogPageDTO;
import com.aladdinworks5.dto.MaintenanceLogConvertCriteriaDTO;
import com.aladdinworks5.service.GenericService;
import com.aladdinworks5.dto.common.RequestDTO;
import com.aladdinworks5.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MaintenanceLogService extends GenericService<MaintenanceLog, Integer> {

	List<MaintenanceLog> findAll();

	ResultDTO addMaintenanceLog(MaintenanceLogDTO maintenanceLogDTO, RequestDTO requestDTO);

	ResultDTO updateMaintenanceLog(MaintenanceLogDTO maintenanceLogDTO, RequestDTO requestDTO);

    Page<MaintenanceLog> getAllMaintenanceLogs(Pageable pageable);

    Page<MaintenanceLog> getAllMaintenanceLogs(Specification<MaintenanceLog> spec, Pageable pageable);

	ResponseEntity<MaintenanceLogPageDTO> getMaintenanceLogs(MaintenanceLogSearchDTO maintenanceLogSearchDTO);
	
	List<MaintenanceLogDTO> convertMaintenanceLogsToMaintenanceLogDTOs(List<MaintenanceLog> maintenanceLogs, MaintenanceLogConvertCriteriaDTO convertCriteria);

	MaintenanceLogDTO getMaintenanceLogDTOById(Integer maintenanceLogId);







}





