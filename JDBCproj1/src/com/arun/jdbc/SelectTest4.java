package com.arun.jdbc;

/* JDBC App that gets employee details from EMPS DB table based on given 3 designations
	version: 1.0
	author: Arun_KC
	date: 15/08/2022
*/
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest4 {
	public static void main(String[] args) {
		Scanner sc=null;
		String fLName=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try{
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.print("Enter First Char of employee name: ");
				fLName=sc.next();
				
			}
			fLName="'"+fLName+"%'";

			//Load JDBC driver class (optional, so commented)
			//Class.forName("oracle.jdbc.dirver.OracleDriver");

			//establish the connection
			con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","arunkc9900");

			//create statement object
			if(con!=null) {
				st=con.createStatement();
			}
			
			//Prepare sql query
			// SELECT EID, ENAME, SALARY FROM EMPS WHERE ENAME LIKE 'a%';
			String query="SELECT EID, ENAME, SALARY,DESG FROM EMPS WHERE ENAME LIKE "+fLName;
			
			//send and execute sql query in database connection
			if(st!=null){
				rs=st.executeQuery(query);
			}
			boolean flag=false;
			if(rs!=null) {
				System.out.println("Printing emp details based on first char matched name");
				System.out.println("EID\tENAME\tSALARY\tDESG");
				while(rs.next()) {
					flag=true;
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getFloat(3)+"\t"+rs.getString(4));
				}
				if(!flag)
					System.out.println("No DATA FOUND");
			}

		}//end of try block
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		/*Bad code becz while close one object, if any exception is raised, we are not in condition to close other
		  as it will never return back to try block again and rest will never close
		*/
		finally {
			//close jdbc objects and stream object
			//as close strim throw checked exception so to handle that
			try {
				if(rs!=null)
					rs.close();
			}
			catch (SQLException xe) {
				xe.printStackTrace();
			}
			
			try {
				if(st!=null)
					st.close();
			}
			catch (SQLException xe) {
				xe.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			}
			catch (SQLException xe) {
				xe.printStackTrace();
			}
			try {
				if(sc!=null)
					sc.close();
			}
			catch (Exception e) {
				e.getStackTrace();
			}
		}//end of finally block

	}//end of main method
}//end of class
