package com.projetweb.dao.impl;

import java.util.List;

import javax.jdo.annotations.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jdo.TransactionAwarePersistenceManagerFactoryProxy;
import org.springframework.stereotype.Repository;

import com.projetweb.AbstractJdoDao;
import com.projetweb.bean.Adresse;
import com.projetweb.dao.AdresseDAO;

@Repository
public class AdresseDAOImpl extends AbstractJdoDao implements AdresseDAO {

	public AdresseDAOImpl(
			final TransactionAwarePersistenceManagerFactoryProxy pmf) {
		super(pmf);
	}

    @Override
    @Transactional
    public Adresse store(Adresse adresse) {
    	return getPersistenceManager().makePersistent(adresse);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Adresse> getAll() {
    	return (List<Adresse>) getPersistenceManager()
				.newQuery(Adresse.class).execute();
    }
}
