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

import com.aladdinworks5.domain.TemperatureAlert;
import com.aladdinworks5.dto.TemperatureAlertDTO;
import com.aladdinworks5.dto.TemperatureAlertSearchDTO;
import com.aladdinworks5.dto.TemperatureAlertPageDTO;
import com.aladdinworks5.service.TemperatureAlertService;
import com.aladdinworks5.dto.common.RequestDTO;
import com.aladdinworks5.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/temperatureAlert")
@RestController
public class TemperatureAlertController {

	private final static Logger logger = LoggerFactory.getLogger(TemperatureAlertController.class);

	@Autowired
	TemperatureAlertService temperatureAlertService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<TemperatureAlert> getAll() {

		List<TemperatureAlert> temperatureAlerts = temperatureAlertService.findAll();
		
		return temperatureAlerts;	
	}

	@GetMapping(value = "/{temperatureAlertId}")
	@ResponseBody
	public TemperatureAlertDTO getTemperatureAlert(@PathVariable Integer temperatureAlertId) {
		
		return (temperatureAlertService.getTemperatureAlertDTOById(temperatureAlertId));
	}

 	@RequestMapping(value = "/addTemperatureAlert", method = RequestMethod.POST)
	public ResponseEntity<?> addTemperatureAlert(@RequestBody TemperatureAlertDTO temperatureAlertDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = temperatureAlertService.addTemperatureAlert(temperatureAlertDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/temperatureAlerts")
	public ResponseEntity<TemperatureAlertPageDTO> getTemperatureAlerts(TemperatureAlertSearchDTO temperatureAlertSearchDTO) {
 
		return temperatureAlertService.getTemperatureAlerts(temperatureAlertSearchDTO);
	}	

	@RequestMapping(value = "/updateTemperatureAlert", method = RequestMethod.POST)
	public ResponseEntity<?> updateTemperatureAlert(@RequestBody TemperatureAlertDTO temperatureAlertDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = temperatureAlertService.updateTemperatureAlert(temperatureAlertDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
