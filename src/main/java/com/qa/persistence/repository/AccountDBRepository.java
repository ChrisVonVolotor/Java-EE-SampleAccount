package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.qa.business.service.AccountService;
import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class AccountDBRepository implements AccountRepository {

	private static final Logger LOGGER = Logger.getLogger(AccountService.class);

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	@Inject
	private JSONUtil util;

	public String getAllAccounts() {
		Query query = manager.createQuery("Select a FROM Account a");
		Collection<Account> accounts = (Collection<Account>) query.getResultList();
		LOGGER.info("AccountDBRepository: getAllAccounts");
		return util.getJSONForObject(accounts);
	}

	@Transactional(REQUIRED)
	public String createAccount(String accout) {
		Account anAccount = util.getObjectForJSON(accout, Account.class);
		manager.persist(anAccount);
		LOGGER.info("AccountDBRepository: createAccount");

		return "{\"message\": \"account has been sucessfully added\"}";
	}

	@Transactional(REQUIRED)
	public String updateAccount(Long id, String accountToUpdate) {
		Account updatedAccount = util.getObjectForJSON(accountToUpdate, Account.class);
		Account accountFromDB = findAccount(id);
		LOGGER.info("AccountDBRepository: updateAccount");
		try {
			if (updatedAccount != null) {
				if (updatedAccount.getFirstName() == null) {
					LOGGER.warn("First Name Not Included in PUT from Database");
					updatedAccount.setFirstName(accountFromDB.getFirstName());
				}
				if (updatedAccount.getSecondName() == null) {
					LOGGER.warn("Second Name Not Included in PUT from Database");

					updatedAccount.setSecondName(accountFromDB.getSecondName());
				}
				if (updatedAccount.getTransaction() == null) {
					LOGGER.info("Transactions Not Included in PUT from Database");

					updatedAccount.setTransaction(accountFromDB.getTransaction());
				}
				if (updatedAccount.getId() == null) {
					LOGGER.info("ID Not Included in PUT from Database");

					updatedAccount.setId(accountFromDB.getId());
				}
				if (updatedAccount.getAccountNumber() == null) {
					LOGGER.warn("Account Number Not Included in PUT from Database");
					updatedAccount.setAccountNumber(accountFromDB.getAccountNumber());
				}
				accountFromDB = updatedAccount;
				manager.merge(accountFromDB);
			}
		} catch (Exception e) {
			LOGGER.error("AccountDBRepository: updateAccount NO JSON STRING");
		}

		return "{\"message\": \"account sucessfully updated\"}";
	}

	@Transactional(REQUIRED)
	public String deleteAccount(Long id) {
		Account accountInDB = findAccount(id);
		if (accountInDB != null) {
			manager.remove(accountInDB);
		}
		LOGGER.info("AccountDBRepository: deleteAccount");
		return "{\"message\": \"account sucessfully deleted\"}";
	}

	private Account findAccount(Long id) {
		LOGGER.info("AccountDBRepository: findAccount ");
		return manager.find(Account.class, id);
	}

	public void setManager(EntityManager manager) {
		LOGGER.info("AccountDBRepository: setManager ");

		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		LOGGER.info("AccountDBRepository: setUtil ");

		this.util = util;
	}

}
