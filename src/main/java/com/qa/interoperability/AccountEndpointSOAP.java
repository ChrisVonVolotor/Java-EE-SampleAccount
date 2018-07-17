package com.qa.interoperability;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import com.qa.business.service.AccountService;
import com.qa.soap.SOAPService;

@Path("/account")
public class AccountEndpointSOAP {
	private static final Logger LOGGER = Logger.getLogger(AccountService.class);

	@Inject
	private SOAPService service;

	@Path("/xml")
	@GET
	@Produces({ "application/json" })
	public String getAllAccounts() {
		LOGGER.info("AccountEndpoint: getAllAccounts");

		return service.getAllAccounts();
	}

}
