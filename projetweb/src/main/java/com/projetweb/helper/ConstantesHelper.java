package com.projetweb.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ConstantesHelper {

	public static final DateFormat DATE_FORMAT_TAN = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static final DateFormat HEURE_FORMAT_TAN = new SimpleDateFormat("HH:mm");
	//Nombre de MS dans une heure pour les conversion
	public static final long MS_IN_HOUR = 3600000;
	//Nombre de MS dans une minute pour les conversion
	public static final long MS_IN_MINUTE = 60000;
	//Nombre de S dans une heure pour les conversion
	public static final long S_IN_MINUTE = 60;
	//Nombre de Mètres dans un km pour les conversion
	public static final int M_IN_KM = 1000;
	//Nombre de minutes dans une heure
	public static final int MINUTE_IN_HOUR = 60;
	
	//Délai d'expiration ticket TAN en heure
	public static final long EXPIRATION_TICKET_TAN = 1;
	public static final String SHAPE_DEPART = "1";
	
}
