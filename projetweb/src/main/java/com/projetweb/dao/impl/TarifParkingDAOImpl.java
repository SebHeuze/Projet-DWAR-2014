package com.projetweb.dao.impl;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.Transactional;

import org.springframework.stereotype.Repository;

import com.projetweb.PMF;
import com.projetweb.bean.Adresse;
import com.projetweb.bean.TarifParking;
import com.projetweb.dao.AdresseDAO;
import com.projetweb.dao.TarifParkingDAO;

@Repository
public class TarifParkingDAOImpl implements TarifParkingDAO {

	private PersistenceManager pm = PMF.get().getPersistenceManager();
	
    @Override
    @Transactional
    public TarifParking store(TarifParking tarifParking) {
    	return pm.makePersistent(tarifParking);
    }
    
    @Override
    @Transactional
    public void storeAll(TarifParking[] tarifParkingArray) {
    	for (TarifParking tarif : tarifParkingArray){
    		this.store(tarif);
    	}
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<TarifParking> getAll() {
    	return (List<TarifParking>) pm.newQuery(TarifParking.class).execute();
    }
    
    @Override
    @Transactional
    public void deleteAll() {
    	pm.deletePersistentAll(this.getAll());
    }
    
}
