package com.trg.boot.controllers;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trg.model.Author;
import com.trg.model.Book;

@RestController
@RequestMapping("/books")
public class BookController {

	Map<Integer,Book> bookData;
	
	public BookController() {
		
		bookData = new TreeMap<Integer,Book>(); //instantiate bookData
		
		Author a1= new Author(111,"J K Rowling","NYC"); //referring Author.java class
		Author a2= new Author(222,"Enid Blyton","London");
		
		bookData.put(10,new Book(10,"Harry Potter","Fiction")); // referring constructor from Book.java class
		bookData.put(20,new Book(20,"Secret Seven","Mystery"));
		
	}
	
	@RequestMapping
	public Collection<Book> getAllBooks(){
		return bookData.values();
	}
	
	@RequestMapping("{bookId}")
	public Book getBook(@PathVariable int bookId) {
		return bookData.get(bookId);
	}
	
	@RequestMapping("{bookId}/author")
	public Author getAuthor(@PathVariable int bookId) {
		return bookData.get(bookId).getAuthor();
	}
}
