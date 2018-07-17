package com.qa.persistence.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;

import org.apache.log4j.Logger;

import com.qa.business.service.AccountService;

@Entity
public class Account {
	private static final Logger LOGGER = Logger.getLogger(AccountService.class);

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	@NotNull
	@Pattern(regexp="^[a-zA-Z-]*$")
	@Size(max=60)
	private String firstName;	
	@NotNull
	@Pattern(regexp="^[a-zA-Z-]*$")
	@Size(max=60)
	private String secondName;
	@NotNull
	@Size(min=4, max=4)
	@Pattern(regexp="^[0-9]*$")
	private String accountNumber;
	@JoinColumn(name = "account_id")
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Transaction> transaction;

	public Account() {
		LOGGER.info("Account: Default Account");

	}

	public Account(String firstName, String secondName, String accountNumber, List<Transaction> transaction) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.accountNumber = accountNumber;
		this.transaction = transaction;
		LOGGER.info("Account: Account");

	}

	public String getFirstName() {
		LOGGER.info("Account: getFirstName");
		return firstName;
	}

	public void setFirstName(String firstName) {
		LOGGER.info("Account: setFirstName");
		this.firstName = firstName;
	}

	public String getSecondName() {
		LOGGER.info("Account: getSecondName");
		return secondName;
	}

	public void setSecondName(String secondName) {
		LOGGER.info("Account: setSecondName");
		this.secondName = secondName;
	}

	public String getAccountNumber() {
		LOGGER.info("Account: getAccountNumber");
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		LOGGER.info("Account: setAccountNumber");
		this.accountNumber = accountNumber;
	}

	public Long getId() {
		LOGGER.info("Account: getId");
		return id;
	}

	public void setId(Long id) {
		LOGGER.info("Account: setId");
		this.id = id;
	}

	public List<Transaction> getTransaction() {
		LOGGER.info("Account: getTransaction");
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		LOGGER.info("Account: setTransaction");
		this.transaction = transaction;
	}

}