package com.akc.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginPage {
   public static void login() {
      Scanner sc = null;
      String user = null;
      String pass = null;
      Connection con = null;
      Statement st = null;
      String query = null;
      ResultSet rs = null;
      int count = 0;

      try {
         sc = new Scanner(System.in);
         if (sc != null) {
            System.out.println("Enter username:::");
            user = sc.nextLine();
            System.out.println("Enter Password::");
            pass = sc.nextLine();
         }

         user = "'" + user + "'";
         pass = "'" + pass + "'";
         con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "arunkc9900");
         if (con != null) {
            st = con.createStatement();
         }

         query = "SELECT COUNT(*)  FROM BANK WHERE UNAME=" + user + "  AND PWD=" + pass;
         System.out.println(query);
         if (st != null) {
            rs = st.executeQuery(query);
         }

         if (rs != null) {
            rs.next();
            count = rs.getInt("count(*)");
         }

         if (count == 0) {
            System.out.println("Invalid Credentials");
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
            if (st != null) {
               st.close();
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
