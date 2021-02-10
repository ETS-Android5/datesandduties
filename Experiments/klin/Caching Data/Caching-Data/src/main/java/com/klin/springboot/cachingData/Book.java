package com.klin.springboot.cachingData;

public class Book {

	private String isbn;
	private String title;
	
	public Book (String isbn, String title) {
		this.isbn = isbn;
		this.title = title;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
	    return "Book{" + "ISBN='" + isbn + '\'' + ", Title='" + title + '\'' + '}';
	}
}
