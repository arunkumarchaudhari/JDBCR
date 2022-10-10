package com.arun.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class PsDateRetrieve {
	//Creating query
	private static final String RETRIEVE_DATE_QUERY="SELECT ACC_NUM, NAME, DOB, DOJ FROM BANK_CUSTOMER";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
									
			//Load JDBC Driver(optional)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Creating jdbc connection object
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","arunkc9900");
			
			//Creating PreparedStatement object
			if(con!=null) 
				ps=con.prepareStatement(RETRIEVE_DATE_QUERY);
			
			//execute query
			if(ps!=null)
				rs=ps.executeQuery();
			
			//Process Resultset object
			
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getLong(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4));
				}
			}
			
			System.out.println("-------------------------");
//			if(rs!=null) {
//				while(rs.next()) {
//					Long acc=rs.getLong(1);
//					String name=rs.getString(2);
//					java.sql.Date sqdob=rs.getDate(3);
//					java.sql.Date sqdoj=rs.getDate(4);
//					
//					//Convert java.sql.Date class object to string value
//					SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
//					String sdob=sdf.format(sqdob);
//					String sdoj=sdf.format(sqdoj);
//					System.out.println(acc+"\t"+name+"\t"+sdob+"\t"+sdoj);
//				}//while
//				
//			}//if
			
						
		}//end of try
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}//end of catch
		finally {
			try {
				if(rs!=null) {
					rs.close();
				}
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			//closing all jdbc object and input stream
			try {
				if(ps!=null)
					ps.close();
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
			
		}//end of finally block
	}//end of main method
}//end of class
