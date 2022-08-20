package com.akc.login;

import java.util.Scanner;

public class MainPage {

	public static void main(String[] args) {
		
		Scanner sc=null;
		int choice=0;
		
		
		
			try {
				sc=new Scanner(System.in);
				System.out.println("1.Login");
				System.out.println("2.Register");
				System.out.println("3.Exit");
				System.out.print("Enter your choice: ");
				choice=sc.nextInt();
				
				switch (choice) {
				case 1: {
					LoginPage.login();
					
					break;
				}
				case 2:{
					RegisterPage.register();
					break;
				}
				case 3:{
					System.out.println("Program terminated successfully...");
					System.exit(0);
				}
				default:
					System.out.println("Enter valid choice");
//					throw new IllegalArgumentException("Unexpected value: " + choice);
					
				}
				
			}//end of try block
			catch (Exception e) {
				e.getStackTrace();
			}//end of catch problem
			finally {
				sc.close();
			}//end of finally problem
		
		

	}

}
