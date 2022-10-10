package com.arun.trywithresource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TryWithResourceDemo1 {
	//creating query
	//select *from emps where salary=(select max(salary) from emps); 
	private static final String QUERY="SELECT EID,ENAME,SALARY,DESG FROM EMPS WHERE SALARY=(SELECT MAX(SALARY) FROM EMPS)";
	public static void main(String[] args) {
		//Declaring scanner stream to take input from end users
		try (
				//Creating connection obj
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","arunkc9900");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(QUERY);
				){
			
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
		
	}//main
}//class