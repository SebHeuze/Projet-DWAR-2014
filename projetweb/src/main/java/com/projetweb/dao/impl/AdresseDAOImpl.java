package com.projetweb.dao.impl;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jdo.TransactionAwarePersistenceManagerFactoryProxy;
import org.springframework.stereotype.Repository;

import com.projetweb.AbstractJdoDao;
import com.projetweb.PMF;
import com.projetweb.bean.Adresse;
import com.projetweb.dao.AdresseDAO;

@Repository
public class AdresseDAOImpl implements AdresseDAO {

	
    @Override
    @Transactional
    public Adresse store(Adresse adresse) {
    	PersistenceManager pm = PMF.get().getPersistenceManager();
    	return pm.makePersistent(adresse);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Adresse> getAll() {
    	PersistenceManager pm = PMF.get().getPersistenceManager();
    	return (List<Adresse>) pm.newQuery(Adresse.class).execute();
    }
}
