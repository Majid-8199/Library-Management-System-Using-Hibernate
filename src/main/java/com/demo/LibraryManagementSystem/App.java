package com.demo.LibraryManagementSystem;

import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
    	AppMethods appMethods=new AppMethods();
    	Scanner sc=new Scanner(System.in);
    	int choice;

    	do {
    		System.out.println();
    		System.out.println("*** ** *LIBRARY MANAGEMENT SYSTEM* ** ***");
    		System.out.println("|1. Member				|");
        	System.out.println("|2. Book				|");
        	System.out.println("|3. Loan Record				|");
        	System.out.println("|0. Exit				|");
        	System.out.println("|_______________________________________|");
        	System.out.print("Enter a choice: ");
        	choice=sc.nextInt();
        
        	switch(choice) {
        
        		case 1: appMethods.Member();
        				break;
        				
        		case 2: appMethods.Book();
        				break;
        				
        		case 3: appMethods.LoanRecord();
        				break;
        				
        		case 0: break;		
        		default: System.out.println("Invalid Input! Try again.");
        
        	}
    	}while(choice!=0);
    	sc.close();
    }
}
