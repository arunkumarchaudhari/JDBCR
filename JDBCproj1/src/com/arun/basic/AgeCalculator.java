/*
 * This SQL query and this sql query based persistance logic is very much specific to Oracle DB s/w.
 */

package com.arun.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AgeCalculator {
	private static final String AGE_CALCULATION="SELECT NAME, ROUND((SYSDATE-DOB)/365.25,2) FROM BANK_CUSTOMER WHERE ACC_NUM=?";
	public static void main(String[] args) {
		
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			sc=new Scanner(System.in);
			Long pid=0l;
			if(sc!=null) {
				System.out.println("Enter AccountNumber(Demo: 70567138): ");
				pid=sc.nextLong();
			}
			
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","arunkc9900");
			if(con!=null)
				ps=con.prepareStatement(AGE_CALCULATION);
			if(ps!=null) 
				ps.setLong(1, pid);
			if(ps!=null)
				rs=ps.executeQuery();
			//Porcessing the resultset
			if(rs!=null) {
				if(rs.next()) {
					System.out.println("Name: "+rs.getString(1));
					float age=rs.getFloat(2);
					System.out.println("Age : "+age+"years");
				}
				else
					System.out.println("No Record found with the given account number.");
			}//end of if
			
		}//end of try
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			//close the jdbc object.
			try {
				if(rs!=null)
					rs.close();
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
			
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
		}//end of finally
	}//end of main
}//end of class
