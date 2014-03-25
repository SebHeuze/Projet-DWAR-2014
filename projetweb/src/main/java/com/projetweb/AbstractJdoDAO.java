package com.projetweb;

import javax.jdo.PersistenceManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jdo.TransactionAwarePersistenceManagerFactoryProxy;

public abstract class AbstractJdoDAO {
	private final TransactionAwarePersistenceManagerFactoryProxy pmf;

	@Autowired
	public AbstractJdoDAO(
			final TransactionAwarePersistenceManagerFactoryProxy pmf) {
		this.pmf = pmf;
	}

	public PersistenceManager getPersistenceManager() {
		return pmf.getObject().getPersistenceManager();
	}
}