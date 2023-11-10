package com.srtech.dto;

import java.time.LocalTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExcceptionDetails {

	private String exceptionCause;
	private String userMessage;
	private LocalTime localTime;
}
