package com.arun.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertTest {
	private static final String STUDENT_INSERT_QUERY="INSERT INTO STUDENT VALUES(?,?,?,?)";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps =null;
		ResultSet rs=null;
		
		try {
			sc=new Scanner(System.in);
			int count=0;
			if(sc!=null) {
				System.out.println("Enter students count: ");
				count=sc.nextInt();
			}
			
			//Register jdbc driver
			//Not required
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","arunkc9900");
			
			//Create Preparedstatement object having pre-compiled SQL query
			if(con!=null) {
				ps=con.prepareStatement(STUDENT_INSERT_QUERY);
			}
			
			//read input values from enduser, set them to query param values and execute the pre-compiled
			//sql query for multiple times
			if(ps!=null && sc!=null) {
				for(int i=1;i<=count;i++) {
					//read each student input values
					System.out.println("enter "+i+" students details");
					System.out.println("Enter student id: ");
					String id=sc.next();
					System.out.println("Enter name: ");
					String name=sc.next();
					System.out.println("Enter Marks: ");
					float marks=sc.nextFloat();
					System.out.println("Enter Student Address: ");
					String add=sc.next();
					
					//set each student details as pre-compiled student params
					ps.setString(1, id);
					ps.setString(2, name);
					ps.setFloat(3, marks);
					ps.setString(4, add);
					
					//execute pre-compiled sql query each time
					int result=ps.executeUpdate();
					
					//process execution result of pre-compiled sql query
					if(result==0) {
						System.out.println(i+" student details not inserted");
					}
					else
						System.out.println(i+" student details inserted");
					
				}
			}//end of for loop
		}//end of try block
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			//Closing all the object and input stream
			try {
				if(ps!=null) {
					ps.close();
				}
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con!=null) {
					con.close();
				}
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
			
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
