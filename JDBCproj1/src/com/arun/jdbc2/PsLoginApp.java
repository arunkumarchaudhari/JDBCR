/*
 Develop a login application that verifies user name and password from Database
 using PL/SQL Procedure....
 
 NOTE: NO SQL injection problem,
 	as it is also containing pre compiled query only
 
 
 CREATE OR REPLACE PROCEDURE P_AUTHENTICATE 
(
  USERNAME IN VARCHAR2 
, PASSWORD IN VARCHAR2 
, RESULT OUT VARCHAR2 
) AS 

 CNT NUMBER(5); //It is like local variable...
BEGIN
  SELECT COUNT(*) INTO CNT FROM BANK_USER_LIST WHERE USERNAME=uname AND PASSWORD=pwd;
  IF(CNT<>0) THEN   //<> is not equal to symbol
    RESULT:='VALID CREDENTIALS';
  ELSE
    RESULT:='INVALID CREDENTIALS';
END IF;
END P_AUTHENTICATE;
 
 
 Data in the table:
 SQL> select *from bank_user_list;

uname           pwd             NAME                 gmail
--------------- --------------- -------------------- --------------------
arunkc          arun9900        Arun KC              arunkc@gmail.com
kverma          k9900           Karan                karan@gmail.com
abhi            abhi            Abhishek             abhi121@gmail.com
 
 
 */


package com.arun.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class PsLoginApp {
	private static final String CALL_PROCEDURE_QUERY="{CALL P_AUTHENTICATE(?,?,?)}";

	public static void main(String[] args) {
		
		//read inputs
		try(Scanner sc=new Scanner(System.in)){
			//read inputs
			String username=null;
			String password=null;
			if(sc!=null) {
				System.out.println("enter username: ");
				username=sc.next();
				System.out.println("Enter password: ");
				password=sc.next();
			}
			
			try(//Establish the connection
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","arunkc9900");
					
				//create callable statement object
				CallableStatement cs=con.prepareCall(CALL_PROCEDURE_QUERY);){
				
				//register OUT params with JDBC types
				if(cs!=null)
					cs.registerOutParameter(3, Types.VARCHAR);
				
				//set values to IN params
				if(cs!=null) {
					cs.setString(1, username);
					cs.setString(2, password);
				}
				
				//call PL/SQL procedure
				if(cs!=null)
					cs.execute();
				
				//gater results from OUT param
				String result=null;
				if(cs!=null)
					result=cs.getString(3);
				System.out.println("Result: "+result);
			}//end of try 2
		} //end of try1
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	} //end of main
} //end of class
