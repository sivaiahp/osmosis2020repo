package com.ownersite.rdr.dto;

import java.util.List;

public class ReportDTO {

	private List<String> categories;
	private List<ReportDataSeriesDTO> data;

	public ReportDTO(List<String> categories, List<ReportDataSeriesDTO> data) {
		super();
		this.categories = categories;
		this.data = data;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public List<ReportDataSeriesDTO> getData() {
		return data;
	}

	public void setData(List<ReportDataSeriesDTO> data) {
		this.data = data;
	}

}
