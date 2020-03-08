package com.ownersite.rdr.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
			errors.put("errorMessage", ((OwnerSiteException) exception).getErrorMessage());
		} else {
			errors.put("errorMessage", "Internal system error. Please contact adminstrator.");
		}

		LOGGER.info("Constructing error response");

		return errors;
	}

	public static List<String> getTwelveMonthsFromToday() {
		LOGGER.info("Getting twelve months from today");

		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM-yyyy");
		Calendar date = Calendar.getInstance();
		List<String> twelveMonths = new ArrayList<>();
		int iterate = 12;
		do {
			twelveMonths.add(dateFormat.format(date.getTime()));
			date.add(Calendar.MONTH, -1);
		} while (--iterate != 0);

		Collections.reverse(twelveMonths);

		LOGGER.info("Got twelve months from today successfully");

		return twelveMonths;
	}

}
