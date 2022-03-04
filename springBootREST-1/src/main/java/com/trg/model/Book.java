package com.trg.model;

public class Book {

	String title;
	int bookId;
	String genre;
	Author author;
	public Book(int bookId, String genre, String title) {
		super();
		this.bookId = bookId;
		this.genre = genre;
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	
	
	
	
}
