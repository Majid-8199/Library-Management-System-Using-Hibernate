package com.demo.LibraryManagementSystem.Entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LoanRecord")
public class LoanRecordEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loanID")
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "memberID")
	private MemberEntity member;
	
	@ManyToOne
	@JoinColumn(name = "bookID")
	private BookEntity book;
	
	@Column(name = "issue_date")
    private LocalDate issueDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

	public LoanRecordEntity(long id, MemberEntity member, BookEntity book, LocalDate issueDate, LocalDate dueDate) {
		super();
		this.id = id;
		this.member = member;
		this.book = book;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
	}
	
	public LoanRecordEntity() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public MemberEntity getMember() {
		return member;
	}

	public void setMember(MemberEntity member) {
		this.member = member;
	}

	public BookEntity getBook() {
		return book;
	}

	public void setBook(BookEntity book) {
		this.book = book;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	
	
}
