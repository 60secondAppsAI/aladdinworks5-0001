package com.aladdinworks5.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.aladdinworks5.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.aladdinworks5.domain.CapacityAlert;
import com.aladdinworks5.dto.CapacityAlertDTO;
import com.aladdinworks5.dto.CapacityAlertSearchDTO;
import com.aladdinworks5.dto.CapacityAlertPageDTO;
import com.aladdinworks5.service.CapacityAlertService;
import com.aladdinworks5.dto.common.RequestDTO;
import com.aladdinworks5.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/capacityAlert")
@RestController
public class CapacityAlertController {

	private final static Logger logger = LoggerFactory.getLogger(CapacityAlertController.class);

	@Autowired
	CapacityAlertService capacityAlertService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<CapacityAlert> getAll() {

		List<CapacityAlert> capacityAlerts = capacityAlertService.findAll();
		
		return capacityAlerts;	
	}

	@GetMapping(value = "/{capacityAlertId}")
	@ResponseBody
	public CapacityAlertDTO getCapacityAlert(@PathVariable Integer capacityAlertId) {
		
		return (capacityAlertService.getCapacityAlertDTOById(capacityAlertId));
	}

 	@RequestMapping(value = "/addCapacityAlert", method = RequestMethod.POST)
	public ResponseEntity<?> addCapacityAlert(@RequestBody CapacityAlertDTO capacityAlertDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = capacityAlertService.addCapacityAlert(capacityAlertDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/capacityAlerts")
	public ResponseEntity<CapacityAlertPageDTO> getCapacityAlerts(CapacityAlertSearchDTO capacityAlertSearchDTO) {
 
		return capacityAlertService.getCapacityAlerts(capacityAlertSearchDTO);
	}	

	@RequestMapping(value = "/updateCapacityAlert", method = RequestMethod.POST)
	public ResponseEntity<?> updateCapacityAlert(@RequestBody CapacityAlertDTO capacityAlertDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = capacityAlertService.updateCapacityAlert(capacityAlertDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
