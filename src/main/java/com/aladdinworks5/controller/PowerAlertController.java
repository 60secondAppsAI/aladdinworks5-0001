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

import com.aladdinworks5.domain.PowerAlert;
import com.aladdinworks5.dto.PowerAlertDTO;
import com.aladdinworks5.dto.PowerAlertSearchDTO;
import com.aladdinworks5.dto.PowerAlertPageDTO;
import com.aladdinworks5.service.PowerAlertService;
import com.aladdinworks5.dto.common.RequestDTO;
import com.aladdinworks5.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/powerAlert")
@RestController
public class PowerAlertController {

	private final static Logger logger = LoggerFactory.getLogger(PowerAlertController.class);

	@Autowired
	PowerAlertService powerAlertService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<PowerAlert> getAll() {

		List<PowerAlert> powerAlerts = powerAlertService.findAll();
		
		return powerAlerts;	
	}

	@GetMapping(value = "/{powerAlertId}")
	@ResponseBody
	public PowerAlertDTO getPowerAlert(@PathVariable Integer powerAlertId) {
		
		return (powerAlertService.getPowerAlertDTOById(powerAlertId));
	}

 	@RequestMapping(value = "/addPowerAlert", method = RequestMethod.POST)
	public ResponseEntity<?> addPowerAlert(@RequestBody PowerAlertDTO powerAlertDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = powerAlertService.addPowerAlert(powerAlertDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/powerAlerts")
	public ResponseEntity<PowerAlertPageDTO> getPowerAlerts(PowerAlertSearchDTO powerAlertSearchDTO) {
 
		return powerAlertService.getPowerAlerts(powerAlertSearchDTO);
	}	

	@RequestMapping(value = "/updatePowerAlert", method = RequestMethod.POST)
	public ResponseEntity<?> updatePowerAlert(@RequestBody PowerAlertDTO powerAlertDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = powerAlertService.updatePowerAlert(powerAlertDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
