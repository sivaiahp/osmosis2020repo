/**
 * 
 */
package com.ownersite.rdr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author polamred
 *
 */
@RestController
@RequestMapping(value = "/ownersite")
public class OwnerSiteController {

	private static final Logger logger = LoggerFactory.getLogger(OwnerSiteController.class);

	
	@Autowired
	public OwnerSiteController() {
	}
	
	@RequestMapping("/")
	public String getWelcome() {
		logger.info("called welcome page=========");
		return "welcome to ownersite main page";
	}
	
	@RequestMapping("/home")
	public String getHome() {
		logger.info("called home page=========");
		return "welcome to ownersite home page";
	}
}
