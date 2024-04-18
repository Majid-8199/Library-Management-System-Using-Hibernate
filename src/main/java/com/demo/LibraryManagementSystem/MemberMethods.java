package com.demo.LibraryManagementSystem;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.demo.LibraryManagementSystem.Entity.MemberEntity;

public class MemberMethods {
    private Scanner sc = new Scanner(System.in);
    private SessionFactory sessionFactory;

    public MemberMethods() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void addMember() {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            System.out.println("Enter name:");
            String name = sc.next();

            System.out.println("Enter email:");
            String email = sc.next();

            System.out.println("Enter mobile number:");
            Long mobileNumber = sc.nextLong();

            MemberEntity memberEntity = new MemberEntity();
            memberEntity.setName(name);
            memberEntity.setEmail(email);
            memberEntity.setMobileNo(mobileNumber);

            session.save(memberEntity);
            transaction.commit();
            System.out.println("Member added successfully.");
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

    public void deleteMember() {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            System.out.println("Enter member ID:");
            Long memberId = sc.nextLong();

            MemberEntity memberEntity = session.get(MemberEntity.class, memberId);
            if (memberEntity != null) {
                session.delete(memberEntity);
                transaction.commit();
                System.out.println("Member deleted successfully.");
            } else {
                System.out.println("Member not found with ID: " + memberId);
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


    public void updateMember() {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            System.out.println("Enter member ID:");
            Long memberId = sc.nextLong();

            MemberEntity memberEntity = session.get(MemberEntity.class, memberId);
            if (memberEntity != null) {
                System.out.println("Enter name:");
                String newName = sc.next();

                System.out.println("Enter email:");
                String newEmail = sc.next();

                System.out.println("Enter mobile number:");
                Long newMobileNumber = sc.nextLong();

                memberEntity.setName(newName);
                memberEntity.setEmail(newEmail);
                memberEntity.setMobileNo(newMobileNumber);

                session.update(memberEntity);
                transaction.commit();
                System.out.println("Member updated successfully.");
            } else {
                System.out.println("Member not found with ID: " + memberId);
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

            List<MemberEntity> members = session.createQuery("FROM MemberEntity", MemberEntity.class).list();

            System.out.println("All Members:");
            System.out.println("+----+----------------------+------------------------+---------------+");
            System.out.println("| ID |        Name          |         Email          | Mobile Number |");
            System.out.println("+----+----------------------+------------------------+---------------+");

            for (MemberEntity member : members) {
                System.out.printf("| %-3d| %-20s| %-23s| %-14s|%n", member.getId(), member.getName(), member.getEmail(), member.getMobileNo());
            }

            System.out.println("+----+----------------------+------------------------+---------------+");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}