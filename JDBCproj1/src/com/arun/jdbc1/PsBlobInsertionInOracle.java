package com.arun.jdbc1;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsBlobInsertionInOracle {
	private static final String INSERT_QUERY="INSERT INTO ARTIST_INFO VALUES(ARTIST_ID.NEXTVAL,?,?,?)";
	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in)){
			String name=null,address=null,photoLocation=null;
			if(sc!=null) {
				System.out.println("Enter Name: ");
				name=sc.next();
				System.out.println("Enter address: ");
				address=sc.next();
				System.out.println("Enter Photo Location: ");
				photoLocation=sc.next().replace("?","");
			}//end of if
			//creating inputStream
			try(
				InputStream is = new FileInputStream(photoLocation)
					){
				//Creating connection object
				try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","arunkc9900");
						PreparedStatement ps=con.prepareStatement(INSERT_QUERY);){
					//Setting parameters of PS
					if(ps!=null) {
						ps.setString(1, name);
						ps.setString(2, address);
						ps.setBinaryStream(3, is);
					}
					//Executing query
					int count=0;
					if(ps!=null) {
						count=ps.executeUpdate();
					}
					if(count==0)
						System.out.println("Record Not Inserted Properly");
					else
						System.out.println("Recorded Inserted Successfully in the database.");
				}//3rd try
			}//2nd try
		}//1st try
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("Problem in Record Insertion");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}//main method
}//class
