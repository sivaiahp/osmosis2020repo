package com.ownersite.rdr.exception;

public class OwnerSiteException extends Exception {

	private static final long serialVersionUID = -3461066109907600889L;
	private final String errorMessage;

	public OwnerSiteException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
