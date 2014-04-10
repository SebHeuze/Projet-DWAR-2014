package com.projetweb.dao.impl;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.Transactional;

import org.springframework.stereotype.Repository;

import com.projetweb.PMF;
import com.projetweb.bean.Adresse;
import com.projetweb.bean.Equipement;
import com.projetweb.bean.TarifParking;
import com.projetweb.dao.AdresseDAO;
import com.projetweb.dao.EquipementDAO;
import com.projetweb.dao.TarifParkingDAO;

@Repository
public class EquipementDAOImpl implements EquipementDAO {

	private PersistenceManager pm = PMF.get().getPersistenceManager();
	
    @Override
    @Transactional
    public Equipement store(Equipement equipement) {
    	return pm.makePersistent(equipement);
    }
    
    @Override
    @Transactional
    public void storeAll(Equipement[] arrayEquipement) {
    	for (Equipement equipement : arrayEquipement){
    		this.store(equipement);
    	}
    }

    @Override
    @Transactional
	public void storeAll(List<Equipement> equipementList) {
    	for (Equipement equipement : equipementList){
    		this.store(equipement);
    	}
	}
    
    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Equipement> getAll() {
    	return (List<Equipement>) pm.newQuery(Equipement.class).execute();
    }
    
    @Override
    @Transactional
    public void deleteAll() {
    	pm.deletePersistentAll(this.getAll());
    }

	
    
}
