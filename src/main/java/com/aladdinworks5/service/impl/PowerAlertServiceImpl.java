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
import com.aladdinworks5.dao.PowerAlertDAO;
import com.aladdinworks5.domain.PowerAlert;
import com.aladdinworks5.dto.PowerAlertDTO;
import com.aladdinworks5.dto.PowerAlertSearchDTO;
import com.aladdinworks5.dto.PowerAlertPageDTO;
import com.aladdinworks5.dto.PowerAlertConvertCriteriaDTO;
import com.aladdinworks5.dto.common.RequestDTO;
import com.aladdinworks5.dto.common.ResultDTO;
import com.aladdinworks5.service.PowerAlertService;
import com.aladdinworks5.util.ControllerUtils;





@Service
public class PowerAlertServiceImpl extends GenericServiceImpl<PowerAlert, Integer> implements PowerAlertService {

    private final static Logger logger = LoggerFactory.getLogger(PowerAlertServiceImpl.class);

	@Autowired
	PowerAlertDAO powerAlertDao;

	


	@Override
	public GenericDAO<PowerAlert, Integer> getDAO() {
		return (GenericDAO<PowerAlert, Integer>) powerAlertDao;
	}
	
	public List<PowerAlert> findAll () {
		List<PowerAlert> powerAlerts = powerAlertDao.findAll();
		
		return powerAlerts;	
		
	}

	public ResultDTO addPowerAlert(PowerAlertDTO powerAlertDTO, RequestDTO requestDTO) {

		PowerAlert powerAlert = new PowerAlert();

		powerAlert.setPowerAlertId(powerAlertDTO.getPowerAlertId());


		powerAlert.setThreshold(powerAlertDTO.getThreshold());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		powerAlert = powerAlertDao.save(powerAlert);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<PowerAlert> getAllPowerAlerts(Pageable pageable) {
		return powerAlertDao.findAll(pageable);
	}

	public Page<PowerAlert> getAllPowerAlerts(Specification<PowerAlert> spec, Pageable pageable) {
		return powerAlertDao.findAll(spec, pageable);
	}

	public ResponseEntity<PowerAlertPageDTO> getPowerAlerts(PowerAlertSearchDTO powerAlertSearchDTO) {
	
			Integer powerAlertId = powerAlertSearchDTO.getPowerAlertId(); 
  			String sortBy = powerAlertSearchDTO.getSortBy();
			String sortOrder = powerAlertSearchDTO.getSortOrder();
			String searchQuery = powerAlertSearchDTO.getSearchQuery();
			Integer page = powerAlertSearchDTO.getPage();
			Integer size = powerAlertSearchDTO.getSize();

	        Specification<PowerAlert> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, powerAlertId, "powerAlertId"); 
			
			

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

		Page<PowerAlert> powerAlerts = this.getAllPowerAlerts(spec, pageable);
		
		//System.out.println(String.valueOf(powerAlerts.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(powerAlerts.getTotalPages()));
		
		List<PowerAlert> powerAlertsList = powerAlerts.getContent();
		
		PowerAlertConvertCriteriaDTO convertCriteria = new PowerAlertConvertCriteriaDTO();
		List<PowerAlertDTO> powerAlertDTOs = this.convertPowerAlertsToPowerAlertDTOs(powerAlertsList,convertCriteria);
		
		PowerAlertPageDTO powerAlertPageDTO = new PowerAlertPageDTO();
		powerAlertPageDTO.setPowerAlerts(powerAlertDTOs);
		powerAlertPageDTO.setTotalElements(powerAlerts.getTotalElements());
		return ResponseEntity.ok(powerAlertPageDTO);
	}

	public List<PowerAlertDTO> convertPowerAlertsToPowerAlertDTOs(List<PowerAlert> powerAlerts, PowerAlertConvertCriteriaDTO convertCriteria) {
		
		List<PowerAlertDTO> powerAlertDTOs = new ArrayList<PowerAlertDTO>();
		
		for (PowerAlert powerAlert : powerAlerts) {
			powerAlertDTOs.add(convertPowerAlertToPowerAlertDTO(powerAlert,convertCriteria));
		}
		
		return powerAlertDTOs;

	}
	
	public PowerAlertDTO convertPowerAlertToPowerAlertDTO(PowerAlert powerAlert, PowerAlertConvertCriteriaDTO convertCriteria) {
		
		PowerAlertDTO powerAlertDTO = new PowerAlertDTO();
		
		powerAlertDTO.setPowerAlertId(powerAlert.getPowerAlertId());

	
		powerAlertDTO.setThreshold(powerAlert.getThreshold());

	

		
		return powerAlertDTO;
	}

	public ResultDTO updatePowerAlert(PowerAlertDTO powerAlertDTO, RequestDTO requestDTO) {
		
		PowerAlert powerAlert = powerAlertDao.getById(powerAlertDTO.getPowerAlertId());

		powerAlert.setPowerAlertId(ControllerUtils.setValue(powerAlert.getPowerAlertId(), powerAlertDTO.getPowerAlertId()));

		powerAlert.setThreshold(ControllerUtils.setValue(powerAlert.getThreshold(), powerAlertDTO.getThreshold()));



        powerAlert = powerAlertDao.save(powerAlert);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public PowerAlertDTO getPowerAlertDTOById(Integer powerAlertId) {
	
		PowerAlert powerAlert = powerAlertDao.getById(powerAlertId);
			
		
		PowerAlertConvertCriteriaDTO convertCriteria = new PowerAlertConvertCriteriaDTO();
		return(this.convertPowerAlertToPowerAlertDTO(powerAlert,convertCriteria));
	}







}
