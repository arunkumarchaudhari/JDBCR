package com.arun.jdbc2;
/*
CREATE OR REPLACE PROCEDURE FIRSTPROC(x in number,y in number,z out number)as
BEGIN
z:=x+y;
END;
 */
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class PsProcedureTest {
	private static final String CALL_PROCEDURE= "{CALL FIRSTPROC(?,?,?)}";
	
	public static void main(String[] args) {
		
		//read inputs
		try(Scanner sc=new Scanner(System.in)){
			System.out.println("Enter first value: ");
			int first=sc.nextInt();
			System.out.println("Enter second value: ");
			int second=sc.nextInt();
			
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","arunkc9900");
				
				CallableStatement cs=con.prepareCall(CALL_PROCEDURE);){
				
				
				if(cs!=null) {
					//reguster OUT params with JDBC data types
					cs.registerOutParameter(3,Types.INTEGER);
					//set values to IN params
					cs.setInt(1, first);
					cs.setInt(2, second);
				}
				
				//execute/call the PL/SQL function
				if(cs!=null)
					cs.execute();
				//gater results from out param
				int result=0;
				if(cs!=null)
					result=cs.getInt(3);
				System.out.println("Sum is: "+result);
						
				
			}//try 2
			
		}//try 1
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	} //main
}//class
