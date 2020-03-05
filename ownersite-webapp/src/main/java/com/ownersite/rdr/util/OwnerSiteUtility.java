package com.ownersite.rdr.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ownersite.rdr.exception.OwnerSiteException;

public final class OwnerSiteUtility {

	private static final Logger LOGGER = LoggerFactory.getLogger(OwnerSiteUtility.class);

	private OwnerSiteUtility() {
		// Left blank intentionally
	}

	public static Map<String, Object> constructErrorResponse(Exception exception) {
		LOGGER.info("Constructing error response");

		Map<String, Object> errors = new HashMap<>();

		if (exception instanceof OwnerSiteException) {
			errors.put("errorMessage", ((OwnerSiteException)exception).getErrorMessage());
		} else {
			errors.put("errorMessage", "Internal system error. Please contact adminstrator.");
		}

		LOGGER.info("Constructing error response");

		return errors;
	}

}
