package com.ownersite.rdr.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * The Response DTO for sending the response to client
 * 
 * @author basridha
 *
 */
public class ResponseDTO {

	private String resultCode;
	private Map<String, Object> properties;

	public ResponseDTO() {
		super();
		properties = new HashMap<>();
	}

	public ResponseDTO(String resultCode) {
		super();
		this.resultCode = resultCode;
	}

	public ResponseDTO(String resultCode, Map<String, Object> properties) {
		super();
		this.resultCode = resultCode;
		this.properties = properties;
	}

	public String getResultCode() {
		return resultCode;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

}
