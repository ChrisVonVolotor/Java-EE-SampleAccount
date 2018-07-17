package com.qa.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
@WebService(targetNamespace = "http://localhost:8080/sampleaccount/soap/account/wsdl")
public interface SOAPService {
	@WebMethod
	String getAllAccounts();
	@WebMethod
	String createAccount(String accout);
	@WebMethod
	String updateAccount(Long id, String accountToUpdate);
	@WebMethod
	String deleteAccount(Long id);
	
}
