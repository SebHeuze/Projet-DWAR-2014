package com.projetweb.dao.impl;

import java.util.List;

import javax.jdo.annotations.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jdo.TransactionAwarePersistenceManagerFactoryProxy;
import org.springframework.stereotype.Repository;

import com.projetweb.AbstractJdoDAO;
import com.projetweb.bean.Arret;
import com.projetweb.dao.ArretDAO;

@Repository
public class ArretDAOImpl extends AbstractJdoDAO implements ArretDAO {

	@Autowired
	public ArretDAOImpl(
			final TransactionAwarePersistenceManagerFactoryProxy pmf) {
		super(pmf);
	}

    @Override
    @Transactional
    public Arret store(Arret arret) {
    	return getPersistenceManager().makePersistent(arret);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Arret> getAll() {
    	return (List<Arret>) getPersistenceManager()
				.newQuery(Arret.class).execute();
    }
}
