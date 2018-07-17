package com.qa.soap;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Path;

import org.apache.log4j.Logger;

import com.qa.business.service.AccountService;
import com.qa.persistence.domain.Account;

@WebService(serviceName = "sampleaccount", portName = "SampleAccount", name = "SampleAccount", endpointInterface = "com.qa.soap.WSDL",
targetNamespace = "http://localhost:8080/sampleaccount/soap/account")
public class SOAPImplementation implements SOAPService {
	private static final Logger LOGGER = Logger.getLogger(AccountService.class);
	
	@PersistenceContext
	private EntityManager manager;
	
	public void SOAPImplementation() {
		
	}

	@Override
	public String getAllAccounts() {
		Query query = manager.createQuery("Select a FROM Account a");
		List<Account> accounts = (List<Account>) query.getResultList();
		LOGGER.info("EntityManager: getAllAccounts");
		return createListString(accounts);
	}

	@Override
	public String createAccount(String accout) {
		LOGGER.info("EntityManager: createAccount");

		return accout;

	}

	@Override
	public String updateAccount(Long id, String accountToUpdate) {
		// TODO Auto-generated method stub
		LOGGER.info("EntityManager: updateAccount");

		return null;
	}

	@Override
	public String deleteAccount(Long id) {
		// TODO Auto-generated method stub
		LOGGER.info("EntityManager: deleteAccount");

		return null;
	}
	
	 private String createListString(final List<Account> accounts) {
			LOGGER.info("EntityManager: createListString");

	        /*
	         * If the list is null or empty then assume the call was anonymous.
	         */
	        if (accounts == null || accounts.isEmpty()) {
	            return "Empty";
	        }

	        final StringBuilder stringBuilder = new StringBuilder();
	        for (int i = 0; i < accounts.size(); i++) {

	            /*
	             * Add the separator if its not the first string or the last separator since that should be an and (&) symbol.
	             */
	            if (i != 0 && i != accounts.size() - 1)
	            	stringBuilder.append(", ");
	            else if (i != 0 && i == accounts.size() - 1)
	            	stringBuilder.append(" & ");

	            stringBuilder.append(accounts.get(i));
	        }

	        stringBuilder.append("!");

	        return stringBuilder.toString();
	    }
	


}
