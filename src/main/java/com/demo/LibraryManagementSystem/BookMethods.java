package com.demo.LibraryManagementSystem;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.demo.LibraryManagementSystem.Entity.BookEntity;

public class BookMethods {
	private Scanner sc = new Scanner(System.in);
    private SessionFactory sessionFactory;

    public BookMethods() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }
	
	public void addBook() {
		Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            System.out.println("Enter Title:");
            String title = sc.next();

            System.out.println("Enter Author:");
            String author = sc.next();

            BookEntity bookEntity = new BookEntity();
            bookEntity.setTitle(title);
            bookEntity.setAuthor(author);

            session.save(bookEntity);
            transaction.commit();
            System.out.println("Book added successfully.");
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

	public void deleteBook() {
		Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            System.out.println("Enter Book ID:");
            Long bookId = sc.nextLong();

            BookEntity bookEntity = session.get(BookEntity.class, bookId);
            if (bookEntity != null) {
                session.delete(bookEntity);
                transaction.commit();
                System.out.println("Book deleted successfully.");
            } else {
                System.out.println("Book not found with ID: " + bookId);
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

	public void updateBook() {
		Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            System.out.println("Enter Book ID");
            Long bookId = sc.nextLong();

            BookEntity bookEntity = session.get(BookEntity.class, bookId);
            if (bookEntity != null) {
            	System.out.println("Enter Title:");
                String title = sc.next();

                System.out.println("Enter Author:");
                String author = sc.next();

                bookEntity.setTitle(title);
                bookEntity.setAuthor(author);

                session.update(bookEntity);
                transaction.commit();
                System.out.println("Book updated successfully.");
            } else {
                System.out.println("Book not found with ID: " + bookId);
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

	        List<BookEntity> books = session.createQuery("FROM BookEntity", BookEntity.class).list();

	        System.out.println("All Books:");
	        System.out.println("+----+--------------------------------+--------------------------------+");
	        System.out.println("| ID |            Title               |            Author              |");
	        System.out.println("+----+--------------------------------+--------------------------------+");

	        for (BookEntity book : books) {
	            System.out.printf("| %-3d| %-30s| %-30s|%n", book.getId(), book.getTitle(), book.getAuthor());
	        }

	        System.out.println("+----+--------------------------------+--------------------------------+---------------+");
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	}

}
