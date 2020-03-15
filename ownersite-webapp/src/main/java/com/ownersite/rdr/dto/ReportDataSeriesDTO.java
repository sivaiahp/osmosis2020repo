package com.ownersite.rdr.dto;

import java.util.List;

/**
 * The DTO for storing the series data in a report
 * 
 * @author basridha
 *
 */
public class ReportDataSeriesDTO {

	private String name;
	private List<Number> data;

	public ReportDataSeriesDTO(String name, List<Number> data) {
		super();
		this.name = name;
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Number> getData() {
		return data;
	}

	public void setData(List<Number> data) {
		this.data = data;
	}

}
