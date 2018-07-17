package com.qa.persistence.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.qa.business.service.AccountService;
import com.qa.persistence.domain.Account;
import com.qa.persistence.domain.Transaction;
import com.qa.util.JSONUtil;

@ApplicationScoped
@Alternative
public class AccountMapRepository implements AccountRepository {
	private static final Logger LOGGER = Logger.getLogger(AccountService.class);

	private Map<Long, Account> accountMap;
	private Long ID;

	@Inject
	private JSONUtil util;

	public AccountMapRepository() {
		this.accountMap = new HashMap<Long, Account>();
		LOGGER.info("AccountMapRepository: AccountMapRepository ");

		initAccountMap();
	}

	public String getAllAccounts() {
		LOGGER.info("AccountMapRepository: getAllAccounts ");

		return util.getJSONForObject(accountMap.values());

	}

	public String createAccount(String account) {
		Account newAccount = util.getObjectForJSON(account, Account.class);
		accountMap.put(newAccount.getId(), newAccount);
		LOGGER.info("AccountMapRepository: createAccount ");

		return account;
	}

	public String updateAccount(Long id, String accountToUpdate) {
		Account newAccount = util.getObjectForJSON(accountToUpdate, Account.class);
		accountMap.put(id, newAccount);
		LOGGER.info("AccountMapRepository: updateAccount ");

		return accountToUpdate;
	}

	public String deleteAccount(Long id) {
		accountMap.remove(id);
		LOGGER.info("AccountMapRepository: deleteAccount ");

		return "{\"message\": \"accout sucessfully removed\"}";
	}

	private void initAccountMap() {
		Transaction transaction = new Transaction("sample");
		transaction.setId(1L);
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(transaction);
		Account account = new Account("Joe", "Bloggs", "1234",transactions);
		accountMap.put(1L, account);
		LOGGER.info("AccountMapRepository: initAccountMap ");

	}

}