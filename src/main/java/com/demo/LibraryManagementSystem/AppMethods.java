package com.demo.LibraryManagementSystem;

import java.util.Scanner;

public class AppMethods {
	Scanner sc=new Scanner(System.in);
	MemberMethods memberMethods=new MemberMethods();
	BookMethods bookMethods=new BookMethods();
	RecordMethods recordMethods=new RecordMethods();
	int choice;
	
	public void Member() {
		do {
			System.out.println();
			System.out.println("1. Add Member");
        	System.out.println("2. Delete Member");
        	System.out.println("3. Update Member");
        	System.out.println("4. View All Member");
        	System.out.println("0. Go back");
        	System.out.print("Enter a choice: ");
        	choice=sc.nextInt();
        
        	switch(choice) {
        
        		case 1: memberMethods.addMember();
        				break;
        				
        		case 2: memberMethods.deleteMember();
        				break;
        				
        		case 3: memberMethods.updateMember();
        				break;
        				
        		case 4: memberMethods.viewAll();
        				break;
        				
        		case 0: break;		
        		default: System.out.println("Invalid Input! Try again.");
        	
        	}
		}while(choice!=0);
	}
	
	public void Book() {
		do {
			System.out.println();
			System.out.println("1. Add Book");
        	System.out.println("2. Delete Book");
        	System.out.println("3. Update Book");
        	System.out.println("4. View All Books");
        	System.out.println("0. Go back");
        	System.out.print("Enter a choice: ");
        	choice=sc.nextInt();
        
        	switch(choice) {
        
        		case 1: bookMethods.addBook();
        				break;
        				
        		case 2: bookMethods.deleteBook();
        				break;
        				
        		case 3: bookMethods.updateBook();
        				break;
        				
        		case 4: bookMethods.viewAll();
        				break;
        				
        		case 0: break;		
        		default: System.out.println("Invalid Input! Try again.");
        	
        	}
		}while(choice!=0);
	}
	
	
	public void LoanRecord() {
		do {
			System.out.println();
			System.out.println("1. Add Record");
        	System.out.println("2. Delete Record");
        	System.out.println("3. Update Record");
        	System.out.println("4. View All Records");
        	System.out.println("0. Go back");
        	System.out.print("Enter a choice: ");
        	choice=sc.nextInt();
        
        	switch(choice) {
        
        		case 1: recordMethods.addRecord();
        				break;
        				
        		case 2: recordMethods.deleteRecord();
        				break;
        				
        		case 3: recordMethods.updateRecord();
        				break;
        				
        		case 4: recordMethods.viewAll();
        				break;
        				
        		case 0: break;		
        		default: System.out.println("Invalid Input! Try again.");
        	
        	}
		}while(choice!=0);
	}
}
