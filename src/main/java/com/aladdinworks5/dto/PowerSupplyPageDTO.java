package com.aladdinworks5.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PowerSupplyPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<PowerSupplyDTO> powerSupplys;
}





