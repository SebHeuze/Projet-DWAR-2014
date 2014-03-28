package com.projetweb.dao.impl;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.Transactional;

import org.springframework.stereotype.Repository;

import com.projetweb.PMF;
import com.projetweb.bean.Adresse;
import com.projetweb.dao.AdresseDAO;

@Repository
public class AdresseDAOImpl implements AdresseDAO {

	private PersistenceManager pm = PMF.get().getPersistenceManager();
	
    @Override
    @Transactional
    public Adresse store(Adresse adresse) {
    	return pm.makePersistent(adresse);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Adresse> getAll() {
    	return (List<Adresse>) pm.newQuery(Adresse.class).execute();
    }
}
