package com.projetweb.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import com.projetweb.bean.Coordonnee;

public class UtilsHelper {

	
	//Couts carburants
	private static String coutsCarburants;
	
	private static HashMap<String, Float> coutsCarburantsHM;
	/**
	 *  convert InputStream to String
	 * @param is
	 * @return
	 */
	public static String getStringFromInputStream(InputStream is) {
 
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
 
		String line;
		try {
 
			br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
 
		return sb.toString();
 
	}
	
	/**
	 * Est ce que l'heure "hour" est comprise entre start et end ( 22 compris entre 20h du soir et 6h du matin)
	 * @param end
	 * @param start
	 * @param time
	 * @return
	 */
	public static boolean betweenHour(double start, double end, double hour){
		if (end<start){
			return ((hour>=start && hour>=end) || (hour<=start && hour<=end));
		} else {
			return (hour>=start && hour<=end);
		}
	}
	
	/**
	 * Est ce que l'heure "hour" est comprise entre start et end ( 22 compris entre 20h du soir et 6h du matin)
	 * @param end
	 * @param start
	 * @param time
	 * @return
	 */
	public static boolean betweenDay(int start, int end, int day){
		if(day==0){
			day=7;
		}
		if (end<start){
			return ((day>=start && day>=end) || (day<=start && day<=end));
		} else {
			return (start<=day && day<=end);
		}
	}
	
	/**
	 * Soustraction d'heures
	 * @param hour1
	 * @param hour2
	 * @return
	 */
	public static double substract_hour(double hour1, double hour2){
		if (hour1>hour2){
			return hour1-hour2;
		} else {
			return (hour1 + 24 - hour2);
		}
	}
	
	
	/**
	 * Trouver la distance entre deux points
	 * 
	 **/
	public static double getDistance(Coordonnee point1, Coordonnee point2) {
        double theta = point1.getLongitude() - point2.getLongitude();
        double dist = Math.sin(deg2rad(point1.getLatitude())) * Math.sin(deg2rad(point1.getLatitude())) + Math.cos(deg2rad(point1.getLatitude())) * Math.cos(deg2rad(point1.getLatitude())) * Math.cos(deg2rad(theta));
        dist = Math.abs(Math.round(rad2deg(Math.acos(dist)) * 60 * 1.1515 * 1.609344 * 1000));
        return (dist);
	} 
	
	/**
	 * Récupérer le cout du trajet niveau carburant
	 */
	public static float getPrixCarburant(String carburant,int distance){
		float prix = coutsCarburantsHM.get(carburant)*(distance/ConstantesHelper.M_IN_KM);
		return prix;
	}
	
	/**
	 * Conversion degré vers Radian
	 * @param deg
	 * @return
	 */
	private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0); 
    }
	
	/**
	 * Conversion radian vers degré
	 * @param rad
	 * @return
	 */
	private static double rad2deg(double rad) {
	    return (rad / Math.PI * 180.0);
	}

	/**
	 * @return the coutsCarburants
	 */
	public static String getCoutsCarburants() {
		return coutsCarburants;
	}

	/**
	 * @param coutsCarburants the coutsCarburants to set
	 */
	public static void setCoutsCarburants(String coutsCarburants) {
		UtilsHelper.coutsCarburants = coutsCarburants;
		UtilsHelper.coutsCarburantsHM = new HashMap<String,Float>();
		
		if (coutsCarburants != null){
			String str[] = coutsCarburants.split(";");
			for(String carburant : str){
				String str2[] = carburant.split("/");
				coutsCarburantsHM.put(str2[0], Float.parseFloat(str2[1]));
			}
		}
		
	} 


}
