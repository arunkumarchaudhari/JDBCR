package com.arun.jdbc2;
/*
CREATE OR REPLACE PROCEDURE P_GET_EMPS_DETAILS_BY_ID 
(
  ENO IN NUMBER 
, ENAME OUT VARCHAR2 
, SALARY OUT NUMBER 
, DESG OUT VARCHAR2 
) AS 
BEGIN
  SELECT ENAME,SALARY,DESG INTO ename,SALARY,DESG FROM EMPS WHERE EID=ENO;
  
END;
 */
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class PsProcedureTest2 {
	private static final String CALL_PROCEDURE= "{CALL P_GET_EMPS_DETAILS_BY_ID(?,?,?,?)}";
	
	public static void main(String[] args) {
		int eid=0;
		//read inputs
		try(Scanner sc=new Scanner(System.in)){
			System.out.println("Enter eid: ");
			eid=sc.nextInt();			
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","arunkc9900");
				
				CallableStatement cs=con.prepareCall(CALL_PROCEDURE);){
				
				
				if(cs!=null) {
					//reguster OUT params with JDBC data types
					cs.registerOutParameter(2, Types.VARCHAR);
					cs.registerOutParameter(3, Types.FLOAT);
					cs.registerOutParameter(4, Types.VARCHAR);
				}
				if(cs!=null) {
					//set values to IN params
					cs.setInt(1, eid);
				}
				
				
				//execute/call the PL/SQL function
				if(cs!=null)
					cs.execute();
				//gater results from out param
				if(cs!=null) {
					String name=cs.getString(2);
					Float salary=cs.getFloat(3);
					String desg=cs.getString(4);
					System.out.println("Eid\t:"+eid);
					System.out.println("Name\t:"+name);
					System.out.println("Salary\t:"+salary);
					System.out.println("Desg\t:"+desg);
				}						
				
			}//try 2
			
		}//try 1
		catch (SQLException se) {
			System.out.println("Employee with eid: "+eid+" is not present.");
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	} //main
}//class
