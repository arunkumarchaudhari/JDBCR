package com.arun.basic;

import java.text.SimpleDateFormat;

public class DateValueConversion {

	public static void main(String[] args) throws Exception{
		String s1="24-08-2022"; //dd-mm-yyyy
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date udl=sdf.parse(s1);
		System.out.println("String Date value: "+s1);
		System.out.println("Util Dates: "+udl);
		
		
		//converting java.util.Date class obj to java.sql.Date class obj
		long ms=udl.getTime();
		//Gives no. of milliseconds tht elapsed b/w udl date and time and 1970 jan mid night 00:00hrs(Spoach standard)
		
		java.sql.Date sdl=new java.sql.Date(ms);
		System.out.println("util Date: "+udl);
		System.out.println("sql date: "+sdl);
	}

}
