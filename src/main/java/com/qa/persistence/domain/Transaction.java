package com.qa.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.log4j.Logger;

import com.qa.business.service.AccountService;

@Entity
public class Transaction {
	private static final Logger LOGGER = Logger.getLogger(AccountService.class);

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	@NotNull
	@Pattern(regexp="^[a-zA-Z-]*$")
	@Size(max=60)
	private String transactionName;

	public Transaction() {

	}

	public Transaction(String transactionName) {
		LOGGER.info("Transaction: Transaction");
		this.transactionName = transactionName;
	}

	public Long getId() {
		LOGGER.info("Transaction: getId");

		return id;
	}

	public void setId(Long id) {
		LOGGER.info("Transaction: setId");

		this.id = id;
	}

	public String getTransactionName() {
		LOGGER.info("Transaction: getTransactionName");

		return transactionName;
	}

	public void setTransactionName(String transactionName) {
		LOGGER.info("Transaction: getTransactionName");

		this.transactionName = transactionName;
	}

	
}