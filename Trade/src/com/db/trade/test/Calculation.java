package com.db.trade.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Calculation {

	
	public static void main (String [] args) throws ParseException {
		SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
		String date1 = "25/07/2020";
		String date2 = sdformat.format(new Date());
		
		Date d1 = sdformat.parse(date1);
	    Date d2 = sdformat.parse(date2);
	      System.out.println("The date 1 is: " + sdformat.format(d1));
	      System.out.println("The date 2 is: " + sdformat.format(d2));
	      if(d1.compareTo(d2) > 0) {
	         System.out.println("Date 1 occurs after Date 2");
	      } else if(d1.compareTo(d2) < 0) {
	         System.out.println("Date 1 occurs before Date 2");
	      } else if(d1.compareTo(d2) == 0) {
	         System.out.println("Both dates are equal");
	      }
	   
		 
	}
	
	
	
	public static int findMax(int arr[]) {
		int max = arr[0];// arr[0] instead of 0
		for (int i = 1; i < arr.length; i++) {
			if (max < arr[i])
				max = arr[i];
		}
		return max;
	}
}