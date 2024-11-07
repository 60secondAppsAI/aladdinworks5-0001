package com.aladdinworks5.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.aladdinworks5.dao.GenericDAO;
import com.aladdinworks5.service.GenericService;
import com.aladdinworks5.service.impl.GenericServiceImpl;
import com.aladdinworks5.dao.CapacityAlertDAO;
import com.aladdinworks5.domain.CapacityAlert;
import com.aladdinworks5.dto.CapacityAlertDTO;
import com.aladdinworks5.dto.CapacityAlertSearchDTO;
import com.aladdinworks5.dto.CapacityAlertPageDTO;
import com.aladdinworks5.dto.CapacityAlertConvertCriteriaDTO;
import com.aladdinworks5.dto.common.RequestDTO;
import com.aladdinworks5.dto.common.ResultDTO;
import com.aladdinworks5.service.CapacityAlertService;
import com.aladdinworks5.util.ControllerUtils;





@Service
public class CapacityAlertServiceImpl extends GenericServiceImpl<CapacityAlert, Integer> implements CapacityAlertService {

    private final static Logger logger = LoggerFactory.getLogger(CapacityAlertServiceImpl.class);

	@Autowired
	CapacityAlertDAO capacityAlertDao;

	


	@Override
	public GenericDAO<CapacityAlert, Integer> getDAO() {
		return (GenericDAO<CapacityAlert, Integer>) capacityAlertDao;
	}
	
	public List<CapacityAlert> findAll () {
		List<CapacityAlert> capacityAlerts = capacityAlertDao.findAll();
		
		return capacityAlerts;	
		
	}

	public ResultDTO addCapacityAlert(CapacityAlertDTO capacityAlertDTO, RequestDTO requestDTO) {

		CapacityAlert capacityAlert = new CapacityAlert();

		capacityAlert.setCapacityAlertId(capacityAlertDTO.getCapacityAlertId());


		capacityAlert.setThreshold(capacityAlertDTO.getThreshold());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		capacityAlert = capacityAlertDao.save(capacityAlert);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<CapacityAlert> getAllCapacityAlerts(Pageable pageable) {
		return capacityAlertDao.findAll(pageable);
	}

	public Page<CapacityAlert> getAllCapacityAlerts(Specification<CapacityAlert> spec, Pageable pageable) {
		return capacityAlertDao.findAll(spec, pageable);
	}

	public ResponseEntity<CapacityAlertPageDTO> getCapacityAlerts(CapacityAlertSearchDTO capacityAlertSearchDTO) {
	
			Integer capacityAlertId = capacityAlertSearchDTO.getCapacityAlertId(); 
  			String sortBy = capacityAlertSearchDTO.getSortBy();
			String sortOrder = capacityAlertSearchDTO.getSortOrder();
			String searchQuery = capacityAlertSearchDTO.getSearchQuery();
			Integer page = capacityAlertSearchDTO.getPage();
			Integer size = capacityAlertSearchDTO.getSize();

	        Specification<CapacityAlert> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, capacityAlertId, "capacityAlertId"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<CapacityAlert> capacityAlerts = this.getAllCapacityAlerts(spec, pageable);
		
		//System.out.println(String.valueOf(capacityAlerts.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(capacityAlerts.getTotalPages()));
		
		List<CapacityAlert> capacityAlertsList = capacityAlerts.getContent();
		
		CapacityAlertConvertCriteriaDTO convertCriteria = new CapacityAlertConvertCriteriaDTO();
		List<CapacityAlertDTO> capacityAlertDTOs = this.convertCapacityAlertsToCapacityAlertDTOs(capacityAlertsList,convertCriteria);
		
		CapacityAlertPageDTO capacityAlertPageDTO = new CapacityAlertPageDTO();
		capacityAlertPageDTO.setCapacityAlerts(capacityAlertDTOs);
		capacityAlertPageDTO.setTotalElements(capacityAlerts.getTotalElements());
		return ResponseEntity.ok(capacityAlertPageDTO);
	}

	public List<CapacityAlertDTO> convertCapacityAlertsToCapacityAlertDTOs(List<CapacityAlert> capacityAlerts, CapacityAlertConvertCriteriaDTO convertCriteria) {
		
		List<CapacityAlertDTO> capacityAlertDTOs = new ArrayList<CapacityAlertDTO>();
		
		for (CapacityAlert capacityAlert : capacityAlerts) {
			capacityAlertDTOs.add(convertCapacityAlertToCapacityAlertDTO(capacityAlert,convertCriteria));
		}
		
		return capacityAlertDTOs;

	}
	
	public CapacityAlertDTO convertCapacityAlertToCapacityAlertDTO(CapacityAlert capacityAlert, CapacityAlertConvertCriteriaDTO convertCriteria) {
		
		CapacityAlertDTO capacityAlertDTO = new CapacityAlertDTO();
		
		capacityAlertDTO.setCapacityAlertId(capacityAlert.getCapacityAlertId());

	
		capacityAlertDTO.setThreshold(capacityAlert.getThreshold());

	

		
		return capacityAlertDTO;
	}

	public ResultDTO updateCapacityAlert(CapacityAlertDTO capacityAlertDTO, RequestDTO requestDTO) {
		
		CapacityAlert capacityAlert = capacityAlertDao.getById(capacityAlertDTO.getCapacityAlertId());

		capacityAlert.setCapacityAlertId(ControllerUtils.setValue(capacityAlert.getCapacityAlertId(), capacityAlertDTO.getCapacityAlertId()));

		capacityAlert.setThreshold(ControllerUtils.setValue(capacityAlert.getThreshold(), capacityAlertDTO.getThreshold()));



        capacityAlert = capacityAlertDao.save(capacityAlert);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CapacityAlertDTO getCapacityAlertDTOById(Integer capacityAlertId) {
	
		CapacityAlert capacityAlert = capacityAlertDao.getById(capacityAlertId);
			
		
		CapacityAlertConvertCriteriaDTO convertCriteria = new CapacityAlertConvertCriteriaDTO();
		return(this.convertCapacityAlertToCapacityAlertDTO(capacityAlert,convertCriteria));
	}







}
