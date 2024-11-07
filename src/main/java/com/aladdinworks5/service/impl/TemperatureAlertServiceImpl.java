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
import com.aladdinworks5.dao.TemperatureAlertDAO;
import com.aladdinworks5.domain.TemperatureAlert;
import com.aladdinworks5.dto.TemperatureAlertDTO;
import com.aladdinworks5.dto.TemperatureAlertSearchDTO;
import com.aladdinworks5.dto.TemperatureAlertPageDTO;
import com.aladdinworks5.dto.TemperatureAlertConvertCriteriaDTO;
import com.aladdinworks5.dto.common.RequestDTO;
import com.aladdinworks5.dto.common.ResultDTO;
import com.aladdinworks5.service.TemperatureAlertService;
import com.aladdinworks5.util.ControllerUtils;





@Service
public class TemperatureAlertServiceImpl extends GenericServiceImpl<TemperatureAlert, Integer> implements TemperatureAlertService {

    private final static Logger logger = LoggerFactory.getLogger(TemperatureAlertServiceImpl.class);

	@Autowired
	TemperatureAlertDAO temperatureAlertDao;

	


	@Override
	public GenericDAO<TemperatureAlert, Integer> getDAO() {
		return (GenericDAO<TemperatureAlert, Integer>) temperatureAlertDao;
	}
	
	public List<TemperatureAlert> findAll () {
		List<TemperatureAlert> temperatureAlerts = temperatureAlertDao.findAll();
		
		return temperatureAlerts;	
		
	}

	public ResultDTO addTemperatureAlert(TemperatureAlertDTO temperatureAlertDTO, RequestDTO requestDTO) {

		TemperatureAlert temperatureAlert = new TemperatureAlert();

		temperatureAlert.setTemperatureAlertId(temperatureAlertDTO.getTemperatureAlertId());


		temperatureAlert.setThreshold(temperatureAlertDTO.getThreshold());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		temperatureAlert = temperatureAlertDao.save(temperatureAlert);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<TemperatureAlert> getAllTemperatureAlerts(Pageable pageable) {
		return temperatureAlertDao.findAll(pageable);
	}

	public Page<TemperatureAlert> getAllTemperatureAlerts(Specification<TemperatureAlert> spec, Pageable pageable) {
		return temperatureAlertDao.findAll(spec, pageable);
	}

	public ResponseEntity<TemperatureAlertPageDTO> getTemperatureAlerts(TemperatureAlertSearchDTO temperatureAlertSearchDTO) {
	
			Integer temperatureAlertId = temperatureAlertSearchDTO.getTemperatureAlertId(); 
  			String sortBy = temperatureAlertSearchDTO.getSortBy();
			String sortOrder = temperatureAlertSearchDTO.getSortOrder();
			String searchQuery = temperatureAlertSearchDTO.getSearchQuery();
			Integer page = temperatureAlertSearchDTO.getPage();
			Integer size = temperatureAlertSearchDTO.getSize();

	        Specification<TemperatureAlert> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, temperatureAlertId, "temperatureAlertId"); 
			
			

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

		Page<TemperatureAlert> temperatureAlerts = this.getAllTemperatureAlerts(spec, pageable);
		
		//System.out.println(String.valueOf(temperatureAlerts.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(temperatureAlerts.getTotalPages()));
		
		List<TemperatureAlert> temperatureAlertsList = temperatureAlerts.getContent();
		
		TemperatureAlertConvertCriteriaDTO convertCriteria = new TemperatureAlertConvertCriteriaDTO();
		List<TemperatureAlertDTO> temperatureAlertDTOs = this.convertTemperatureAlertsToTemperatureAlertDTOs(temperatureAlertsList,convertCriteria);
		
		TemperatureAlertPageDTO temperatureAlertPageDTO = new TemperatureAlertPageDTO();
		temperatureAlertPageDTO.setTemperatureAlerts(temperatureAlertDTOs);
		temperatureAlertPageDTO.setTotalElements(temperatureAlerts.getTotalElements());
		return ResponseEntity.ok(temperatureAlertPageDTO);
	}

	public List<TemperatureAlertDTO> convertTemperatureAlertsToTemperatureAlertDTOs(List<TemperatureAlert> temperatureAlerts, TemperatureAlertConvertCriteriaDTO convertCriteria) {
		
		List<TemperatureAlertDTO> temperatureAlertDTOs = new ArrayList<TemperatureAlertDTO>();
		
		for (TemperatureAlert temperatureAlert : temperatureAlerts) {
			temperatureAlertDTOs.add(convertTemperatureAlertToTemperatureAlertDTO(temperatureAlert,convertCriteria));
		}
		
		return temperatureAlertDTOs;

	}
	
	public TemperatureAlertDTO convertTemperatureAlertToTemperatureAlertDTO(TemperatureAlert temperatureAlert, TemperatureAlertConvertCriteriaDTO convertCriteria) {
		
		TemperatureAlertDTO temperatureAlertDTO = new TemperatureAlertDTO();
		
		temperatureAlertDTO.setTemperatureAlertId(temperatureAlert.getTemperatureAlertId());

	
		temperatureAlertDTO.setThreshold(temperatureAlert.getThreshold());

	

		
		return temperatureAlertDTO;
	}

	public ResultDTO updateTemperatureAlert(TemperatureAlertDTO temperatureAlertDTO, RequestDTO requestDTO) {
		
		TemperatureAlert temperatureAlert = temperatureAlertDao.getById(temperatureAlertDTO.getTemperatureAlertId());

		temperatureAlert.setTemperatureAlertId(ControllerUtils.setValue(temperatureAlert.getTemperatureAlertId(), temperatureAlertDTO.getTemperatureAlertId()));

		temperatureAlert.setThreshold(ControllerUtils.setValue(temperatureAlert.getThreshold(), temperatureAlertDTO.getThreshold()));



        temperatureAlert = temperatureAlertDao.save(temperatureAlert);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public TemperatureAlertDTO getTemperatureAlertDTOById(Integer temperatureAlertId) {
	
		TemperatureAlert temperatureAlert = temperatureAlertDao.getById(temperatureAlertId);
			
		
		TemperatureAlertConvertCriteriaDTO convertCriteria = new TemperatureAlertConvertCriteriaDTO();
		return(this.convertTemperatureAlertToTemperatureAlertDTO(temperatureAlert,convertCriteria));
	}







}
