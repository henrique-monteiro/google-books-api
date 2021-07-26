package com.googlebooksapi.googlebooksapi.dto;

public class BooksDTO {
	private String bookCoverImg;
	private String title;
	private String description;
	private String publishedDate;
	
	public BooksDTO() {}

	public BooksDTO(String bookCoverImg, String title, String description, String publishedDate) {
		this.bookCoverImg = bookCoverImg;
		this.title = title;
		this.description = description;
		this.publishedDate = publishedDate;
	}
	
	public BooksDTO(String title, String description, String publishedDate) {
		this.title = title;
		this.description = description;
		this.publishedDate = publishedDate;
	}
	
	
	public String getBookCoverImg() {
		return bookCoverImg;
	}
	public void setBookCoverImg(String bookCoverImg) {
		this.bookCoverImg = bookCoverImg;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}
	
	@Override
	public String toString() {		
		return "title: " + this.title + "\ndescription: " + this.description + "\nPublishedDate: " + this.publishedDate;
	}
	
}
