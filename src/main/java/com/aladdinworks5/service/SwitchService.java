package com.aladdinworks5.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks5.domain.Switch;
import com.aladdinworks5.dto.SwitchDTO;
import com.aladdinworks5.dto.SwitchSearchDTO;
import com.aladdinworks5.dto.SwitchPageDTO;
import com.aladdinworks5.dto.SwitchConvertCriteriaDTO;
import com.aladdinworks5.service.GenericService;
import com.aladdinworks5.dto.common.RequestDTO;
import com.aladdinworks5.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SwitchService extends GenericService<Switch, Integer> {

	List<Switch> findAll();

	ResultDTO addSwitch(SwitchDTO switchDTO, RequestDTO requestDTO);

	ResultDTO updateSwitch(SwitchDTO switchDTO, RequestDTO requestDTO);

    Page<Switch> getAllSwitchs(Pageable pageable);

    Page<Switch> getAllSwitchs(Specification<Switch> spec, Pageable pageable);

	ResponseEntity<SwitchPageDTO> getSwitchs(SwitchSearchDTO switchSearchDTO);
	
	List<SwitchDTO> convertSwitchsToSwitchDTOs(List<Switch> switchs, SwitchConvertCriteriaDTO convertCriteria);

	SwitchDTO getSwitchDTOById(Integer switchId);







}





