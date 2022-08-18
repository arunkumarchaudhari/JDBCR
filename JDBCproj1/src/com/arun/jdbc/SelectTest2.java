package com.arun.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 =>In this program we are displaying employee details,
 based on the Highest salary of the employee.
 version: 1.0
 author: Arun KC
 date: 17Aug, 2022
 */

public class SelectTest2 {

	public static void main(String[] args) {
		//Declaring scanner stream to take input from end users
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			
			//taking users input
			//In this case we don't required any input from the end users
			
			//creating query
			//select *from emps where salary=(select max(salary) from emps);
			String query="SELECT EID,ENAME,SALARY,DESG FROM EMPS WHERE SALARY=(SELECT MAX(SALARY) FROM EMPS)";
			//Creating connection obj
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","arunkc9900");
			
			if(con!=null) {
				st=con.createStatement();
			}
			if(st!=null) {
				rs=st.executeQuery(query);
			}
			
			if(rs!=null) {
				boolean flag=false;
				//Printing Heading format explicitly
				System.out.println("EID\tENAME\tSALARY\tDESG");
				while(rs.next()) {
					flag=true;
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getFloat(3)+"\t"+rs.getString(4));
				}
				//This case only be true of the table don't contain any data,table is empty.
				if(!flag) {
					System.out.println("No Record Found");
				}
			}
			
		}//end of try block
		catch (SQLException se) {
			se.getStackTrace();
		}
		catch (Exception e) {
			e.getStackTrace();
		}//end of catch block
		finally {
			//closing all the jdbc objects.
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
		}
		
	}

}
