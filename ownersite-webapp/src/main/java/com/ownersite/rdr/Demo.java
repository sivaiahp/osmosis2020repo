package com.ownersite.rdr;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class Demo {

	public static void main(String[] args) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM-yyyy");
		Calendar date = Calendar.getInstance();
		List<String> twelveMonths = new ArrayList<>();
		int iterate = 12;
		do {
			twelveMonths.add(dateFormat.format(date.getTime()));
			date.add(Calendar.MONTH, -1);
		} while (--iterate != 0);
		
		Collections.reverse(twelveMonths);
		System.out.println(twelveMonths);
	}

}
