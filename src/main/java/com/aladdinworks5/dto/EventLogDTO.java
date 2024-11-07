package com.aladdinworks5.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class EventLogDTO {

	private Integer eventLogId;

	private Date timestamp;

	private String eventDescription;






}
