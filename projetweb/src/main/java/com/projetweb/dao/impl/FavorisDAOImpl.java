package com.projetweb.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.Transactional;

import org.springframework.stereotype.Repository;

import com.google.appengine.api.users.User;
import com.projetweb.PMF;
import com.projetweb.bean.Favoris;
import com.projetweb.bean.Stop;
import com.projetweb.dao.FavorisDAO;

@Repository
public class FavorisDAOImpl implements FavorisDAO {

	private PersistenceManager pm = PMF.get().getPersistenceManager();
	
    @Override
    @Transactional
    public Favoris store(Favoris favoris) {
    	return pm.makePersistent(favoris);
    }
    
    @Override
    @Transactional
    public void storeAll(Favoris[] arrayFavoris) {
    	for (Favoris favoris : arrayFavoris){
    		this.store(favoris);
    	}
    }

    @Override
    @Transactional
	public void storeAll(List<Favoris> favorisList) {
    	for (Favoris favoris : favorisList){
    		this.store(favoris);
    	}
	}
    
    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Favoris> getAll() {
    	return (List<Favoris>) pm.newQuery(Favoris.class).execute();
    }
    
    @Override
    @Transactional
    public void deleteAll() {
    	pm.deletePersistentAll(this.getAll());
    }
    
    @SuppressWarnings("unchecked")
   	@Override
       @Transactional
   	public boolean exist(Favoris favoris) {
    	List<Favoris> returnFav = new ArrayList<Favoris>();
       	try {
       		Query  q = pm.newQuery(Favoris.class);
       		q.setFilter("user == userParam && depart == departParam && arrivee == arriveeParam"); 
       		q.declareParameters("String userParam, String departParam, String arriveeParam");
       		returnFav = (List<Favoris>) q.execute(favoris.getUser(), favoris.getDepart(), favoris.getArrivee());
       	} catch (Exception e) {
          
       	}
       	return returnFav.size()==0?false:true;  
   	}

	@Override
	public List<Favoris> getAllByUser(User user) {
		List<Favoris> returnFav = new ArrayList<Favoris>();
       	try {
       		Query  q = pm.newQuery(Favoris.class);
       		q.setFilter("user == userParam"); 
       		q.declareParameters("String userParam");
       		returnFav = (List<Favoris>) q.execute(user.getEmail());
       	} catch (Exception e) {
          
       	}
       	return returnFav;  
	}

	
    
}
