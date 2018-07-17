package com.qa.util;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

import com.google.gson.Gson;
import com.qa.business.service.AccountService;
import com.qa.persistence.domain.Account;

public class JSONUtil {
	private static final Logger LOGGER = Logger.getLogger(AccountService.class);

	private Gson gson;

	public JSONUtil() {
		LOGGER.info("JSONUtil: JSONUtil ");

		this.gson = new Gson();
	}

	public String getJSONForObject(Object obj) {
		LOGGER.info("JSONUtil: getJSONForObject ");

		return gson.toJson(obj);
	}

	public <T> T getObjectForJSON(String jsonString, Class<T> clazz) {
		LOGGER.info("JSONUtil: getObjectForJSON ");

		return gson.fromJson(jsonString, clazz);
	}
	
	public static <T> T mergeObjects(T a, T b) throws InstantiationException, IllegalAccessException {
		LOGGER.info("JSONUtil: mergeObjects ");

	    T result = (T) a.getClass().newInstance();
	    BeanUtils.copyProperties(a, result);
	    BeanUtils.copyProperties(b, result);
	    return result;
	}

}