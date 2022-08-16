package com.arun.jdbc;
/*
 * In this application, with given department number,
 * we are displaying deptno, name and facultyid form database.
 * author:Arun KC
 * version: 1.0
 * Date: 16Aug 2022
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest1 {

	public static void main(String[] args) {
		
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			sc=new Scanner(System.in);
			int deptno=0;
			if(sc!=null) {
				System.out.println("Enter dept no: ");
				deptno=sc.nextInt();
			}
			
			//Load JDBC Driver class(optional for this versio)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","arunkc9900");
			
			//Creating jdbc statement object
			if(con!=null) {
				st=con.createStatement();
			}
			
			//Preparing sql queries
			// SELECT *FROM DEPARTMENT WHERE DEPTID=1;
			String query="SELECT DEPTID,DEPTNAME,FACULTYID FROM DEPARTMENT WHERE DEPTID="+deptno;
			
			//creating ResultSet obj
			if(st!=null) {
				rs=st.executeQuery(query);
			}
			
			//Processing the result
			//As we have 0 or 1 record no need of while loop no need of flag variable
			if(rs!=null) {
				if(rs.next()) {
					System.out.println("DEPT_ID\tDEPT_NAME  FACULTY_ID");
					System.out.println("   "+rs.getInt(1)+"  \t"+rs.getString(2)+"\t\t"+rs.getInt(3));
				}
				else {
					System.out.println("No Record Found.");
				}
			}
		}//end of try block
		
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			//closing jdbc object and stream obj
			
			//closing rs object
			try {
				if(rs!=null) {
					rs.close();
				}
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
			
			//closing st object
			try {
				if(st!=null) {
					st.close();
				}
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
			
			//closing con object
			try {
				if(con!=null) {
					con.close();
				}
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
			
			//closing sc stream(scanner strim)
			try {
				if(sc!=null) {
					sc.close();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}//end of finally block
		
		
		
	}//end of main method

}//end of class
