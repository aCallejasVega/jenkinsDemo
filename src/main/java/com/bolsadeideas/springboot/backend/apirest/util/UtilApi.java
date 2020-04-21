package com.bolsadeideas.springboot.backend.apirest.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilApi {
	
	public static Date stringToDate(String date) {
		try {
			  Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(date);
			  return date1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public static String dateToString(String date) {
		return null;
	}

}
