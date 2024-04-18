package com.demo.LibraryManagementSystem;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.demo.LibraryManagementSystem.Entity.BookEntity;
import com.demo.LibraryManagementSystem.Entity.LoanRecordEntity;
import com.demo.LibraryManagementSystem.Entity.MemberEntity;

public class RecordMethods {
    private SessionFactory sessionFactory;
    Scanner sc=new Scanner(System.in);

    public RecordMethods() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void addRecord() {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            System.out.println("Enter Member ID:");
            long memberId = sc.nextLong();
            MemberEntity member = session.get(MemberEntity.class, memberId);

            System.out.println("Enter Book ID:");
            long bookId = sc.nextLong();
            BookEntity book = session.get(BookEntity.class, bookId);

            System.out.println("Enter Issue Date (YYYY-MM-DD):");
            String issueDateString = sc.next();
            LocalDate issueDate = LocalDate.parse(issueDateString);

            System.out.println("Enter Due Date (YYYY-MM-DD):");
            String dueDateString = sc.next();
            LocalDate dueDate = LocalDate.parse(dueDateString);

            LoanRecordEntity loanRecord = new LoanRecordEntity();
            loanRecord.setBook(book);
            loanRecord.setMember(member);
            loanRecord.setDueDate(dueDate);
            loanRecord.setIssueDate(issueDate);
            session.save(loanRecord);

            transaction.commit();
            System.out.println("Loan record added successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void deleteRecord() {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            System.out.println("Enter Record ID:");
            Long recordId = sc.nextLong();
            
            LoanRecordEntity loanRecord = session.get(LoanRecordEntity.class, recordId);
            if (loanRecord != null) {
                session.delete(loanRecord);
                transaction.commit();
                System.out.println("Loan record deleted successfully.");
            } else {
                System.out.println("Loan record not found with ID: " + recordId);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void updateRecord() {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            System.out.println("Enter record ID:");
            Long recordId = sc.nextLong();
            
            LoanRecordEntity recordEntity = session.get(LoanRecordEntity.class, recordId);
            if (recordEntity != null) {
            	System.out.println("Enter Member ID:");
                long memberId = sc.nextLong();
                MemberEntity member = session.get(MemberEntity.class, memberId);

                System.out.println("Enter Book ID:");
                long bookId = sc.nextLong();
                BookEntity book = session.get(BookEntity.class, bookId);

                System.out.println("Enter Issue Date (YYYY-MM-DD):");
                String issueDateString = sc.next();
                LocalDate issueDate = LocalDate.parse(issueDateString);

                System.out.println("Enter Due Date (YYYY-MM-DD):");
                String dueDateString = sc.next();
                LocalDate dueDate = LocalDate.parse(dueDateString);

                recordEntity.setBook(book);
                recordEntity.setMember(member);
                recordEntity.setDueDate(dueDate);
                recordEntity.setIssueDate(issueDate);
               
                session.update(recordEntity);
                transaction.commit();
                System.out.println("Record updated successfully.");
            } else {
                System.out.println("Record not found with ID: " + recordId);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void viewAll() {
        Session session = null;

        try {
            session = sessionFactory.openSession();

            List<LoanRecordEntity> loanRecords = session.createQuery("FROM LoanRecordEntity", LoanRecordEntity.class).list();

            System.out.println("All Loan Records:");
            System.out.println("+---------+------------+----------+----------------------+----------------------+");
            System.out.println("| Loan ID | Member ID  | Book ID  |      Issue Date      |       Due Date       |");
            System.out.println("+---------+------------+----------+----------------------+----------------------+");

            for (LoanRecordEntity loanRecord : loanRecords) {
                System.out.printf("| %-8d| %-11d| %-9d| %-20s| %-20s|%n", loanRecord.getId(), loanRecord.getMember().getId(), loanRecord.getBook().getId(), loanRecord.getIssueDate(), loanRecord.getDueDate());
            }

            System.out.println("+---------+------------+----------+----------------------+----------------------+");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
