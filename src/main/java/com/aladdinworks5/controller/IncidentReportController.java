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

import com.aladdinworks5.domain.IncidentReport;
import com.aladdinworks5.dto.IncidentReportDTO;
import com.aladdinworks5.dto.IncidentReportSearchDTO;
import com.aladdinworks5.dto.IncidentReportPageDTO;
import com.aladdinworks5.service.IncidentReportService;
import com.aladdinworks5.dto.common.RequestDTO;
import com.aladdinworks5.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/incidentReport")
@RestController
public class IncidentReportController {

	private final static Logger logger = LoggerFactory.getLogger(IncidentReportController.class);

	@Autowired
	IncidentReportService incidentReportService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<IncidentReport> getAll() {

		List<IncidentReport> incidentReports = incidentReportService.findAll();
		
		return incidentReports;	
	}

	@GetMapping(value = "/{incidentReportId}")
	@ResponseBody
	public IncidentReportDTO getIncidentReport(@PathVariable Integer incidentReportId) {
		
		return (incidentReportService.getIncidentReportDTOById(incidentReportId));
	}

 	@RequestMapping(value = "/addIncidentReport", method = RequestMethod.POST)
	public ResponseEntity<?> addIncidentReport(@RequestBody IncidentReportDTO incidentReportDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = incidentReportService.addIncidentReport(incidentReportDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/incidentReports")
	public ResponseEntity<IncidentReportPageDTO> getIncidentReports(IncidentReportSearchDTO incidentReportSearchDTO) {
 
		return incidentReportService.getIncidentReports(incidentReportSearchDTO);
	}	

	@RequestMapping(value = "/updateIncidentReport", method = RequestMethod.POST)
	public ResponseEntity<?> updateIncidentReport(@RequestBody IncidentReportDTO incidentReportDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = incidentReportService.updateIncidentReport(incidentReportDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
