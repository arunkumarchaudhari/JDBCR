package com.arun.jdbc;
/*
 NOTE: Make sure while writing query, do not enter ; at the end of query
 =>To execute both select and non select query using execute(-) => never recommended to use
 author: Arun KC
 date: 18Aug, 2022
*/

/* PROBLEMS WITH THIS APPLICATION
	1)Taking query from end user(bad practice)
	2)we need call total 2 method to complete the job
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectNonSelectMethod {

	public static void main(String[] args) {
		
		//read inputs
		Scanner sc= null;
		String query=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			sc=new Scanner(System.in);
			//gathering query from end users(NEVER RECOMMENDED)
			if(sc!=null) {
				System.out.println("Enter query(select or non select): ");
				query=sc.nextLine();
			}
			
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","arunkc9900");
			
			//Creating JDBC Statement object
			if(con!=null) {
				st=con.createStatement();
			}
			
			//send and execute sql query in db software
			if(st!=null) {
				
				//public boolean execute(query) throws SQLException;
				boolean flag=st.execute(query);
				
				//if flag true->select query
				if(flag==true) {
					System.out.println("SELECT query executed");
					
					//gathering and processing the ResultSet
					rs=st.getResultSet();
					
					if(rs!=null) {
						while(rs.next()) {
							System.out.println(rs.getString(1)+"\t"+rs.getString(2));
						}//while
					}//if
				}//if
				else {
					System.out.println("Non-Select sql query is executed");
					//gather results;
					int count=st.getUpdateCount();
					if(count==0) {
						System.out.println("No record effected.");
					}
					else {
						System.out.println("No of records effected: "+count);
					}
				}//else
			}
			
			
		}//end of try block
		catch (SQLException se) {
			se.getStackTrace();
		}
		catch (Exception e) {
			e.getStackTrace();
		}//end of catch block
		finally {
			//closing all db objects and input stream
			try {
				if(rs!=null) {
					rs.close();
				}
			}
			catch (SQLException se) {
				se.getStackTrace();
			}
			
			try {
				if(st!=null) {
					st.close();
				}
			}
			catch (SQLException se) {
				se.getStackTrace();
			}
			
			try {
				if(con!=null) {
					con.close();
				}
			}
			catch (SQLException se) {
				se.getStackTrace();
			}
			
			try {
				if(sc!=null) {
					sc.close();
				}
			}
			catch (Exception e) {
				e.getStackTrace();
			}
		}//end of finally block

	}

}
