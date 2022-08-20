package com.arun.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PsLoginApplication {
	private static final String LOGIN_QUERY="SELECT COUNT(*) FROM BANK WHERE UNAME=? AND PWD=?";
   public static void main(String[] args) {
      Scanner sc = null;
      Connection con = null;
      ResultSet rs = null;
      PreparedStatement ps=null;
      int count = 0;

      try {
         sc = new Scanner(System.in);
         String user=null;
         String pass=null;
         if (sc != null) {
            System.out.println("Enter username:::");
            user = sc.nextLine();
            System.out.println("Enter Password::");
            pass = sc.nextLine();
         }

         con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "arunkc9900");
         
         //Create PreparedStatement object
         if (con != null) {
            ps=con.prepareStatement(LOGIN_QUERY);
         }
         //set values to params of pre-compiled SQL query
         if(ps!=null) {
        	 ps.setString(1,user);
        	 ps.setString(2,pass);
         }

         if (ps != null) {
            rs = ps.executeQuery();
         }

         if (rs != null) {
            rs.next();
            count = rs.getInt("count(*)");
         }

         if (count == 0) {
            System.out.println("Invalid Credentials");
            System.out.println("Please Register or Enter valid username and password");
            System.out.println("Thank you!");
         } else {
            System.out.println(" Valid credentials");
         }
      } catch (SQLException var35) {
         var35.printStackTrace();
      } catch (Exception var36) {
         var36.printStackTrace();
      } finally {
         try {
            if (rs != null) {
               rs.close();
            }
         } catch (SQLException var34) {
            var34.printStackTrace();
         }

         try {
            if (ps != null) {
               ps.close();
            }
         } catch (SQLException var33) {
            var33.printStackTrace();
         }

         try {
            if (con != null) {
               con.close();
            }
         } catch (SQLException var32) {
            var32.printStackTrace();
         }

         try {
            if (sc != null) {
               sc.close();
            }
         } catch (Exception var31) {
            var31.printStackTrace();
         }

      }

   }
}
