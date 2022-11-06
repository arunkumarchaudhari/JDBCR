package com.arun.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PsDateInsertTest {
	//Creating query
	private static final String INSERT_DATE_QUERY="INSERT INTO BANK_CUSTOMER VALUES(ACC_NUMBER.NEXTVAL,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			sc=new Scanner(System.in);
			String name=null;
			String DOB=null;
			String DOJ=null;
			if(sc!=null) {
				System.out.println("Enter name: ");
				name=sc.nextLine();
				System.out.println("Enter DOB(dd-MM-yyyy)");
				DOB=sc.next();
				System.out.println("Enter DOJ(yyyy-MM-dd)");
				DOJ=sc.next();
			}
			
			//Converting String date values to java.sql.Date class obj
			//for DOB(dd-MM-yyyy)
			SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date udob=sdf1.parse(DOB);
			//converting java.util.Date class obj to java.sql.Date class obj
			long ms=udob.getTime();
			java.sql.Date sqdob=new java.sql.Date(ms);
			
			//doj is alaready in java.util.Date fromat so directly converting it to java.sql.Date class obj
			java.sql.Date sqdoj=java.sql.Date.valueOf(DOJ);
			
			//Load JDBC Driver(optional)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Creating jdbc connection object
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","arunkc9900");
			
			//Creating PreparedStatement object
			if(con!=null) {
				ps=con.prepareStatement(INSERT_DATE_QUERY);
			}
			
			//setting values to the query params
			if(ps!=null) {
				ps.setString(1, name);
				ps.setDate(2, sqdob);
				ps.setDate(3, sqdoj);
			}
			
			int count=0;
			if(ps!=null) {
				count=ps.executeUpdate();
			}
			if(count==0)
				System.out.println("Record not inserted.");
			else
				System.out.println("Record Inserted.");
			
		}//end of try
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}//end of catch
		finally {
			//closing all jdbc object and input stream
			try {
				if(ps!=null)
					ps.close();
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(sc!=null)
					sc.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}//end of finally block
	}//end of main method
}//end of class
